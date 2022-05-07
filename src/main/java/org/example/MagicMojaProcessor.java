package org.example; // PackageElement

import com.google.auto.service.AutoService;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.*;
import javax.tools.Diagnostic;
import java.io.IOException;
import java.util.Set;

@AutoService(Processor.class) // Processor로 등록
public class MagicMojaProcessor extends AbstractProcessor {  // ClassElement

    @Override
    public Set<String> getSupportedAnnotationTypes() {  // MethodElement
        return Set.of(Magic.class.getName());
    }

    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latestSupported();
    }

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        Set<? extends Element> elements = roundEnv.getElementsAnnotatedWith(Magic.class); //Magic 에노테테이션
        elements.forEach( element -> {
            Name elementName = element.getSimpleName();
            if(element.getKind() != ElementKind.INTERFACE){ // Element Type이 Interface가 아니면
                processingEnv.getMessager().printMessage(Diagnostic.Kind.ERROR, "Magic annotaion can not be used on " + element.getSimpleName());
            }else{
                processingEnv.getMessager().printMessage(Diagnostic.Kind.NOTE, "Processiong " + elementName);
            }

            // 소스코드를 생성하는 부분(핵심)
            TypeElement typeElement = (TypeElement) element;
            ClassName className = ClassName.get(typeElement); // 클래스 정보를 가져옴(패키지 이름, 적용된 에노테이션 등)

            MethodSpec pullOut = MethodSpec.methodBuilder("pullOut") // pullOut 메서드 생성
                    .addModifiers(Modifier.PUBLIC)
                    .returns(String.class)
                    .addStatement("return $S", "Hello World")
                    .build();

            TypeSpec magicMoja = TypeSpec.classBuilder("MagicMoja")
                    .addModifiers(Modifier.PUBLIC)
                    .addSuperinterface(className)
                    .addMethod(pullOut)
                    .build();

            Filer filer = processingEnv.getFiler();
            try {
                JavaFile.builder(className.packageName(), magicMoja)
                        .build()
                        .writeTo(filer);
                /*
                    소스 파일이 파일로 만들어지고 자바 컴파일러가 컴파일한 클래스가 생성.
                 */
            } catch (IOException e) {
                processingEnv.getMessager().printMessage(Diagnostic.Kind.ERROR, "ERROR : " + e);
            }
        });

        return true;
    }
}

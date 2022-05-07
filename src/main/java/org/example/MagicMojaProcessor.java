package org.example; // PackageElement

import com.google.auto.service.AutoService;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.Name;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic;
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
        Set<? extends Element> elements = roundEnv.getElementsAnnotatedWith(Magic.class);
        for(Element element: elements){
            Name elementName = element.getSimpleName();
            if(element.getKind() != ElementKind.INTERFACE){ // Element Type이 Interface가 아니면
                processingEnv.getMessager().printMessage(Diagnostic.Kind.ERROR, "Magic annotaion can not be used on " + element.getSimpleName());
            }else{
                processingEnv.getMessager().printMessage(Diagnostic.Kind.NOTE, "Processiong " + elementName);
            }
        }
        return true;
    }
}

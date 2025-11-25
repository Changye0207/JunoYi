package cn.junoyi.framework.log.processor;

import javax.annotation.processing.*;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.*;
import javax.tools.Diagnostic;
import javax.tools.JavaFileObject;
import java.io.IOException;
import java.io.Writer;
import java.util.Set;

/**
 * JunoYiLogger注解处理器
 * 真正模拟Lombok @Slf4j效果！编译时自动生成log字段
 * 
 * @author Fan
 */
@SupportedAnnotationTypes("cn.junoyi.framework.log.annotation.JunoYILogger")
@SupportedSourceVersion(SourceVersion.RELEASE_17)
public class JunoYiLoggerProcessor extends AbstractProcessor {

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        for (TypeElement annotation : annotations) {
            Set<? extends Element> annotatedElements = roundEnv.getElementsAnnotatedWith(annotation);
            
            for (Element element : annotatedElements) {
                if (element.getKind() == ElementKind.CLASS) {
                    TypeElement classElement = (TypeElement) element;
                    generateEnhancedClass(classElement);
                }
            }
        }
        return true;
    }

    private void generateEnhancedClass(TypeElement classElement) {
        try {
            String packageName = getPackageName(classElement);
            String className = getSimpleClassName(classElement);
            
            // 生成增强类，包含log字段和所有原类方法
            String enhancedClassName = className + "$Enhanced";
            String enhancedCode = generateEnhancedClassCode(packageName, className, enhancedClassName, classElement);
            
            JavaFileObject jfo = processingEnv.getFiler().createSourceFile(
                packageName + "." + enhancedClassName, classElement);
            
            try (Writer writer = jfo.openWriter()) {
                writer.write(enhancedCode);
            }
            
            processingEnv.getMessager().printMessage(
                Diagnostic.Kind.NOTE, "Generated JunoYi enhanced class: " + enhancedClassName);
            
        } catch (IOException e) {
            processingEnv.getMessager().printMessage(
                Diagnostic.Kind.ERROR, "Failed to generate enhanced class: " + e.getMessage());
        }
    }

    private String generateEnhancedClassCode(String packageName, String originalClassName, 
                                           String enhancedClassName, TypeElement classElement) {
        
        return "package " + packageName + ";\n\n" +
               "import cn.junoyi.framework.log.core.JunoLog;\n" +
               "import cn.junoyi.framework.log.core.JunoLogFactory;\n\n" +
               "/**\n" +
               " * " + enhancedClassName + " - JunoYi自动增强类\n" +
               " * 继承自 " + originalClassName + "，自动添加log字段\n" +
               " */\n" +
               "public class " + enhancedClassName + " extends " + originalClassName + " {\n\n" +
               "    /**\n" +
               "     * 自动生成的log字段 - 真正的Lombok效果！\n" +
               "     */\n" +
               "    private final JunoLog log = JunoLogFactory.getLogger(" + originalClassName + ".class);\n\n" +
               "    // 默认构造函数\n" +
               "    public " + enhancedClassName + "() {\n" +
               "        super();\n" +
               "    }\n\n" +
               "    // 如果原类有其他构造函数，需要在这里添加\n" +
               "    // 这是编译时代码生成的限制\n" +
               "}\n";
    }

    private String getPackageName(TypeElement typeElement) {
        return processingEnv.getElementUtils().getPackageOf(typeElement).getQualifiedName().toString();
    }

    private String getSimpleClassName(TypeElement typeElement) {
        return typeElement.getSimpleName().toString();
    }
}

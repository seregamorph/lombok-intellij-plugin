package de.plushnikov.intellij.lombok.processor;

import com.intellij.openapi.util.text.StringUtil;
import com.intellij.psi.PsiAnnotation;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;

import java.lang.annotation.Annotation;

/**
 * Base lombok processor class
 *
 * @author Plushnikov Michail
 */
public abstract class AbstractLombokProcessor implements LombokProcessor {
  /**
   * Anntotation qualified name this processor supports
   */
  private final String supportedAnnotation;
  /**
   * Anntotation class this processor supports
   */
  private final Class<? extends Annotation> supportedAnnotationClass;
  /**
   * Kind of output elements this processor supports
   */
  private final Class<?> supportedClass;

  /**
   * Constructor for all Lombok-Processors
   *
   * @param supportedAnnotationClass anntotation this processor supports
   * @param supportedClass           kind of output elements this processor supports
   */
  protected AbstractLombokProcessor(@NotNull Class<? extends Annotation> supportedAnnotationClass, @NotNull Class<?> supportedClass) {
    this.supportedAnnotationClass = supportedAnnotationClass;
    this.supportedAnnotation = supportedAnnotationClass.getName();
    this.supportedClass = supportedClass;
  }

  @NotNull
  @Override
  public String getSupportedAnnotation() {
    return supportedAnnotation;
  }

  @NotNull
  @Override
  public Class<? extends Annotation> getSupportedAnnotationClass() {
    return supportedAnnotationClass;
  }

  public <Psi extends PsiElement> boolean acceptAnnotation(@NotNull PsiAnnotation psiAnnotation, @NotNull Class<Psi> type) {
    final String annotationName = StringUtil.notNullize(psiAnnotation.getQualifiedName()).trim();
    return supportedAnnotation.equals(annotationName) && (type.isAssignableFrom(supportedClass));
  }

}
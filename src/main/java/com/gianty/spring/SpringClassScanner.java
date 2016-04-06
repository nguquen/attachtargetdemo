package com.gianty.spring;

import java.lang.annotation.Annotation;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.util.ClassUtils;

public class SpringClassScanner {

	private final ClassPathScanningCandidateComponentProvider scanner = new ClassPathScanningCandidateComponentProvider(false);

	public Set<Class<?>> findClasses(String... packages) {
		Set<Class<?>> classes = new HashSet<Class<?>>();
		for (String pack : packages) {
			for (BeanDefinition candicate : scanner.findCandidateComponents(pack)) {
				classes.add(ClassUtils.resolveClassName(candicate.getBeanClassName(), ClassUtils.getDefaultClassLoader()));
			}
		}
		return classes;
	}

	public void withAnnotationFilter(final Class<? extends Annotation> annotationClass) {
		scanner.addIncludeFilter(new AnnotationTypeFilter(annotationClass));
	}
}

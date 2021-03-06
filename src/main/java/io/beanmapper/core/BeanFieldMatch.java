package io.beanmapper.core;

import java.lang.annotation.Annotation;

import io.beanmapper.annotations.BeanDefault;
import io.beanmapper.core.converter.collections.BeanCollectionInstructions;
import io.beanmapper.exceptions.BeanMappingException;

public class BeanFieldMatch {

    private BeanMatch beanMatch;
    private Object source;
    private Object target;
    private BeanField sourceBeanField;
    private BeanField targetBeanField;
    private String targetFieldName;

    public BeanFieldMatch(Object source, Object target, BeanField sourceBeanField, BeanField targetBeanField, String targetFieldName, BeanMatch beanMatch) {
        this.source = source;
        this.target = target;
        this.sourceBeanField = sourceBeanField;
        this.targetBeanField = targetBeanField;
        this.targetFieldName = targetFieldName;
        this.beanMatch = beanMatch;
    }
    public boolean hasSimilarClasses() {
        return sourceBeanField.getProperty().getType().equals(targetBeanField.getProperty().getType());
    }
    public Object getTarget() { return target; }
    public String getTargetFieldName() { return targetFieldName; }
    public boolean hasMatchingSource() { return sourceBeanField != null; }
    public boolean isMappable() {
        return sourceBeanField.getProperty().isReadable() && targetBeanField.getProperty().isWritable();
    }
    public Class<?> getSourceClass() {
        return sourceBeanField.getProperty().getType();
    }
    public Class<?> getTargetClass() { return targetBeanField.getProperty().getType(); }
    public boolean targetHasAnnotation(Class<? extends Annotation> annotationClass) {
        return hasAnnotation(targetBeanField, annotationClass);
    }
    public boolean sourceHasAnnotation(Class<? extends Annotation> annotationClass) {
        return hasAnnotation(sourceBeanField, annotationClass);
    }
    protected boolean hasAnnotation(BeanField beanField, Class<? extends Annotation> annotationClass) {
        return beanField.getProperty().findAnnotation(annotationClass) != null;
    }
    public Object getSourceDefaultValue() {
        return getDefaultValue(sourceBeanField);
    }
    public Object getTargetDefaultValue() {
        return getDefaultValue(targetBeanField);
    }
    protected Object getDefaultValue(BeanField beanField) {
        return beanField.getProperty().findAnnotation(BeanDefault.class).value();
    }
    public void setTarget(Object value) throws BeanMappingException {
        targetBeanField.getProperty().setValue(target, value);
    }
    public void writeObject(Object value) throws BeanMappingException {
        targetBeanField.writeObject(value, target, source, beanMatch);
    }
    public Object getSourceObject() throws BeanMappingException {
        return sourceBeanField.getObject(source);
    }
    public Object getTargetObject() throws BeanMappingException {
        return targetBeanField.getObject(target);
    }
    public BeanCollectionInstructions getCollectionInstructions() {
        return targetBeanField.getCollectionInstructions() != null ?
                targetBeanField.getCollectionInstructions() :
                sourceBeanField.getCollectionInstructions() != null ?
                        sourceBeanField.getCollectionInstructions() :
                        null;
    }

    public String getSourceFieldName() {
        return sourceBeanField.getName();
    }

    public BeanMatch getBeanMatch() {
        return beanMatch;
    }

    public String sourceToString() {
        return source.getClass().getSimpleName() + (sourceBeanField == null ? "" : "." + sourceBeanField.getName());
    }

    public String targetToString() {
        return target.getClass().getSimpleName() + (targetBeanField == null ? "" : "." + targetBeanField.getName());
    }

}

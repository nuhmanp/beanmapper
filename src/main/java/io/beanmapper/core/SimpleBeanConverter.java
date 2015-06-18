/*
 * (C) 2014 42 bv (www.42.nl). All rights reserved.
 */
package io.beanmapper.core;

/**
 * Default converter that should be used for single type-to-type conversions.
 *
 * @author Jeroen van Schagen
 * @since Jun 18, 2015
 */
public abstract class SimpleBeanConverter<S, T> extends AbstractBeanConverter<S, T> {
    
    /**
     * Construct a new bean converter, dynamically resolving the source and target class. 
     * <br>
     * <b>This constructor requires a dependency to Spring.</b>
     */
    public SimpleBeanConverter() {
        super();
    }
    
    /**
     * Construct a new bean converter, manually declaring the source and target class.
     * @param sourceClass the source class
     * @param targetClass the target class
     */
    public SimpleBeanConverter(Class<?> sourceClass, Class<?> targetClass) {
        super(sourceClass, targetClass);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    @SuppressWarnings("unchecked")
    protected final Object doConvert(S source, Class<? extends T> targetClass) {
        return doConvert(source); // No need to provide the target class
    }
    
    /**
     * Convert the source instance into our target type.
     * @param source the source instance
     * @return the converted target
     */
    protected abstract T doConvert(S source);

}

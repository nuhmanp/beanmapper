package io.beanmapper.config;

import io.beanmapper.core.BeanMatchStore;
import io.beanmapper.core.constructor.BeanInitializer;
import io.beanmapper.core.constructor.DefaultBeanInitializer;
import io.beanmapper.core.converter.BeanConverter;
import io.beanmapper.core.rule.MappableFields;
import io.beanmapper.core.unproxy.BeanUnproxy;
import io.beanmapper.core.unproxy.DefaultBeanUnproxy;
import io.beanmapper.core.unproxy.SkippingBeanUnproxy;
import io.beanmapper.exceptions.BeanConfigurationOperationNotAllowedException;

import java.util.ArrayList;
import java.util.List;

public class CoreConfiguration extends AbstractConfiguration {

    /**
     * Initializes the beans.
     */
    private BeanInitializer beanInitializer = new DefaultBeanInitializer();

    /**
     * Removes any potential proxies of beans.
     */
    private SkippingBeanUnproxy beanUnproxy = new SkippingBeanUnproxy(new DefaultBeanUnproxy());

    /**
     * Contains a store of matches for source and target class pairs. A pair is created only
     * once and reused every time thereafter.
     */
    private BeanMatchStore beanMatchStore = new BeanMatchStore();

    /**
     * The list of packages (and subpackages) containing classes which are eligible for mapping.
     */
    private List<String> packagePrefixes = new ArrayList<String>();

    /**
     * The list of converters that should be checked for conversions.
     */
    private List<BeanConverter> beanConverters = new ArrayList<BeanConverter>();

    private boolean addDefaultConverters = true;

    @Override
    public Class getTargetClass() {
        return null;
    }

    @Override
    public Object getTarget() {
        return null;
    }

    @Override
    public Class getCollectionClass() {
        return null;
    }

    @Override
    public MappableFields getMappableFields() {
        return null;
    }

    @Override
    public BeanInitializer getBeanInitializer() {
        return this.beanInitializer;
    }

    @Override
    public SkippingBeanUnproxy getBeanUnproxy() {
        return this.beanUnproxy;
    }

    @Override
    public BeanMatchStore getBeanMatchStore() {
        return this.beanMatchStore;
    }

    @Override
    public List<String> getPackagePrefixes() {
        return this.packagePrefixes;
    }

    @Override
    public List<BeanConverter> getBeanConverters() {
        return this.beanConverters;
    }

    @Override
    public boolean isConverterChoosable() {
        return false;
    }

    @Override
    public void withoutDefaultConverters() {
        this.addDefaultConverters = false;
    }

    @Override
    public void addConverter(BeanConverter converter) {
        this.beanConverters.add(converter);
    }

    @Override
    public void addProxySkipClass(Class<?> clazz) {
        this.beanUnproxy.skip(clazz);
    }

    @Override
    public void addPackagePrefix(Class<?> clazz) {
        if (clazz.getPackage() != null) {
            addPackagePrefix(clazz.getPackage().getName());
        }
    }

    @Override
    public void addPackagePrefix(String packagePrefix) {
        this.packagePrefixes.add(packagePrefix);
    }

    @Override
    public void setBeanInitializer(BeanInitializer beanInitializer) {
        this.beanInitializer = beanInitializer;
    }

    @Override
    public void setBeanUnproxy(BeanUnproxy beanUnproxy) {
        this.beanUnproxy.setDelegate(beanUnproxy);
    }

    public boolean isAddDefaultConverters() {
        return this.addDefaultConverters;
    }

    @Override
    public void setMappableFields(MappableFields mappableFields) {
        throw new BeanConfigurationOperationNotAllowedException(
                "Illegal to set mappable fields on the Core configuration, works only for override configurations");
    }

    @Override
    public void setConverterChoosable(boolean converterChoosable) {
        throw new BeanConfigurationOperationNotAllowedException(
                "Illegal to set whether the converter is choosable on the Core configuration, works only for override configurations.");
    }

    @Override
    public void setCollectionClass(Class collectionClass, boolean fallbackToParent) {
        throw new BeanConfigurationOperationNotAllowedException(
                "Illegal to set a target instance on the Core configuration, works only for override configurations");
    }

    @Override
    public void setTargetClass(Class targetClass, boolean fallbackToParent) {
        throw new BeanConfigurationOperationNotAllowedException(
                "Illegal to set target class on the Core configuration, works only for override configurations");
    }

    @Override
    public void setTarget(Object target, boolean fallbackToParent) {
        throw new BeanConfigurationOperationNotAllowedException(
                "Illegal to set a target instance on the Core configuration, works only for override configurations");
    }

    @Override
    public boolean canReuse() {
        return false;
    }

}

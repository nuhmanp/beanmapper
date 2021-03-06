package io.beanmapper.testmodel.collections;

import java.util.HashSet;
import java.util.Set;

import io.beanmapper.annotations.BeanCollection;

public class CollectionSetTargetSpecificSubtype {

    @BeanCollection(elementType = Long.class, targetCollectionType = HashSet.class)
    public Set<Long> items;
}

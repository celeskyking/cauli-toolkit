package com.github.celeskyking.common.topological;

import java.util.Optional;
import java.util.Set;

/**
 * Created by sky on 15-11-17
 * 在此解决依赖关系
 */
public interface Vertex<T extends Vertex> {

    /***是否有依赖**/
    boolean dependent();

    /**所有的依赖关系**/
    Optional<Set<T>> dependenies();

    /**节点的tag**/
    String tag();

    /**
     * 是否被访问过
     * */
    VisitStatus status();


    void setStatus(VisitStatus status);
}

package com.github.celeskyking.common.topological;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

import java.util.*;

/**
 * Created by sky on 15-11-18
 * 深度优先算法实现的图遍历
 * 非线程安全
 */
public class Graph<T extends Vertex>{


    private Map<String,T> vertexMap = Maps.newConcurrentMap();


    public void add(T vertex) {
        if(vertexMap.containsKey(vertex.tag())){
            throw new RuntimeException("the vertex has existed,tag:"+vertex.tag());
        }else{
            this.vertexMap.put(vertex.tag(),vertex);
        }
    }

    public Optional<Set<T>> getVertexs() {
        return Optional.ofNullable(Sets.newHashSet(vertexMap.values()));
    }

    @SuppressWarnings("unchecked")
    private void visitVertex(T vertex,LinkedList<T> sortList){
        vertex.setStatus(VisitStatus.VISITING);
        Optional<Set<T>> optional = vertex.dependenies();
        if(optional.isPresent()){
            optional.get().forEach(it -> {
                if(it.status()==VisitStatus.VISITING){
                    throw new RuntimeException("依赖关系含有环,tag:"+it.tag());
                }
                if(vertexMap.containsKey(it.tag())){
                    if(it.status()==VisitStatus.NOVISITED){
                        visitVertex(it,sortList);
                    }
                }
            });
        }
        vertex.setStatus(VisitStatus.VISITED);
        sortList.add(vertex);
    }


    public List<T> sort() {
        LinkedList<T> list = Lists.newLinkedList();
        Optional<Set<T>> optional = getVertexs();
        if(optional.isPresent()){
            if(optional.get().size()<2){
                list.addAll(optional.get());
                return list;
            }
            optional.get().forEach(it -> {
                if(it.status()!=VisitStatus.VISITED){
                    visitVertex(it,list);
                }
            });
        }
        return list;
    }
}

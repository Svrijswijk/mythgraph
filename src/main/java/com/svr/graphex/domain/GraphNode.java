package com.svr.graphex.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;

@Entity
public class GraphNode {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long nodeNumber;
    @NotNull
    private String name;

    public GraphNode(){
    }

    public GraphNode(String name){
        this.name = name;
    }

    public long getNodeNumber() {
        return nodeNumber;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }
}

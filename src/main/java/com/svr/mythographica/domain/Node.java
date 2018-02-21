package com.svr.mythographica.domain;

import com.svr.mythographica.enums.NodeType;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class Node {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @NotNull
    private String name;
    private String description;

    @Enumerated(EnumType.STRING)
    private NodeType type;


    public Node(){
    }

    public Node(String name, NodeType type){
        this.name = name;
        this.type = type;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public NodeType getType() {
        return type;
    }

    @Override
    public String toString() {
        return name;
    }
}

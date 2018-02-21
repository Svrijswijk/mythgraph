package com.svr.mythographica.domain;

import com.svr.mythographica.enums.LinkType;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class Link {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @NotNull
    private long startNodeId;
    @NotNull
    private long endNodeId;
    @Enumerated(EnumType.STRING)
    private LinkType type;

    public Link(){
    }

    public Link(long startNodeId, long endNode){
        this.startNodeId = startNodeId;
        this.endNodeId = endNode;
    }

    public long getId() {
        return id;
    }

    public long getStartNodeId() {
        return startNodeId;
    }

    public long getEndNodeId() {
        return endNodeId;
    }

    public LinkType getType() {
        return type;
    }

    @Override
    public String toString() {
        return this.startNodeId + " -> " + this.endNodeId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Link that = (Link) o;

        return id == that.id;
    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }
}

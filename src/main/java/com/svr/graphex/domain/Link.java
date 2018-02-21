package com.svr.graphex.domain;

import com.svr.graphex.enums.LinkType;

import javax.persistence.*;

@Entity
public class Link {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @ManyToOne
    private Node startNode;
    @ManyToOne
    private Node endNode;
    @Enumerated(EnumType.STRING)
    private LinkType type;

    public Link(){
    }

    public Link(Node startNode, Node endNode){
        this.startNode = startNode;
        this.endNode = endNode;
    }

    public long getId() {
        return id;
    }

    public Node getStartNode() {
        return startNode;
    }

    public Node getEndNode() {
        return endNode;
    }

    public LinkType getType() {
        return type;
    }

    @Override
    public String toString() {
        return startNode.getName() + " -> " + endNode.getName();
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

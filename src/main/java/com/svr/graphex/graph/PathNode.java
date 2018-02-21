package com.svr.graphex.graph;

import com.svr.graphex.domain.Link;
import com.svr.graphex.domain.Node;

import java.util.ArrayList;
import java.util.List;

public class PathNode {

    private final Node node;
    private PathNode parentNode;
    private Link parentLink;
    private long id;

    private List linkList = new ArrayList<Link>();
    private double priority;

    public PathNode(Node graphNode, PathNode parentNode, Link parentLink) {
        this.node = graphNode;
        this.id = graphNode.getId();

        this.parentLink = parentLink;

        if (parentNode != null) {
            this.parentNode = parentNode;
            setPriority(parentNode.getPriority());
        }
    }

    public PathNode(Node node) {
        this.node = node;
        this.id = node.getId();
    }

    public Node getNode() {
        return node;
    }

    public List<Link> getLinkList() {
        return linkList;
    }

    public void setLinkList(List linkList) {
        //System.out.println("connectionlist: " + linkList);
        this.linkList = linkList;
    }

    public double getPriority() {
        return priority;
    }

    public void setPriority(double priority) {
        this.priority = priority;
    }

    public Link getParentLink() {
        return parentLink;
    }

    public void setParentLink(Link parentLink) {
        this.parentLink = parentLink;
    }

    public PathNode getParentNode() {
        return parentNode;
    }

    public void setParentNode(PathNode parentNode) {
        this.parentNode = parentNode;
    }

    public long getId() {
        return id;
    }

    @Override
    public String toString() {
        return this.node.getName();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PathNode pathNode = (PathNode) o;

        return id == pathNode.id;
    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }
}

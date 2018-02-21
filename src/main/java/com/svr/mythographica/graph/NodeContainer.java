package com.svr.mythographica.graph;

import com.svr.mythographica.domain.Link;
import com.svr.mythographica.domain.Node;

import java.util.ArrayList;
import java.util.List;

public class NodeContainer {

    private final Node node;
    private NodeContainer parentNode;
    private Link link;

    private List linkList = new ArrayList<Link>();
    private double priority;

    public NodeContainer(Node node, NodeContainer parentNode, Link link) {
        this.node = node;
        this.link = link;

        if (parentNode != null) {
            this.parentNode = parentNode;
            setPriority(parentNode.getPriority());
        }
    }

    public NodeContainer(Node node) {
        this.node = node;
    }

    public Node getNode() {
        return node;
    }

    public List<Link> getLinkList() {
        return linkList;
    }

    public void setLinkList(List linkList) {
        //System.out.println("linkList: " + linkList);
        this.linkList = linkList;
    }

    public double getPriority() {
        return priority;
    }

    public void setPriority(double priority) {
        this.priority = priority;
    }

    public Link getLink() {
        return link;
    }

    public void setLink(Link link) {
        this.link = link;
    }

    public NodeContainer getParentNode() {
        return parentNode;
    }

    public void setParentNode(NodeContainer parentNode) {
        this.parentNode = parentNode;
    }

    @Override
    public String toString() {
        return this.node.getName();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        NodeContainer nodeContainer = (NodeContainer) o;

        return node != null ? node.equals(nodeContainer.node) : nodeContainer.node == null;
    }

    @Override
    public int hashCode() {
        return node != null ? node.hashCode() : 0;
    }
}

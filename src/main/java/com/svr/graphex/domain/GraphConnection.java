package com.svr.graphex.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class GraphConnection {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long connectionNumber;
    @ManyToOne
    private GraphNode startNode;
    @ManyToOne
    private GraphNode endNode;

    public GraphConnection(){
    }

    public GraphConnection(GraphNode startNode, GraphNode endNode){
        this.startNode = startNode;
        this.endNode = endNode;
    }

    public GraphNode getStartNode() {
        return startNode;
    }

    public void setStartNode(GraphNode startNode) {
        this.startNode = startNode;
    }

    public GraphNode getEndNode() {
        return endNode;
    }

    public void setEndNode(GraphNode endNode) {
        this.endNode = endNode;
    }

    public long getConnectionNumber() {
        return connectionNumber;
    }
}

package com.svr.graphex.manager;

import com.svr.graphex.domain.GraphConnection;
import com.svr.graphex.domain.GraphNode;
import com.svr.graphex.exception.NotFoundException;
import com.svr.graphex.repository.ConnectionRepository;
import com.svr.graphex.repository.NodeRepository;
import org.springframework.stereotype.Component;

@Component
public class NodeManager {

    private final NodeRepository nodeRepository;
    private final ConnectionRepository connectionRepository;

    public NodeManager(NodeRepository nodeRepository, ConnectionRepository connectionRepository){
        this.nodeRepository = nodeRepository;
        this.connectionRepository = connectionRepository;
    }

    public GraphNode insert(GraphNode graphNode){
        return nodeRepository.save(graphNode);
    }

    public Iterable<GraphNode> getGraph(){
        return nodeRepository.findAll();
    }

    public void delete(long nodeNumber) {
        nodeRepository.delete(nodeNumber);
    }

    public GraphNode update(long nodeNumber, GraphNode graphNode) {
        if (!nodeRepository.exists(nodeNumber)) throw new NotFoundException();
        GraphNode updateGraphNode = nodeRepository.findOne(nodeNumber);
        //updateGraphNode.setProperty(graphNode.getProperty());
        return nodeRepository.save(updateGraphNode);
    }

    // Find a node using the node ID as input
    public GraphNode findNode(long id) {
        GraphNode graphNode = nodeRepository.findOne(id);
        if (graphNode == null)
            throw new NotFoundException();
        return graphNode;
    }

    public long findByName(String name){
        GraphNode node = nodeRepository.findByName(name);
        if(node == null){
            return -1;
        }
        return node.getNodeNumber();
    }
}

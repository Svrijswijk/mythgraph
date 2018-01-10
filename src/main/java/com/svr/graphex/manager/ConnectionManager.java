package com.svr.graphex.manager;

import com.svr.graphex.domain.GraphConnection;
import com.svr.graphex.domain.GraphNode;
import com.svr.graphex.exception.NotFoundException;
import com.svr.graphex.repository.ConnectionRepository;
import com.svr.graphex.repository.NodeRepository;
import org.springframework.stereotype.Component;

@Component
public class ConnectionManager {

    public NodeRepository nodeRepository;
    private final ConnectionRepository connectionRepository;

    public ConnectionManager(NodeRepository nodeRepository, ConnectionRepository connectionRepository){
        this.nodeRepository = nodeRepository;
        this.connectionRepository = connectionRepository;
    }

    public Iterable<GraphConnection> getConnections(){
        return connectionRepository.findAll();
    }

    public GraphConnection getConnection(long id) {
        GraphConnection graphConnection = connectionRepository.findOne(id);
        if (graphConnection == null)
            throw new NotFoundException();
        return graphConnection;
    }

    public void delete(long connectionNumber) {
        connectionRepository.delete(connectionNumber);
    }

    public GraphConnection insert(GraphConnection graphConnection) {
        if(graphConnection == null) {
            System.out.println("graphConnection is null");
            throw new NotFoundException();
        }

        GraphNode startNode = nodeRepository.findOne(graphConnection.getStartNode().getNodeNumber());
        GraphNode endNode = nodeRepository.findOne(graphConnection.getEndNode().getNodeNumber());
        if(startNode == null || endNode == null) {
            System.out.println("One of the nodes is null");
            throw new NotFoundException();
        }
        connectionRepository.save(graphConnection);
        return graphConnection;
    }

    public Iterable<GraphConnection> findByStartNodeNodeNumber(long nodeNumber){
        Iterable<GraphConnection> connections = connectionRepository.findByStartNodeNodeNumber(nodeNumber);
        if(connections == null){
            throw new NotFoundException();
        }
        return connections;
    }

    public Iterable<GraphConnection> findByEndNodeNodeNumber(long nodeNumber){
        Iterable<GraphConnection> connections = connectionRepository.findByEndNodeNodeNumber(nodeNumber);
        if(connections == null){
            throw new NotFoundException();
        }
        return connections;
    }

    public GraphConnection update(long id, GraphConnection graphConnection){
        if(graphConnection == null) throw new NotFoundException();

        GraphConnection foundGraphConnection = connectionRepository.findOne(id);
        if(foundGraphConnection == null) throw new NotFoundException();

        GraphNode startNode = nodeRepository.findOne(graphConnection.getStartNode().getNodeNumber());
        GraphNode endNode = nodeRepository.findOne(graphConnection.getEndNode().getNodeNumber());
        if(startNode == null || endNode == null) {
            System.out.println("One of the nodes is null");
            throw new NotFoundException();
        }
        connectionRepository.save(graphConnection);
        return foundGraphConnection;
    }

    public void remove(long id) {
        GraphConnection foundGraphConnection = connectionRepository.findOne(id);
        if (foundGraphConnection == null) throw new NotFoundException();
        connectionRepository.delete(foundGraphConnection);
    }
}

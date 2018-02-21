package com.svr.mythographica.manager;

import com.svr.mythographica.domain.Node;
import com.svr.mythographica.exception.NotFoundException;
import com.svr.mythographica.repository.LinkRepository;
import com.svr.mythographica.repository.NodeRepository;
import org.springframework.stereotype.Component;

@Component
public class NodeManager {

    private final NodeRepository nodeRepository;
    private final LinkRepository linkRepository;

    public NodeManager(NodeRepository nodeRepository, LinkRepository linkRepository){
        this.nodeRepository = nodeRepository;
        this.linkRepository = linkRepository;
    }

    public Node insert(Node node){
        //return nodeRepository.save(new Node("Chronos", NodeType.TITAN));
        System.out.println(node);
        return nodeRepository.save(node);
    }

    public Iterable<Node> getGraph(){
        return nodeRepository.findAll();
    }

    public void delete(long nodeNumber) {
        nodeRepository.delete(nodeNumber);
    }

    public Node update(long nodeNumber, Node node) {
        if (!nodeRepository.exists(nodeNumber)) throw new NotFoundException();
        Node updateNode = nodeRepository.findOne(nodeNumber);
        //updateNode.setProperty(node.getProperty());
        return nodeRepository.save(updateNode);
    }

    // Find a node using the node ID as input
    public Node findNode(long id) {
        Node node = nodeRepository.findOne(id);
        if (node == null)
            throw new NotFoundException();
        return node;
    }

    public long findByName(String name){
        Node node = nodeRepository.findByName(name);
        if(node == null){
            return -1;
        }
        return node.getId();
    }
}

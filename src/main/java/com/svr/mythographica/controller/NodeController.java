package com.svr.mythographica.controller;

import com.svr.mythographica.domain.Node;
import com.svr.mythographica.manager.NodeManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/api/node")
public class NodeController {

    @Autowired
    private final NodeManager nodeManager;

    public NodeController(NodeManager nodeManager){
        this.nodeManager = nodeManager;
    }

    @RequestMapping(method = RequestMethod.GET)
    public Iterable<Node> getGraph(){
        return nodeManager.getGraph();
    }

    @RequestMapping(value = "{nodeNumber}", method = RequestMethod.GET)
    public Node getNode(@PathVariable long nodeNumber){
        return nodeManager.findNode(nodeNumber);
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public void insert(@RequestBody Node node){
        System.out.println(node);
        nodeManager.insert(node);
    }

    @RequestMapping(value = "{nodeNumber}", method = RequestMethod.PUT)
    public void update(@PathVariable long nodeNumber, @RequestBody Node node){
        nodeManager.update(nodeNumber, node);
    }

    @RequestMapping(value = "{nodeNumber}", method = RequestMethod.DELETE)
    public void delete(@PathVariable long nodeNumber) {
        nodeManager.delete(nodeNumber);
    }
}

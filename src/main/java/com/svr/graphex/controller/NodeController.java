package com.svr.graphex.controller;

import com.svr.graphex.domain.GraphNode;
import com.svr.graphex.manager.NodeManager;
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
    public Iterable<GraphNode> getGraph(){
        return nodeManager.getGraph();
    }

    @RequestMapping(value = "{nodeNumber}", method = RequestMethod.GET)
    public GraphNode getGraphNode(@PathVariable long nodeNumber){
        return nodeManager.findNode(nodeNumber);
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public void insert(@RequestBody GraphNode graphNode){
        nodeManager.insert(graphNode);
    }

    @RequestMapping(value = "{nodeNumber}", method = RequestMethod.PUT)
    public void update(@PathVariable long nodeNumber, @RequestBody GraphNode node){
        nodeManager.update(nodeNumber, node);
    }

    @RequestMapping(value = "{nodeNumber}", method = RequestMethod.DELETE)
    public void delete(@PathVariable long nodeNumber) {
        nodeManager.delete(nodeNumber);
    }
}

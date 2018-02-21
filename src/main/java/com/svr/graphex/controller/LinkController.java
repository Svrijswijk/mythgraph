package com.svr.graphex.controller;

import com.svr.graphex.domain.Link;
import com.svr.graphex.manager.LinkManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/api/link")
public class LinkController {

    @Autowired
    private final LinkManager linkManager;

    public LinkController(LinkManager linkManager){
        this.linkManager = linkManager;
    }

    @RequestMapping(method = RequestMethod.GET)
    public Iterable<Link> getLinks(){
        return linkManager.getConnections();
    }

    @RequestMapping(value = "{connectionNumber}", method = RequestMethod.GET)
    public Link getLink(@PathVariable long connectionNumber){
        return linkManager.getConnection(connectionNumber);
    }

    @RequestMapping(value = "/start/{nodeNumber}", method = RequestMethod.GET)
    public Iterable<Link> findByStartNodeNodeNumber(@PathVariable long nodeNumber){
        return linkManager.findByStartNodeNodeNumber(nodeNumber);
    }

    @RequestMapping(value = "/end/{nodeNumber}", method = RequestMethod.GET)
    public Iterable<Link> findByEndNodeNodeNumber(@PathVariable long nodeNumber){
        return linkManager.findByEndNodeNodeNumber(nodeNumber);
    }

    @RequestMapping(value = "/both/{nodeNumber}", method = RequestMethod.GET)
    public Iterable<Link> findByEndNodeNodeNumberAndStartNodeNumber(@PathVariable long nodeNumber){
        return linkManager.findByEndNodeIdAndStartNodeId(nodeNumber);
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public void insert(@RequestBody Link connection){
        linkManager.insert(connection);
    }

    @RequestMapping(value = "{connectionNumber}", method = RequestMethod.PUT)
    public void update(@PathVariable long connectionNumber, @RequestBody Link connection){
        linkManager.update(connectionNumber, connection);
    }

    @RequestMapping(value = "{connectionNumber}", method = RequestMethod.DELETE)
    public void delete(@PathVariable long connectionNumber) {
        linkManager.remove(connectionNumber);
    }
}

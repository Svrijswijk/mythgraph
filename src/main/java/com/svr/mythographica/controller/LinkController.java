package com.svr.mythographica.controller;

import com.svr.mythographica.domain.Link;
import com.svr.mythographica.manager.LinkManager;
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
        return linkManager.getLink();
    }

    @RequestMapping(value = "{linkNumber}", method = RequestMethod.GET)
    public Link getLink(@PathVariable long linkNumber){
        return linkManager.getLink(linkNumber);
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
    public void insert(@RequestBody Link link){
        linkManager.insert(link);
    }

    @RequestMapping(value = "{linkNumber}", method = RequestMethod.PUT)
    public void update(@PathVariable long linkNumber, @RequestBody Link link){
        linkManager.update(linkNumber, link);
    }

    @RequestMapping(value = "{linkNumber}", method = RequestMethod.DELETE)
    public void delete(@PathVariable long linkNumber) {
        linkManager.remove(linkNumber);
    }
}

package com.svr.mythographica.controller;

import com.svr.mythographica.domain.Link;
import com.svr.mythographica.manager.PathfindingManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/api/path")
public class PathfindingController {

    @Autowired
    private final PathfindingManager pathfindingManager;

    public PathfindingController(PathfindingManager pathfindingManager){
        this.pathfindingManager = pathfindingManager;
    }

    @RequestMapping(value = "/route/{startId}/{endId}", method = RequestMethod.GET)
    public Iterable<Link> findRoute(@PathVariable long startId, @PathVariable long endId){
        return pathfindingManager.findPath(startId, endId);
    }
}

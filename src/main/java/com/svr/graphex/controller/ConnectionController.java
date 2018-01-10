package com.svr.graphex.controller;

import com.svr.graphex.domain.GraphConnection;
import com.svr.graphex.domain.GraphNode;
import com.svr.graphex.manager.ConnectionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/api/connection")
public class ConnectionController {

    @Autowired
    private final ConnectionManager connectionManager;

    public ConnectionController(ConnectionManager connectionManager){
        this.connectionManager = connectionManager;
    }

    @RequestMapping(method = RequestMethod.GET)
    public Iterable<GraphConnection> getGraphConnections(){
        return connectionManager.getConnections();
    }

    @RequestMapping(value = "{connectionNumber}", method = RequestMethod.GET)
    public GraphConnection getGraphConnection(@PathVariable long connectionNumber){
        return connectionManager.getConnection(connectionNumber);
    }

    @RequestMapping(value = "/start/{nodeNumber}", method = RequestMethod.GET)
    public Iterable<GraphConnection> findByStartNodeNodeNumber(@PathVariable long nodeNumber){
        return connectionManager.findByStartNodeNodeNumber(nodeNumber);
    }

    @RequestMapping(value = "/end/{nodeNumber}", method = RequestMethod.GET)
    public Iterable<GraphConnection> findByEndNodeNodeNumber(@PathVariable long nodeNumber){
        return connectionManager.findByEndNodeNodeNumber(nodeNumber);
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public void insert(@RequestBody GraphConnection connection){
        connectionManager.insert(connection);
    }

    @RequestMapping(value = "{connectionNumber}", method = RequestMethod.PUT)
    public void update(@PathVariable long connectionNumber, @RequestBody GraphConnection connection){
        connectionManager.update(connectionNumber, connection);
    }

    @RequestMapping(value = "{connectionNumber}", method = RequestMethod.DELETE)
    public void delete(@PathVariable long connectionNumber) {
        connectionManager.remove(connectionNumber);
    }
}

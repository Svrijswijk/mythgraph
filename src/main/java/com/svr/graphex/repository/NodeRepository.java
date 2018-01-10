package com.svr.graphex.repository;

import com.svr.graphex.domain.GraphNode;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NodeRepository extends CrudRepository<GraphNode, Long> {

    GraphNode findByName(String name);
}
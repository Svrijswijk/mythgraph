package com.svr.graphex.repository;

import com.svr.graphex.domain.GraphConnection;
import com.svr.graphex.domain.GraphNode;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConnectionRepository extends CrudRepository<GraphConnection, Long> {
    Iterable<GraphConnection> findByStartNodeNodeNumber(long findByStartNodeNodeNumber);

    Iterable<GraphConnection> findByEndNodeNodeNumber(long findByEndNodeNodeNumber);
}
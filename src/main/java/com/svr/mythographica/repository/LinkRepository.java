package com.svr.graphex.repository;

import com.svr.graphex.domain.Link;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LinkRepository extends CrudRepository<Link, Long> {
    List<Link> findByStartNodeId(long StartNodeNodeId);

    List<Link> findByEndNodeId(long EndNodeNodeId);
}
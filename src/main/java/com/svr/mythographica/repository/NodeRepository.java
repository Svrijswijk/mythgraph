package com.svr.mythographica.repository;

import com.svr.mythographica.domain.Node;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NodeRepository extends CrudRepository<Node, Long> {

    Node findByName(String name);
}
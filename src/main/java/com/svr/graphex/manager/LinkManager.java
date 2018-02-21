package com.svr.graphex.manager;

import com.svr.graphex.domain.Link;
import com.svr.graphex.domain.Node;
import com.svr.graphex.exception.NotFoundException;
import com.svr.graphex.repository.LinkRepository;
import com.svr.graphex.repository.NodeRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class LinkManager {

    private final NodeRepository nodeRepository;
    private final LinkRepository linkRepository;

    public LinkManager(NodeRepository nodeRepository, LinkRepository linkRepository){
        this.nodeRepository = nodeRepository;
        this.linkRepository = linkRepository;
    }

    public Iterable<Link> getLink(){
        return linkRepository.findAll();
    }

    public Link getLink(long id) {
        Link link = linkRepository.findOne(id);
        if (link == null)
            throw new NotFoundException();
        return link;
    }

    public void delete(long linkNumber) {
        linkRepository.delete(linkNumber);
    }

    public Link insert(Link link) {
        if(link == null) {
            System.out.println("link is null");
            throw new NotFoundException();
        }

        Node startNode = nodeRepository.findOne(link.getStartNode().getId());
        Node endNode = nodeRepository.findOne(link.getEndNode().getId());
        if(startNode == null || endNode == null) {
            System.out.println("One of the nodes is null");
            throw new NotFoundException();
        }
        linkRepository.save(link);
        return link;
    }

    public Iterable<Link> findByStartNodeNodeNumber(long id){
        Iterable<Link> links = linkRepository.findByStartNodeId(id);
        if(links == null){
            throw new NotFoundException();
        }
        return links;
    }

    public Iterable<Link> findByEndNodeNodeNumber(long id){
        Iterable<Link> links = linkRepository.findByEndNodeId(id);
        if(links == null){
            throw new NotFoundException();
        }
        return links;
    }

    public Iterable<Link> findByEndNodeIdAndStartNodeId(long id){
        List<Link> startLinks = linkRepository.findByStartNodeId(id);
        List<Link> endLinks = linkRepository.findByEndNodeId(id);

        if(startLinks == null || endLinks == null){
            throw new NotFoundException();
        }

        startLinks.addAll(endLinks);

        return startLinks;
    }

    public Link update(long id, Link link){
        if(link == null) throw new NotFoundException();

        Link foundLink = linkRepository.findOne(id);
        if(foundLink == null) throw new NotFoundException();

        Node startNode = nodeRepository.findOne(link.getStartNode().getId());
        Node endNode = nodeRepository.findOne(link.getEndNode().getId());
        if(startNode == null || endNode == null) {
            System.out.println("One of the nodes is null");
            throw new NotFoundException();
        }
        linkRepository.save(link);
        return foundLink;
    }

    public void remove(long id) {
        Link foundLink = linkRepository.findOne(id);
        if (foundLink == null) throw new NotFoundException();
        linkRepository.delete(foundLink);
    }
}

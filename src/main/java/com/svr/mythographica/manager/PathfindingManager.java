package com.svr.mythographica.manager;

import com.svr.mythographica.comparator.PriorityComparator;
import com.svr.mythographica.domain.Link;
import com.svr.mythographica.domain.Node;
import com.svr.mythographica.graph.GraphHelper;
import com.svr.mythographica.graph.NodeContainer;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class PathfindingManager {

    //Weight of search variables
    private final double LINK_WEIGHT = 1;

    private final LinkManager linkManager;
    private final NodeManager nodeManager;

    private Comparator<NodeContainer> priorityComparator = new PriorityComparator();

    private Queue<NodeContainer> nodeQueue;
    private Map<Long, NodeContainer> pastNodes;
    private List<Link> linkList;

    // The node that is checked in each iteration of the calculatePath method
    // Inside of a container that holds additional data about the node
    private NodeContainer currentNodeContainer;

    // A node that has a link to the node in the currentNodeContainer
    private Node linkedNode;

    public PathfindingManager(NodeManager nodeManager, LinkManager linkManager) {
        this.nodeManager = nodeManager;
        this.linkManager = linkManager;
    }

    // Calculate the priority of a node using predefined criteria
    private double calculatePriority(NodeContainer currentNode, Link connection) {
        double startPriority = currentNode.getPriority();
        double priority = 1.0;

//        switch (link.getType()) {
//            case CHILD:
//                priority += 1 * CONNECTION_WEIGHT;
//                break;
//            case PARENT:
//                priority += 1 * CONNECTION_WEIGHT;
//                break;
//            case SIBLING:
//                priority += 2 * CONNECTION_WEIGHT;
//                break;
//            case FRIEND:
//                priority += 3 * CONNECTION_WEIGHT;
//                break;
//            case ENEMY:
//                priority += 3 * CONNECTION_WEIGHT;
//                break;
//            default:
//                priority += 5 * CONNECTION_WEIGHT;
//                break;
//        }

//        if (localNode.getType() == endNode.getNode().getType()) {
//            priority -= 4;
//        }

        return startPriority + priority;
    }

    //Find a path between two nodes
    public List<Link> findPath(long startId, long endId) {

        Node startNode;
        Node endNode;
        List<Link> nodeRoute = new ArrayList<>();

        nodeQueue = new PriorityQueue<>(priorityComparator);
        pastNodes = new HashMap<>();
        linkList = new ArrayList<>();

        startNode = nodeManager.findNode(startId);
        endNode = nodeManager.findNode(endId);
        this.currentNodeContainer = new NodeContainer(startNode);

        while (!currentNodeContainer.getNode().equals(endNode)) {
            calculatePath();
        }

        // Gather the path calculated by the previous while loop by backtracking through the created ParentNode chain
        while (currentNodeContainer.getNode() != startNode) {
            nodeRoute.add(currentNodeContainer.getLink());
            currentNodeContainer = currentNodeContainer.getParentNode();
        }
        return nodeRoute;
    }

    // Calculate the path by finding the priority of
    private void calculatePath() {
        currentNodeContainer.setLinkList(GraphHelper.makeCollection(linkManager.findByEndNodeIdAndStartNodeId(currentNodeContainer.getNode().getId())));
        for (Link link : currentNodeContainer.getLinkList()) {
            //Check if the link has been tested
            if (linkList.contains(link)) {
                continue;
            } else {
                linkList.add(link);
            }

            linkedNode = checkNodePosition(currentNodeContainer, link);
            double calculatedPriority = calculatePriority(currentNodeContainer, link);

            //Check if a node corresponding to the id already exists in the nodeMap
            setPriority(link, calculatedPriority);
        }
        pastNodes.put(currentNodeContainer.getNode().getId(), currentNodeContainer);
        currentNodeContainer = nodeQueue.poll();
    }

    private void setPriority(Link link, double calculatedPriority) {
        NodeContainer localNode = pastNodes.get(linkedNode.getId());
        // Give the node a priority if it hasn't been checked before
        if (localNode == null) {
            localNode = new NodeContainer(linkedNode, currentNodeContainer, link);
            localNode.setPriority(calculatedPriority);
        } else {
            // If it has been checked before, check if a shorter path with the node exists
            if (localNode.getPriority() <= calculatedPriority) {
                localNode.setParentNode(currentNodeContainer);
                localNode.setPriority(calculatedPriority);
                localNode.setLink(link);
                System.out.println(localNode + " " + localNode.getLink());
            }
        }
        addNode(localNode);
    }

    // Check for a link if the currentNode is the start- or the endNode
    private Node checkNodePosition(NodeContainer currentNode, Link link) {
        if (currentNode.getNode().getId() == link.getStartNodeId()) {
            return nodeManager.findNode(link.getEndNodeId());
        } else {
            return nodeManager.findNode(link.getStartNodeId());
        }
    }

    //Add a node to queue if it has not been added yet
    private void addNode(NodeContainer node) {
        if (!nodeQueue.contains(node)) {
            nodeQueue.add(node);
        }
    }
}
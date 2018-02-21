package com.svr.graphex.manager;

import com.svr.graphex.comparator.PriorityComparator;
import com.svr.graphex.domain.Link;
import com.svr.graphex.domain.Node;
import com.svr.graphex.graph.GraphHelper;
import com.svr.graphex.graph.PathNode;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class PathfindingManager {

    //Weight of search variables
    private final double LINK_WEIGHT = 1;

    private final LinkManager linkManager;
    private final NodeManager nodeManager;

    private Comparator<PathNode> priorityComparator = new PriorityComparator();

    public PathfindingManager(NodeManager nodeManager, LinkManager linkManager) {
        this.nodeManager = nodeManager;
        this.linkManager = linkManager;
    }

    //Check for each link of a node if it connects to the endNode
    public boolean checkNode(PathNode firstNode, PathNode secondNode) {
        if (firstNode.getNode() == secondNode.getNode()) {
            return true;
        }
        return false;
    }

    public double calculatePriority(Node localNode, PathNode currentNode, PathNode endNode, Link connection) {
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

    //Find a path between two of the Nodes
    public List<Link> findPath(long startId, long endId) {
        Queue<PathNode> nodeQueue = new PriorityQueue<>(priorityComparator);
        Map<Long, PathNode> pastNodes = new HashMap<>();
        List<Link> linkList = new ArrayList<>();

        PathNode startNode = new PathNode(nodeManager.findNode(startId));

        PathNode endNode = new PathNode(nodeManager.findNode(endId));

        PathNode currentNode = startNode;

        //Loop trough the nodes until one has been found that matches the endNode
        while (!currentNode.equals(endNode)) {
            PathNode localNode;
            Node templateNode;

            currentNode.setLinkList(GraphHelper.makeCollection(linkManager.findByEndNodeIdAndStartNodeId(currentNode.getId())));
            for (Link link : currentNode.getLinkList()) {

                //Check if the link has come up before
                if (linkList.contains(link)) {
                    continue;
                } else {
                    linkList.add(link);
                }

                //Check if the currentNode is the start- or the endNode of the link
                if (currentNode.getNode().getId() == link.getStartNodeId()) {
                    templateNode = nodeManager.findNode(link.getEndNodeId());
                } else {
                    templateNode = nodeManager.findNode(link.getStartNodeId());
                }

                double calculatedPriority = calculatePriority(templateNode, currentNode, endNode, link);
                System.out.println(calculatedPriority);

                //Check if a node corresponding to the id already exists in the nodeMap
                PathNode mapNode = pastNodes.get(templateNode.getId());
                if (mapNode == null) {
                    localNode = new PathNode(templateNode, currentNode, link);
                    localNode.setPriority(calculatedPriority);
                } else {
                    //If the node already exists, check if a shorter path with the node can be found
                    localNode = mapNode;
                    if (localNode.getPriority() <= calculatedPriority) {
                        localNode.setParentNode(currentNode);
                        localNode.setPriority(calculatedPriority);
                        localNode.setParentLink(link);
                        System.out.println(localNode + " " + localNode.getParentLink());
                    }
                }

                if (!nodeQueue.contains(localNode)) {
                    nodeQueue.add(localNode);
                }
            }
            pastNodes.put(currentNode.getId(), currentNode);
            currentNode = nodeQueue.poll();
        }

        List<Link> nodeRoute = new ArrayList<>();

        while (currentNode != startNode) {
            nodeRoute.add(currentNode.getParentLink());
            currentNode = currentNode.getParentNode();
        }
        return nodeRoute;
    }
}
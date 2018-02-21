package com.svr.mythographica.comparator;
import com.svr.mythographica.graph.NodeContainer;

import java.util.Comparator;

public class PriorityComparator implements Comparator<NodeContainer>
{
    @Override
    public int compare(NodeContainer node1, NodeContainer node2) {
        if (node1.getPriority() < node2.getPriority())
        {
            return -1;
        }
        if (node1.getPriority() > node2.getPriority())
        {
            return 1;
        }
        return 0;
    }
}
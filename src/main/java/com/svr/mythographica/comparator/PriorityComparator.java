package com.svr.mythographica.comparator;
import com.svr.mythographica.graph.PathNode;

import java.util.Comparator;

public class PriorityComparator implements Comparator<PathNode>
{
    @Override
    public int compare(PathNode node1, PathNode node2) {
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
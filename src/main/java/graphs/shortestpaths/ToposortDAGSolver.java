package graphs.shortestpaths;

import graphs.Edge;
import graphs.Graph;

import java.util.*;

/**
 * Topological sorting implementation of the {@link ShortestPathSolver} interface for <b>directed acyclic graphs</b>.
 *
 * @param <V> the type of vertices.
 * @see ShortestPathSolver
 */
public class ToposortDAGSolver<V> implements ShortestPathSolver<V> {
    private final Map<V, Edge<V>> edgeTo;
    private final Map<V, Double> distTo;

    /**
     * Constructs a new instance by executing the toposort-DAG-shortest-paths algorithm on the graph from the start.
     *
     * @param graph the input graph.
     * @param start the start vertex.
     */
    public ToposortDAGSolver(Graph<V> graph, V start) {
        edgeTo = new HashMap<>();
        distTo = new HashMap<>();
        Set<V> visited = new HashSet<>();
        List<V> result = new LinkedList<>();
        // TODO: Replace with your code
        edgeTo.put(start, null);
        distTo.put(start, 0.0);

        dfsPostOrder(graph, start, visited, result);
        Collections.reverse(result);

        for (V vertex : result) {
            for (Edge<V> edge : graph.neighbors(vertex)) {
                V neighbor = edge.to;
                double neighborDistance = distTo.get(vertex) + edge.weight;
                if (!edgeTo.containsKey(neighbor)) {
                    edgeTo.put(neighbor, edge);
                    distTo.put(neighbor, neighborDistance);
                } else if (neighborDistance < distTo.get(neighbor)) {
                    edgeTo.replace(neighbor, edge);
                    distTo.replace(neighbor, neighborDistance);
                }
            }
        }
        //throw new UnsupportedOperationException("Not implemented yet");
    }

    /**
     * Recursively adds nodes from the graph to the result in DFS postorder from the start vertex.
     *
     * @param graph   the input graph.
     * @param start   the start vertex.
     * @param visited the set of visited vertices.
     * @param result  the destination for adding nodes.
     */
    private void dfsPostOrder(Graph<V> graph, V start, Set<V> visited, List<V> result) {
        // TODO: Replace with your code
        visited.add(start);
        for (Edge<V> e : graph.neighbors(start)) {
            V to = e.to;
            if (!visited.contains(to)) {
                dfsPostOrder(graph, to, visited, result);
            }
        }
        result.add(start);
        //throw new UnsupportedOperationException("Not implemented yet");
    }


    @Override
    public List<V> solution(V goal) {
        List<V> path = new ArrayList<>();
        V curr = goal;
        path.add(curr);
        while (edgeTo.get(curr) != null) {
            curr = edgeTo.get(curr).from;
            path.add(curr);
        }
        Collections.reverse(path);
        return path;
    }
}

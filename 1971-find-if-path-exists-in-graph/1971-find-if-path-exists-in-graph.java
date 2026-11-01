
import java.util.*;

class Solution {
    public boolean validPath(int n, int[][] edges, int source, int destination) {

        // Same source and destination
        if (source == destination) return true;

        // Create adjacency list
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }

        // Build undirected graph
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];

            adj.get(u).add(v);
            adj.get(v).add(u);
        }

        boolean[] visited = new boolean[n];

        Queue<Integer> q = new LinkedList<>();

        q.add(source);
        visited[source] = true;

        while (!q.isEmpty()) {

            int node = q.poll();

            if (node == destination) {
                return true;
            }

            for (int neighbor : adj.get(node)) {

                if (!visited[neighbor]) {

                    visited[neighbor] = true;
                    q.add(neighbor);
                }
            }
        }

        return false;
    }
}

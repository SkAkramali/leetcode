
import java.util.*;

class Solution {
    public boolean validPath(int n, int[][] edges, int source, int destination) {
        if(source == destination) return true;
        List<List<Integer>> graph = new ArrayList<>();

        for(int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }

        for(int[] edge : edges) {
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
        }
        boolean[] visited = new boolean[graph.size()];
        return dfs(graph, source, visited, destination); 
    }

    public boolean dfs(List<List<Integer>> graph, int source, boolean[] visited, int destination) {
        if(source == destination) return true;
        visited[source] = true;
        for(int edge : graph.get(source)){
            if(!visited[edge]) {
                if(dfs(graph, edge, visited, destination)) {
                    return true;
                }
            }
        }

        return false;    
    }
}

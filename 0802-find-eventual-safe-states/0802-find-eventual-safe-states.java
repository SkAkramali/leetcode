class Solution {
    public List<Integer> eventualSafeNodes(int[][] graph) {
        int[] visited = new int[graph.length];
        int[] pathVisited = new int[graph.length];
        int[] safeNodes = new int[graph.length];
        for(int index = 0; index < graph.length; index++) {
            if(visited[index] == 0) {
                dfs(visited, pathVisited, safeNodes, graph, index);
            }
        }

        List<Integer> ans = new ArrayList<>();
        for(int index = 0; index < graph.length; index++) {
            if(safeNodes[index] == 1) ans.add(index);
        }

        return ans;
    }

    public boolean dfs(int[] visited, int[] pathVisited, int[] safeNodes, int[][] graph, int node) {
        visited[node]  = 1;
        pathVisited[node] = 1;
        for(int neighbor : graph[node]) {
            if(visited[neighbor] == 0) {
                if(dfs(visited, pathVisited, safeNodes, graph, neighbor)) {
                    return true;
                }
            } else if(pathVisited[neighbor] == 1) {
                    return true;
            }
        }

        pathVisited[node] = 0;
        safeNodes[node] = 1;
        return false;
    }
} 
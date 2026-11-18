class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
        makeAdjacencyList(graph, prerequisites, numCourses);
        if(doCycleExist(graph, numCourses)) return false;

        return true;
    }

    public boolean doCycleExist(ArrayList<ArrayList<Integer>> graph, int numCourses) {
        int[] visited = new int[numCourses];
        int[] pathVisited = new int[numCourses];
        for(int index = 0; index < numCourses; index++) {
            if(visited[index] == 0) {
                if(dfs(visited, pathVisited, graph, index)) return true;
            }
        }

        return false;
    }


    public boolean dfs(int[] visited, int[] pathVisited, 
                        ArrayList<ArrayList<Integer>> graph, int node) {
        visited[node]  = 1;
        pathVisited[node] = 1;
        for(int neighbor : graph.get(node)) {
            if(visited[neighbor] == 0) {
                if(dfs(visited, pathVisited, graph, neighbor)) {
                    return true;
                }
            } else if(pathVisited[neighbor] == 1) {
                    return true;
            }
        }
        pathVisited[node] = 0;
        return false;
    }

    public void makeAdjacencyList(ArrayList<ArrayList<Integer>> graph,
                                    int[][] prerequisites, 
                                    int numCourses) {
        for(int index  = 0; index < numCourses; index++) {
            graph.add(new ArrayList<Integer>());
        }

        for(int[] edge : prerequisites) {
            graph.get(edge[1]).add(edge[0]);
        }
    }
}
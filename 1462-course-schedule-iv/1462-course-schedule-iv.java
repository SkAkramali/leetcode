class Solution {
    public List<Boolean> checkIfPrerequisite(int numCourses, int[][] prerequisites, int[][] queries) {
        ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
        for(int index = 0; index < numCourses; index++) {
            graph.add(new ArrayList<Integer>());
        }
        for(int[] edge : prerequisites) {
            graph.get(edge[0]).add(edge[1]);
        }

        List<Boolean> ans = new ArrayList<>();
        for(int[] query : queries) {
            ans.add(isPathExist(graph, query[0], query[1], numCourses));
        } 

        return ans;
    }

    public boolean isPathExist(ArrayList<ArrayList<Integer>> graph, int source, int dest, int size) {
        boolean[] visited = new boolean[size];
        Deque<Integer> queue = new ArrayDeque<Integer>();
        queue.addLast(source);
        while(queue.size() > 0) {
            int cur = queue.removeFirst();
            for(int neigh : graph.get(cur)) {
                if(neigh == dest) return true;
                queue.addLast(neigh); 
            }
        }

        return false;
    }
}
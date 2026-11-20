class Solution {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        Deque<Integer> queue = new ArrayDeque<>();
        ArrayList<ArrayList<Integer>> graph = new ArrayList<>(); 
        int[] indegree = getIndegree(graph, prerequisites, numCourses);
        for(int index = 0; index < numCourses; index++) {
            if(indegree[index] == 0) queue.addLast(index);
        }

        int[] ans = new int[numCourses];
        int index = 0;

        while(queue.size() > 0) {
            int course = queue.removeFirst();
            ans[index++] = course;
            for(int neighbor : graph.get(course)) {
                indegree[neighbor]--;
                if(indegree[neighbor] == 0) {
                    queue.addLast(neighbor);
                }
            }
        }

        if(index != numCourses) return new int[]{};
        return ans;
    }

    public int[] getIndegree(ArrayList<ArrayList<Integer>> graph,
                            int[][] prerequisites, int numCourses) {
        for(int index = 0; index < numCourses; index++) {
            graph.add(new ArrayList<Integer>());
        }  


        int[] indegree = new int[numCourses];
        for(int[] edge : prerequisites) {
            indegree[edge[0]]++;
            graph.get(edge[1]).add(edge[0]);
        }

        return indegree;
    }
}

class Solution {
    public boolean isBipartite(int[][] graph) {

        int n = graph.length;
        int[] color = new int[n];
        Queue<Integer> queue = new LinkedList<>();

        for (int i = 0; i < n; i++) {
            if (color[i] != 0) continue;
            color[i] = 1;
            queue.offer(i);

            while (!queue.isEmpty()) {

                int node = queue.poll();

                for (int neighbor : graph[node]) {
                    if (color[neighbor] == 0) {

                        color[neighbor] = -color[node];
                        queue.offer(neighbor);

                    }
                    else if (color[neighbor] == color[node]) {
                        return false;
                    }
                }
            }
        }

        return true;
    }
}

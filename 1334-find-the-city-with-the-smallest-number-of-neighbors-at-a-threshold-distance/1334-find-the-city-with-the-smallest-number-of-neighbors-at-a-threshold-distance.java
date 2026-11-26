
class Solution {
    public int findTheCity(int n, int[][] edges, int distanceThreshold) {
        ArrayList<ArrayList<int[]>> graph = new ArrayList<>();
        int[][] dist = new int[n][n];
        for(int[] row : dist) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }

        for(int row = 0; row < n; row++) {
            for(int col = row; col <= row; col++) {
                dist[row][col] = 0;
            }
        }
        
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            int weight = edge[2];

            dist[u][v] = weight;
            dist[v][u] = weight;
        }


        for(int k = 0; k < n; k++) {

            for(int i = 0; i < n; i++) {

                for(int j = 0; j < n; j++) {

                    if(dist[i][k] != Integer.MAX_VALUE &&
                       dist[k][j] != Integer.MAX_VALUE) {

                        dist[i][j] = Math.min(
                            dist[i][j],
                            dist[i][k] + dist[k][j]
                        );
                    }
                }
            }
        }
        

        int[] reachableCount = new int[n];
       
        int answer = -1;
        int minCount = Integer.MAX_VALUE;
        for(int source = 0; source < n; source++) {
            for(int index = 0; index < n; index++) {
                if(index != source && dist[source][index] != Integer.MAX_VALUE && dist[source][index] <= distanceThreshold) reachableCount[source]++;
            } 

            if (reachableCount[source] <= minCount) {

                minCount = reachableCount[source];
                answer = source;
            }
        }

        return answer;
    }
}

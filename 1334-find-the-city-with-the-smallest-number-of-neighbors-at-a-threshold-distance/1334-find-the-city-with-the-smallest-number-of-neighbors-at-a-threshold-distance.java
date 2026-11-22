
class Solution {

    public int findTheCity(int n, int[][] edges, int distanceThreshold) {

        // Build adjacency list
        ArrayList<ArrayList<int[]>> graph = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            int weight = edge[2];

            graph.get(u).add(new int[]{v, weight});
            graph.get(v).add(new int[]{u, weight});
        }

        // Store reachable city count for every source
        int[] reachableCount = new int[n];

        // Run Dijkstra from every city
        for (int source = 0; source < n; source++) {

            int[] distance = new int[n];
            Arrays.fill(distance, Integer.MAX_VALUE);

            PriorityQueue<int[]> pq = new PriorityQueue<>(
                (a, b) -> Integer.compare(a[1], b[1])
            );

            distance[source] = 0;
            pq.offer(new int[]{source, 0});

            // Dijkstra algorithm
            while (!pq.isEmpty()) {

                int[] current = pq.poll();

                int node = current[0];
                int dist = current[1];

                if (dist > distance[node]) {
                    continue;
                }

                for (int[] neighbor : graph.get(node)) {

                    int nextNode = neighbor[0];
                    int weight = neighbor[1];

                    int newDist = dist + weight;

                    if (newDist < distance[nextNode]) {

                        distance[nextNode] = newDist;

                        pq.offer(new int[]{nextNode, newDist});
                    }
                }
            }

            // Count reachable cities
            int count = 0;

            for (int city = 0; city < n; city++) {

                if (city != source &&
                    distance[city] <= distanceThreshold) {

                    count++;
                }
            }

            reachableCount[source] = count;
        }

        // Find city with minimum reachable count
        // In case of tie, choose greatest city number
        int answer = -1;
        int minCount = Integer.MAX_VALUE;

        for (int city = 0; city < n; city++) {

            if (reachableCount[city] <= minCount) {

                minCount = reachableCount[city];
                answer = city;
            }
        }

        return answer;
    }
}

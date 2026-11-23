class Solution {
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {

        ArrayList<ArrayList<int[]>> graph = new ArrayList<>();

        for(int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }

        for(int[] edge : flights) {
            graph.get(edge[0]).add(new int[]{edge[1], edge[2]});
        }

        int[][] dist = new int[n][k + 2];

        for(int[] row : dist) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }

        dist[src][0] = 0;

        PriorityQueue<int[]> heap = new PriorityQueue<>(
            (a, b) -> Integer.compare(a[1], b[1])
        );

        heap.offer(new int[]{src, 0, 0});

        while(!heap.isEmpty()) {

            int[] cur = heap.poll();

            int node = cur[0];
            int cost = cur[1];
            int flightsTaken = cur[2];

            if(node == dst) return cost;

            if(cost > dist[node][flightsTaken]) continue;

            if(flightsTaken == k + 1) continue;

            for(int[] edge : graph.get(node)) {

                int nextNode = edge[0];
                int price = edge[1];

                int nextCost = cost + price;
                int nextFlights = flightsTaken + 1;

                if(nextCost < dist[nextNode][nextFlights]) {
                    dist[nextNode][nextFlights] = nextCost;
                    heap.offer(new int[]{nextNode, nextCost, nextFlights});
                }
            }
        }

        return -1;
    }
}
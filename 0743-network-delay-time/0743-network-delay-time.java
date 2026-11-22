class Solution {
    ArrayList<ArrayList<int[]>> graph;

    public int networkDelayTime(int[][] times, int n, int k) {

        graph = new ArrayList<>();
        buildGraph(times, n);

        int[] distance = new int[n];
        Arrays.fill(distance, Integer.MAX_VALUE);

        distance[k - 1] = 0;

        PriorityQueue<int[]> heap = new PriorityQueue<>(
            (a, b) -> Integer.compare(a[1], b[1])
        );

        heap.offer(new int[]{k - 1, 0});

        while(!heap.isEmpty()) {

            int[] cur = heap.poll();

            int node = cur[0];
            int dist = cur[1];


            for(int[] neighbour : graph.get(node)) {

                int nextNode = neighbour[0];
                int weight = neighbour[1];

                int newDistance = dist + weight;

                if(newDistance < distance[nextNode]) {

                    distance[nextNode] = newDistance;

                    heap.offer(new int[]{nextNode, newDistance});
                }
            }
        }

        int ans = 0;

        for(int cur : distance) {

            if(cur == Integer.MAX_VALUE)
                return -1;

            ans = Math.max(ans, cur);
        }

        return ans;
    }

    public void buildGraph(int[][] times, int n) {

        for(int index = 0; index < n; index++) {
            graph.add(new ArrayList<>());
        }

        for(int[] cur : times) {
            graph.get(cur[0] - 1)
                 .add(new int[]{cur[1] - 1, cur[2]});
        }
    }
}
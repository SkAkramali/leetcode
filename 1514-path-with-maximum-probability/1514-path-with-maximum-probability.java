
class Solution {
    public double maxProbability(
        int n,
        int[][] edges,
        double[] succProb,
        int start,
        int end
    ) {

        List<List<double[]>> adj = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }

        for (int i = 0; i < edges.length; i++) {

            int u = edges[i][0];
            int v = edges[i][1];
            double prob = succProb[i];

            adj.get(u).add(new double[]{v, prob});
            adj.get(v).add(new double[]{u, prob});
        }

        double[] probability = new double[n];
        Arrays.fill(probability, 0.0);

        probability[start] = 1.0;

        PriorityQueue<double[]> pq = new PriorityQueue<>(
            (a, b) -> Double.compare(b[0], a[0])
        );

        pq.offer(new double[]{1.0, start});

        while (!pq.isEmpty()) {

            double[] current = pq.poll();

            double currProb = current[0];
            int node = (int) current[1];

            if (currProb < probability[node]) {
                continue;
            }

            if (node == end) {
                return currProb;
            }

            for (double[] neighbour : adj.get(node)) {

                int nextNode = (int) neighbour[0];
                double edgeProb = neighbour[1];

                double newProb = currProb * edgeProb;

                if (newProb > probability[nextNode]) {

                    probability[nextNode] = newProb;

                    pq.offer(new double[]{
                        newProb, nextNode
                    });
                }
            }
        }

        return 0.0;
    }
}

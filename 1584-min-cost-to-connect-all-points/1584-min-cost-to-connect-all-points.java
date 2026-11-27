class Solution {
    public int minCostConnectPoints(int[][] points) {
        PriorityQueue<int[]> heap = new PriorityQueue<>((a, b) ->{
            return a[1] - b[1];
        });

        ArrayList<ArrayList<int[]>> graph = new ArrayList<>();
        for(int index = 0; index < points.length; index++) {
            graph.add(new ArrayList<>());
        }

        for(int index = 0; index < points.length; index++) {
            for(int sec = index + 1; sec < points.length; sec++) {
                int[] p1 = points[index];
                int[] p2 = points[sec];
                int dist = Math.abs(p1[0] - p2[0]) + Math.abs(p1[1] - p2[1]);
                graph.get(index).add(new int[]{sec, dist});
                graph.get(sec).add(new int[]{index, dist});
            }
        }

        int sum = 0;
        boolean[] visited = new boolean[points.length];
        heap.offer(new int[]{0, 0});
        while(heap.size() > 0) {
            int[] node = heap.poll();
            int point = node[0];
            int dist = node[1];
            if(visited[point]) continue;
            sum += dist;
            visited[point] = true;
            for(int[] neighbor : graph.get(point)) {
                int nextNode = neighbor[0];
                int weight = neighbor[1];
                if(visited[nextNode]) continue;
                heap.offer(new int[]{nextNode, weight});
            }
            
        }

        return sum;
    } 
}
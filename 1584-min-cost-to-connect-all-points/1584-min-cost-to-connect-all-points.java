class Dsu{
    int[] parent;
    int[] ranks;
    Dsu(int n) {
        parent = new int[n];
        ranks = new int[n];
        for(int index = 0; index < n; index++) {
            parent[index] = index;
        }
    }


    public int getParent(int node) {
        if(parent[node] == node) return node;

        return parent[node] = getParent(parent[node]);
    }


    void union(int u, int v) {
        int pu = getParent(u);
        int pv = getParent(v);
        if(pu == pv) return;
        if(ranks[pu] < ranks[pv]) {
            parent[pu] = pv;
        } else if(ranks[pv] < ranks[pu]) {
            parent[pv] = pu;
        } else {
            parent[pv] = pu; 
            ranks[pu]++;
        }
    }
}

class Solution {
    public int minCostConnectPoints(int[][] points) {
        Dsu graph = new Dsu(points.length);
        PriorityQueue<int[]> heap = new PriorityQueue<>((a, b) ->{
            return a[2] - b[2];
        });

        for(int index = 0; index < points.length; index++) {
            for(int sec = index + 1; sec < points.length; sec++) {
                int[] p1 = points[index];
                int[] p2 = points[sec];
                int dist = Math.abs(p1[0] - p2[0]) + Math.abs(p1[1] - p2[1]);
                heap.offer(new int[]{index, sec, dist});
            }
        }
        
        int sum = 0;
        while(heap.size() > 0) {
            int[] edge = heap.poll();
            if(graph.getParent(edge[0]) == graph.getParent(edge[1])) continue;
            sum += edge[2];
            graph.union(edge[0], edge[1]);
        }
        return sum;
    }
}
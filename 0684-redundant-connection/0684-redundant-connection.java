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
    public int[] findRedundantConnection(int[][] edges) {
        Dsu graph = new Dsu(edges.length + 1);
        for(int[] edge : edges) {
            if(graph.getParent(edge[0]) == graph.getParent(edge[1])) 
                return new int[]{edge[0], edge[1]};
            
            graph.union(edge[0], edge[1]);
        }

        return new int[]{0, 0};
    }
}
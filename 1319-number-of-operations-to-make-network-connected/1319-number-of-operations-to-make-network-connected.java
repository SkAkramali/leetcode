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
    public int makeConnected(int n, int[][] connections) {
        if(connections.length  < n - 1) return -1;
        Dsu graph = new Dsu(n);
        for(int[] connection : connections) {
            graph.union(connection[0], connection[1]);
        }

        HashSet<Integer> components = new HashSet<>();
        for(int index = 0; index < n; index++) {
            components.add(graph.getParent(index));
        }

        return components.size() - 1;
    }
}
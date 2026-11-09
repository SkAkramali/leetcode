class Solution {
    public boolean canVisitAllRooms(List<List<Integer>> rooms) {
        boolean[] visited = new boolean[rooms.size()];
        dfs(rooms, visited, 0);
        for(int index = 0; index < rooms.size(); index++) {
            if(!visited[index]) return false;
        }

        return true;
    }

    public void dfs(List<List<Integer>> rooms, boolean[] visited, int source) {
        if(visited[source]) return;
        visited[source] = true;
        for(int neighbor : rooms.get(source)) {
            if(!visited[neighbor]) {
                dfs(rooms, visited, neighbor);
            } 
        }
    }
}
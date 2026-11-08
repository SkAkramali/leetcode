record Server(int row, int col) {

}
class Solution {
    public int countServers(int[][] grid) {
        HashSet<Server> servers = new HashSet<>();
        for(int col = 0; col < grid[0].length;  col++) {
            HashSet<Server> curColServers = new HashSet<>();
            for(int row = 0; row < grid.length; row++) {
                if(grid[row][col] == 1) {
                    curColServers.add(new Server(row, col));
                }
            }

            if(curColServers.size() > 1) {
                for(Server cur : curColServers) {
                    servers.add(cur);
                }
            }
        }

        for(int col = 0; col < grid.length;  col++) {
            HashSet<Server> curColServers = new HashSet<>();
            for(int row = 0; row < grid[0].length; row++) {
                if(grid[col][row] == 1) {
                    curColServers.add(new Server(col, row));
                }
            }

            if(curColServers.size() > 1) {
                for(Server cur : curColServers) {
                    servers.add(cur);
                }
            }
        }

        return servers.size();

    }
}
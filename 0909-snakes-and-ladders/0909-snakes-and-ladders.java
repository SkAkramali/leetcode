
class Solution {
    public int snakesAndLadders(int[][] board) {
        int n = board.length;
        int dieRolls = 0;

        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[n * n + 1];
        queue.offer(1);
        visited[1] = true;
        while (!queue.isEmpty()) {
            int size = queue.size();
            dieRolls++;
            while (size-- > 0) {
                int cur = queue.poll();

                for (int die = 1; die <= 6; die++) {
                    int next = cur + die;

                    if (next > n * n) break;

                    int rowFromBottom = (next - 1) / n;
                    int col = (next - 1) % n;

                    int row = n - 1 - rowFromBottom;

                    if (rowFromBottom % 2 == 1) {
                        col = n - 1 - col;
                    }

                    if (board[row][col] != -1) {
                        next = board[row][col];
                    }

                    if (next == n * n) return dieRolls;

                    if (!visited[next]) {
                        visited[next] = true;
                        queue.offer(next);
                    }
                }
            }
        }

        return -1;
    }
}

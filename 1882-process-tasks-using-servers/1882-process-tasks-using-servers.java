class Solution {
    public int[] assignTasks(int[] servers, int[] tasks) {

        PriorityQueue<int[]> queue = new PriorityQueue<>((a, b) -> {
            if(a[0] != b[0])
                return a[0] - b[0];
            return a[1] - b[1];
        });

        PriorityQueue<int[]> busy = new PriorityQueue<>((a, b) -> {
            if(a[2] != b[2])
                return Integer.compare(a[2], b[2]);
            if(a[0] != b[0])
                return a[0] - b[0];
            return a[1] - b[1];
        });

        for(int index = 0; index < servers.length; index++) {
            queue.offer(new int[]{servers[index], index, 0});
        }

        int[] ans = new int[tasks.length];
        int time = 0;

        for(int index = 0; index < tasks.length; index++) {

            time = Math.max(time, index);

            while(!busy.isEmpty() && busy.peek()[2] <= time) {
                queue.offer(busy.poll());
            }

            if(queue.isEmpty()) {
                time = busy.peek()[2];

                while(!busy.isEmpty() && busy.peek()[2] <= time) {
                    queue.offer(busy.poll());
                }
            }

            int[] curServer = queue.poll();

            ans[index] = curServer[1];

            curServer[2] = time + tasks[index];

            busy.offer(curServer);
        }

        return ans;
    }
}
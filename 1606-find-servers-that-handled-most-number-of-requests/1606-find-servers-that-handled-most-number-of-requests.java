class Solution {
    public List<Integer> busiestServers(int k, int[] arrival, int[] load) {
        List<Integer> ans = new ArrayList<>();
        int max = 0;
        int[] servers = new int[k];
        TreeSet<Integer> avaliable = new TreeSet<>();
        PriorityQueue<int[]> busy = new PriorityQueue<>(
            (a, b) -> a[0] - b[0]
        );
        for(int index = 0; index < k; index++) {
            avaliable.add(index);
        }

        for(int index = 0; index < arrival.length; index++) {
            while(busy.size() > 0 && busy.peek()[0] <= arrival[index]) {
                int[] curServer = busy.poll();
                avaliable.add(curServer[1]);
            }
            Integer server = avaliable.ceiling(index % k);
            if(server == null && avaliable.size() > 0) {
                server = avaliable.first();
            }

            if(server == null) continue;
            avaliable.remove(server);
            busy.add(new int[]{arrival[index] + load[index], server});
            servers[server]++;
            max = Math.max(servers[server], max);
        }

        for(int index = 0; index < k; index++) {
            if(servers[index] == max) {
                ans.add(index);
            }
        }

        return ans;

    }
}
class Solution {
    public String digitSum(String s, int k) {
        String cur = s;
        while(cur.length() > k) {
            StringBuilder sb = new StringBuilder();
            int sum = 0;
            int left = 0;
            for(int index = 0; index <= cur.length(); index++) {
                if(cur.length() == index) {
                    if(left < cur.length()) {
                        sb.append(sum);
                    }
                    break;
                }
                sum += cur.charAt(index) - '0';
                if(index - left + 1 == k) {
                    left = index + 1;
                    sb.append(sum);
                    sum = 0;
                }
            }

            cur = sb.toString();
        }

        return cur;
    }
}
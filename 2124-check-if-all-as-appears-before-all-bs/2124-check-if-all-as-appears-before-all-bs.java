class Solution {
    public boolean checkString(String s) {
        boolean a = false;
        boolean b = false;
        for(char cur : s.toCharArray()) {
            if(cur == 'b') {
                b = true;
            } else {
                a = true;
            }
            if(cur =='a' && b) return false;
        }

        return true;
    }
}
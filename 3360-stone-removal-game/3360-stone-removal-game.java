class Solution {
    public boolean canAliceWin(int n) {
        int toggle = 0;
        int cur = 10;
        while(n >= cur) {
            n -= cur;
            cur--;
            toggle = (toggle == 0) ? 1 : 0; 
        }

        if(toggle == 1) return true;
        return false;
        
    }
}
import java.util.Collections;
import java.util.ArrayList;

class Solution {
    public int lengthOfLongestSubstring(String s) {
        int n = s.length();
        int maxLength = 0;
        StringBuilder subStr = new StringBuilder();
        for (int i = 0; i < n; i++) {
            char currentChar = s.charAt(i);
            int indexInWindow = subStr.indexOf(String.valueOf(currentChar));

            if (indexInWindow == -1) {
                subStr.append(currentChar);
            } else {
                subStr.delete(0, indexInWindow + 1);
                subStr.append(currentChar);
            }
            
            maxLength = Math.max(maxLength, subStr.length());
        }
        
        return maxLength;
    }
}

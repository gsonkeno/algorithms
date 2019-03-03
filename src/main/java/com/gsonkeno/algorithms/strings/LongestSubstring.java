package com.gsonkeno.algorithms.strings;

import java.util.HashSet;
import java.util.Set;

/**
 * 不重复的最长子串
 * https://leetcode-cn.com/problems/longest-substring-without-repeating-characters/solution/
 * @author gaosong
 * @since 2019-03-03
 */
public class LongestSubstring {

    public int lengthOfLongestSubstring(String s) {
        int n = s.length();
        Set<Character> set = new HashSet<>();
        int ans = 0, i = 0, j = 0;
        while (i < n && j < n) {
            // try to extend the range [i, j]
            if (!set.contains(s.charAt(j))){
                set.add(s.charAt(j++));
                ans = Math.max(ans, j - i);
            }
            else {
                set.remove(s.charAt(i++));
            }
        }
        return ans;
    }
}

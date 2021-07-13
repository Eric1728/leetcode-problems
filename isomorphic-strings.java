class Solution {
    public boolean isIsomorphic(String s, String t) {
        if (s.length() != t.length()) return false;
        
        Map<Character, Character> map = new HashMap<>();
        
        /*
        Run-Time: O(n) + O(m)
        Space Complexity: O(n)
        
        Example 1:
        Input: s = "egg", t = "add"
        Output: true
        
        Example 2:
        Input: s = "foo", t = "bar"
        Output: false
        
        Example 3:
        Input: s = "paper", t = "title"
        Output: true
        
        Both string length's should be same and both strings should
        have the same number of unique characters. Iterate through first string,
        if we have not seen the s-char yet, add it the map if we have not
        seen the t-char also. If we have not seen the s-char, but have already
        mapped the t-char to another s-char, then return false because the number of unique
        char's do not match. Also return false if the s-char already exists
        but the current t-char is different from the one we have seen before
        because this would also mean a difference in number of unique strings.
        If we get to the end of the loop successfully, return true.
        
        */
        for (int i = 0; i < s.length(); i++) {
            if (!map.containsKey(s.charAt(i))) {
                if (map.containsValue(t.charAt(i))) return false;
                map.put(s.charAt(i), t.charAt(i));
            } else if (map.get(s.charAt(i)) != t.charAt(i)) {
                return false;
            }
        }
        
        return true;
    }
}

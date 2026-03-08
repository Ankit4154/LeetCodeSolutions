// 1980. Find Unique Binary String
// https://leetcode.com/problems/find-unique-binary-string/description/
class Solution {
    String res = "";
    public String findDifferentBinaryString(String[] nums) {
        Set<String> set = new HashSet<>();
        for(int i=0;i<nums.length;i++){
            set.add(nums[i]);
        }
        String s = nums[0];
    
        solve(0, s, set) ;
        return res;
    }
   boolean solve(int ind, String s, Set<String> set){
       if(res!="")
           return true;
       if(ind!=0 && !set.contains(s)){
           res = s;
           return true;
       }
       if(ind == s.length())
           return false;
       for(int i=ind;i<s.length();i++){
           char c = s.charAt(i);
           String mod = "";
           if(c == '0'){
               mod = s.substring(0, i) + 
                   "1" + s.substring(i+1);
           }else{
               mod = s.substring(0, i) + 
                   "0" + s.substring(i+1);
           }
           if(solve(ind+1, mod, set))
               return true;
       }
       return false;
   } 
}

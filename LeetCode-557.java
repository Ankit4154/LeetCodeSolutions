// 557. Reverse Words in a String III
// https://leetcode.com/problems/reverse-words-in-a-string-iii/

class Solution {
    public String reverseWords(String s) {
        char[] arr = s.toCharArray();
        int start = 0;
        
        for(int end = 0; end <= arr.length; end++){
            // If we reach space or end of string, reverse the word
            if(end == arr.length || arr[end] == ' '){
                reverse(arr, start, end - 1);
                start = end + 1;
            }
        }
        
        return new String(arr);
    }
    
    private void reverse(char[] arr, int left, int right){
        while(left < right){
            char temp = arr[left];
            arr[left] = arr[right];
            arr[right] = temp;
            left++;
            right--;
        }
    }
}
// init
class Solution {
    public String reverseWords(String s) {
        String[] sArray = s.split(" ");
        String out = "";
        for(int i=0;i<sArray.length;i++){
            for(int j=sArray[i].length()-1;j>=0;j--){
                out += sArray[i].charAt(j);
            }
            out += " ";
        }
        out = out.substring(0,out.length()-1);
        return out;
    }
}
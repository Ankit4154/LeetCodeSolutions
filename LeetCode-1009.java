// 1009. Complement of Base 10 Integer
// https://leetcode.com/problems/complement-of-base-10-integer

// slight optimized
class Solution {
    public int bitwiseComplement(int n) {
        if(n == 0)
            return 1;
        String bin = Integer.toBinaryString(n);
        String complement = getComplement(bin);
        return Integer.parseInt(complement, 2);
    }
    String getComplement(String binary){
        char[] arr = binary.toCharArray();
        for(int i=0;i<arr.length;i++){
            if(arr[i] == '0')
                arr[i] = '1';
            else
                arr[i] = '0';
        }
        return new String(arr);
    }
}


// init
class Solution {
    public int bitwiseComplement(int n) {
        if(n == 0)
            return 1;
        String bin = getBinaryString(n);
        String complement = getComplement(bin);
        return getNumber(complement);
    }
    int getNumber(String bin){
        int decimal = 0;
        int power = 1;
        for(int i=bin.length()-1;i>=0;i--){
            if(bin.charAt(i) == '1'){
                decimal += power;
            }
            power *= 2;
        }
        return decimal;
    }
    String getComplement(String binary){
        char[] arr = binary.toCharArray();
        for(int i=0;i<arr.length;i++){
            if(arr[i] == '0')
                arr[i] = '1';
            else
                arr[i] = '0';
        }
        return new String(arr);
    }
    String getBinaryString(int n){
        int rem = 0;
        int div = 2;
        String s = "";
        while(n>0){
            rem = n % div;
            s = rem + s;
            n = n / div;
        }
        return s;
    }
}
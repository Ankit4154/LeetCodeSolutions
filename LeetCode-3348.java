// 3348. Smallest Divisible Digit Product II
// https://leetcode.com/problems/smallest-divisible-digit-product-ii
class Solution {
    static final int[] E2 = {0,0,1,0,2,0,1,0,3,0};
    static final int[] E3 = {0,0,0,1,0,0,1,0,0,2};
    static final int[] E5 = {0,0,0,0,0,1,0,0,0,0};
    static final int[] E7 = {0,0,0,0,0,0,0,1,0,0};

    int[][] minDigits23;
    int a2, a3, a5, a7;

    public String smallestNumber(String num, long t) {
        long temp = t;
        a2 = a3 = a5 = a7 = 0;
        while (temp % 2 == 0) { a2++; temp /= 2; }
        while (temp % 3 == 0) { a3++; temp /= 3; }
        while (temp % 5 == 0) { a5++; temp /= 5; }
        while (temp % 7 == 0) { a7++; temp /= 7; }
        if (temp != 1) return "-1";

        minDigits23 = new int[a2 + 1][a3 + 1];
        for (int r2 = 0; r2 <= a2; r2++) {
            for (int r3 = 0; r3 <= a3; r3++) {
                if (r2 == 0 && r3 == 0) { minDigits23[r2][r3] = 0; continue; }
                int best = Integer.MAX_VALUE;
                best = Math.min(best, trans(r2, r3, 1, 0));
                best = Math.min(best, trans(r2, r3, 0, 1));
                best = Math.min(best, trans(r2, r3, 2, 0));
                best = Math.min(best, trans(r2, r3, 1, 1));
                best = Math.min(best, trans(r2, r3, 3, 0));
                best = Math.min(best, trans(r2, r3, 0, 2));
                minDigits23[r2][r3] = best;
            }
        }

        int L = num.length();
        char[] arr = num.toCharArray();

        boolean hasZero = false;
        long e2s = 0, e3s = 0, e5s = 0, e7s = 0;
        for (char c : arr) {
            if (c == '0') { hasZero = true; break; }
            int d = c - '0';
            e2s += E2[d]; e3s += E3[d]; e5s += E5[d]; e7s += E7[d];
        }
        if (!hasZero && e2s >= a2 && e3s >= a3 && e5s >= a5 && e7s >= a7) return num;

        int M = minDigits23[a2][a3] + a5 + a7;

        if (M <= L) {
            String res = trySameLength(arr, L);
            if (res != null) return res;
        }

        int targetLen = Math.max(L + 1, M);
        return buildSuffix(targetLen, a2, a3, a5, a7);
    }

    private int trans(int r2, int r3, int k2, int k3) {
        int nr2 = Math.max(r2 - k2, 0);
        int nr3 = Math.max(r3 - k3, 0);
        if (nr2 == r2 && nr3 == r3) return Integer.MAX_VALUE;
        int sub = minDigits23[nr2][nr3];
        if (sub == Integer.MAX_VALUE) return Integer.MAX_VALUE;
        return 1 + sub;
    }

    private String trySameLength(char[] arr, int L) {
        int firstZero = L;
        for (int j = 0; j < L; j++) if (arr[j] == '0') { firstZero = j; break; }
        int maxI = Math.min(L - 1, firstZero);

        int[] pe2 = new int[L + 1], pe3 = new int[L + 1], pe5 = new int[L + 1], pe7 = new int[L + 1];
        for (int i = 0; i < L; i++) {
            if (i < firstZero) {
                int d = arr[i] - '0';
                pe2[i+1] = pe2[i] + E2[d];
                pe3[i+1] = pe3[i] + E3[d];
                pe5[i+1] = pe5[i] + E5[d];
                pe7[i+1] = pe7[i] + E7[d];
            } else {
                pe2[i+1] = pe2[i]; pe3[i+1] = pe3[i]; pe5[i+1] = pe5[i]; pe7[i+1] = pe7[i];
            }
        }

        for (int i = maxI; i >= 0; i--) {
            int p2 = pe2[i], p3 = pe3[i], p5 = pe5[i], p7 = pe7[i];
            int startD = (arr[i] - '0') + 1;
            for (int d = startD; d <= 9; d++) {
                int u2 = p2 + E2[d], u3 = p3 + E3[d], u5 = p5 + E5[d], u7 = p7 + E7[d];
                int r2 = Math.max(a2 - u2, 0), r3 = Math.max(a3 - u3, 0);
                int r5 = Math.max(a5 - u5, 0), r7 = Math.max(a7 - u7, 0);
                int suffixLen = L - 1 - i;
                int need = minDigits23[r2][r3] + r5 + r7;
                if (need <= suffixLen) {
                    StringBuilder sb = new StringBuilder();
                    sb.append(arr, 0, i);
                    sb.append((char) ('0' + d));
                    sb.append(buildSuffix(suffixLen, r2, r3, r5, r7));
                    return sb.toString();
                }
            }
        }
        return null;
    }

    private String buildSuffix(int length, int r2, int r3, int r5, int r7) {
        StringBuilder sb = new StringBuilder();
        for (int pos = 0; pos < length; pos++) {
            int remLen = length - pos - 1;
            for (int d = 1; d <= 9; d++) {
                int nr2 = Math.max(r2 - E2[d], 0), nr3 = Math.max(r3 - E3[d], 0);
                int nr5 = Math.max(r5 - E5[d], 0), nr7 = Math.max(r7 - E7[d], 0);
                int need = minDigits23[nr2][nr3] + nr5 + nr7;
                if (need <= remLen) {
                    sb.append((char) ('0' + d));
                    r2 = nr2; r3 = nr3; r5 = nr5; r7 = nr7;
                    break;
                }
            }
        }
        return sb.toString();
    }
}
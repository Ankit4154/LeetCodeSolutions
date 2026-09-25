// 1096. Brace Expansion II
// https://leetcode.com/problems/brace-expansion-ii
class Solution {
    int i = 0;

    public List<String> braceExpansionII(String expression) {

        Set<String> set = parseExpression(expression);

        List<String> out = new ArrayList<>(set);
        Collections.sort(out);

        return out;
    }

    // Handles concatenation
    private Set<String> parseExpression(String s) {

        Set<String> result = new HashSet<>();
        result.add("");

        while (i < s.length()
                && s.charAt(i) != '}'
                && s.charAt(i) != ',') {

            Set<String> current = parseTerm(s);

            // Cartesian product = concatenation
            Set<String> temp = new HashSet<>();

            for (String a : result) {
                for (String b : current) {
                    temp.add(a + b);
                }
            }

            result = temp;
        }

        return result;
    }

    // Handles a single letter OR {...}
    private Set<String> parseTerm(String s) {

        Set<String> result = new HashSet<>();

        // Case 1: { ... }
        if (s.charAt(i) == '{') {

            i++; // skip '{'

            while (true) {

                // Parse one expression inside the braces
                Set<String> current = parseExpression(s);

                // Union
                result.addAll(current);

                if (s.charAt(i) == ',') {
                    i++; // skip ','
                } else {
                    break; // reached '}'
                }
            }

            i++; // skip '}'

        } else {

            // Case 2: lowercase letter
            result.add(String.valueOf(s.charAt(i)));
            i++;
        }

        return result;
    }
}
// 690. Employee Importance
// https://leetcode.com/problems/employee-importance
/*
// Definition for Employee.
class Employee {
    public int id;
    public int importance;
    public List<Integer> subordinates;
};
*/

class Solution {
    Map<Integer, Employee> map = new HashMap<>();

    public int getImportance(List<Employee> employees, int id) {
        for(Employee e : employees) {
            map.put(e.id, e);
        }
        return dfs(id);
    }

    private int dfs(int id) {
        Employee emp = map.get(id);
        int total = emp.importance;

        for(int subId : emp.subordinates) {
            total += dfs(subId);
        }
        return total;
    }
}
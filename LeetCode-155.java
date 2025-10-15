// 155. Min Stack
// https://leetcode.com/problems/min-stack/
// Optimization on space to use int[] instead of Dynamic ArrayList.
class MinStack {

    List<int[]> list;
    int top = -1;
    int min = Integer.MAX_VALUE;

    public MinStack() {
        list = new LinkedList<>();
    }
    
    public void push(int val) {
        if(val < min)
            min = val;
        int[] l = new int[2];
        l[0] = val;
        l[1] = min;
        list.add(++top, l);
    }

    public void pop() {
        int m = list.get(top)[1];
        if(m == min && top-1 >=0 ){
             min = list.get(top-1)[1];
        }else{
            min = Integer.MAX_VALUE;
        }
        list.remove(top--);
    }
    
    public int top() {
        return list.get(top)[0];
    }
    
    public int getMin() {
        return list.get(top)[1];
    }
}

// Optimization on time for pop operation by reducing from O(n) to O(1) time
// by storing minimum value as a pair with each top element.
// During pop operation ensure to re-assign min value and edge case check
// if stack has only 1 last element remaining, reset min to Highest value.
class MinStack {

    List<List<Integer>> list;
    int top = -1;
    int min = Integer.MAX_VALUE;

    public MinStack() {
        list = new LinkedList<>();
    }
    
    public void push(int val) {
        if(val < min)
            min = val;
        List<Integer> l = new ArrayList<>(2);
        l.add(0, val);
        l.add(1 ,min);
        list.add(++top, l);
    }

    public void pop() {
        int m = list.get(top).get(1);
        if(m == min && top-1 >=0 )
             min = list.get(top-1).get(1);
        else
            min = Integer.MAX_VALUE;
        list.remove(top--);
    }
    
    public int top() {
        return list.get(top).get(0);
    }
    
    public int getMin() {
        return list.get(top).get(1);
    }
}

// Initial version, pop operation takes O(n) time 
// to compare each value and assign a new minimum
class MinStack {

    List<Integer> list;
    int top = -1;
    int min = Integer.MAX_VALUE;

    public MinStack() {
        list = new LinkedList<>();
    }
    
    public void push(int val) {
        if(val < min)
            min = val;
        list.add(val);
        top++;
    }
    public void pop() {
        int o = list.remove(top);
        if(min == o){
            min = Integer.MAX_VALUE;
            for(Integer x : list){
                if(x < min){
                    min = x;
                }
            }
        }
        top--;
    }
    
    public int top() {
        return list.get(top);
    }
    
    public int getMin() {
        return min;
    }
}

/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(val);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */
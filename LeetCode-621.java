// 621. Task Scheduler
// https://leetcode.com/problems/task-scheduler
class Solution {
    public int leastInterval(char[] tasks, int n) {
		Map<Character, Integer> map = new HashMap<>();
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a,b)->b - a);
		for(char ch : tasks){
			map.put(ch, map.getOrDefault(ch, 0)+1);
		}
		maxHeap.addAll(map.values());
		int currTime = 0;
		Queue<int[]> q = new ArrayDeque<>();
		while(!maxHeap.isEmpty() || !q.isEmpty()){
            currTime++;
            if(!maxHeap.isEmpty()){
                int freq = maxHeap.poll();
                freq--;
                if(freq > 0)
                    q.add(new int[]{freq, currTime+n});
            }
			if(!q.isEmpty() && q.peek()[1] == currTime){
				maxHeap.add(q.poll()[0]);
			}
            
		}
		return currTime;
	}
}

// init
class Solution {
    public int leastInterval(char[] tasks, int n) {
		Map<Character, Integer> map = new HashMap<>();
        PriorityQueue<Task> maxHeap = new PriorityQueue<>((a,b)->b.freq - a.freq);
		for(char ch : tasks){
			map.put(ch, map.getOrDefault(ch, 0)+1);
		}
		for(Map.Entry<Character, Integer> entry : map.entrySet()){
			maxHeap.add(new Task(entry.getKey(), entry.getValue(), 0));
		}
		int count = 0;
		// A -> 3, 0 B -> 3, 0 , n = 2
		// A-> 3,2 , 
		//0  1  2     3  4  5
		//A  B  idle  A  B
		
		while(!maxHeap.isEmpty()){
			Task t = maxHeap.poll();
			currTime++;
			if(t.freq == 0)
				continue;
			if(t.next < n){
				count++;
				maxHeap.add(new Task(t.ch, t.freq-1, t.next+n+1));
			}else if(t.next > n){
				count++; // idle
				maxHeap.add(new Task(t.ch, t.freq, t.next-n));
			}
		}
		return count;
		//map.entrySet().stream().forEach(maxHeap.add(new Task(entry.getKey())))
	}
	private class Task{
		char ch;
		int freq = 0;
		int next;
		Task(char ch, int freq, int next){
			this.ch = ch;
			this.freq = freq;
			this.next = next;
		}
	}
}
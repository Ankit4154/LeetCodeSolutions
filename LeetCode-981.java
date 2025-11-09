// 981. Time Based Key-Value Store
// https://leetcode.com/problems/time-based-key-value-store/

class TimeMap {

    Map<String, List<Object[]>> map = null;

    public TimeMap() {
        this.map = new HashMap<>();
    }
    
    public void set(String key, String value, int timestamp) {
		Object[] pair = new Object[2];
		pair[0] = value;
		pair[1] = timestamp;
        if(map.containsKey(key)){
            map.get(key).add(pair);
        }else{
            List<Object[]> list = new ArrayList<>();
		    list.add(pair);
            map.put(key, list);
        }
    }
    
    public String get(String key, int timestamp) {
		if(null == map.get(key))
			return "";
		List<Object[]> l = map.get(key);
        int low = 0, high = l.size()-1;
        String target = "";
        while(low <= high){
            int mid = low + (high-low)/2;
            int i = (Integer)l.get(mid)[1];
            String t = (String)l.get(mid)[0];
			if(i <= timestamp){
				target = t;
                low = mid + 1;
			}else{
				high = mid - 1;
			}
        }
		return target;
    }
}

/**
 * Your TimeMap object will be instantiated and called as such:
 * TimeMap obj = new TimeMap();
 * obj.set(key,value,timestamp);
 * String param_2 = obj.get(key,timestamp);
 */

// public class TimeMap {

//     private Map<String, List<Pair<Integer, String>>> keyStore;

//     public TimeMap() {
//         keyStore = new HashMap<>();
//     }

//     public void set(String key, String value, int timestamp) {
//         keyStore.computeIfAbsent(key, k -> new ArrayList<>()).add(new Pair<>(timestamp, value));
//     }

//     public String get(String key, int timestamp) {
//         List<Pair<Integer, String>> values = keyStore.getOrDefault(key, new ArrayList<>());
//         int left = 0, right = values.size() - 1;
//         String result = "";

//         while (left <= right) {
//             int mid = left + (right - left) / 2;
//             if (values.get(mid).getKey() <= timestamp) {
//                 result = values.get(mid).getValue();
//                 left = mid + 1;
//             } else {
//                 right = mid - 1;
//             }
//         }

//         return result;
//     }

//     private static class Pair<K, V> {
//         private final K key;
//         private final V value;

//         public Pair(K key, V value) {
//             this.key = key;
//             this.value = value;
//         }

//         public K getKey() {
//             return key;
//         }

//         public V getValue() {
//             return value;
//         }
//     }

// }

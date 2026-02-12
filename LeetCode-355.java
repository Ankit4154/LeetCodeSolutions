// 355. Design Twitter
// https://leetcode.com/problems/design-twitter/
class Twitter {

	PriorityQueue<int[]> tweets;
	Map<Integer, List<int[]>> userTweets;
	Map<Integer, Set<Integer>> userFollowers;
	int time = 0;
    public Twitter() {
		userTweets = new HashMap<>();
		userFollowers = new HashMap<>();
    }
    
    public void postTweet(int userId, int tweetId) {
		userTweets.computeIfAbsent(userId, k-> new ArrayList<>()).add(new int[]{time++, tweetId});
    }
    
    public List<Integer> getNewsFeed(int userId) {
		tweets = new PriorityQueue<>((a,b)->b[0]-a[0]);
        List<Integer> news = new ArrayList<>();
        if(userTweets.containsKey(userId)){
			for(int[] p : userTweets.get(userId)){
                tweets.add(p);
            }
        }
        if(userFollowers.containsKey(userId)){
            for(Integer follower : userFollowers.get(userId)){
				if(userTweets.containsKey(follower)){
					for(int[] p : userTweets.get(follower)){
						tweets.add(p);
					}
				}
            }
        }
		
		while(!tweets.isEmpty() && news.size() < 10){
			news.add(tweets.poll()[1]);
		}
		return news;
    }
    
    public void follow(int followerId, int followeeId) {
		if(followerId == followeeId)
			return;
        userFollowers.computeIfAbsent(followerId, k->new HashSet<Integer>()).add(followeeId);
    }
    
    public void unfollow(int followerId, int followeeId) {
        if(userFollowers.containsKey(followerId)){
			userFollowers.get(followerId).remove(followeeId);
		}
    }
}

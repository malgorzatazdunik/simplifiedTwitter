package simplifiedTwitter;

import java.util.HashMap;
import java.util.Stack;

public class Twitter {
	
	public static class User {

		String username;
		HashMap<Integer, String> followed;
		
		User(String _username){
			// just stores ID of the followee under the followee ID (could store something like username)
			this.followed = new HashMap<Integer, String>();
			this.username = _username;
		}
		
	}
	public static class Tweet {
		int UserID;
		int TweetID;
		String content;
		
		Tweet(int userID, int tweetID, String _content)
		{
			this.UserID = userID;
			this.TweetID = tweetID;
			this.content = _content;
		}
	}
	
	// hash map of all users (userID, User object)
	public static HashMap<Integer, User> users = new HashMap<Integer, User>();
	
	// stack of all recent tweets
	public static Stack<Tweet> feed = new Stack<Tweet>();
	
	public static void postTweet(int userID, int tweetID, String content)
	{
		// creates a new Tweet object and adds it to the feed queue
		Tweet tweet = new Tweet(userID, tweetID, content);
		feed.add(tweet);
	}
	
	public static void getNewsFeed(int userID)
	{
		int count = 0;
		User user = users.get(userID);
		Stack<Tweet> temp = new Stack<Tweet>();

		// while loop to return head of the stack until either it’s empty or 10 tweets were returned
		while (!feed.isEmpty())
		{
			
			Tweet tweet = feed.pop();
			
			temp.add(tweet);
			
			// checks if the user id of the user that posted the tweet matches the User, or if it matches any of the ids followed by User
			if (tweet.UserID == userID || user.followed.get(tweet.UserID) != null)
			{
				System.out.println("User: " + users.get(tweet.UserID).username + " Tweet: "+ tweet.TweetID + " " + tweet.content);
				count++;
			}
			
			if (count == 10) break; // stops printing after 10 tweets
		}
		
		// add tweets back to feed
		while (!temp.isEmpty())
		{
			feed.add(temp.pop());
		}
		
	}
	
	public static void follow(int followerID, int followeeID)
	{
		// adds followees IDs to the User's map of the followees
		users.get(followerID).followed.put(followeeID, users.get(followeeID).username);
	}
	
	public static void unfollow(int followerID, int followeeID)
	{
		// removes followee ID from the User's map of the followees
		users.get(followerID).followed.remove(followeeID);
	}
	
	public static void main (String[] args)
	{
		User user1 = new User("A");
		User user2 = new User("B");
		User user3 = new User("C");
		User user4 = new User("D");
		User user5 = new User("E");
		
		users.put(1, user1);
		users.put(2, user2);
		users.put(3, user3);
		users.put(4, user4);
		users.put(5, user5);
		
		postTweet(1, 0, "Tweet #0");
		getNewsFeed(1);
		
		postTweet(2, 1, "Tweet #1");
		postTweet(3, 2, "Tweet #2");
		postTweet(4, 3, "Tweet #3");
		
		System.out.println();
		follow(1, 4);
		getNewsFeed(1);
		
		follow(1, 2);
		
		for (int i = 4; i < 10; i++)
		{
			postTweet(2, i, "Tweet " + "#" + i);
		}
		System.out.println();
		postTweet(1, 10, "Tweet #10");
		getNewsFeed(1);
		
	}
	
	

}

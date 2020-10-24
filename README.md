# simplifiedTwitter

A small project I have worked on during my insight week at Bloomberg.

Implements some simple features:
* postTweet(userId, tweetId): Compose a new tweet.
* getNewsFeed(userId): Retrieve the 10 most recent tweet ids (and tweets) in the user's news feed.
Each item in the news feed was posted by users who the user followed or by the user
themselves. Tweets are ordered from most recent to least recent.
* follow(followerId, followeeId): Follower follows a followee.
* unfollow(followerId, followeeId): Follower unfollows a followee.
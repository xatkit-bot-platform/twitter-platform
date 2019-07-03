package com.xatkit.plugins.twitter.platform.action;

import com.xatkit.core.platform.action.RuntimeAction;
import com.xatkit.core.session.XatkitSession;
import com.xatkit.plugins.twitter.platform.TwitterPlatform;

import twitter4j.Twitter;
import twitter4j.TwitterException;

/**
 * Adds a property to the provided {@code node}.
 * <p>
 * This class relies on the {@link TwitterPlatform}'s {@link twitter4j.Twitter}
 * to integrate with twitter.
 */
public class PostAtweet extends RuntimeAction<TwitterPlatform> {
	/**
	 * The content of the tweet.
	 */
	private String content;

	/**
	 * Post a new tweet {@link PostATweet} action with the provided
	 * {@code runtimePlatform}, {@code session}, {@code content}.
	 *
	 * @param runtimePlatform the {@link TwitterPlatform} containing the database to
	 *                        store the created property
	 * @param session         the {@link XatkitSession} associated to this action
	 * @param content         the content of the tweet to post
	 */
	public PostAtweet(TwitterPlatform runtimePlatform, XatkitSession session, String content) {
		super(runtimePlatform, session);
		this.content = content;
	}

	/**
	 * Post a tweet with the provided {@code content}.
	 * <p>
	 * This action opens a new conection to with twiter and posts a tweet with the content
	 * specified by the chatbot.
	 *
	 * @return 0 if no errors; 1 if errors
	 */
	@Override
	protected Object compute() {
		int error = 0;
		Twitter twitterService = this.runtimePlatform.getTwitterService();
		/*
		 * Gets the twitter API instance and calls updateStatus method
		 * to post a tweet with content {@code content}.
		 */
		try {
			twitterService.updateStatus(this.content);
		} catch (TwitterException e) {
			error = 1;
			e.printStackTrace();
		}
		return error;
	}
}

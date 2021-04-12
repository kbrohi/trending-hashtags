package com.kaleem.coding.assignment.service;

import org.springframework.scheduling.annotation.Async;

import com.kaleem.coding.assignment.model.TweetRequest;

public interface TrendingHashtagService {

	@Async(value = "threadPoolTaskExecutor")
	void create(TweetRequest tweet);

	Object trendingHashtags();

}

package com.kaleem.coding.assignment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.kaleem.coding.assignment.model.TweetRequest;
import com.kaleem.coding.assignment.service.TrendingHashtagService;
import com.kaleem.coding.assignment.util.Constants;

@RestController
public class TrendingHashTagController {

	@Autowired
	private TrendingHashtagService trendingHashtagService;

	@PostMapping(value = Constants.TWEET, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<?> create(@RequestBody TweetRequest tweet) {
		if (StringUtils.isEmpty(tweet.getTweet().trim())) {
			return ResponseEntity.badRequest().body("Please Provide a valid tweet");
		}
		trendingHashtagService.create(tweet);
		return ResponseEntity.ok(Constants.STATUS_SUCCESS);
	}

	@GetMapping(value = Constants.TRENDING)
	public ResponseEntity<Object> trendingHashtags() {
		return ResponseEntity.ok(trendingHashtagService.trendingHashtags());
	}

	@GetMapping(value = Constants.HEALTH, produces = { MediaType.APPLICATION_JSON_VALUE })
	public String health() {
		return "I'm Alive";
	}
}

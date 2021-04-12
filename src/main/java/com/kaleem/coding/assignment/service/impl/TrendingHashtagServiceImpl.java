package com.kaleem.coding.assignment.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.kaleem.coding.assignment.entity.TrendingTagEntity;
import com.kaleem.coding.assignment.entity.TweetInfoEntity;
import com.kaleem.coding.assignment.model.TweetRequest;
import com.kaleem.coding.assignment.repository.TrendingHashtagRepository;
import com.kaleem.coding.assignment.repository.TweetInfoRepository;
import com.kaleem.coding.assignment.service.TrendingHashtagService;
import com.kaleem.coding.assignment.util.Constants;
import com.kaleem.coding.assignment.util.HashtagUtil;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class TrendingHashtagServiceImpl implements TrendingHashtagService {

	@Autowired
	private TrendingHashtagRepository trendingHashtagRepository;

	@Autowired
	private TweetInfoRepository tweetInfoRepository;

	@Value("${com.twitter.hashtag.pattern}")
	private String pattern;

	@Override
	public void create(TweetRequest tweetRequest) {
		// check repeating tweets
		TweetInfoEntity tweetInfoEntity = tweetInfoRepository.findByTweet(tweetRequest.getTweet().trim());
		if (tweetInfoEntity == null) {
			List<String> tags = HashtagUtil.getTags(tweetRequest.getTweet().trim(), pattern);
			tweetInfoEntity = TweetInfoEntity.builder().tweet(tweetRequest.getTweet().trim())
					.hashTags(tags.stream().collect(Collectors.joining(","))).build();
			tweetInfoRepository.save(tweetInfoEntity);
			handleTrending(tags);
		} else {
			log.info("Duplicate Tweet ");
		}
	}

	private synchronized void handleTrending(List<String> tags) {
		if (!CollectionUtils.isEmpty(tags)) {
			tags.forEach(tag -> {
				TrendingTagEntity trendingTagEntity = trendingHashtagRepository.findByTags(tag);
				if (trendingTagEntity == null) {
					trendingTagEntity = TrendingTagEntity.builder().tags(tag).trending(1).build();
				} else {
					trendingTagEntity.setTrending(trendingTagEntity.getTrending() + 1);
				}
				trendingHashtagRepository.save(trendingTagEntity);
			});
		}
	}

	@Override
	public Object trendingHashtags() {
		ObjectNode responseNode = new ObjectMapper().createObjectNode();
		responseNode.putPOJO(Constants.HASH_TAGS, trendingHashtagRepository.findTop25TrendingTags());
		return responseNode;
	}

}

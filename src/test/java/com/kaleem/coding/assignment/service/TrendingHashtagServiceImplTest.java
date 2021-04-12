package com.kaleem.coding.assignment.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.util.ReflectionTestUtils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.kaleem.coding.assignment.entity.TweetInfoEntity;
import com.kaleem.coding.assignment.model.TweetRequest;
import com.kaleem.coding.assignment.repository.TrendingHashtagRepository;
import com.kaleem.coding.assignment.repository.TweetInfoRepository;
import com.kaleem.coding.assignment.service.impl.TrendingHashtagServiceImpl;
import com.kaleem.coding.assignment.util.Constants;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class TrendingHashtagServiceImplTest {

	@InjectMocks
	TrendingHashtagServiceImpl trendingHashtagServiceImplTest;

	@Mock
	TrendingHashtagRepository trendingHashtagRepository;

	@Mock
	TweetInfoRepository tweetInfoRepository;

	ObjectMapper objectMapper;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		ReflectionTestUtils.setField(trendingHashtagServiceImplTest, "pattern", "#(\\w+)");
		objectMapper = new ObjectMapper();
	}

	@Test
	public void tweetSaveSuccessfullyTest() {
		TweetRequest tweetRequest = TweetRequest.builder().tweet("This is test tweet with hash tags #tagme #hashtags #trendingTweets #trendingTweets")
				.build();
		Mockito.when(tweetInfoRepository.findByTweet(Mockito.anyString())).thenReturn(null);
		trendingHashtagServiceImplTest.create(tweetRequest);

	}

	@Test
	public void tweetRepeatTest() {
		TweetRequest tweetRequest = TweetRequest.builder()
				.tweet("This is test tweet with hash tags #tagme #hashtags #trendingTweets #trendingTweets").build();
		Mockito.when(tweetInfoRepository.findByTweet(Mockito.anyString()))
				.thenReturn(TweetInfoEntity.builder().id(123L).build());
		trendingHashtagServiceImplTest.create(tweetRequest);

	}

	@Test
	public void tredningHashtagTest() {
		ObjectNode expectedNode = objectMapper.createObjectNode();
		List<String> tags = Arrays.asList("#me", "#hashtag", "#trendingTweets", "#trends1", "trends2",
				"#trendingTweets");
		expectedNode.putPOJO(Constants.HASH_TAGS, tags);
		Mockito.when(trendingHashtagRepository.findTop25TrendingTags()).thenReturn(tags);
		ObjectNode responseNode = (ObjectNode) trendingHashtagServiceImplTest.trendingHashtags();
		assertNotNull(responseNode);
		assertEquals(responseNode, expectedNode);
		assertEquals(responseNode.get(Constants.HASH_TAGS), expectedNode.get(Constants.HASH_TAGS));
	}

}

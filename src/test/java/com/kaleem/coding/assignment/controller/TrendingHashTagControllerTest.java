package com.kaleem.coding.assignment.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kaleem.coding.assignment.model.TweetRequest;
import com.kaleem.coding.assignment.service.TrendingHashtagService;
import com.kaleem.coding.assignment.util.Constants;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class TrendingHashTagControllerTest {

	@InjectMocks
	TrendingHashTagController trendingHashTagController;

	@Mock
	private TrendingHashtagService trendingHashtagService;

	@Autowired
	private MockMvc mockMvc;

	ObjectMapper objectMapper;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		this.mockMvc = MockMvcBuilders.standaloneSetup(trendingHashTagController).build();
		objectMapper = new ObjectMapper();
	}

	@Test
	public void validateTweetEndpointNotFound() throws Exception {
		this.mockMvc.perform(post("/tweets")).andDo(print()).andExpect(status().isNotFound());
	}

	@Test
	public void validateTweetEndpointOk() throws Exception {
		TweetRequest request = TweetRequest.builder().tweet("Test tweet #test #tweets #trending").build();
		String payload = objectMapper.writeValueAsString(request);
		this.mockMvc.perform(post(Constants.TWEET).content(payload).contentType(MediaType.APPLICATION_JSON_VALUE))
				.andDo(print()).andExpect(status().isOk());
	}

	@Test
	public void validateTweetEndpointBadRequest() throws Exception {
		TweetRequest request = TweetRequest.builder().tweet("     ").build();
		String payload = objectMapper.writeValueAsString(request);
		this.mockMvc.perform(post(Constants.TWEET).content(payload).contentType(MediaType.APPLICATION_JSON_VALUE))
				.andDo(print()).andExpect(status().isBadRequest());
	}

	@Test
	public void validateTrendingHashtagEndpointOk() throws Exception {
		this.mockMvc.perform(get(Constants.TRENDING).accept(MediaType.APPLICATION_JSON_VALUE)).andDo(print())
				.andExpect(status().isOk());
	}

	@Test
	public void validateTrendingHashtagEndpointNotFound() throws Exception {
		this.mockMvc.perform(get(Constants.TRENDING+"ssss").accept(MediaType.APPLICATION_JSON_VALUE)).andDo(print())
		.andExpect(status().isNotFound());
	}

}

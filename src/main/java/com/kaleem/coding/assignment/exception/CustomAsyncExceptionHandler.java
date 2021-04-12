package com.kaleem.coding.assignment.exception;

import java.lang.reflect.Method;

import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CustomAsyncExceptionHandler implements AsyncUncaughtExceptionHandler {

	@Override
	public void handleUncaughtException(Throwable throwable, Method method, Object... obj) {
		log.error("Trending hashtags Exception message - {} ", throwable.getMessage());
		log.error("Trending hashtags Method name - {} ", method.getName());
		for (Object param : obj) {
			log.error("Trending hashtags Parameter value - {} ", param);
		}
	}

}
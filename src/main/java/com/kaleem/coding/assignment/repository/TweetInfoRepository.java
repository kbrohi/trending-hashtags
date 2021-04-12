package com.kaleem.coding.assignment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kaleem.coding.assignment.entity.TweetInfoEntity;

@Repository
public interface TweetInfoRepository extends JpaRepository<TweetInfoEntity, Long> {

	TweetInfoEntity findByTweet(String trim);

}

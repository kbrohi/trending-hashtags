package com.kaleem.coding.assignment.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.kaleem.coding.assignment.entity.TrendingTagEntity;

@Repository
public interface TrendingHashtagRepository extends JpaRepository<TrendingTagEntity, Long> {

	public TrendingTagEntity findByTags(String tag);

	@Query(value = "SELECT tags FROM TrendingTagEntity ORDER BY trending DESC")
	List<String> findTop25TrendingTags();
}

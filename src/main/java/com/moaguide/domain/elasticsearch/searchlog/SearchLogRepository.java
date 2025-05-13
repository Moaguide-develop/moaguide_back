package com.moaguide.domain.elasticsearch.searchlog;

import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface SearchLogRepository extends JpaRepository<SearchLog, Long> {

	@Query("""
        SELECT s.searchTerm, COUNT(s) 
        FROM SearchLog s 
        WHERE s.timestamp BETWEEN :start AND :end 
        GROUP BY s.searchTerm 
        ORDER BY COUNT(s) DESC
        """)
	List<Object[]> findTopSearchTermsBetween(Date start, Date end);
}

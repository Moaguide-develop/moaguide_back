package com.moaguide.service;

import com.moaguide.domain.elasticsearch.searchlog.SearchLog;
import com.moaguide.domain.elasticsearch.searchlog.SearchLogRepository;
import com.moaguide.domain.product.ProductRepository;
import com.moaguide.dto.SearchRankDto;
import com.moaguide.dto.searchProductDto;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SearchService {

	private final ProductRepository productRepository;
	private final SearchLogRepository searchLogRepository;


	// 검색어 저장 메서드
    public void saveKeyword(String keyword) {
        if(keyword.isEmpty()){
		}else{
            searchLogRepository.save(new SearchLog(keyword, new Date()));
        }
    }

    // 검색 수행 메서드 (product 인덱스에 대한 검색)
	public List<searchProductDto> searchProducts(String keyword) {

		// ✅ 키워드 전처리: +blue +umber
		String processedKeyword = Arrays.stream(keyword.trim().split("\\s+"))
			.filter(word -> word.length() > 1)
			.map(word -> "+" + word)
			.collect(Collectors.joining(" "));

		// ✅ MySQL Native Query 실행
		List<Object[]> result = productRepository.searchProductsRaw(processedKeyword);

		// ✅ DTO 매핑
		return result.stream()
			.map(row -> new searchProductDto(
				(String) row[0],
				(String) row[1],
				(String) row[2],
				(String) row[3]
			))
			.toList();
	}

    // 검색어 순위 집계 메서드 (최근 24시간 기준)
	public List<SearchRankDto> getSearchRank() {
		OffsetDateTime now = OffsetDateTime.now(ZoneOffset.UTC);
		OffsetDateTime last24Hours = now.minusHours(24*5);

		List<Object[]> rawResult = searchLogRepository.findTopSearchTermsBetween(
			Date.from(last24Hours.toInstant()),
			Date.from(now.toInstant())
		);

		List<SearchRankDto> ranks = new ArrayList<>();
		int rank = 1;
		for (Object[] row : rawResult.stream().limit(5).toList()) {
			String keyword = (String) row[0];
			ranks.add(new SearchRankDto(keyword, rank++));
		}

		return ranks;
	}

}

//package com.moaguide.controller;
//
//import com.moaguide.dto.SearchRankDto;
//import com.moaguide.dto.searchProductDto;
//import com.moaguide.service.SearchService;
//import jakarta.servlet.http.Cookie;
//import jakarta.servlet.http.HttpServletRequest;
//import lombok.AllArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.context.annotation.Profile;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.io.IOException;
//import java.util.List;
//
//@RestController
//@AllArgsConstructor
//@Slf4j
//@Profile({"blue", "green"})
//public class SearchRestController {
//    private final SearchService searchService;
//
//    @GetMapping("/search")
//    public ResponseEntity<?> search(@RequestParam String keyword, HttpServletRequest request) throws IOException {
//        try {
//            List<searchProductDto> dto = searchService.searchProducts(keyword);  // 검색 수행
//            if(dto.isEmpty() || dto.size() == 0){
//                log.info("IP주소 {}",request.getRemoteAddr());
//                if (request.getCookies() != null) {
//                    for (Cookie cookie : request.getCookies()) {
//                        if ("refresh".equals(cookie.getName())) {
//                            log.info("쿠키 값 {}",cookie.getValue());
//                        }
//                    }
//                }
//            }else{
//                searchService.saveKeyword(keyword);  // 키워드 저장
//            }
//            return ResponseEntity.ok(dto);  // 성공 응답
//        } catch (IOException e) {
//            // IOException이 발생하면 적절한 에러 메시지와 함께 500 에러를 반환
//            return ResponseEntity.status(500).body("Elasticsearch와 통신 중 오류가 발생했습니다.");
//        } catch (Exception e) {
//            // 그 외 다른 예외가 발생한 경우 처리
//            return ResponseEntity.status(500).body("알 수 없는 오류가 발생했습니다.");
//        }
//    }
//
//    // 검색어 순위 API
//    @GetMapping("/searchRank")
//    public ResponseEntity<?> getSearchRank() {
//        try {
//            List<SearchRankDto> searchRankList = searchService.getSearchRank();
//            return ResponseEntity.ok(searchRankList);
//        } catch (IOException e) {
//            return ResponseEntity.status(500).body("검색 순위를 가져오는 중 오류가 발생했습니다.");
//        }
//    }
//}
package com.moaguide.refactor.building.controller;

import static com.moaguide.refactor.util.EmptyCheckUtil.isEmpty;
import static com.moaguide.refactor.util.EmptyCheckUtil.isListEmpty;

import com.moaguide.refactor.building.dto.graph.BuildingSubwayResponseDto;
import com.moaguide.refactor.building.dto.graph.LandDto;
import com.moaguide.refactor.building.dto.graph.PopulationDto;
import com.moaguide.refactor.building.dto.graph.RentDto;
import com.moaguide.refactor.building.dto.graph.StayDayDto;
import com.moaguide.refactor.building.dto.graph.StayRateDto;
import com.moaguide.refactor.building.dto.graph.VacancyrateDto;
import com.moaguide.refactor.building.service.graph.LandPriceService;
import com.moaguide.refactor.building.service.graph.PopulationService;
import com.moaguide.refactor.building.service.graph.RentService;
import com.moaguide.refactor.building.service.graph.StayService;
import com.moaguide.refactor.building.service.graph.SubwayService;
import com.moaguide.refactor.building.service.graph.VacancyRateService;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 건물 그래프 컨트롤러
 */
@RestController
@AllArgsConstructor
@Slf4j
@RequestMapping("/detail/building")
public class BuildingGraphController {

	private final RentService rentService;
	private final LandPriceService landPriceService;
	private final StayService stayService;
	private final SubwayService subwayService;
	private final PopulationService populationService;
	private final VacancyRateService vacancyRateService;

	/**
	 * 건물의 상권 임대료 그래프를 조회하는 API
	 *
	 * @param product_Id 콘텐츠 고유 ID
	 * @param type       상권 유형 (소규모/중대형)
	 * @param syear      시작 연도
	 * @param eyear      종료 연도
	 * @return 건물의 상권 임대료 그래프 응답 DTO
	 */
	@GetMapping("/rentrate/{product_Id}")
	public ResponseEntity<Object> rentrate(@PathVariable String product_Id,
		@RequestParam String type, @RequestParam int syear, @RequestParam int eyear) {
		Map<String, List<RentDto>> rentDtos = rentService.getRentByRegion(product_Id, type, syear,
			eyear);
		Map<String, Object> response = new HashMap<>();

		if (rentDtos == null) {
			response.put("populations", new ArrayList<>());
			return ResponseEntity.ok(response);
		}
		response.put("rent", rentDtos);
		return ResponseEntity.ok(response);
	}

	/**
	 * 건물의 상권 공실률 그래프를 조회하는 API
	 *
	 * @param product_Id 콘텐츠 고유 ID
	 * @param type       상권 유형 (소규모/중대형)
	 * @param syear      시작 연도
	 * @param eyear      종료 연도
	 * @return 건물의 상권 공실률 그래프 응답 DTO
	 */
	@GetMapping("/vacancyrate/{product_Id}")
	public ResponseEntity<Object> vacancyrate(@PathVariable String product_Id,
		@RequestParam String type, @RequestParam int syear, @RequestParam int eyear) {
		Map<String, List<VacancyrateDto>> vacancyrateDtos = vacancyRateService.findBase(product_Id,
			type, syear, eyear);
		Map<String, Object> response = new HashMap<>();

		if (vacancyrateDtos == null) {
			response.put("populations", new ArrayList<>());
			return ResponseEntity.ok(response);
		}
		response.put("vacancyrate", vacancyrateDtos);
		return ResponseEntity.ok(response);
	}

	/**
	 * 건물의 숙박 방문자 수 그래프를 조회하는 API
	 *
	 * @param productId 콘텐츠 고유 ID
	 * @param syear     시작 연도
	 * @param eyear     종료 연도
	 * @return 숙박 유형별 방문자 수 그래프 응답 DTO
	 */
	@GetMapping("/stay/day/{productId}")
	public ResponseEntity<Object> stayday(@PathVariable String productId, @RequestParam int syear,
		@RequestParam int eyear) {
		String keyword;
		Map<String, Object> response = new HashMap<>();

		if (productId.equals("sou.6")) {
			keyword = "전주 시화연풍";
			List<StayDayDto> stayday = stayService.findbykeyword(keyword, syear, eyear);
			response.put("object", stayday);
			return ResponseEntity.ok(response);
		} else if (productId.equals("kasa.KR011A20000052")) {
			keyword = "부티크호텔 더 페이즈";
			List<StayDayDto> stayday = stayService.findbykeyword(keyword, syear, eyear);
			response.put("object", stayday);
			return ResponseEntity.ok(response);
		} else if (productId.equals("kasa.KR011A20000090")) {
			keyword = "북촌 월하재";
			List<StayDayDto> stayday = stayService.findbykeyword(keyword, syear, eyear);
			response.put("object", stayday);
			return ResponseEntity.ok(response);
		} else if (productId.equals("funble.FB2412111")) {
			keyword = "더 코노셔 여의도";
			List<StayDayDto> stayday = stayService.findbykeyword(keyword, syear, eyear);
			response.put("object", stayday);
			return ResponseEntity.ok(response);
		} else {
			response.put("object", new ArrayList<>());
			return ResponseEntity.ok(response);
		}
	}

	/**
	 * 건물의 숙박 방문자 / 숙박일 그래프를 조회하는 API
	 *
	 * @param productId 콘텐츠 고유 ID
	 * @param syear     시작 연도
	 * @param eyear     종료 연도
	 * @return 숙박 방문자 비율 / 평균 숙박일 그래프 응답 DTO
	 */
	@GetMapping("/stay/rate/{productId}")
	public ResponseEntity<Object> stayrate(@PathVariable String productId, @RequestParam int syear,
		@RequestParam int eyear) {
		String keyword;
		Map<String, Object> response = new HashMap<>();
		if (productId.equals("sou.6")) {
			keyword = "전주 시화연풍";
			List<StayRateDto> stayday = stayService.findRateBykeyword(keyword, syear, eyear);
			response.put("object", stayday);
			return ResponseEntity.ok(response);
		} else if (productId.equals("kasa.KR011A20000052")) {
			keyword = "부티크호텔 더 페이즈";
			List<StayRateDto> stayday = stayService.findRateBykeyword(keyword, syear, eyear);
			response.put("object", stayday);
			return ResponseEntity.ok(response);
		} else if (productId.equals("kasa.KR011A20000090")) {
			keyword = "북촌 월하재";
			List<StayRateDto> stayday = stayService.findRateBykeyword(keyword, syear, eyear);
			response.put("object", stayday);
			return ResponseEntity.ok(response);
		} else if (productId.equals("funble.FB2412111")) {
			keyword = "더 코노셔 여의도";
			List<StayRateDto> stayday = stayService.findRateBykeyword(keyword, syear, eyear);
			response.put("object", stayday);
			return ResponseEntity.ok(response);
		} else {
			response.put("object", new ArrayList<>());
			return ResponseEntity.ok(response);
		}
	}

	/**
	 * 건물의 상권 공시지가 그래프를 조회하는 API
	 *
	 * @param product_Id 콘텐츠 고유 ID
	 * @return 건물의 상권 공시지가 그래프 응답 DTO
	 */
	@GetMapping("/land/{product_Id}")
	public ResponseEntity<Object> land(@PathVariable String product_Id) {
		List<LandDto> landPrice = landPriceService.priceList(product_Id);
		Map<String, Object> response = new HashMap<>();

		if (isListEmpty(landPrice)) {
			response.put("lands", new ArrayList<>());
			return ResponseEntity.ok(response);
		}

		response.put("lands", landPrice);
		return ResponseEntity.ok(response);
	}

	/**
	 * 건물의 상권 유동 인구 그래프를 조회하는 API
	 *
	 * @param productId 콘텐츠 고유 ID
	 * @return 건물의 상권 유동 인구 그래프 응답 DTO
	 */
	@GetMapping("/subway/{productId}")
	public ResponseEntity<Object> subway(@PathVariable String productId) {
		BuildingSubwayResponseDto subwayResponseDto = subwayService.findByProductId(productId);

		if (isEmpty(subwayResponseDto) ||
			subwayResponseDto.getSubwayDay().isEmpty() ||
			subwayResponseDto.getSubwayMonth().isEmpty()) {
			return ResponseEntity.ok(new BuildingSubwayResponseDto());
		}

		return ResponseEntity.ok(subwayResponseDto);
	}

	/**
	 * 건물의 상권 유동 인구 그래프를 조회하는 API
	 *
	 * @param product_Id 콘텐츠 고유 ID
	 * @return 건물의 상권 유동 인구 그래프 응답 DTO
	 */
	@GetMapping("/population/{product_Id}")
	public ResponseEntity<Object> population(@PathVariable String product_Id) {
		List<PopulationDto> populationDto = populationService.findbydate(product_Id);
		Map<String, Object> response = new HashMap<>();

		if (populationDto == null) {
			response.put("populations", new ArrayList<>());
			return ResponseEntity.ok(response);
		}
		response.put("populations", populationDto);
		return ResponseEntity.ok(response);
	}
}

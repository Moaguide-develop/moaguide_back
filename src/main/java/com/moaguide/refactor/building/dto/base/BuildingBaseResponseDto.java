package com.moaguide.refactor.building.dto.base;


import com.moaguide.refactor.building.entity.LandRegistry;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class BuildingBaseResponseDto {

	private PublishDto publish;
	private BuildingDetailDto buildingDetail;
	private LandRegistry landRegistry;
	private List<LeaseDto> lease;
	private CurrentDivideDto divide;

	public BuildingBaseResponseDto(BuildingBaseDto building, List<LeaseDto> leaseDtos) {
		this.publish = new PublishDto(
			building.getName(),
			building.getPublisher(),
			building.getPiece(),
			building.getLastDivide(),
			building.getBasePrice(),
			building.getTotalPrice(),
			building.getSubscription(),
			building.getListingDate()
		);

		this.buildingDetail = new BuildingDetailDto(
			building.getName(),
			building.getUseArea(),
			building.getMainUse(),
			building.getCompletionDate(),
			building.getLandArea(),
			building.getFloorArea(),
			building.getFloorAreaRate(),
			building.getDryRatio(),
			building.getHeight(),
			building.getScale(),
			building.getMainStructure(),
			building.getParking(),
			building.getLift(),
			building.getLocation()
		);

		this.landRegistry = new LandRegistry(
			building.getLandElevation(),
			building.getLandShape(),
			building.getRoadInterface(),
			building.getZoningNational(),
			building.getZoningOther()
		);

		this.lease = leaseDtos; // Lease 정보는 그대로 사용
		this.divide = new CurrentDivideDto(
			building.getLastDivide(),
			building.getDivideCycle(),
			building.getPaymentDay(),
			building.getDivideRate()
		);
	}

}

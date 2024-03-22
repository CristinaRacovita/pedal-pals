package soa.group11.bikeManagementService.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import soa.group11.bikeManagementService.enums.BikeEType;
import soa.group11.bikeManagementService.enums.SuitableRoad;

@Getter
@Setter
@Entity
@Table(name = "BikeType")
public class BikeType {
    @Id
    private int Id;

    @Enumerated(EnumType.ORDINAL)
    private BikeEType type;

    @Enumerated(EnumType.ORDINAL)
    private SuitableRoad suitability;

    public BikeType(SuitableRoad suitability) {
        this.suitability = suitability;
    }
}

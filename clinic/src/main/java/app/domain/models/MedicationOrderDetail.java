package app.domain.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MedicationOrderDetail {

    private int itemNumber;
    private long medicationId;
    private String medicationName;
    private String dose;
    private String treatmentDuration;
    private double cost;

}

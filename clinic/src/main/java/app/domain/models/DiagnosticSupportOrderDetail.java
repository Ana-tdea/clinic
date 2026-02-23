package app.domain.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class DiagnosticSupportOrderDetail {

    private int itemNumber;
    private long diagnosticSupportId;
    private String diagnosticSupportName;
    private int quantity;
    private double cost;
    private boolean requiresSpecialist;
    private Long specialistTypeId;

}

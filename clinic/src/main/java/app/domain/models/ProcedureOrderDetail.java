package app.domain.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ProcedureOrderDetail {

    private int itemNumber;
    private long procedureId;
    private String procedureName;
    private int repeatCount;
    private String frequency;
    private double cost;
    private boolean requiresSpecialist;
    private Long specialistTypeId;

}

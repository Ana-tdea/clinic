package app.domain.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class MedicalOrder {

    private String orderNumber;
    private String patientDocument;
    private String doctorDocument;
    private Date creationDate;
    private List<MedicationOrderDetail> medications = new ArrayList<>();
    private List<ProcedureOrderDetail> procedures = new ArrayList<>();
    private List<DiagnosticSupportOrderDetail> diagnosticSupports = new ArrayList<>();

}

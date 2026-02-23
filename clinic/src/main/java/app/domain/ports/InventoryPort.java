package app.domain.ports;

public interface InventoryPort {

    public boolean existsMedicationById(long medicationId);
    public boolean existsProcedureById(long procedureId);
    public boolean existsDiagnosticSupportById(long diagnosticSupportId);

}

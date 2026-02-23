package app.domain.services;

import app.domain.Exceptions.BusinessException;
import app.domain.models.DiagnosticSupportOrderDetail;
import app.domain.models.MedicalOrder;
import app.domain.models.MedicationOrderDetail;
import app.domain.models.ProcedureOrderDetail;
import app.domain.models.Role;
import app.domain.models.User;
import app.domain.ports.InventoryPort;
import app.domain.ports.OrderPort;
import app.domain.ports.PatientPort;
import app.domain.ports.UserPort;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CreateOrderTest {

    @Test
    void shouldCreateOrderWhenItMeetsAllRules() {
        CreateOrder service = new CreateOrder(new InMemoryOrderPort(), new StubPatientPort(), new StubUserPort(), new StubInventoryPort());

        MedicalOrder order = baseOrder();
        MedicationOrderDetail medication = new MedicationOrderDetail();
        medication.setItemNumber(1);
        medication.setMedicationId(11L);
        order.setMedications(List.of(medication));

        assertDoesNotThrow(() -> service.createOrder(order, doctorUser()));
    }

    @Test
    void shouldFailWhenOrderNumberAlreadyExists() {
        InMemoryOrderPort orderPort = new InMemoryOrderPort();
        CreateOrder service = new CreateOrder(orderPort, new StubPatientPort(), new StubUserPort(), new StubInventoryPort());

        MedicalOrder firstOrder = baseOrder();
        MedicationOrderDetail medication = new MedicationOrderDetail();
        medication.setItemNumber(1);
        medication.setMedicationId(11L);
        firstOrder.setMedications(List.of(medication));
        service.createOrder(firstOrder, doctorUser());

        MedicalOrder duplicated = baseOrder();
        duplicated.setMedications(List.of(medication));

        assertThrows(BusinessException.class, () -> service.createOrder(duplicated, doctorUser()));
    }

    @Test
    void shouldFailWhenDiagnosticSupportAndMedicationAreMixed() {
        CreateOrder service = new CreateOrder(new InMemoryOrderPort(), new StubPatientPort(), new StubUserPort(), new StubInventoryPort());

        MedicalOrder order = baseOrder();
        MedicationOrderDetail medication = new MedicationOrderDetail();
        medication.setItemNumber(1);
        medication.setMedicationId(11L);

        DiagnosticSupportOrderDetail support = new DiagnosticSupportOrderDetail();
        support.setItemNumber(2);
        support.setDiagnosticSupportId(31L);

        order.setMedications(List.of(medication));
        order.setDiagnosticSupports(List.of(support));

        assertThrows(BusinessException.class, () -> service.createOrder(order, doctorUser()));
    }

    @Test
    void shouldFailWhenItemNumberIsRepeatedAcrossTypes() {
        CreateOrder service = new CreateOrder(new InMemoryOrderPort(), new StubPatientPort(), new StubUserPort(), new StubInventoryPort());

        MedicalOrder order = baseOrder();
        MedicationOrderDetail medication = new MedicationOrderDetail();
        medication.setItemNumber(1);
        medication.setMedicationId(11L);

        ProcedureOrderDetail procedure = new ProcedureOrderDetail();
        procedure.setItemNumber(1);
        procedure.setProcedureId(21L);

        order.setMedications(List.of(medication));
        order.setProcedures(List.of(procedure));

        assertThrows(BusinessException.class, () -> service.createOrder(order, doctorUser()));
    }

    @Test
    void shouldFailWhenSpecialistTypeIsMissingButRequired() {
        CreateOrder service = new CreateOrder(new InMemoryOrderPort(), new StubPatientPort(), new StubUserPort(), new StubInventoryPort());

        MedicalOrder order = baseOrder();
        DiagnosticSupportOrderDetail support = new DiagnosticSupportOrderDetail();
        support.setItemNumber(1);
        support.setDiagnosticSupportId(31L);
        support.setRequiresSpecialist(true);
        support.setSpecialistTypeId(null);
        order.setDiagnosticSupports(List.of(support));

        assertThrows(BusinessException.class, () -> service.createOrder(order, doctorUser()));
    }

    private MedicalOrder baseOrder() {
        MedicalOrder order = new MedicalOrder();
        order.setOrderNumber("123456");
        order.setPatientDocument("99999999");
        order.setDoctorDocument("12345");
        return order;
    }

    private User doctorUser() {
        User user = new User();
        user.setDocument("12345");
        user.setRole(Role.DOCTOR);
        return user;
    }

    private static class InMemoryOrderPort implements OrderPort {
        private final java.util.Set<String> orderNumbers = new java.util.HashSet<>();

        @Override
        public boolean existsByOrderNumber(String orderNumber) {
            return orderNumbers.contains(orderNumber);
        }

        @Override
        public MedicalOrder save(MedicalOrder order) {
            orderNumbers.add(order.getOrderNumber());
            return order;
        }
    }

    private static class StubPatientPort implements PatientPort {
        @Override
        public boolean existsByDocument(String cedula) {
            return true;
        }

        @Override
        public app.domain.models.Patient save(app.domain.models.Patient patient) {
            return patient;
        }
    }

    private static class StubUserPort implements UserPort {
        @Override
        public boolean existsByDocument(String cedula) {
            return true;
        }

        @Override
        public boolean existsByUserName(String userName) {
            return false;
        }

        @Override
        public void save(User user) {
        }
    }

    private static class StubInventoryPort implements InventoryPort {
        @Override
        public boolean existsMedicationById(long medicationId) {
            return medicationId == 11L;
        }

        @Override
        public boolean existsProcedureById(long procedureId) {
            return procedureId == 21L;
        }

        @Override
        public boolean existsDiagnosticSupportById(long diagnosticSupportId) {
            return diagnosticSupportId == 31L;
        }

        @Override
        public boolean existsSpecialistTypeById(long specialistTypeId) {
            return specialistTypeId == 41L;
        }
    }
}

package app.domain.services;

import app.domain.Exceptions.BusinessException;
import app.domain.models.Patient;
import app.domain.ports.ContactEmergencyPort;
import app.domain.ports.PatientPort;

public class CreatePatient {

    private PatientPort patientPort;
    private ContactEmergencyPort emergencyContactPort;
    private CreateEmergencyContact createEmergencyContact;

    public void createPatien(Patient patient) {

        if(patientPort.existsByDocument(patient.getDocument())) {
            throw new BusinessException("Ya existe un paciente con esa cedula");
        }

        if (!emergencyContactPort.existsByDocument(patient.getEmergencyContact().getDocument())) {
            createEmergencyContact.createEmergencyContact(patient.getEmergencyContact());
        }
        patientPort.save(patient);

    }
}

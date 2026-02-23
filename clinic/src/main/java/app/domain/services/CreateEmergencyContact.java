package app.domain.services;

import app.domain.Exceptions.BusinessException;
import app.domain.models.EmergencyContact;
import app.domain.ports.ContactEmergencyPort;

public class CreateEmergencyContact {

    private ContactEmergencyPort emergencyContactPort;

    public void createEmergencyContact(EmergencyContact emergencyContact){
        ///Aqui iria la logica para crear un contacto de emergencia
        if (emergencyContactPort.existsByDocument(emergencyContact.getDocument())) {
            throw new BusinessException("Ya exisye un contacto de emergencia con esa cedula");
        }
        emergencyContactPort.save(emergencyContact);
    }



}

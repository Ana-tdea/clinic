package app.domain.ports;

import app.domain.models.MedicalOrder;

public interface OrderPort {

    public boolean findByOrderNumber(String orderNumber);
    public MedicalOrder save(MedicalOrder order);

}

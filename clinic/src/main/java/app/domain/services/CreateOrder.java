package app.domain.services;


import app.domain.models.MedicalOrder;
import app.domain.ports.InventoryPort;
import app.domain.ports.OrderPort;

public class CreateOrder {

    private final OrderPort orderPort;
    private final InventoryPort inventoryPort;

    public CreateOrder(OrderPort orderPort, InventoryPort inventoryPort) {
        this.orderPort = orderPort;
        this.inventoryPort = inventoryPort;
    }

    public void createOrder(MedicalOrder medicalOrder) {
        /// Aquí iría la lógica de validación y persistencia
    }

}
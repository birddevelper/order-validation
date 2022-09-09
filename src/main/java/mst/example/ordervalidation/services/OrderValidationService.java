package mst.example.ordervalidation.services;


import mst.example.ordervalidation.models.ValidationError;
import mst.example.ordervalidation.models.ValidationRequest;
import mst.example.ordervalidation.models.ValidationResult;
import mst.example.ordervalidation.models.order.*;

import java.util.List;

public interface OrderValidationService {

    /**
     * This method validates the order based on its type
     * @param order The given order
     * @return ValidationResult
     */
    ValidationResult validateOrder(Order order);
}

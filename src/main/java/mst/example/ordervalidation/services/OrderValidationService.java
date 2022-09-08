package mst.example.ordervalidation.services;


import mst.example.ordervalidation.models.ValidationError;
import org.springframework.core.annotation.Order;

import java.util.List;

public interface OrderValidationService {

    /**
     * This method validates the order based on its type
     * @param order The given order
     * @param validationErrors Validation errors of the order will be appended to validationErrors List
     * @return true in case it founds no error, false in case it found error
     */
    boolean validateOrder(Order order, List<ValidationError> validationErrors);
}

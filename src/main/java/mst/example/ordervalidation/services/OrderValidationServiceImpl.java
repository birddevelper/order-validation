package mst.example.ordervalidation.services;

import mst.example.ordervalidation.models.ValidationError;
import mst.example.ordervalidation.models.ValidationRequest;
import mst.example.ordervalidation.models.order.*;
import mst.example.ordervalidation.models.ValidationResult;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderValidationServiceImpl implements OrderValidationService {

    /**
     * This method validates the order based on its type
     *
     * @param order            The given order
     * @return ValidationResult
     */
    @Override
    public ValidationResult validateOrder(Order order) {

        // validate the order with validate method of the object
        ValidationResult validationResult = order.validate();
        return validationResult;
    }
}

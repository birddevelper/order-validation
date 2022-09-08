package mst.example.ordervalidation.services;

import mst.example.ordervalidation.models.ValidationError;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderValidationServiceImpl implements OrderValidationService {

    /**
     * This method validates the order based on its type
     *
     * @param order            The given order
     * @param validationErrors Validation errors of the order will be appended to validationErrors List
     * @return true in case it founds no error, false in case it found error
     */
    @Override
    public boolean validateOrder(Order order, List<ValidationError> validationErrors) {
        return false;
    }
}

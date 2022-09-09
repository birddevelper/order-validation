package mst.example.ordervalidation.services;

import mst.example.ordervalidation.models.ValidationRequest;
import mst.example.ordervalidation.models.order.Order;

import java.util.List;

public interface ValidationRequestService {
    /**
     * This method stores the validation results into DB
     * @param validationRequest
     */
    void storeValidationRequest(ValidationRequest validationRequest);

    /**
     * This method retrieves all validation request history from the DB
     * @return List<ValidationRequest>
     */
    List<ValidationRequest> validationRequestHistory();

}

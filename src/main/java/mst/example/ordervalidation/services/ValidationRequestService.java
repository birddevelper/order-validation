package mst.example.ordervalidation.services;

import mst.example.ordervalidation.models.ValidationRequest;

import java.util.List;

public interface ValidationRequestService {

    void storeValidationRequest();

    List<ValidationRequest> validationRequestHistory();
}

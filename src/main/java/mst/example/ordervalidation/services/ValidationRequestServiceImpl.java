package mst.example.ordervalidation.services;

import mst.example.ordervalidation.models.ValidationRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ValidationRequestServiceImpl implements ValidationRequestService{
    @Override
    public void storeValidationRequest() {

    }

    @Override
    public List<ValidationRequest> validationRequestHistory() {
        return null;
    }
}

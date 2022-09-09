package mst.example.ordervalidation.services;

import mst.example.ordervalidation.models.ValidationRequest;
import mst.example.ordervalidation.repositories.ValidationRequestRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ValidationRequestServiceImpl implements ValidationRequestService{

    private ValidationRequestRepository validationRequestRepository;

    public ValidationRequestServiceImpl(ValidationRequestRepository validationRequestRepository) {
        this.validationRequestRepository = validationRequestRepository;
    }

    /**
     * This method stores the validation results into DB
     * @param validationRequest
     */
    @Override
    public void storeValidationRequest(ValidationRequest validationRequest) {
        // save validationRequest with the repository
        validationRequestRepository.save(validationRequest);
    }

    /**
     * This method retrieves all validation request history from the DB
     * @return List<ValidationRequest>
     */
    @Override
    public List<ValidationRequest> validationRequestHistory() {

        // query validationRequest history from db
        return validationRequestRepository.findAll();
    }


}

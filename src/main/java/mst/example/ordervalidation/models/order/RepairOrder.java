package mst.example.ordervalidation.models.order;

import mst.example.ordervalidation.models.ValidationError;

import java.util.Date;
import java.util.List;

public class RepairOrder extends Order{

    private Date analysisDate;
    private Date testDate;
    private String responsiblePerson;



    /**
     * This method applies validation related to specific type of the order and put all possible errors into the validationErrors,
     * if no error found, it returns true.
     *
     * @param validationErrors Validation errors of the order will be appended to validationErrors List
     * @return true in case it founds no error, false in case it found error
     */
    @Override
    boolean extendedValidation(List<ValidationError> validationErrors) {
        return false;
    }
}

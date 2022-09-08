package mst.example.ordervalidation.models.order;

import mst.example.ordervalidation.models.Part;
import mst.example.ordervalidation.models.ValidationError;

import java.util.Date;
import java.util.List;

public abstract class Order {

    private String type;
    private String department;
    private Date startDate;
    private Date endDate;
    private String currency;
    private float cost;
    private List<Part> parts;

    public boolean basicValidate(List<ValidationError> validationErrors){

        return true;
    }

    /**
     * This method applies validation related to specific type of the order and put all possible errors into the validationErrors,
     * if no error found, it returns true.
     * @param validationErrors Validation errors of the order will be appended to validationErrors List
     * @return true in case it founds no error, false in case it found error
     */
    abstract boolean extendedValidation(List<ValidationError>  validationErrors);


}

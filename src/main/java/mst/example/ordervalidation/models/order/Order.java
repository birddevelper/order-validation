package mst.example.ordervalidation.models.order;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.*;
import lombok.experimental.Accessors;
import mst.example.ordervalidation.models.Department;
import mst.example.ordervalidation.models.Part;
import mst.example.ordervalidation.models.ValidationError;
import mst.example.ordervalidation.models.ValidationResult;

import javax.money.CurrencyUnit;
import javax.money.Monetary;
import javax.money.UnknownCurrencyException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type", visible = true)
// Automatically cast the order to its exact subclass
@JsonSubTypes({
        @JsonSubTypes.Type(value = AnalysisOrder.class, name = "ANALYSIS"),
        @JsonSubTypes.Type(value = RepairOrder.class, name = "REPAIR"),
        @JsonSubTypes.Type(value = ReplacementOrder.class, name = "REPLACEMENT")
})
@Getter
@Setter
@Accessors(fluent = false, chain = true)
public abstract class Order {

    @JsonProperty("type")
    protected String type;

    @JsonProperty("department")
    protected String department;

    @JsonProperty("start_date")
    protected Date startDate;

    @JsonProperty("end_date")
    protected Date endDate;

    @JsonProperty("currency")
    protected String currency;

    @JsonProperty("cost")
    protected double cost;

    @JsonProperty("parts")
    protected List<Part> parts;

    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    @JsonIgnore
    protected boolean valid = true;

    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    @JsonIgnore
    protected List<ValidationError> validationErrors;

    /**
     * This method valid order against the basic fields including department, startDate, endDate, currency and cost.
     * It appends all errors into validationErrors list if any is found.
     * @return ValidationResult
     */
    protected ValidationResult basicValidate(){
        valid = true;
        validationErrors = new ArrayList<>();
        this.validateDepartment();
        this.validateStartDate();
        this.validateEndDate();
        this.validateCurrency();
        this.validateCost();

        return new ValidationResult(valid, validationErrors);
    }

    /**
     * This method applies validation related to specific type of the order and put all possible errors into the validationErrors,
     * if no error found, it returns true.
     *
     * @return true in case it founds no error, false in case it found error
     */
    public abstract ValidationResult validate();

    /**
     * This method validates department field of the order object
     */
    protected void validateDepartment(){
        if(this.department.length()==0){
            valid=false;
            validationErrors.add(new ValidationError("Department field is empty",
                    "Department field must not be empty and must be in valid departments list"));
            return;
        }

        if(!Department.exists(this.department)){
            valid=false;
            validationErrors.add(new ValidationError("Provided department is not valid",
                    "Department field must not be empty and must be in valid departments list"));
        }
    }

    /**
     * This method validates startDate field of the order object
     */
    protected void validateStartDate(){
        if(this.startDate == null){
            valid=false;
            validationErrors.add(new ValidationError("startDate field is empty",
                    "startDate field must not be empty, and must be before current date"));
            return;
        }

        Date currentDate = new Date();
        if(!this.startDate.before(currentDate)){
            valid=false;
            validationErrors.add(new ValidationError("startDate field is not before current date",
                    "startDate field must not be empty, and must be before current date"));
        }

    }

    /**
     * This method endDate department field of the order object
     */
    protected void validateEndDate(){
        if(this.endDate == null){
            valid=false;
            validationErrors.add(new ValidationError("endDate field is empty",
                    "endDate field must not be empty, and must be after startDate"));
            return;
        }

        if(this.startDate!=null)
            if(!this.endDate.after(this.startDate)){
                valid=false;
                validationErrors.add(new ValidationError("endDate field is not after startDate",
                        "endDate field must not be empty, and must be after startDate"));
            }

    }


    /**
     * This method validates currency field of the order object
     */
    protected void validateCurrency(){
        if( this.currency == null || this.currency.length()==0){
            valid=false;
            validationErrors.add(new ValidationError("currency field is empty",
                    "currency field must not be empty and must be valid iso currency code"));
            return;
        }


        try {
            Monetary.getCurrency(this.currency);
        } catch (UnknownCurrencyException e) {
            valid=false;
            validationErrors.add(new ValidationError("currency code is not valid iso currency code",
                    "currency field must not be empty and must be valid iso currency code"));
        }

    }

    /**
     * This method validates cost field of the order object
     */
    protected void validateCost(){
        if(this.cost<=0){
            valid=false;
            validationErrors.add(new ValidationError("cost is not greater than 0",
                    "cost must greater than 0"));

        }

    }



}

package mst.example.ordervalidation.models.order;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.experimental.Accessors;
import mst.example.ordervalidation.models.Part;
import mst.example.ordervalidation.models.ValidationError;
import mst.example.ordervalidation.models.ValidationResult;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
@Accessors(fluent = false, chain = true)
public class ReplacementOrder extends Order{

    @JsonProperty("factory_name")
    private String factoryName;

    @JsonProperty("factory_order_number")
    private String factoryOrderNumber;

    /**
     * This method applies validation related to Replacement type of the order and put all possible errors into the validationErrors,
     * if no error found, it returns true.
     *
     * @return true in case it founds no error, false in case it found error
     */
    @Override
    public ValidationResult validate() {
        this.basicValidate();
        this.validateFactoryName();
        this.validateFactoryOrderNumber();
        this.validateParts();

        return new ValidationResult(valid, validationErrors);

    }

    /**
     * This method validates factoryName field of the replacement order object
     */
    protected void validateFactoryName() {
        // if it is null or empty then change valid status to false and add error to the list
        if (this.factoryName == null || this.factoryName.equals("")) {
            valid = false;
            validationErrors.add(new ValidationError("factoryName field is empty",
                    "factoryName field must not be empty"));
            return;
        }
    }

    /**
     * This method validates factoryOrderNumber field of the replacement order object
     */
    protected void validateFactoryOrderNumber() {
        // if it is null then change valid status to false and add error to the list
        if (this.factoryOrderNumber == null) {
            valid = false;
            validationErrors.add(new ValidationError("factoryOrderNumber field is empty",
                    "factoryOrderNumber length is 10, first 2 characters - letters, others – numbers."));
            return;
        }

        // check if it matches the required pattern
        if (!this.factoryOrderNumber.matches("^[a-zA-Z]{2}[0-9]{8}$")) {
            valid = false;
            validationErrors.add(new ValidationError("factoryOrderNumber is invalid",
                    "factoryOrderNumber length is 10, first 2 characters - letters, others – numbers."));
            return;
        }

    }

    /**
     * This method validates parts field of the replacement order object
     */
    protected void validateParts(){
        // if it is null then change valid status to false and add error to the list
        if(this.parts == null){
            valid=false;
            validationErrors.add(new ValidationError("parts field is empty",
                    "parts field must not be empty, and total count must be greater than 0"));
            return;
        }

        int emptyInventoryNumberCount=0;
        for (Part part: this.parts) {
            if(part.getInventoryNumber()==null || part.getInventoryNumber().equals(""))
                emptyInventoryNumberCount++;
        }

        // if there exists any empty inventoryNumber change the valid state to false and add the error to the list
        if(emptyInventoryNumberCount>0){
            valid=false;
            validationErrors.add(new ValidationError(emptyInventoryNumberCount + " InventoryNumber(s) are empty",
                    "All inventory numbers must be not empty"));
        }
    }

}

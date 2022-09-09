package mst.example.ordervalidation.models.order;

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

    private String factoryName;
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

    protected void validateFactoryName() {
        if (this.factoryName == null || this.factoryName.equals("")) {
            valid = false;
            validationErrors.add(new ValidationError("factoryName field is empty",
                    "factoryName field must not be empty"));
            return;
        }
    }

    protected void validateFactoryOrderNumber() {
        if (this.factoryOrderNumber == null) {
            valid = false;
            validationErrors.add(new ValidationError("factoryOrderNumber field is empty",
                    "factoryOrderNumber length is 10, first 2 characters - letters, others – numbers."));
            return;
        }

        if (!this.factoryOrderNumber.matches("^[a-zA-Z]{2}[0-9]{8}$")) {
            valid = false;
            validationErrors.add(new ValidationError("factoryOrderNumber is invalid",
                    "factoryOrderNumber length is 10, first 2 characters - letters, others – numbers."));
            return;
        }

    }


    protected void validateParts(){
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
        if(emptyInventoryNumberCount>0){
            valid=false;
            validationErrors.add(new ValidationError(emptyInventoryNumberCount + " InventoryNumber(s) are empty",
                    "All inventory numbers must be not empty"));
        }
    }

}

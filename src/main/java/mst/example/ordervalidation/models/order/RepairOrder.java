package mst.example.ordervalidation.models.order;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.experimental.Accessors;
import mst.example.ordervalidation.models.Part;
import mst.example.ordervalidation.models.ValidationError;
import mst.example.ordervalidation.models.ValidationResult;

import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
@Accessors(fluent = false, chain = true)
public class RepairOrder extends Order{

    @JsonProperty("analysis_date")
    private Date analysisDate;

    @JsonProperty("test_date")
    private Date testDate;

    @JsonProperty("responsible_person")
    private String responsiblePerson;



    /**
     * This method applies validation related to Repair type of the order and put all possible errors into the validationErrors,
     * if no error found, it returns true.
     *
     * @return true in case it founds no error, false in case it found error
     */
    @Override
    public ValidationResult validate() {

        this.basicValidate();
        this.validateAnalysisDate();
        this.validateResponsiblePerson();
        this.validateTestDate();
        this.validateParts();

        return new ValidationResult(valid, validationErrors);
    }

    /**
     * This method validates AnalysisDate field of the repair order object
     */
    protected void validateAnalysisDate(){

        // if it is null then change the valid status to false and add error to the list
        if(this.analysisDate == null){
            valid=false;
            validationErrors.add(new ValidationError("analysisDate field is empty",
                    "analysisDate field must not be empty, and must be after startDate and before endDate"));
            return;
        }

        if(this.startDate!=null)
            if(!this.analysisDate.after(this.startDate) ){
                valid=false;
                validationErrors.add(new ValidationError("analysisDate is not valid",
                        "analysisDate field must not be empty, and must be after startDate and before endDate"));
            }

        if(this.endDate!=null)
            if(!this.analysisDate.before(this.endDate) ){
                valid=false;
                validationErrors.add(new ValidationError("analysisDate is not valid",
                        "analysisDate field must not be empty, and must be after startDate and before endDate"));
            }

    }

    /**
     * This method validates responsiblePerson field of the repair order object
     */
    protected void validateResponsiblePerson() {
        // if it is null or empty change valid status to false and add error to the list
        if (this.responsiblePerson == null || this.responsiblePerson.equals("")) {
            valid = false;
            validationErrors.add(new ValidationError("responsiblePerson field is empty",
                    "responsiblePerson field must not be empty"));
            return;
        }
    }

    /**
     * This method validates testDate field of the repair order object
     */
    protected void validateTestDate(){
        // if it is null then change valid status to false and add error to the list
        if(this.testDate == null){
            valid=false;
            validationErrors.add(new ValidationError("testDate field is empty",
                    "testDate field must not be empty, and must be after analysisDate and before endDate"));
            return;
        }

        // if analysisDate is not null, then check testDate with analysisDate
        if(this.analysisDate!=null) {
            if (!this.testDate.after(this.analysisDate)) {
                valid = false;
                validationErrors.add(new ValidationError("testDate is not valid",
                        "testDate field must not be empty, and must be after analysisDate and before endDate"));
            }
        }
        // if analysisDate is null, we can check the testDate with startDate
        else if(this.startDate!=null){
            if (!this.testDate.after(this.startDate)) {
                valid = false;
                validationErrors.add(new ValidationError("testDate is not valid",
                        "testDate field must not be empty, and must be after analysisDate and before endDate"));
            }
        }

        // if endDate is not null, then check testDate with endDate
        if(this.endDate!=null)
            if(!this.testDate.before(this.endDate) ){
                valid=false;
                validationErrors.add(new ValidationError("testDate is not valid",
                        "testDate field must not be empty, and must be after analysisDate and before endDate"));
            }



    }

    /**
     * This method validates parts field of the repair order object
     */
    protected void validateParts(){
        // if it is null then change valid status to false and add error to the list
        if(this.parts == null){
            valid=false;
            validationErrors.add(new ValidationError("parts field is empty",
                    "parts field must not be empty, and total count must be greater than 0"));
            return;
        }
        int totalCount=0;
        for (Part part: this.parts) {
            totalCount+=part.getCount();
        }

        // if total count of the parts is less than 1, then change valid status to false and add error to the list
        if(totalCount<1){
            valid=false;
            validationErrors.add(new ValidationError("parts total count is not greater than 0",
                    "parts field must not be empty, and total count must be greater than 0"));
        }
    }
}

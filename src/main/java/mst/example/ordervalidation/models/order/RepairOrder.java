package mst.example.ordervalidation.models.order;

import lombok.*;
import lombok.experimental.Accessors;
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

    private Date analysisDate;
    private Date testDate;
    private String responsiblePerson;



    /**
     * This method applies validation related to specific type of the order and put all possible errors into the validationErrors,
     * if no error found, it returns true.
     *
     * @return true in case it founds no error, false in case it found error
     */
    @Override
    public ValidationResult validate() {
        return null;
    }
}

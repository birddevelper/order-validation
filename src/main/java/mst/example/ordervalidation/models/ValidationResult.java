package mst.example.ordervalidation.models;

import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
/**
 * This class contains the validation result and possible errors list
 */
public class ValidationResult {

    private boolean isValid;
    private List<ValidationError> validationErrors;
}

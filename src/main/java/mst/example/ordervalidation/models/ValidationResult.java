package mst.example.ordervalidation.models;

import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class ValidationResult {

    private boolean isValid;
    private List<ValidationError> validationErrors;
}

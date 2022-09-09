package mst.example.ordervalidation.models.responseModel;

import lombok.*;
import mst.example.ordervalidation.models.ValidationResult;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class ValidationApiResponseModel {

    private String message;
    private String description;
    private ValidationResult validationResult;
}
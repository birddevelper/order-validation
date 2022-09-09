package mst.example.ordervalidation.models.responseModel;

import lombok.*;
import mst.example.ordervalidation.models.ValidationRequest;
import mst.example.ordervalidation.models.ValidationResult;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class ValidationHistoryApiResponseModel {

    private String message;
    private String description;
    private List<ValidationRequest> validationRequests;
}
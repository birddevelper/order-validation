package mst.example.ordervalidation.models;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class ValidationError {

    private String error;
    private String message;
}

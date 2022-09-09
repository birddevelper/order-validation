package mst.example.ordervalidation.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class Part {

    @JsonProperty("inventory_number")
    private String inventoryNumber;

    @JsonProperty("name")
    private String name;

    @JsonProperty("count")
    private int count;
}

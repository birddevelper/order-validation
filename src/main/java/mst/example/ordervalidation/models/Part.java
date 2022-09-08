package mst.example.ordervalidation.models;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class Part {

    private String inventoryNumber;
    private String name;
    private int count;
}

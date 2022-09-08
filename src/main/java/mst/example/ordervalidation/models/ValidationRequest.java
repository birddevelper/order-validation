package mst.example.ordervalidation.models;

import lombok.*;
import mst.example.ordervalidation.models.order.OrderStatus;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
@Entity
public class ValidationRequest {

    @Id
    private int Id;
    private Date requestDate;
    private String orderType;
    private String department;
    private OrderStatus status;
}

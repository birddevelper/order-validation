package mst.example.ordervalidation.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import mst.example.ordervalidation.models.order.OrderStatus;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private int Id;
    private Date requestDate;
    private String orderType;
    private String department;
    private OrderStatus status;

    public ValidationRequest(Date requestDate, String orderType, String department, OrderStatus status) {
        this.requestDate = requestDate;
        this.orderType = orderType;
        this.department = department;
        this.status = status;
    }
}

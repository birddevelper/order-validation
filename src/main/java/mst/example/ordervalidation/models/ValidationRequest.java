package mst.example.ordervalidation.models;

import mst.example.ordervalidation.models.order.OrderStatus;

import java.util.Date;

public class ValidationRequest {

    private Date requestDate;
    private String orderType;
    private String department;
    private OrderStatus status;
}

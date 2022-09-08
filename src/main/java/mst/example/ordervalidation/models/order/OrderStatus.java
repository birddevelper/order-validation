package mst.example.ordervalidation.models.order;

import com.fasterxml.jackson.annotation.JsonValue;

public enum OrderStatus {
    VALID("valid"),
    NOT_VALID("not valid");

    OrderStatus(String statusName) {
        statusName = statusName;
    }

    private String statusName;

    @JsonValue
    public String getStatusName() {
        return statusName;
    }
}

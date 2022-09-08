package mst.example.ordervalidation.controllers;

import mst.example.ordervalidation.models.order.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
public class OrderValidationController {

    @ResponseStatus(HttpStatus.OK)
    @PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public void doSomething(@RequestBody Order order) {

    }
}

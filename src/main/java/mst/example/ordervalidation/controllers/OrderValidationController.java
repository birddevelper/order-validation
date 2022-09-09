package mst.example.ordervalidation.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import mst.example.ordervalidation.models.ValidationRequest;
import mst.example.ordervalidation.models.ValidationResult;
import mst.example.ordervalidation.models.order.Order;
import mst.example.ordervalidation.models.order.OrderStatus;
import mst.example.ordervalidation.models.responseModel.ErrorResponseModel;
import mst.example.ordervalidation.models.responseModel.ValidationApiResponseModel;
import mst.example.ordervalidation.models.responseModel.ValidationHistoryApiResponseModel;
import mst.example.ordervalidation.services.OrderValidationServiceImpl;
import mst.example.ordervalidation.services.ValidationRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/validation")
@Tag(name = "Queries", description = "Endpoints to query the DB")
public class OrderValidationController {


    private OrderValidationServiceImpl orderValidationService;
    private ValidationRequestService validationRequestService;

    @Autowired
    public OrderValidationController(OrderValidationServiceImpl orderValidationService, ValidationRequestService validationRequestService) {
        this.orderValidationService = orderValidationService;
        this.validationRequestService = validationRequestService;
    }


    // - POST /api/queries/genresBestSellingMovies
    // (Gets an order and returns its validation result)
    @Operation(summary = "Gets an order and returns its validation result")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Validation done.",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ValidationApiResponseModel.class)) }),
            @ApiResponse(responseCode = "400", description = "Illegal input parameters",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponseModel.class))),
            @ApiResponse(responseCode = "500", description = "Internal Error",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponseModel.class))) })

    @PostMapping(value = "/validateOrder", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ValidationApiResponseModel> validateOrder(@RequestBody Order order) {

        ValidationApiResponseModel validationApiResponseModel = new ValidationApiResponseModel();
        validationApiResponseModel.setMessage("OK");
        validationApiResponseModel.setDescription("Validation done.");
        ValidationResult validationResult = orderValidationService.validateOrder(order);
        validationApiResponseModel.setValidationResult(validationResult);

        ValidationRequest validationRequest = new ValidationRequest(
                                            new Date(),
                                            order.getType(),
                                            order.getDepartment(),
                                            validationResult.isValid()? OrderStatus.VALID : OrderStatus.NOT_VALID);

        validationRequestService.storeValidationRequest(validationRequest);

        return new ResponseEntity(validationApiResponseModel, HttpStatus.OK);
    }


    @Operation(summary = "Returns all validation request history")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Validation history successfully retrieved.",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ValidationHistoryApiResponseModel.class)) }),
            @ApiResponse(responseCode = "400", description = "Illegal input parameters",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponseModel.class))),
            @ApiResponse(responseCode = "500", description = "Internal Error",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponseModel.class))) })
    @PostMapping(value = "/validationHistory", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ValidationHistoryApiResponseModel> validationRequestHistory(){

        List<ValidationRequest> validationRequests = validationRequestService.validationRequestHistory();

        // initialize the response
        ValidationHistoryApiResponseModel validationHistoryApiResponseModel = new ValidationHistoryApiResponseModel();
        validationHistoryApiResponseModel.setMessage("OK");
        validationHistoryApiResponseModel.setDescription("Validation done.");
        validationHistoryApiResponseModel.setValidationRequests(validationRequests);


        return new ResponseEntity(validationHistoryApiResponseModel, HttpStatus.OK);
    }


}

package mst.example.ordervalidation.controllers;

import com.fasterxml.jackson.databind.exc.InvalidTypeIdException;
import mst.example.ordervalidation.models.responseModel.ErrorResponseModel;
import mst.example.ordervalidation.models.responseModel.ValidationApiResponseModel;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;


// This class handles error and return appropriate response to client
@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class InvalidInputExceptionHelper {





    // handling IllegalArgumentException
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponseModel> handleIllegalArgumentException(IllegalArgumentException ex, WebRequest request) {


        ErrorResponseModel errorResponseModel = new ErrorResponseModel();
        errorResponseModel.setMessage("Illegal Input Parameter");
        errorResponseModel.setDescription(ex.getMessage());

        // send response with 400 status code
        return new ResponseEntity(errorResponseModel, HttpStatus.BAD_REQUEST);


    }

    // handling InvalidTypeIdException
    @ExceptionHandler(InvalidTypeIdException.class)
    public ResponseEntity<ErrorResponseModel> handleIInvalidTypeIdException(InvalidTypeIdException ex, WebRequest request) {


        ErrorResponseModel errorResponseModel = new ErrorResponseModel();
        errorResponseModel.setMessage("Input Parameter Error");
        errorResponseModel.setDescription(ex.getMessage());

        // send response with 400 status code
        return new ResponseEntity(errorResponseModel, HttpStatus.BAD_REQUEST);


    }






}
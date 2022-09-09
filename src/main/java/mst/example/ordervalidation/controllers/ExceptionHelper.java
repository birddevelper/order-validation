package mst.example.ordervalidation.controllers;

import mst.example.ordervalidation.models.responseModel.ErrorResponseModel;
import mst.example.ordervalidation.models.responseModel.ValidationApiResponseModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


// This class handles error and return appropriate response to client
@ControllerAdvice
public class ExceptionHelper {


    // handling IllegalArgumentException
    @ExceptionHandler(value = {IllegalArgumentException.class})
    public ResponseEntity<ErrorResponseModel> handleIllegalArgumentException(IllegalArgumentException ex) {


        ErrorResponseModel errorResponseModel = new ErrorResponseModel();
        errorResponseModel.setMessage("ERROR");
        errorResponseModel.setDescription(ex.getMessage());

        // send response with 400 status code
        return new ResponseEntity(errorResponseModel, HttpStatus.BAD_REQUEST);


    }


    // handling other Exception
    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity<ErrorResponseModel> handleException(Exception ex) {

        ErrorResponseModel errorResponseModel = new ErrorResponseModel();
        errorResponseModel.setMessage("ERROR");
        errorResponseModel.setDescription(ex.getMessage());

        // send response with 500 status code
        return new ResponseEntity<>(errorResponseModel, HttpStatus.INTERNAL_SERVER_ERROR);


    }

}
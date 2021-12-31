package com.example.car_park.exception;

import com.example.car_park.dto.ResponseModel;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author TuanNA
 * @Date 24/12/2021 11:33 AM
 * @Version 1.0
 */
@ControllerAdvice
public class ApiRequestException extends ResponseEntityExceptionHandler{

    @ExceptionHandler(MyCustomException.class)
    public ResponseEntity<?> mapException(MyCustomException e){
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        GeneralException generalException = new GeneralException();
        generalException.setHttpStatus(httpStatus);
        generalException.setErrorMessage(e.getMessage());
        return new ResponseEntity<>(generalException, httpStatus);
    }

//    @ExceptionHandler(BindException.class)
//    @ResponseStatus(HttpStatus.BAD_REQUEST)  // Nếu validate fail thì trả về 400
//    public String handleBindException(BindException e) {
//        // Trả về message của lỗi đầu tiên
//        String errorMessage = "Request không hợp lệ";
//        if (e.getBindingResult().hasErrors())
//            e.getBindingResult().getAllErrors().get(0).getDefaultMessage();
//        return errorMessage;
//    }


    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        Map<String, String> error = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((er)->{
            String fieldName = ((FieldError) er).getField();
            String message = er.getDefaultMessage();
            error.put(fieldName, message);
        });
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
}

package org.example.homework003.exception;

import org.apache.ibatis.javassist.NotFoundException;
import org.example.homework003.model.response.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
@RestControllerAdvice
public class GlobalException {
    @ExceptionHandler(NotFoundExceptions.class)
    public ProblemDetail handleUserNotFoundException(NotFoundExceptions e) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, e.getMessage());
        problemDetail.setTitle(" Not Found");
        problemDetail.setProperty("timestamp", LocalDateTime.now());
        return problemDetail;
    }
//@ExceptionHandler(NotFoundExceptions.class)
//    public ResponseEntity<?> handleNotFoundException(NotFoundExceptions e) {
//    return new ResponseEntity<>(new ErrorResponse(e.getMessage(), HttpStatus.NOT_FOUND), HttpStatus.NOT_FOUND );
//}


    @ExceptionHandler(NullPointerExceptino.class)
    public ProblemDetail handleNullPointer(NullPointerExceptino e) {
        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
        problemDetail.setTitle(" Not Found");
        problemDetail.setProperty("Error:", e.getMessage());
        problemDetail.setProperty("timestamp", LocalDateTime.now());
        return problemDetail;
    }
//@ExceptionHandler(NullPointerExceptino.class)
//    public ResponseEntity<?> handleNullPointerException(NullPointerExceptino e) {
//    return new ResponseEntity<>(new ErrorResponse(e.getMessage(), HttpStatus.BAD_REQUEST), HttpStatus.BAD_REQUEST );
//
//}
//@ExceptionHandler(NullPointerExceptino.class)
//public ProblemDetail handleNullPointer(NullPointerExceptino e) {
//    ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
//    problemDetail.setTitle("Invalid Input");
//
//    // Adding multiple error messages
//    problemDetail.setProperty("error",problemDetail);
//    problemDetail.setProperty("timestamp", LocalDateTime.now());
//
//    return problemDetail;
//}

}

package org.example.homework003.exception;

import org.apache.ibatis.javassist.NotFoundException;
import org.example.homework003.model.response.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.List;

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


//    @ExceptionHandler(NullPointerExceptino.class)
//    public ProblemDetail handleNullPointer(NullPointerExceptino e) {
//        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
//        problemDetail.setTitle(" Not Found");
//        problemDetail.setProperty("Error:", e.getMessage());
//        problemDetail.setProperty("timestamp", LocalDateTime.now());
//        return problemDetail;
//    }
    @ExceptionHandler(NullPointerExceptino.class)
    public ProblemDetail handleNullPointer(NullPointerExceptino e) {
        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
        problemDetail.setTitle("Bad Request");

        // Add error details as a map (to match the structure in your image)
        problemDetail.setProperty("errors", e.getErrorDetails());
        problemDetail.setProperty("timestamp", LocalDateTime.now());

        return problemDetail;
        /// ✅ Use e.getMessage() when you only need a single error message (e.g., "Invalid input").
        ///
        /// ✅ Use e.getErrorDetails() when you need structured validation errors for multiple fields (e.g
    }

}

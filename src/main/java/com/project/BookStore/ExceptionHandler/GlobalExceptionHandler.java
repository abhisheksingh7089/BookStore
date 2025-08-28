//package com.project.BookStore.ExceptionHandler;
//
//
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.MethodArgumentNotValidException;
//import org.springframework.web.bind.annotation.ControllerAdvice;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//
//import java.time.LocalDateTime;
//import java.util.HashMap;
//import java.util.Map;
//
//@ControllerAdvice
//public class GlobalExceptionHandler {
//
//    // Handle custom runtime exceptions (like the one you threw in addBook)
//    @ExceptionHandler(RuntimeException.class)
//    public ResponseEntity<Map<String, Object>> handleRuntimeException(RuntimeException ex) {
//        Map<String, Object> error = new HashMap<>();
//        error.put("timestamp", LocalDateTime.now());
//        error.put("status", HttpStatus.BAD_REQUEST.value());
//        error.put("error", "Runtime Exception");
//        error.put("message", ex.getMessage());
//
//        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
//    }
//    
//
//    // Handle validation errors (if you add @Valid in your DTOs later)
//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    public ResponseEntity<Map<String, Object>> handleValidationException(MethodArgumentNotValidException ex) {
//        Map<String, Object> error = new HashMap<>();
//        error.put("timestamp", LocalDateTime.now());
//        error.put("status", HttpStatus.BAD_REQUEST.value());
//        error.put("error", "Validation Failed");
//
//        // Get the first validation error message
//        String message = ex.getBindingResult().getFieldError().getDefaultMessage();
//        error.put("message", message);
//
//        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
//    }
//
//    // Catch all other exceptions
//    @ExceptionHandler(Exception.class)
//    public ResponseEntity<Map<String, Object>> handleGenericException(Exception ex) {
//        Map<String, Object> error = new HashMap<>();
//        error.put("timestamp", LocalDateTime.now());
//        error.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
//        error.put("error", "Internal Server Error");
//        error.put("message", ex.getMessage());
//
//        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
//    }
//}

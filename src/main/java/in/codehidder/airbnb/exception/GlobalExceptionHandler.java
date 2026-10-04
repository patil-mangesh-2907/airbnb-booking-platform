package in.codehidder.airbnb.exception;

import in.codehidder.airbnb.dto.ApiResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException e,
                                                                             HttpServletRequest request) {

        Map<String, String> errors = new HashMap<>();

        e.getBindingResult().getFieldErrors().stream()
                .forEach(err -> errors.put(err.getField(), err.getDefaultMessage()));


        ApiResponse apiResponse = new ApiResponse(
                false,
                "Validation failed",
                HttpStatus.BAD_REQUEST.value(),
                errors,
                LocalDateTime.now(),
                request.getRequestURI()

        );

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(apiResponse);
    }

    @ExceptionHandler(DuplicateHotelContactException.class)
    public ResponseEntity<ApiResponse> handleDuplicateHotelContactException(DuplicateHotelContactException e,
                                                                            HttpServletRequest request) {
        ApiResponse apiResponse = new ApiResponse(
                false,
                e.getMessage(),
                HttpStatus.CONFLICT.value(),
                null,
                LocalDateTime.now(),
                request.getRequestURI()
        );

        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(apiResponse);
    }

    @ExceptionHandler(HotelNotFoundException.class)
    public ResponseEntity<ApiResponse> handleHotelNotFoundException(HotelNotFoundException e,
                                                                    HttpServletRequest request) {
        ApiResponse apiResponse = new ApiResponse(
                false,
                e.getMessage(),
                HttpStatus.NOT_FOUND.value(),
                null,
                LocalDateTime.now(),
                request.getRequestURI()
        );

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(apiResponse);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ApiResponse> handleRuntimeException(RuntimeException e,
                                                              HttpServletRequest request) {
        ApiResponse apiResponse = new ApiResponse(
                false,
                e.getMessage(),
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                null,
                LocalDateTime.now(),
                request.getRequestURI()
        );

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(apiResponse);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse> handleGlobalException(Exception e,
                                                              HttpServletRequest request) {
        ApiResponse apiResponse = new ApiResponse(
                false,
                e.getMessage(),
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                null,
                LocalDateTime.now(),
                request.getRequestURI()
        );

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(apiResponse);
    }
}

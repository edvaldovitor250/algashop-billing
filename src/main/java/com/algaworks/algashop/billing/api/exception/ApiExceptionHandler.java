package com.algaworks.algashop.billing.api.exception;

import java.time.OffsetDateTime;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.algaworks.algashop.billing.domain.model.DomainException;
import com.algaworks.algashop.billing.domain.model.DomainEntityNotFoundException;
import com.algaworks.algashop.billing.domain.model.integration.BadGatewayException;
import com.algaworks.algashop.billing.domain.model.integration.GatewayTimeoutException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApiExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(ApiExceptionHandler.class);

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ProblemDetail> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
        problemDetail.setTitle("Invalid request");
        problemDetail.setDetail("One or more fields are invalid. Fill correctly and try again.");

        List<Map<String, Object>> fields = ex.getBindingResult().getFieldErrors().stream()
                .map(this::toField)
                .toList();

        Map<String, Object> props = new LinkedHashMap<>();
        props.put("timestamp", OffsetDateTime.now());
        props.put("fields", fields);
        problemDetail.setProperties(props);

        return ResponseEntity.badRequest().body(problemDetail);
    }

    @ExceptionHandler(DomainEntityNotFoundException.class)
    public ResponseEntity<ProblemDetail> handleDomainEntityNotFound(DomainEntityNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(basicProblem(HttpStatus.NOT_FOUND, "Resource not found", ex.getMessage()));
    }

    @ExceptionHandler(DomainException.class)
    public ResponseEntity<ProblemDetail> handleDomainException(DomainException ex) {
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY)
                .body(basicProblem(HttpStatus.UNPROCESSABLE_ENTITY, "Business rule violation", ex.getMessage()));
    }

    @ExceptionHandler(GatewayTimeoutException.class)
    public ResponseEntity<ProblemDetail> handleGatewayTimeout(GatewayTimeoutException ex) {
        log.error("Payment gateway timeout", ex);
        return ResponseEntity.status(HttpStatus.GATEWAY_TIMEOUT)
                .body(basicProblem(HttpStatus.GATEWAY_TIMEOUT, "Payment gateway timeout", ex.getMessage()));
    }

    @ExceptionHandler(BadGatewayException.class)
    public ResponseEntity<ProblemDetail> handleBadGateway(BadGatewayException ex) {
        log.error("Payment gateway failure", ex);
        return ResponseEntity.status(HttpStatus.BAD_GATEWAY)
                .body(basicProblem(HttpStatus.BAD_GATEWAY, "Payment gateway failure", ex.getMessage()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ProblemDetail> handleUncaught(Exception ex) {
        log.error("Unhandled error", ex);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(basicProblem(HttpStatus.INTERNAL_SERVER_ERROR, "Internal server error", "An unexpected internal error occurred."));
    }

    private ProblemDetail basicProblem(HttpStatusCode status, String title, String detail) {
        ProblemDetail problemDetail = ProblemDetail.forStatus(status);
        problemDetail.setTitle(title);
        if (detail != null && !detail.isBlank()) {
            problemDetail.setDetail(detail);
        }

        Map<String, Object> props = new LinkedHashMap<>();
        props.put("timestamp", OffsetDateTime.now());
        problemDetail.setProperties(props);

        return problemDetail;
    }

    private Map<String, Object> toField(FieldError fieldError) {
        Map<String, Object> field = new LinkedHashMap<>();
        field.put("name", fieldError.getField());
        field.put("message", fieldError.getDefaultMessage());
        return field;
    }
}

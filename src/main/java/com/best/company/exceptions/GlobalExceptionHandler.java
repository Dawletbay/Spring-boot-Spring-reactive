package com.best.company.exceptions;

import com.best.company.service.LocalizationService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ServerWebExchange;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Powered by: Dawletbay Tilepbaev
 * Date: 08.04.2022 13:05
 */

@RestControllerAdvice
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @Autowired
    private LocalizationService localizationService;

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorDTO> appGlobalExceptionHandler(NotFoundException ex, ServerWebExchange request) {
        return new ResponseEntity<>(ErrorDTO.builder().title(ex.getTitle()).detail(ex.getMessage()).status(ex.getStatus().value()).path(request.getRequest().getURI().getPath()).timestamp(LocalDateTime.now().toString()).build(), ex.getStatus());
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ErrorDTO> appGlobalExceptionHandler(BadRequestException ex, ServerWebExchange request) {
        return new ResponseEntity<>(ErrorDTO.builder().title(ex.getTitle()).detail(ex.getMessage()).status(ex.getStatus().value()).path(request.getRequest().getURI().getPath()).timestamp(LocalDateTime.now().toString()).build(), ex.getStatus());
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDTO> genericExceptionHandler(Exception ex, ServerWebExchange request) {
        return new ResponseEntity<>(
                ErrorDTO.builder()
                        .title(localizationService.localize("unexpected.error.has.been.occurred"))
                        .detail(localizationService.localize("unexpected.error.has.been.occurred"))
                        .status(HttpStatus.BAD_REQUEST.value())
                        .path(request.getRequest().getURI().getPath())
                        .timestamp(LocalDateTime.now().toString())
                        .build(),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorDTO> runtimeExceptionHandler(RuntimeException ex, ServerWebExchange request) {
        return new ResponseEntity<>(ErrorDTO
                .builder()
                .title(localizationService.localize("unexpected.error.has.been.occurred"))
                .detail(localizationService.localize("unexpected.error.has.been.occurred"))
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .path(request.getRequest().getURI().getPath())
                .timestamp(LocalDateTime.now().toString())
                .build(),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<ErrorDTO> nullPointerExceptionHandler(NullPointerException ex, ServerWebExchange request) {
        StringBuilder detailMessage = new StringBuilder();
        detailMessage.append("NULL POINTER EXCEPTION: ");
        detailMessage.append(Optional.ofNullable(ex.getMessage()).orElse(""));
        detailMessage.append(Arrays.stream(ex.getStackTrace()).map(StackTraceElement::toString).filter(s -> s.contains("uz.smartbox.jobsterjse"))
                .map(s -> s.replaceAll("[<,>]", "")).collect(Collectors.joining(" \n ")));
        return new ResponseEntity<>(ErrorDTO.builder().title(localizationService.localize("value.null.error")).detail(ex.getMessage()).status(HttpStatus.INTERNAL_SERVER_ERROR.value()).path(request.getRequest().getURI().getPath()).timestamp(LocalDateTime.now().toString()).build(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<ErrorDTO> authenticationExceptionHandler(Exception ex, ServerWebExchange request) {
        return new ResponseEntity<>(ErrorDTO.builder()
                .title(ex.getMessage())
                .status(HttpStatus.UNAUTHORIZED.value())
                .path(request.getRequest().getURI().getPath())
                .timestamp(LocalDateTime.now().toString())
                .build(),
                HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(BindException.class)
    public ResponseEntity<ErrorDTO> validationExceptionHandler(Exception ex, ServerWebExchange request) {
        if (ex instanceof MethodArgumentNotValidException) {
            BindingResult result = ((MethodArgumentNotValidException) ex).getBindingResult();
            List<FieldErrorVM> fieldErrors = result.getFieldErrors().stream().map(f -> new FieldErrorVM(f.getObjectName().replaceFirst("DTO$", ""), f.getField(), StringUtils.isNotBlank(f.getDefaultMessage()) ? f.getDefaultMessage() : f.getCode())).collect(Collectors.toList());

            return new ResponseEntity<>(
                    ErrorDTO
                            .builder()
                            .title(localizationService.localize("method.argument.invalid.error"))
                            .detail(localizationService.localize("method.argument.invalid.error"))
                            .status(HttpStatus.BAD_REQUEST.value())
                            .path(request.getRequest().getURI().getPath())
                            .timestamp(LocalDateTime.now().toString())
                            .errorFields(fieldErrors).build(),
                    HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(ErrorDTO.builder().title(ex.getMessage()).status(HttpStatus.BAD_REQUEST.value()).path(request.getRequest().getURI().getPath()).timestamp(LocalDateTime.now().toString()).build(), HttpStatus.BAD_REQUEST);
    }

}

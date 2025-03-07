package com.qr.advice;

import com.qr.exception.ConflictException;
import com.qr.exception.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.format.DateTimeFormatter;

@ControllerAdvice(basePackages = "com.qr")
public class ErrorHandler {
    private final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String handleExceptions(final RuntimeException e, Model model) {
        String errorMessage = "Произошла неизвестная ошибка при обработке вашего запроса.";
        model.addAttribute("errorMessage", errorMessage);
        return "error";
    }


    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleExceptions(final IllegalArgumentException e, Model model) {
        String errorMessage = "Произошла неизвестная ошибка при обработке вашего запроса.";
        model.addAttribute("errorMessage", errorMessage);
        return "error";
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleExceptions(final NotFoundException e, Model model) {
        String errorMessage = "Запрашиваемая коробка отсутствует";
        model.addAttribute("errorMessage", errorMessage);
        return "error";
    }

    @ExceptionHandler(ConflictException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public String handleExceptions(final ConflictException e, Model model) {
        String errorMessage = "Произошла неизвестная ошибка при обработке вашего запроса.";
        model.addAttribute("errorMessage", errorMessage);
        return "error";
    }
}

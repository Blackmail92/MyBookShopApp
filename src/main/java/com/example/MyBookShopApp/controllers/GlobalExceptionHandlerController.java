package com.example.MyBookShopApp.controllers;

import com.example.MyBookShopApp.errors.EmptySearchException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@ControllerAdvice
public class GlobalExceptionHandlerController {
    private final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandlerController.class);

    @ExceptionHandler(EmptySearchException.class)
    public String handle(EmptySearchException ex, RedirectAttributes attr) {
        logger.warn(ex.getLocalizedMessage());
        attr.addFlashAttribute("searchError", ex);
        return "redirect:/books";
    }
}

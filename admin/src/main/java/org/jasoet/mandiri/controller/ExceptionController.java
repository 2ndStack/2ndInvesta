package org.jasoet.mandiri.controller;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;

/**
 * User: Deny Prasetyo, S.T.
 * http://www.jasoet.com
 */
@Controller
public class ExceptionController {

    @ExceptionHandler
    public String handle(Exception e, HttpServletRequest request) {
        request.setAttribute("message", e.getMessage());
        return "exception/parent";
    }

    @ExceptionHandler()
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public String notFound(HttpServletRequest request) {
        request.setAttribute("message", "Page that you looking for is Not Found");
        return "exception/parent";
    }


}

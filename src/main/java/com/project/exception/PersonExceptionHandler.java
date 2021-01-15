package com.project.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class PersonExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(PersonException.class)
    public final ResponseEntity<String> handlePersonException(PersonException personException,
                                                              WebRequest request){
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        if(personException.getError() == PersonException.PersonErrors.PERSON_NOT_FOUND){
            status=HttpStatus.NOT_FOUND;
        }
        else if(personException.getError() ==PersonException.PersonErrors.PERSON_WITH_SAME_EMAIL_ALREADY_EXISTS){
            status=HttpStatus.BAD_REQUEST;
        }
        else if(personException.getError()==PersonException.PersonErrors.PERSON_COULD_NOT_BE_SAVED){
            status=HttpStatus.BAD_REQUEST;
        }
        else if(personException.getError()==PersonException.PersonErrors.PERSON_COULD_NOT_BE_REMOVED){
            status=HttpStatus.NOT_ACCEPTABLE;
        }
        return new ResponseEntity<String >(personException.getError().name(),status);
    }

}

package com.project.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class AnimalExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(AnimalException.class)
    public final ResponseEntity<String> handleAnimalException(AnimalException animalException,
                                                              WebRequest request){
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        if(animalException.getError() == AnimalException.AnimalErrors.ANIMAL_NOT_FOUND){
            status=HttpStatus.NOT_FOUND;
        }
        else if(animalException.getError() ==AnimalException.AnimalErrors.ANIMAL_COULD_NOT_BE_REMOVED){
            status=HttpStatus.BAD_REQUEST;
        }
        else if(animalException.getError()==AnimalException.AnimalErrors.ANIMAL_COULD_NOT_BE_SAVED){
            status=HttpStatus.BAD_REQUEST;
        }
        else if(animalException.getError()==AnimalException.AnimalErrors.ANIMAL_WITH_SAME_NAME_ALREADY_EXISTS){
            status=HttpStatus.NOT_ACCEPTABLE;
        }
        return new ResponseEntity<String >(animalException.getError().name(),status);
    }

}

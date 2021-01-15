package com.project.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class AdoptionExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(AdoptionException.class)
    public final ResponseEntity<String> handleAdoptionException(AdoptionException adoptionException,
                                                                WebRequest request) {
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        if (adoptionException.getError() == AdoptionException.AdoptionErrors.ANIMAL_IS_ALREADY_ADOPTED) {
            status = HttpStatus.FORBIDDEN;
        } else if (adoptionException.getError() == AdoptionException.AdoptionErrors.ADOPTION_COULD_NOT_BE_REMOVED) {
            status = HttpStatus.BAD_REQUEST;
        } else if (adoptionException.getError() == AdoptionException.AdoptionErrors.ADOPTION_COULD_NOT_BE_SAVED) {
            status = HttpStatus.BAD_REQUEST;
        } else if (adoptionException.getError() == AdoptionException.AdoptionErrors.ADOPTION_NOT_FOUND) {
            status = HttpStatus.NOT_FOUND;
        }
        else if (adoptionException.getError() == AdoptionException.AdoptionErrors.PERSON_NOT_FOUND) {
            status = HttpStatus.NOT_FOUND;
        }
        else if (adoptionException.getError() == AdoptionException.AdoptionErrors.ADMIN_NOT_FOUND) {
            status = HttpStatus.NOT_FOUND;
        }
        else if (adoptionException.getError() == AdoptionException.AdoptionErrors.VET_NOT_FOUND) {
            status = HttpStatus.NOT_FOUND;
        }
        return new ResponseEntity<String>(adoptionException.getError().name(), status);
    }
}

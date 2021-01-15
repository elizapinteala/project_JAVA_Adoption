package com.project.exception;

public class AdoptionException extends RuntimeException{

    public enum AdoptionErrors{
        ANIMAL_IS_ALREADY_ADOPTED,
        ADOPTION_COULD_NOT_BE_SAVED,
        ADOPTION_COULD_NOT_BE_REMOVED,
        ADOPTION_NOT_FOUND,
        PERSON_NOT_FOUND,
        ADMIN_NOT_FOUND,
        VET_NOT_FOUND
    }

    private AdoptionErrors error;

    public AdoptionException(AdoptionErrors error) {
        this.error = error;
    }

    public AdoptionErrors getError() {
        return error;
    }

    @Override
    public String toString() {
        return error.name().toUpperCase();
    }

    public static AdoptionException animalIsAlreadyAdopted(){
        return new AdoptionException(AdoptionErrors.ANIMAL_IS_ALREADY_ADOPTED);
    }

    public static AdoptionException adoptionCouldNotBeSaved(){
        return new AdoptionException(AdoptionErrors.ADOPTION_COULD_NOT_BE_SAVED);
    }

    public static AdoptionException adoptionCouldNotBeRemoved(){
        return new AdoptionException(AdoptionErrors.ADOPTION_COULD_NOT_BE_REMOVED);
    }

    public static AdoptionException adoptionNotFound(){
        return new AdoptionException(AdoptionErrors.ADOPTION_NOT_FOUND);
    }

    public static AdoptionException personNotFound(){
        return new AdoptionException(AdoptionErrors.PERSON_NOT_FOUND);
    }

    public static AdoptionException adminNotFound(){
        return new AdoptionException(AdoptionErrors.ADMIN_NOT_FOUND);
    }
    public static AdoptionException vetNotFound(){
        return new AdoptionException(AdoptionErrors.VET_NOT_FOUND);
    }
}

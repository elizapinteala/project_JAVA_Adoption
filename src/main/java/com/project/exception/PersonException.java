package com.project.exception;

public class PersonException extends RuntimeException{

    public enum PersonErrors{
        PERSON_NOT_FOUND,
        PERSON_WITH_SAME_EMAIL_ALREADY_EXISTS,
        PERSON_COULD_NOT_BE_SAVED,
        PERSON_COULD_NOT_BE_REMOVED
    }

    private PersonErrors error;

    private PersonException(PersonErrors error) {this.error = error;}

    public PersonErrors getError(){return error;}

    @Override
    public String toString() {
        return error.name().toUpperCase();
    }

    public static PersonException personNotFound(){
        return new PersonException(PersonErrors.PERSON_NOT_FOUND);
    }

    public static PersonException personWithSameEmailAlreadyExists(){
        return new PersonException(PersonErrors.PERSON_WITH_SAME_EMAIL_ALREADY_EXISTS);
    }

    public static PersonException personCouldNotBeSaved(){
        return new PersonException(PersonErrors.PERSON_COULD_NOT_BE_SAVED);
    }

    public static PersonException personCouldNotBeRemoved(){
        return new PersonException(PersonErrors.PERSON_COULD_NOT_BE_REMOVED);
    }

}

package com.project.exception;

public class AnimalException extends RuntimeException {

    public enum AnimalErrors{
        ANIMAL_NOT_FOUND,
        ANIMAL_WITH_SAME_NAME_ALREADY_EXISTS,
        ANIMAL_COULD_NOT_BE_SAVED,
        ANIMAL_COULD_NOT_BE_REMOVED
    }

    private AnimalErrors error;

    private AnimalException(AnimalErrors error){
        this.error=error;
    }

    public AnimalErrors getError(){return error;}

    @Override
    public String toString() {
        return error.name().toUpperCase();
    }

    public static AnimalException animalNotFound(){

        return new AnimalException(AnimalErrors.ANIMAL_NOT_FOUND) ;
    }

    public static AnimalException animalWithSameNameAlreadyExists(){
        return new AnimalException(AnimalErrors.ANIMAL_WITH_SAME_NAME_ALREADY_EXISTS);
    }

    public static AnimalException animalCouldNotBeSaved(){
        return new AnimalException(AnimalErrors.ANIMAL_COULD_NOT_BE_SAVED);
    }

    public static AnimalException animalCouldNotBeRemoved(){
        return new AnimalException(AnimalErrors.ANIMAL_COULD_NOT_BE_REMOVED);
    }

}

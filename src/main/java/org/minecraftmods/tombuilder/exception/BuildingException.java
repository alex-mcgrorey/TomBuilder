package org.minecraftmods.tombuilder.exception;

public class BuildingException extends Exception{

    private String errorMessage;

    public BuildingException(String errorMessage){
        this.errorMessage = errorMessage;
    }
    public String getErrorMessage() {
        return errorMessage;
    }

}

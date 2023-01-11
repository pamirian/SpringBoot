package com.pamirian.todo.validation;

import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;

public class ToDoValidationErrorBuilder {
    public static ToDoValidationError fromBindingError(Errors errors){
        ToDoValidationError error = new ToDoValidationError("Validation failed" + errors.getErrorCount() + "error(s)");
        for(ObjectError objectError: errors.getAllErrors()){
            error.addValidationError(objectError.getDefaultMessage());
        }
        return error;
    }
}

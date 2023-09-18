package com.wellington.test.fruits.intercept.exception;

import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Path;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

import java.util.HashMap;

@Provider
public class ConstraintViolationHandler implements ExceptionMapper<ConstraintViolationException> {

    @Override
    public Response toResponse(ConstraintViolationException constraintViolation) {
        var violations = constraintViolation.getConstraintViolations();
        var validationErrorMessage = new HashMap<String, String>(violations.size());

        for (var violation : violations) {
            validationErrorMessage.put(getPath(violation.getPropertyPath()), violation.getMessage());
        }

        return Response.status(Response.Status.BAD_REQUEST).entity(validationErrorMessage).build();
    }

    private String getPath(Path path) {
        var propertyPath = path.toString();
        return propertyPath.substring(propertyPath.lastIndexOf(".") + 1);
    }
}
package com.trendyol.common.model.resource;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor(access = AccessLevel.NONE)
public class ValidationMessageResource {
    private boolean isValid;
    private String message;

    public static ValidationMessageResource buildError(String message){
        ValidationMessageResource validationErrorResource = new ValidationMessageResource();
        validationErrorResource.setValid(false);
        validationErrorResource.setMessage(message);
        return validationErrorResource;
    }

    public static ValidationMessageResource buildSuccess(String message){
        ValidationMessageResource validationErrorResource = new ValidationMessageResource();
        validationErrorResource.setValid(true);
        validationErrorResource.setMessage(message);
        return validationErrorResource;
    }

    public static ValidationMessageResource buildSuccess(){
        return buildSuccess("success");
    }
}

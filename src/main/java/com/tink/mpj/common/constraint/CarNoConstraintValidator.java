/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tink.mpj.common.constraint;

import com.tink.mpj.common.logging.Loggable;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 *
 * @author shearer
 */
@Loggable
public class CarNoConstraintValidator implements ConstraintValidator<CarNo, String> {
    
    @Override
    public void initialize(CarNo constraintAnnotation) {
        //throw new UnsupportedOperationException("Not supported yet.");
    }
    
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if(value == null || value.equals("")){
            return true;
        }
        
        if(value.length() != 4){
            return false;
        }
        
        return true;
    }
}
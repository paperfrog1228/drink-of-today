package paperfrog.validator;

import org.thymeleaf.util.StringUtils;
import paperfrog.annotation.NoSpace;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class SpaceValidator implements ConstraintValidator<NoSpace, String> {
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        boolean ret=true;
        for(int i=0;i<value.length();i++) {
            if (value.charAt(i)== ' ') {
                ret=false;
                break;
            }
        }
        return ret;
    }
}

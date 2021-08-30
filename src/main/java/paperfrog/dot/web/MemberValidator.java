package paperfrog.validator;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import paperfrog.dot.domain.Member;
import paperfrog.dot.domain.MemberSaveForm;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
@Component
public class MemberValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return Member.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Member member = (Member)target;
        Pattern pattern = Pattern.compile("^[a-zA-Z0-9]*$");
        Matcher matcher=pattern.matcher(member.getLoginId());
        if(matcher.find())
            errors.rejectValue("loginID", "loginId.eng");
    }
}

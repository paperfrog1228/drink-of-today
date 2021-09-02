package paperfrog.dot.web;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.bind.MethodArgumentNotValidException;
import paperfrog.dot.domain.Member;
import paperfrog.dot.domain.MemberSaveForm;
import paperfrog.dot.repository.MemberRepository;

import javax.validation.ConstraintViolation;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
@Component
@Slf4j
@RequiredArgsConstructor
public class MemberValidator implements Validator {
    private final MemberRepository memberRepository;
    @Override
    public boolean supports(Class<?> clazz) {
        return MemberSaveForm.class.isAssignableFrom(clazz);
    }
    Set<ConstraintViolation<MemberSaveForm>> errors;
    @Override
    public void validate(Object target, Errors errors) {
        MemberSaveForm member = (MemberSaveForm) target;
        if(!loginIdPatternMatching(member.getLoginId())){
            errors.rejectValue("loginId","Eng");
        }
        if(!member.getEmail().equals(member.getEmailConfirm())){
            errors.rejectValue("emailConfirm","Confirm");
        }
        if(isDuplicatedLoginId(member.getLoginId())){
            errors.rejectValue("loginId","Duplicate");
        }
        if(isDuplicatedNickname(member.getNickname())){
            errors.rejectValue("nickname","Duplicate");
        }
        if(isDuplicatedEmail(member.getEmail())){
            errors.rejectValue("email","Duplicate");
        }
    }

    private boolean loginIdPatternMatching(String loginId){
        Pattern pattern = Pattern.compile("^[a-zA-Z0-9]*$");
        Matcher matcher=pattern.matcher(loginId);
        if(!matcher.find()) return false;
        return true;
    }
    private boolean isDuplicatedLoginId(String loginId){
        List<Member> list = memberRepository.findAll();
        for (Member m:list) {
            if(m.getLoginId().equals(loginId))
                return true;
        }
        return false;
    }
    private boolean isDuplicatedNickname(String nickname){

        List<Member> list = memberRepository.findAll();
        for (Member m:list) {
            if(m.getNickname().equals(nickname))
                return true;
        }
        return false;
    }
    private boolean isDuplicatedEmail(String email){
        List<Member> list = memberRepository.findAll();
        for (Member m:list) {
            if(m.getEmail().equals(email))
                return true;
        }
        return false;
    }

}

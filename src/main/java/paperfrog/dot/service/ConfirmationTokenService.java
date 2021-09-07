package paperfrog.dot.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;
import paperfrog.dot.domain.ConfirmationToken;
import paperfrog.dot.repository.ConfirmationTokenRepository;
import paperfrog.dot.web.EmailConfirmException;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ConfirmationTokenService {
    private final ConfirmationTokenRepository confirmationTokenRepository;
    private final EmailService emailService;
    @Value("${homepage.url}")
    private String homepageUrl;
    public String createEmailConfirmationToken(Long memberId, String receiverEmail){
        ConfirmationToken emailConfirmationToken = ConfirmationToken.createEmailConfirmationToken(memberId);
        confirmationTokenRepository.save(emailConfirmationToken);
        SimpleMailMessage mailMessage = new SimpleMailMessage();

        mailMessage.setTo(receiverEmail);
        mailMessage.setSubject("오늘의 술상 회원가입 이메일 인증 안내");
        mailMessage.setText(homepageUrl+"/user/confirm_email?token="+emailConfirmationToken.getId()+"\n링크로 접속해 인증을 완료해주세요");
        emailService.sendEmail(mailMessage);
        return emailConfirmationToken.getId();
    }

    public ConfirmationToken findById(String confirmationTokenId){
        ConfirmationToken confirmationToken = confirmationTokenRepository.findById(confirmationTokenId);
        return confirmationToken;
    }
    public void expireToken(String tokenId){
        ConfirmationToken token = confirmationTokenRepository.findById(tokenId);
        try{
            token.isExpired();
        }
        catch (NullPointerException e){
            throw e;
        }
//        if(token.isExpired()) throw new EmailConfirmException("이미 만료된 토큰 입니다 토큰 uuid : "+tokenId);
        token.expireToken();
    }
}

package paperfrog.dot.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;
import paperfrog.dot.domain.ConfirmationToken;
import paperfrog.dot.repository.ConfirmationTokenRepository;

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
        mailMessage.setText(homepageUrl+"/confirm-email?token="+emailConfirmationToken.getId());
        emailService.sendEmail(mailMessage);
        return emailConfirmationToken.getId();
    }

    public ConfirmationToken findById(String confirmationTokenId){
        ConfirmationToken confirmationToken = confirmationTokenRepository.findById(confirmationTokenId);
        return confirmationToken;
    }
    public void expireToken(String tokenId){
        confirmationTokenRepository.expireToken(tokenId);
    }
}

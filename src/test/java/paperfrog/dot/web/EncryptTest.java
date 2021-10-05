package paperfrog.dot.web;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import paperfrog.dot.domain.Member;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

class EncryptTest {
    @Test
    @DisplayName("솔팅 확인")
    public void add_salting() throws NoSuchAlgorithmException {
        //given
        String salt="test";
        String password="p@ssw@rd!23";
        EncryptManager encryptManager=new EncryptManager();
        //when
        String expect=encryptManager.encrypt(password+salt);
        encryptManager.salt=salt;
        String result=encryptManager.encrypt(password);
        //then
        Assertions.assertThat(result).isEqualTo(expect);
    }

    @Test
    @DisplayName("해싱 동일성 확인")
    public void same_password() throws NoSuchAlgorithmException {
        //given
        String password1="p@ssw@rd!23";
        String password2="p@ssw@rd!23";
        EncryptManager encryptManager1=new EncryptManager();
        EncryptManager encryptManager2=new EncryptManager();
        //when
        String encrypt1=encryptManager1.encrypt(password1);
        String encrypt2=encryptManager2.encrypt(password2);
        //then
        Assertions.assertThat(encrypt1).isEqualTo(encrypt2);
    }

    @Test
    @DisplayName("키 스트레칭 횟수 확인")
    public void key_stretching() throws  NoSuchAlgorithmException {
        //given
        String password="p@ssw@rd!23";
        EncryptManager encryptManager=new EncryptManager();
        MessageDigest messageDigest=MessageDigest.getInstance("sha-256");
        encryptManager.iteration=30;
        String result="";
        //when
        String encrypt=encryptManager.encrypt(password);
        int t=30;
        byte[] test=password.getBytes();
        while(t>0){
            messageDigest.update(test);
            test=messageDigest.digest();
            t--;
        }
        result=bytesToHex(test);
        //then
        Assertions.assertThat(encrypt).isEqualTo(result);
    }
    private String bytesToHex(byte[] bytes) {
        StringBuilder builder = new StringBuilder();
        for (byte b : bytes) {
            builder.append(String.format("%02x", b));
        }
        return builder.toString();
    }
}
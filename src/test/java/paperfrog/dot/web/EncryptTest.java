package paperfrog.dot.web;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import paperfrog.dot.domain.Member;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.security.NoSuchAlgorithmException;

class EncryptTest {
    @Test
    @DisplayName("솔팅 확인")
    public void add_salting() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        //given
        String salt="test";
        String password="p@ssw@rd!23";
        EncryptManager encryptManager=new EncryptManager();
        Method method = encryptManager.getClass().getDeclaredMethod("addSalt", String.class, String.class);
        System.out.println(method);
        method.setAccessible(true);
        //when
        byte[] result= (byte[]) method.invoke(encryptManager,password,salt);
        byte[] expect=new String(password+salt).getBytes();
        //then
        Assertions.assertThat(result).isEqualTo(expect);
    }

    @Test
    @DisplayName("해싱 동일성 확인")
    public void same_password() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, NoSuchAlgorithmException {
        //given
        String password1="p@ssw@rd!23";
        String password2="p@ssw@rd!23";
        EncryptManager encryptManager=new EncryptManager();
        //when
        String encrypt1=encryptManager.encrypt(password1);
        String encrypt2=encryptManager.encrypt(password2);
        //then
        Assertions.assertThat(encrypt1).isEqualTo(encrypt2);
    }

}
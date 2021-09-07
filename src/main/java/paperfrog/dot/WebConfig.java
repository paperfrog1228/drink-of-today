package paperfrog.dot;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import paperfrog.annotation.argumentresolver.LoginArgumentResolver;
import paperfrog.interceptor.LogInterceptor;
import paperfrog.interceptor.LoginCheckInterceptor;
import paperfrog.interceptor.ManagerCheckInterceptor;

import java.util.List;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(new LoginArgumentResolver());
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
       registry.addInterceptor(new LogInterceptor())
               .order(1)
               .addPathPatterns("/**")
               .excludePathPatterns("/css/**","/*.ico","/error");
       registry.addInterceptor(new LoginCheckInterceptor())
               .order(2)
               .addPathPatterns("/board/write/*")
               .excludePathPatterns("/css/**","/*.ico","/error");
        registry.addInterceptor(new ManagerCheckInterceptor())
                .order(3)
                .addPathPatterns("/board/write/NOTICE")
                .excludePathPatterns("/css/**","/*.ico","/error");
    }


}

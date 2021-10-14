package paperfrog.dot;

import com.navercorp.lucy.security.xss.servletfilter.XssEscapeServletFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
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

    /*
     * lucy-xss-filter
     *
     * */
    @Bean
    public FilterRegistrationBean getFilterRegistrationBean(){
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.setFilter(new XssEscapeServletFilter());
        registrationBean.setOrder(1);
        registrationBean.addUrlPatterns("/board/*");    //filter를 거칠 url patterns
        return registrationBean;
    }
}

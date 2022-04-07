package hello.login;

import hello.login.web.filter.LogFilter;
import hello.login.web.filter.LoginCheckFilter;
import hello.login.web.interceptor.LogInterceptor;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

import javax.servlet.Filter;

@Configuration //빈 설정
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LogInterceptor())
                .order(1)
                .addPathPatterns("/**") //모든 경로 허용
                .excludePathPatterns("/css/**", "/*.ico", "error");//excludePathPatterns경우 제외

        //로그인 체크 등록
        registry.addInterceptor(new LocaleChangeInterceptor())
                .order(2)
                .addPathPatterns("/**") //모든 경로 허용
                .excludePathPatterns("/", "/members/add", "/login", "/logout", "/css/**", "/*.ioc", "/error"); //excludePathPatterns 뺴고
    }

//    @Bean
    public FilterRegistrationBean logFilter() {
        FilterRegistrationBean<Filter> filterFilterRegistrationBean = new FilterRegistrationBean<>();
        filterFilterRegistrationBean.setFilter(new LogFilter());
        filterFilterRegistrationBean.setOrder(1);//필터 순서 정해주기
        filterFilterRegistrationBean.addUrlPatterns("/*"); // /* 모든 URL에 적용됨

        return filterFilterRegistrationBean;
    }

    //필터를 이용한 로그인 체크
//    @Bean
    public FilterRegistrationBean loginCheckFilter() {
        FilterRegistrationBean<Filter> filterFilterRegistrationBean = new FilterRegistrationBean<>();
        filterFilterRegistrationBean.setFilter(new LoginCheckFilter());
        filterFilterRegistrationBean.setOrder(2);//필터 순서 정해주기
        filterFilterRegistrationBean.addUrlPatterns("/*"); // /* 모든 URL에 적용됨

        return filterFilterRegistrationBean;
    }
}

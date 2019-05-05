package com.example.travelagencyclientapp.app.interceptors;


import com.example.travelagencyclientapp.app.models.services.UserSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;

@Configuration
public class WebMvcConfig extends HandlerInterceptorAdapter implements WebMvcConfigurer {

    final UserSession userSession;

    private static final List<String> allowedUrls = Arrays.asList("/login", "/registration-form","/logout");

    @Autowired
    public WebMvcConfig(UserSession userSession) {
        this.userSession = userSession;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(this);
    }

    //-------------------------------------------------------------------------------------


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String requestUrlAsString = request.getRequestURL().toString();

//        if(requestUrlAsString.contains("/admin/") && userSession.getRole()){
//            return true;
//        }

        if(userSession.isLogin()){
            return true;
        }


        if (allowedUrls.stream().anyMatch(s -> requestUrlAsString.contains(s))) {
            return super.preHandle(request, response, handler);
        }

        response.sendRedirect("/login");
        return false;
    }


}

package kr.ac.jejunu.interceptor;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by replay on 2017. 6. 9..
 */
public class LoginCheckInterceptor extends HandlerInterceptorAdapter {
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {

        HttpSession session = request.getSession(false);

        if (session == null){
            response.sendRedirect(request.getContextPath()+"login");
            return false;
        }


        return true;
    }
}

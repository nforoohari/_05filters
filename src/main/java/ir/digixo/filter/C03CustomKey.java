package ir.digixo.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.io.IOException;
@Component
public class C03CustomKey implements Filter {


    @Value("${auth.key}")
    private String authKey;


    @Autowired
    private Environment environment;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        var request = (HttpServletRequest) servletRequest;
        var response = (HttpServletResponse) servletResponse;


        System.out.println(authKey);


        String myId = request.getHeader("Authorization");
        if (myId.equals(environment.getProperty("auth.key"))) {
            filterChain.doFilter(request, response);

        } else {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        }

    }
}

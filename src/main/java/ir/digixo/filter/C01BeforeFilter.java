package ir.digixo.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class C01BeforeFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {


       var request= (HttpServletRequest) servletRequest;
       var response= (HttpServletResponse) servletResponse;

        String myId = request.getHeader("myId");
      //  System.out.println(myId);


        if (myId == null) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
        filterChain.doFilter(request, response);

    }
}

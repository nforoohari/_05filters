//package ir.digixo.filter;
//
//import jakarta.servlet.*;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import org.springframework.stereotype.Component;
//
//import java.io.IOException;
//
//@Component
//public class C01BeforeFilter implements Filter {
//
//    @Override
//    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
//
//        var request = (HttpServletRequest) servletRequest;
//        var response = (HttpServletResponse) servletResponse;
//
//        String myId = request.getHeader("myId");
//        System.out.println("Before Basic Authentication Filter. check myId header: " + myId);
//        filterChain.doFilter(request, response);
//
//    }
//}

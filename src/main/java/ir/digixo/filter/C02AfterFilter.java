//package ir.digixo.filter;
//
//import jakarta.servlet.*;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Component;
//
//import java.io.IOException;
//
//@Component
//public class C02AfterFilter implements Filter {
//
//    @Value("${auth.key}")
//    private String authKey;
//
//    @Override
//    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
//
//        var request = (HttpServletRequest) servletRequest;
//        var response = (HttpServletResponse) servletResponse;
//
//        String myId = request.getHeader("myId");
//        System.out.println("After Basic Authentication Filter. check myId header: " + myId);
//
//        if (myId == null) {
//            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
//        } else if (authKey.equals(myId)) {
//            response.setStatus(HttpServletResponse.SC_ACCEPTED);
//        } else {
//            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
//        }
//
//        filterChain.doFilter(request, response);
//
//    }
//}

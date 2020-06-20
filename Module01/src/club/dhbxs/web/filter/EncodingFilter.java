package club.dhbxs.web.filter;

import com.sun.deploy.nativesandbox.comm.Response;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(filterName = "EncodingFilter", urlPatterns = {"/*"})
public class EncodingFilter implements Filter {
    @Override
    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
//        if (request.getSession().getAttribute("userName") != null){
//            if (!request.getParameter("userName").equals(request.getSession().getAttribute("userName"))){
//                response.sendRedirect("index.jsp");
//            }
//        }
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=" + "UTF-8");
        chain.doFilter(req, resp);
    }

    @Override
    public void init(FilterConfig config) throws ServletException {

    }

}

package club.dhbxs.web.servlet;

import club.dhbxs.bean.User;
import club.dhbxs.service.UserSignUpService;
import club.dhbxs.service.impl.UserSignUpServiceImpl;

import javax.lang.model.element.VariableElement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;

/**
 * @author 17235
 */
@WebServlet(name = "UserSignUp", urlPatterns = {"/UserSignUp"})
public class UserSignUp extends HttpServlet {
    UserSignUpService userSignUpService = new UserSignUpServiceImpl();

    /**
     * 验证普通用户登陆
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void verify(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userName = request.getParameter("inputUserName");
        String userPassword = request.getParameter("inputPassword");
        if (userName != null && userPassword != null) {
            User user = new User();
            user.setUserName(userName);
            user.setUserPassword(userPassword);
            User user1 = new User();
            user1 = userSignUpService.verify(user);
            if (user1 != null) {
                request.getSession().setAttribute("user", user);
                response.sendRedirect("GetRepository");
                return;
            } else {
                response.sendRedirect("index.jsp");
                return;
            }
        } else {
            response.sendRedirect("index.jsp");
            return;
        }
    }

    /**
     * 验证管理员
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void verifyAdmin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userName = request.getParameter("inputUserName");
        String userPassword = request.getParameter("inputPassword");
        if (userName != null && userPassword != null) {
            User user = new User();
            user.setUserName(userName);
            user.setUserPassword(userPassword);
            User user1 = new User();
            user1 = userSignUpService.verifyAdmin(user);
            if (user1 != null) {
                request.getSession().setAttribute("user", user1);
                response.sendRedirect("GetRepository");
                return;
            } else {
                response.sendRedirect("index.jsp");
                return;
            }
        } else {
            response.sendRedirect("index.jsp");
            return;
        }
    }

    protected void signOut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getSession().invalidate();
        response.sendRedirect("index.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        try {
            //获取业务鉴别字符串，获取相应业务的方法，方法反射对象
            Method method = this.getClass().getDeclaredMethod(action, HttpServletRequest.class, HttpServletResponse.class);
            method.invoke(this, request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}

package sigra;

import org.wso2.carbon.identity.sso.agent.SSOAgentConstants;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RedirecionamentoServlet extends HttpServlet {
	
	private final static String SAML_URI = "/samlsso";
	private final static String HOME_URI = "home.jsp";
	private final static String LOGOUT_URI = "/logout";
	private final static String INDEX_URI = "index.jsp";

    protected  void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(request.getRequestURI().endsWith(SAML_URI)){
            request.getRequestDispatcher(HOME_URI).forward(request,response);
        } else if (request.getRequestURI().endsWith(LOGOUT_URI)){
            request.getRequestDispatcher(INDEX_URI).forward(request,response);
        }
    }

}

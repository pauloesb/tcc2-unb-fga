package sigra;

import org.apache.axiom.om.util.Base64;
import org.apache.commons.lang.StringUtils;
import org.wso2.carbon.identity.sso.agent.SSOAgentConstants;
import org.wso2.carbon.identity.sso.agent.SSOAgentFilter;
import org.wso2.carbon.identity.sso.agent.bean.SSOAgentConfig;

import javax.servlet.*;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FiltroSAMLSSO extends SSOAgentFilter {

    private static Logger LOGGER = Logger.getLogger(FiltroSAMLSSO.class.getName());

    private static final String HTTP_POST_BIND = "urn:oasis:names:tc:SAML:2.0:bindings:HTTP-POST";
    private static final String USUARIO = "username";
    private static final String SENHA = "password";
    private static final String ENCODE = "UTF-8";
    private static final String HTML_A = "<html>\n" +
            							"<body>\n" +
            							"<form method='post' action='";
    private static final String HTML_B = "'>\n" +
            							 "<input type='hidden' name='sectoken' value='";
    private static final String HTML_C = "'/>\n" +
            							 "</form>\n" +
            							 "<script type='text/javascript'>\n" +
            							 "document.forms[0].submit();\n" +
            							 "</script>\n" +
            							 "</body>\n" +
            							 "</html>";
    
    private static Properties propriedades = GerenciamentoContexto.getProperties();
    protected FilterConfig filtroConfig = null;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.filtroConfig = filterConfig;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {

        String binding = servletRequest.getParameter(SSOAgentConstants.SSOAgentConfig.SAML2.HTTP_BINDING);
        if(binding == null || binding.isEmpty() || !("HTTP-POST".equals(binding))){
        	binding = HTTP_POST_BIND;
        } 
        SSOAgentConfig config = (SSOAgentConfig)filtroConfig.getServletContext().getAttribute(SSOAgentConstants.CONFIG_BEAN_NAME);
        config.getSAML2().setHttpBinding(binding);
        redirecionamento(config, servletRequest);
        servletRequest.setAttribute(SSOAgentConstants.CONFIG_BEAN_NAME,config);
        super.doFilter(servletRequest, servletResponse, filterChain);
    }
    
    public String postHtml(String usuario, String senha, String samlidpurl) throws UnsupportedEncodingException {
    	String autorizacao = usuario + ":" + senha;
    	autorizacao = new String(Base64.encode(autorizacao.getBytes(ENCODE)));
    	return HTML_A + samlidpurl + HTML_B + autorizacao + HTML_C;
    }
    
    public void redirecionamento(SSOAgentConfig config, ServletRequest request) {
    	if (StringUtils.isNotEmpty(request.getParameter(USUARIO)) &&
                StringUtils.isNotEmpty(request.getParameter(SENHA))) {
    		try {
            config.getSAML2().setPostBindingRequestHTMLPayload(postHtml(
            		request.getParameter(USUARIO), 
            		request.getParameter(SENHA), 
            		propriedades.getProperty(GerenciamentoContexto.PROPERTIE_SAML2IDPURL)));
    		} catch (UnsupportedEncodingException e) {
    			LOGGER.log(Level.SEVERE, e.getMessage());
    		}
        } else {
            config.getSAML2().setPostBindingRequestHTMLPayload(null);
        }
    }

    @Override
    public void destroy() {

    }
}

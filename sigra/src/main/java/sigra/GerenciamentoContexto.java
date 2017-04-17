package sigra;

import org.wso2.carbon.identity.sso.agent.SSOAgentConstants;
import org.wso2.carbon.identity.sso.agent.bean.SSOAgentConfig;
import org.wso2.carbon.identity.sso.agent.SSOAgentException;
import org.wso2.carbon.identity.sso.agent.saml.SSOAgentX509Credential;
import org.wso2.carbon.identity.sso.agent.saml.SSOAgentX509KeyStoreCredential;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GerenciamentoContexto implements ServletContextListener {
	
	private final static String PATH = "sigra";
	private final static String PATH_PROPERTIES = "/WEB-INF/classes/sigra.properties";
	private final static String PATH_CERT = "/WEB-INF/classes/wso2carbon.jks";
	private final static String PROPERTIE_KSP = "KeyStorePassword";
	private final static String PROPERTIE_IDPCERTALIAS = "IdPPublicCertAlias";
	private final static String PROPERTIE_PKA = "PrivateKeyAlias";
	private final static String PROPERTIE_PKP = "PrivateKeyPassword";
	
	public final static String PROPERTIE_SAML2IDPURL = "SAML2.IdPURL";

    private static Logger logger = Logger.getLogger(GerenciamentoContexto.class.getName());
    private static Properties propriedades = new Properties();

    public void contextInitialized(ServletContextEvent servletContextEvent) {
        try {
            if(servletContextEvent.getServletContext().getContextPath().contains(PATH)) {
                propriedades.load(servletContextEvent.getServletContext().getResourceAsStream(PATH_PROPERTIES));
            }
            SSOAgentConfig config = new SSOAgentConfig();
            config.initConfig(propriedades);
            config.getSAML2().setSSOAgentX509Credential(associandoCredencial(servletContextEvent.getServletContext().
            		getResourceAsStream(PATH_CERT), propriedades));
            servletContextEvent.getServletContext().setAttribute(SSOAgentConstants.CONFIG_BEAN_NAME, config);
        } catch (IOException e){
            logger.log(Level.SEVERE, e.getMessage(), e);
        } catch (SSOAgentException e) {
            logger.log(Level.SEVERE, e.getMessage(), e);
        }
    }
    
    public SSOAgentX509Credential associandoCredencial(InputStream keyStoreInputStream, Properties propriedades) throws SSOAgentException {
        return new SSOAgentX509KeyStoreCredential(keyStoreInputStream,
                        propriedades.getProperty(PROPERTIE_KSP).toCharArray(),
                        propriedades.getProperty(PROPERTIE_IDPCERTALIAS),
                        propriedades.getProperty(PROPERTIE_PKA),
                        propriedades.getProperty(PROPERTIE_PKP).toCharArray());
    	
    }

    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }

    public static Properties getProperties(){
        return propriedades;
    }
}

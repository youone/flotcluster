
 
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.ContextHandler;
import org.eclipse.jetty.server.handler.ContextHandlerCollection;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
 
public class FlotClusterHttpServer {

    public static void main( String[] args ) throws Exception
    {
        Server server = new Server(8181);
 
        ResourceHandler staticHandler = new ResourceHandler();
        staticHandler.setDirectoriesListed(true);
        staticHandler.setResourceBase("./");        
        ContextHandler staticcontext = new ContextHandler("/");
        staticcontext.setContextPath("/");
        staticcontext.setHandler(staticHandler);
        
        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.setContextPath("/");
        context.setResourceBase(System.getProperty("java.io.tmpdir"));
        context.addServlet(HelloServlet.class, "/dump/*");
 
        ContextHandlerCollection contexts = new ContextHandlerCollection();
        contexts.setHandlers(new Handler[] {staticcontext, context});
 
        server.setHandler(contexts);

        server.start();
        server.join();
    }

    
    
}

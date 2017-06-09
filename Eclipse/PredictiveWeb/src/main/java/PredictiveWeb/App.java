package PredictiveWeb;
//

//import org.eclipse.jetty.server.Server;
//import org.eclipse.jetty.servlet.ServletContextHandler;
//import org.eclipse.jetty.servlet.ServletHolder;
//import org.glassfish.jersey.server.ResourceConfig;
//import org.glassfish.jersey.servlet.ServletContainer;
//
///**
// * Hello world!
// *
// */
//
//public class App {
//	public static void main(String[] args) throws Exception {
//		ResourceConfig config = new ResourceConfig();
//		config.packages("PredictiveWeb");
//		ServletHolder servlet = new ServletHolder(new ServletContainer(config));
//
//		Server server = new Server(2222);
//		ServletContextHandler context = new ServletContextHandler(server, "/*");
//		context.addServlet(servlet, "/*");
//		//server.start();
//		//server.join();
//	}
//}

//
//import org.eclipse.jetty.server.Server;
//import org.eclipse.jetty.servlet.ServletContextHandler;
//import org.eclipse.jetty.servlet.ServletHolder;
//
//public class App {
//    public static void main(String[] args) throws Exception {
//    	
//    	
//        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
//        context.setContextPath("/");
//
//        Server jettyServer = new Server(2020);
//        jettyServer.setHandler(context);
//        ServletHolder jerseyServlet = context.addServlet(org.glassfish.jersey.servlet.ServletContainer.class, "/*");
//        jerseyServlet.setInitOrder(0);
//
//        // Tells the Jersey Servlet which REST service/class to load.
//        jerseyServlet.setInitParameter("jersey.config.server.provider.classnames",EntryPoint.class.getCanonicalName());
//
//        try {
//            jettyServer.start();
//            jettyServer.join();
//        } finally {
//            jettyServer.destroy();
//        }
//    }
//}

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.servlet.DefaultServlet;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

public class App {
	public static void main(String[] args) {

		System.setProperty("org.eclipse.jetty.LEVEL", "INFO");

		Server server = new Server();
		ServerConnector connector = new ServerConnector(server);
		connector.setPort(8080);
		server.addConnector(connector);

		// The filesystem paths we will map
		String homePath = System.getProperty("user.home");
		String pwdPath = System.getProperty("user.dir");

		// Setup the basic application "context" for this application at "/"
		// This is also known as the handler tree (in jetty speak)

		ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
		context.setResourceBase(pwdPath);
		context.setContextPath("/");
		server.setHandler(context);

		// add a simple Servlet at "/dynamic/*"
		// ServletHolder holderDynamic = new ServletHolder("dynamic",
		// DynamicServlet.class);

		ServletHolder jerseyServlet = context.addServlet(org.glassfish.jersey.servlet.ServletContainer.class, "/*");
		jerseyServlet.setInitOrder(0);
		jerseyServlet.setInitParameter("jersey.config.server.provider.classnames", EntryPoint.class.getCanonicalName());

		context.addServlet(jerseyServlet, "/dynamic/*");

		// add special pathspec of "/home/" content mapped to the homePath
		ServletHolder holderHome = new ServletHolder("static-home", DefaultServlet.class);
		// holderPwd.setInitParameter("resourceBase", homePath);
		holderHome.setInitParameter("dirAllowed", "true");
		holderHome.setInitParameter("pathInfoOnly", "true");
		context.addServlet(holderHome, "/home/*");

		// Lastly, the default servlet for root content (always needed, to
		// satisfy servlet spec)
		// It is important that this is last.
		ServletHolder holderPwd = new ServletHolder("default", DefaultServlet.class);
		holderPwd.setInitParameter("dirAllowed", "true");
		context.addServlet(holderPwd, "/");

		try {
			server.start();
			// server.dump(System.err);
			server.join();
		} catch (Throwable t) {
			t.printStackTrace(System.err);
		}
	}

}
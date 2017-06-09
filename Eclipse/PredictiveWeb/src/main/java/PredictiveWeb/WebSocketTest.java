package PredictiveWeb;

import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.DefaultHandler;
import org.eclipse.jetty.server.handler.HandlerCollection;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.websocket.server.WebSocketHandler;
import org.eclipse.jetty.websocket.servlet.WebSocketServletFactory;

public class WebSocketTest {

	public static void main(String[] args) throws Exception {
		Server server = new Server(8080);

		ResourceHandler resource_handler = new ResourceHandler();
		resource_handler.setDirectoriesListed(true);
		resource_handler.setWelcomeFiles(new String[] { "src/resource/index.html" });
		resource_handler.setResourceBase(".");

		HandlerList handlers = new HandlerList();
		handlers.setHandlers(new Handler[] { resource_handler, new DefaultHandler() });

		WebSocketHandler wsHandler = new WebSocketHandler() {
			@Override
			public void configure(WebSocketServletFactory factory) {
				factory.register(MyWebSocketHandler.class);
			}
		};
		
		HandlerCollection handlerCollection = new HandlerCollection();
		handlerCollection.setHandlers(new Handler[] { wsHandler,resource_handler });
		server.setHandler(handlerCollection);
		
		server.start();
		server.join();
		
//        int port=8080;
//        Server server = new Server(port);
//        
//        ResourceHandler resource_handler=new ResourceHandler();
//        resource_handler.setResourceBase(".");
//        
//        HandlerList handlers = new HandlerList();
//        handlers.setHandlers(new Handler[]{resource_handler,new DefaultHandler()});
//        server.setHandler(handlers);
//        
//        server.start();
//        server.join();
        
	}
}
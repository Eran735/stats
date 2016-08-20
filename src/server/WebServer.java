package server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

public class WebServer {
	
	private HttpServer server;
	
	public void init() throws IOException {
	    HttpServer server = HttpServer.create(new InetSocketAddress(8005), 0);
	    this.server = server;
	    
	    server.setExecutor(Executors.newFixedThreadPool(10)); 
	}
	
	public void addContext(String path, HttpHandler handler) {
		server.createContext(path, handler);
	}
	
	public void start() {
		this.server.start();
	}
	
}

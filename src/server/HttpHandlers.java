package server;

import java.io.IOException;
import java.io.OutputStream;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import datastore.EventsStats;

public class HttpHandlers {

	public static class WordsStatsReportHandler implements HttpHandler {
		
	    @Override
	    public void handle(HttpExchange t) throws IOException {
	    	String wordsReport = EventsStats.self().wordStats.getStatsReportAsJson();
	        t.sendResponseHeaders(200, wordsReport.length());
	        OutputStream os = t.getResponseBody();
	        os.write(wordsReport.getBytes());
	        os.close();
	    }
	}
	
	public static class TypeStatsReportHandler implements HttpHandler {
		
	    @Override
	    public void handle(HttpExchange t) throws IOException {
	    	String typesReport = EventsStats.self().typeStats.getStatsReportAsJson();
	        t.sendResponseHeaders(200, typesReport.length());
	        OutputStream os = t.getResponseBody();
	        os.write(typesReport.getBytes());
	        os.close();
	    }
	}
}
package processor;

import java.io.IOException;

import server.HttpHandlers.TypeStatsReportHandler;
import server.HttpHandlers.WordsStatsReportHandler;
import server.WebServer;


public class runner {

	public static void main(String[] args) {
		EventsReader reader = new EventsReader(new StdinStream());
		reader.start();
		WebServer server = new WebServer();
		try {
			server.init();
			server.addContext("/wordReport", new WordsStatsReportHandler());
			server.addContext("/typeReport", new TypeStatsReportHandler());
			server.start();
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		
	}
	
}

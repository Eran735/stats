package processor;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import modules.Event;
import modules.Event.EventSerializer;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;

public class EventsReader extends Thread {

	private static final int BATCH_SIZE = 5;
	
	private static Gson gson;
	private static ExecutorService threadPool;
	

	private DataStream stream;
	
	
	static {
		GsonBuilder builder = new GsonBuilder();
		builder.registerTypeAdapter(Event.class, new EventSerializer());
		gson = builder.create();
		threadPool = Executors.newFixedThreadPool(10);
	}
	
	public EventsReader(DataStream stream) {
		this.stream = stream;
	}
	
	public void read() {
		List<String> data = new LinkedList<String>();
		for(int i=0; i<BATCH_SIZE; i++) {
			try {
				String line = stream.read();
				if(line != null) {
					data.add(line);
				}
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}
		process(data);
	}
	
	public void process(List<String> EventsJsons) {
		List<Event> eventsBatch = new LinkedList<Event>();
		for(String eventJson : EventsJsons) {
			try {
				Event event = gson.fromJson(eventJson, Event.class);
				eventsBatch.add(event);
			}
			catch(JsonSyntaxException jse) {}
			catch(Exception e) {}
		}
		StatsBatch statsBatch = new StatsBatch(eventsBatch);
		threadPool.submit(statsBatch);
		
	}
	
	@Override
	public void run() {
		while(true) {
			read();
			try {
				sleep(5);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}
	
}

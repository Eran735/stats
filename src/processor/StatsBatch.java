package processor;

import java.util.List;

import datastore.EventsStats;
import modules.Event;

public class StatsBatch implements Runnable {

	public List<Event> batch;
	
	public StatsBatch(List<Event> batch) {
		this.batch = batch;
	}

	@Override
	public void run() {
		for(Event e : batch) {
			EventsStats.self().typeStats.report(e.type);
			String[] words = e.data.split("\\s+");
			for(String word : words) {
				EventsStats.self().wordStats.report(word);
			}
		}
		
	}
	
	

}

package datastore;

public class EventsStats {

	public static EventsStats eventStats;
	
	public EventsStats() {
		this.typeStats = new CountStat();
		this.wordStats = new CountStat();
	}
	
	public CountStat typeStats;
	public CountStat wordStats;
	
	static {
		eventStats = new EventsStats();
	}
	
	public static EventsStats self() {
		return eventStats;
	}
	
}

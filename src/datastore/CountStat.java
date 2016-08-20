package datastore;

import java.util.Collections;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

import com.google.gson.Gson;


public class CountStat {

	public static Gson gson = new Gson();
	
	ConcurrentHashMap<String, AtomicLong> stats = new ConcurrentHashMap<String, AtomicLong>();
	
	public void report(String key) {
		stats.putIfAbsent(key, new AtomicLong());
		stats.get(key).incrementAndGet();
	}
	
	public String getStatsReportAsJson() {
		return gson.toJson(Collections.unmodifiableMap(stats));
	}
	
}

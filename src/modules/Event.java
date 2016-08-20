package modules;

import java.lang.reflect.Type;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializer;

public class Event {

	public String type;
	public String data;
	public long timeStamp;
	
	public Event(String type, String data, long timeStamp) {
		this.type = type;
		this.data = data;
		this.timeStamp = timeStamp;
	}

	public static class EventSerializer  implements JsonDeserializer<Event>{
	
		@Override
		public Event deserialize(JsonElement arg0, Type arg1,
				JsonDeserializationContext arg2) throws JsonParseException {
			JsonObject o = arg0.getAsJsonObject();
			String type = o.get("event_type").getAsString();
			String data = o.get("data").getAsString();
			long timeStamp = o.get("timestamp").getAsLong();
			Event event = new Event(type, data, timeStamp);
			return event;
		}
	}
}

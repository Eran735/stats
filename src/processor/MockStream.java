package processor;

public class MockStream implements DataStream {

	@Override
	public String read() {
		return "{ \"event_type\": \"foo\", \"data\": \"lorem\", \"timestamp\": 34235 }";
	}

}

package Test;

import static org.junit.Assert.*;

import org.junit.Test;
import main.SpeedTest;

public class SpeedTestTest {
	
	@Test
	public final void launchTest() {
		SpeedTest st = new SpeedTest();
		if (st.launch() != 0) {
			fail("failed");
		}
	}
}

import org.junit.Assert;
import org.junit.Test;

public class FrequencyCheckerTest extends Assert {
    public static final long MINUTE = 60 * 1000;
    public static final String ACCEPTED_MSG = "Message was accepted instead of rejection";
    public static final String REJECTED_MSG = "Message was rejected instead of accepting";

    @Test
    public void isAllowed() throws Exception {
        final int n = 3;
        FrequencyChecker fc = new FrequencyCheckerImpl(n, MINUTE);
        long now = System.currentTimeMillis();
        int times = 5;
        for (int i = 0; i < times; i++) {
            now += MINUTE;
            for (int j = 0; j < n; j++) {
                assertTrue(REJECTED_MSG, fc.isAllowed(now));
            }
        }
        for (int i = 0; i < n; i++) {
            assertFalse(ACCEPTED_MSG, fc.isAllowed(now));
        }
        for (int i = 0; i < n; i++) {
            assertFalse(ACCEPTED_MSG, fc.isAllowed(now + MINUTE-1));
        }
    }
}
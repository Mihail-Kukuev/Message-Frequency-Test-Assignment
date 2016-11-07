public class FrequencyCheckerImpl implements FrequencyChecker {
    public final long interval;
    private final ArrayQueue queue;
    private volatile boolean fastMode = false;
    private volatile long peekedValue;

    public FrequencyCheckerImpl(int N, long interval) {
        this.interval = interval;
        queue = new ArrayQueue(N, 0);
    }

    public boolean isAllowed(long now) {
        if (fastMode) {
            if (now - peekedValue < interval)
                return false;
            else
                fastMode = false;
        }
        try {
            synchronized (queue) {
                peekedValue = queue.peek();
                if (now - peekedValue >= interval) {
                    queue.removeAndAdd(now);
                    return true;
                } else if (!fastMode) {
                    fastMode = true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
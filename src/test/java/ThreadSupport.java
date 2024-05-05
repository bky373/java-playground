public class ThreadSupport {
    public static long getThreadId() {
        return Thread.currentThread()
                     .getId();
    }

    public static String getThreadName() {
        return Thread.currentThread()
                     .getName();
    }
}

public class ThreadSupport {
    public static long getThreadId() {
        return Thread.currentThread()
                     .getId();
    }
}

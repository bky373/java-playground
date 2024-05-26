package org.example;

import java.util.concurrent.Semaphore;

public class SemaphoreExample {

    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(3);
//        Runnable task = acquireRunnable(semaphore);
        Runnable task = tryAcquireRunnable(semaphore);

        // 5개의 쓰레드를 생성하고 시작
        for (int i = 0; i < 5; i++) {
            new Thread(task).start();
        }
    }

    /**
     * acquire() 메서드는 세마포어의 허가를 요청합니다.
     * 만약 허가가 없다면 쓰레드는 블록됩니다.
     * release() 메서드는 사용한 자원을 반환하여 세마포어의 허가를 증가시킵니다.
     * 이 예제에서 5개의 쓰레드가 있지만, 동시에 최대 3개의 쓰레드만 자원에 접근할 수 있습니다.
     * 각 쓰레드는 acquire()를 호출하여 세마포어의 허가를 얻고, 자원을 사용한 후 release()를 호출하여 허가를 반환합니다.
     */
    private static Runnable acquireRunnable(Semaphore semaphore) {
        return () -> {
            try {
                System.out.println("[-] " + ThreadSupport.getThreadName() + " waiting for permit.");
                semaphore.acquire();
                System.out.println("[O] " + ThreadSupport.getThreadName() + " acquired permit.");

                // Critical section: 자원에 접근
                Thread.sleep(2000); // 예시로 2초간 자원을 사용
            } catch (InterruptedException e) {
                Thread.currentThread()
                      .interrupt();
            } finally {
                System.out.println("[-] " + ThreadSupport.getThreadName() + " releasing permit.");
                semaphore.release();
            }
        };
    }

    /**
     * tryAcquire() 메서드는 쓰레드가 즉시 허가를 얻으려고 시도하지만, 실패하면 블록되지 않습니다.
     * 이 메서드는 boolean 값을 반환하여 허가를 얻었는지 여부를 나타냅니다.
     */
    private static Runnable tryAcquireRunnable(Semaphore semaphore) {
        return () -> {
            try {
                System.out.println("[-] " + ThreadSupport.getThreadName() + " attempting to acquire permit.");
                if (semaphore.tryAcquire()) {
                    try {
                        System.out.println("[O] " + ThreadSupport.getThreadName() + " acquired permit.");
                        // Critical section: 자원에 접근
                        Thread.sleep(2000); // 예시로 2초간 자원을 사용
                    } finally {
                        System.out.println("[-] " + ThreadSupport.getThreadName() + " releasing permit.");
                        semaphore.release();
                    }
                } else {
                    System.out.println("[X] " + ThreadSupport.getThreadName() + " could not acquire permit.");
                }
            } catch (InterruptedException e) {
                Thread.currentThread()
                      .interrupt();
            }
        };
    }
}

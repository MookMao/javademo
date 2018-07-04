package thread.wait;

/**
 * @Author: maojunkai
 * @Date: 2018/6/23 下午1:23
 * @Description:
 */
public class MyThread2 extends Thread{

    private Object lock;

    private Object waitObject;

    public MyThread2(Object lock, Object waitObject) {
        this.lock = lock;
        this.waitObject = waitObject;
    }

    @Override
    public void run() {
        synchronized (lock) {
            System.out.println("MyThread2: " + System.currentTimeMillis());
            lock.notify();
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("MyThread2: " + System.currentTimeMillis());

        }
    }
}

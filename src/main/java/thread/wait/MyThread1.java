package thread.wait;

/**
 * @Author: maojunkai
 * @Date: 2018/6/23 下午1:16
 * @Description:
 */
public class MyThread1 extends Thread{

    private Object lock;

    private Object waitObject;

    public MyThread1(Object lock, Object waitObject) {
        this.lock = lock;
        this.waitObject = waitObject;
    }

    @Override
    public void run() {
        synchronized (lock) {
            System.out.println("MyThread1: " + System.currentTimeMillis());
            try {
                lock.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("MyThread1: " + System.currentTimeMillis());

        }
    }
}

public class BakeryRunnable implements  Runnable{

    FixnumLockable lock;
    private static volatile int a = 0;
    int b;
    BakeryRunnable(FixnumLockable lock,  int b) {
        this.lock = lock;
        this.b = b;
    }
    @Override
    public void run() {
        lock.lock();
        for(int i = 0; i < 10; i++) {
            a += b;
            //System.out.println(Thread.currentThread().getName() + " " + a);
        }
        System.out.println(Thread.currentThread().getName() + " " + a);
        lock.unlock();
    }
}

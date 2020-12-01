public class Bakery {
    //private static int numThread = 10;


    public static void Simulate(FixnumLockable lock, int numThread) throws InterruptedException {
        //FixnumLock lock = new BakeryLock(numThread);
        //FixnumLock lock = new ImprovedBakeryLock(numThread);

        Thread[] threads = new Thread[numThread];

        for(int i = 0; i < numThread; i++) {
            int b;
            if( i % 2 == 0)
                b = -1;
            else
                b = 1;
            if( i == numThread - 1 && numThread % 2 == 1)
                b = 0;
            threads[i] = new Thread((Runnable) new BakeryRunnable(lock,b));
            lock.registerThread();
            threads[i].start();
        }
        for(int i = 0; i < numThread; i++) {
            threads[i].join();
            lock.unregisterThread();
        }
    }
}
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;

public class BakeryLock extends FixnumLock{
    private int numberOfThreads;
    private static int[] tickets;

    BakeryLock(int numberOfThreads) {
        super(numberOfThreads);
        this.numberOfThreads = numberOfThreads;
        tickets = new int[numberOfThreads];
        for(int i = 0; i < numberOfThreads; i++)
            tickets[i] = 0;

    }
    @Override
    public void lock() {
        int id = (int) Thread.currentThread().getId() % numberOfThreads;
        tickets[id] = findMax() + 1;
        for (int i = 0; i < tickets.length; i++) {
            if (i == id) { continue; }
            while (tickets[i] != 0 && tickets[i] < tickets[id]) {Thread.yield(); }
        }
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean tryLock() {
        return false;
    }

    @Override
    public boolean tryLock(long time, @NotNull TimeUnit unit) throws InterruptedException {
        return false;
    }

    public void unlock() {
        int id = (int) Thread.currentThread().getId() % numberOfThreads;
        tickets[id] = 0;
    }

    @NotNull
    @Override
    public Condition newCondition() {
        return null;
    }

    private int findMax() {
        int m = tickets[0];
        for (int i = 1; i < tickets.length; i++) {
            if (tickets[i] > m)
                m = tickets[i];
        }
        return m;
    }
}

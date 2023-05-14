package sandro.objects;

import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Parking {
    private final boolean[] spots;

    private final Random random = new Random();
    private final Lock LOCK = new ReentrantLock(true);
    private final Condition UNLOCK_CONDITION = LOCK.newCondition();

    public Parking(int capacity) {
        spots = new boolean[capacity];
    }

    public void park() {
        LOCK.lock();

        int id = Math.abs(random.nextInt());
        if (isAllSpotsOccupied()) {
            try {
                System.out.println(id + " - Wait free spot");
                UNLOCK_CONDITION.await();
            } catch (InterruptedException ignored) {
            }
        }

        for (int i = 0, count = spots.length; i < count; i++) {
            boolean spot = spots[i];
            if (!spot) {
                spots[i] = true;
                System.out.println(id + " - Spot occupied");
                break;
            }
        }

        LOCK.unlock();
    }

    public void unpark() {
        LOCK.lock();
        for (int i = spots.length - 1; i >= 0; i--) {
            boolean spot = spots[i];
            if (spot) {
                spots[i] = false;
                System.out.println("Spot is vacated");
                UNLOCK_CONDITION.signal();
                break;
            }
        }
        LOCK.unlock();
    }

    private boolean isAllSpotsOccupied() {
        for (int i = spots.length - 1; i >= 0; i--) {
            if (!spots[i]) {
                return false;
            }
        }
        return true;
    }
}

package edu.cooper.ece465;

import java.util.Random;

public class Consumer implements Runnable {
    private Drop drop;
    private int id;

    public Consumer(Drop drop, int id) {
        this.id = id;
        this.drop = drop;
        this.drop.addCons();
    }

    public void run() {
        Random random = new Random();
        for (Integer message = drop.take(); message != -1; message = drop.take()) {
            System.out.format("Consumer #%d took: %d%n", this.id, message);
            try {
                Thread.sleep(random.nextInt(10));
            } catch (InterruptedException e) {}
        }

        System.out.format("Consumer #%d Died\n", this.id);

    }
}

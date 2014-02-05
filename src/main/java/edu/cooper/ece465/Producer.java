package edu.cooper.ece465;

import java.util.Random;

public class Producer implements Runnable {
    private Drop drop;
    private int id;

    public Producer(Drop drop, int id) {
        this.id = id;
        this.drop = drop;
        this.drop.addProd();
    }

    public void run() {

        Random random = new Random();

        for (int i = 0; i < 250; i++) {
            System.out.format("Producer #%d put: %d%n", this.id, i);
            drop.put(i);
            try {
                Thread.sleep(random.nextInt(10));
            } catch (InterruptedException e) {}
        }
        drop.rmProd();

        System.out.format("Producer #%d Died\n", this.id);
    }
}

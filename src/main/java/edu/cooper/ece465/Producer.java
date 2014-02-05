package edu.cooper.ece465;

import java.util.Random;

public class Producer implements Runnable {
    private Drop drop;
    private int id;
    private static int thingToProduce = 0;

    public Producer(Drop drop, int id) {
        this.id = id;
        this.drop = drop;
        this.drop.addProd();
    }

    private synchronized int incrementThingToProduce(){
        return this.thingToProduce++;
    }

    public void run() {
        int numProd = 0;
        Random random = new Random();

        for (int tmp = this.incrementThingToProduce(); tmp < 1000; tmp = this.incrementThingToProduce()) {
            System.out.format("Producer #%d put: %d%n", this.id, tmp);
            drop.put(tmp);
            numProd ++;
            try {
                Thread.sleep(random.nextInt(10));
            } catch (InterruptedException e) {}
        }
        drop.rmProd();

        System.out.format("Producer #%d produced %d \n", this.id, numProd);
    }
}

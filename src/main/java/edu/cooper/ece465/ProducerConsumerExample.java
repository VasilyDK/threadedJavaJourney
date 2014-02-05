package edu.cooper.ece465;

public class ProducerConsumerExample {
    public static void main(String[] args) {
        Drop drop = new Drop();
        (new Thread(new Producer(drop, 1))).start();
		// (new Thread(new Producer(drop, 2))).start();
        (new Thread(new Consumer(drop, 1))).start();
        (new Thread(new Consumer(drop, 2))).start();
    }
}

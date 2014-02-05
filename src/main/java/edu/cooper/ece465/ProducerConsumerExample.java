package edu.cooper.ece465;

public class ProducerConsumerExample {
    public static void main(String[] args) {
        Drop drop = new Drop();

		for (int pNum = 1; pNum<5 ; pNum++) {
			(new Thread(new Producer(drop, pNum))).start();
		}

		for (int cNum = 1; cNum<5 ; cNum++) {
			(new Thread(new Consumer(drop, cNum))).start();
		}
    }
}

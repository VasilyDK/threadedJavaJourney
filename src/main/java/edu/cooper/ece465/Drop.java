package edu.cooper.ece465;

import java.util.Queue;
import java.util.LinkedList;

public class Drop {
    // Message sent from producer
    // to consumer.
    private Queue<Integer> messages;
    // True if consumer should wait
    // for producer to send message,
    // false if producer should wait for
    // consumer to retrieve message.

    public Drop(){
        this.messages = new LinkedList<Integer>();
    }

    public synchronized Integer take() {
        // Wait until message is
        // available.
        while (messages.peek() == null) {
            try {
                wait();
            } catch (InterruptedException e) {}
        }
        // Notify producer that
        // status has changed.
        notifyAll();
        if (messages.peek() == -1){
            return messages.peek();
        }
        return messages.remove();
    }

    public synchronized void put(Integer message) {
        // Wait until message has
        // been retrieved.
        while (messages.peek() != null) {
            try {
                wait();
            } catch (InterruptedException e) {}
        }
        // Store message.
        this.messages.add(message);
        // Notify consumer that status
        // has changed.
        notifyAll();
    }
}

package edu.cooper.ece465;

import java.util.Queue;
import java.util.LinkedList;

public class Drop {
    // Message sent from producer
    // to consumer.
    private Queue<String> messages;
    // True if consumer should wait
    // for producer to send message,
    // false if producer should wait for
    // consumer to retrieve message.

    public Drop(){
        this.messages = new LinkedList<String>();
    }

    public synchronized String take() {
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
        return messages.remove();
    }

    public synchronized void put(String message) {
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

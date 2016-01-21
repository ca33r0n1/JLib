package com.j0ach1mmall3.jlib.storage.database;

import java.util.LinkedList;

/**
 * @author j0ach1mmall3 (business.j0ach1mmall3@gmail.com)
 * @since 17/01/16
 */
public final class DatabaseThread extends Thread {
    private boolean running = true;
    private final LinkedList<Runnable> queue = new LinkedList<>();

    @Override
    public void run() {
        while(true) {
            if(!this.queue.isEmpty()) {
                Runnable r = this.queue.removeFirst();
                if(r != null) r.run();
            }
        }
    }

    public void addRunnable(Runnable r) {
        this.queue.addLast(r);
    }

    public void stopThread() {
        this.running = false;
        while(!this.queue.isEmpty()) {
            Runnable r = this.queue.removeFirst();
            if(r != null) r.run();
        }
    }
}

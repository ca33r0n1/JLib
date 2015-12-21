package com.j0ach1mmall3.jlib.storage.database;

import java.util.ArrayList;
import java.util.List;

/**
 * @author j0ach1mmall3 (business.j0ach1mmall3@gmail.com)
 * @since 21/12/15
 */
public final class DatabaseThread extends Thread {
    private boolean running = true;
    private List<Runnable> runnables = new ArrayList<>();

    @Override
    public void run() {
        do {
            for(Runnable r : this.runnables) {
                r.run();
            }
            this.runnables.clear();
        } while (this.running);
    }

    public void addRunnable(Runnable r) {
        this.runnables.add(r);
    }

    public void stopThread() {
        this.running = false;
        for(Runnable r : this.runnables) {
            r.run();
        }
        this.runnables.clear();
    }
}

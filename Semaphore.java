package network;

import java.util.LinkedList;
import java.util.Queue;

public class Semaphore {

    Queue<device> devices;
    int Connection;

    Semaphore() {
        Connection = 0;
        devices = null;
    }

    Semaphore(int Connection) {
        this.Connection = Connection;
        devices = new LinkedList<device>();
    }

    public synchronized void Occupy(device device) {
        device.router.printing(device.name + " (" + device.type + ") Waiting.");
        Connection--;
        if (Connection < 0) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        device.router.Occupy(device);

    }

    public synchronized void Release(device device) {
        device.router.Release(device);
        Connection++;
        if (Connection <= 0) {
            notify();
        }
    }
}

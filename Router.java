package network;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Queue;

public class Router {

    private int max;
    Semaphore S;
    device[] connections;
    int k;                                  //lastConnection

    Router() {
        max = 0;
        S = null;
        connections = null;
        k = 0;
    }

    Router(int maxConnections) {
        this.max = maxConnections;
        S = new Semaphore(maxConnections);
        connections = new device[maxConnections];
        k = 0;
    }

    public void printing(String message) {
        System.out.println(message);
        try {
            FileWriter writer = new FileWriter("output.txt");
            writer.write(message + '\n');
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void Occupy(device device) {
        for (int i = 0; i < max; i++) {
            if (connections[i] == null) {
                connections[i] = device;
                k = i + 1;
                break;
            }
        }
        printing("Connection " + k + ": " + device.name + " (" + device.type + ") Occupied");
    }

    public void Release(device device) {
        for (int i = 0; i < connections.length; i++) {
            if (connections[i] != null && connections[i].name == device.name) {
                k = i + 1;
                connections[i] = null;
                break;
            }
        }
        printing("Connection " + k + ": " + device.name + " (" + device.type + ") Released.");
    }

}

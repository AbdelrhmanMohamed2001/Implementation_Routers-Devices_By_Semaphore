package network;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Network {

    public static void main(String[] args) {

        Router router = new Router();
        int maxConnections, numOfDevices;
        ArrayList<device> devices = new ArrayList<>();
        Scanner intInput = new Scanner(System.in);
        Scanner stringInput = new Scanner(System.in);

        // Take router input
        System.out.println("What is the number of WI-FI Connections? ");
        maxConnections = intInput.nextInt();
        if (maxConnections < 1) {
            while (maxConnections < 1) {
                System.out.println("ERROR...Enter a positive number greater than 0: ");
                maxConnections = intInput.nextInt();
            }
        } else {
            router = new Router(maxConnections);
        }

        // Take devices input
        System.out.println("What is the number of devices Clients want to connect?");
        numOfDevices = intInput.nextInt();
        if (numOfDevices < 1) {
            while (numOfDevices < 1) {
                System.out.println("Please enter a positive number greater than 0: ");
                numOfDevices = intInput.nextInt();
            }
        } else {
            for (int i = 0; i < numOfDevices; i++) {
                System.out.printf("Enter Device " + (i + 1) + " Name: ");
                String newName = stringInput.nextLine();
                System.out.printf("Enter Device " + (i + 1) + " Type: ");
                String newType = stringInput.nextLine();
                device newDevice = new device(newName, newType, router);
                devices.add(newDevice);
            }
        }

        // Start queueing devices to connect to router
        for (int i = 0; i < devices.size(); i++) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            devices.get(i).start();
        }
    }
}

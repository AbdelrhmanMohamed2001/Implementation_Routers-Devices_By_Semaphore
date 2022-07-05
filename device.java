package network;

import java.util.Random;

public class device extends Thread {

    String name;
    String type;
    Router router;

    device() {
        this.name = "";
        this.type = "";
        this.router = null;
    }

    device(String name, String type, Router router) {
        this.name = name;
        this.type = type;
        this.router = router;
    }

    public void doActivity() {
        router.printing("Connection " + router.k + ": " + name + " (" + type + ")  performs online activity ");
        try {
            Thread.sleep(new Random().nextInt(10000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        router.S.Occupy(this);
        doActivity();
        router.S.Release(this);
    }
}

package server;

import java.io.IOException; 
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;

public class ServiceThread extends Thread {

    private Socket socket;
    private ObjectOutputStream out;
    private ObjectInputStream in;

    public ServiceThread(Socket socket) throws IOException {
        this.socket = socket;
        out = new ObjectOutputStream(socket.getOutputStream());
        in = new ObjectInputStream(socket.getInputStream());
    }

    public void run() {
        try {
            while (true) {
                String msj = null;
                List<String> numere = (List<String>) in.readObject();
                if (numere.get(1).equals("fem")) {
                    msj = "Bonjour" + " Mme. " + numere.get(0);
                }
                else if (numere.get(1).equals("masc")) {
                    msj = "Bonjour" + " M. " + numere.get(0);
                }else
                    msj = "Nu ati ales sau ati scris gresit";
                

                out.writeObject(msj);
                in.close();out.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

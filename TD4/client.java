package client; 

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.stream.Stream;

public class Client {

    public static void main(String[] args) {

        try {
            Socket socket = new Socket("localhost", 7777);
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());

            Scanner sc = new Scanner(System.in);
            System.out.println("Scrieti numele :");
            String nume = sc.nextLine();
            System.out.println("Sexul: (masc/fem)");
            String s = sc.nextLine();

            List<String> comp = new ArrayList<>();
            comp.add(nume);
            comp.add(s);
            out.writeObject(comp);

            String rez = (String) in.readObject();
            System.out.println(rez);
            
           
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

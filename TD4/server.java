package server;

import java.net.ServerSocket; 


public class Server {

    public static void main(String[] args) {
        try{
            ServerSocket ss = new ServerSocket(7777);
            while(true){
                new ServiceThread(ss.accept()).start();
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
}

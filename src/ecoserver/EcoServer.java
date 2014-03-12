/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ecoserver;

import java.io.IOException;
import java.net.ServerSocket;

/**
 *
 * @author cosentino.tommaso
 */
public class EcoServer {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        int port=6000;
        ServerSocket ss=new ServerSocket(port);
        while(true){
            new EcoThread(ss.accept()).start();
            System.out.println("connesso");
        }
    }   
}

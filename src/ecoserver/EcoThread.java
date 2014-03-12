 /*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ecoserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

/**
 *
 * @author cosentino.tommaso
 */
class EcoThread extends Thread{
    Socket s;
    String frase, invio, host;
    boolean maiuscole = false;
    public EcoThread(Socket s){
        this.s=s;
    }
    public void run(){
        try{
            BufferedReader in=new BufferedReader(new InputStreamReader(s.getInputStream()));
            PrintWriter out=new PrintWriter(new OutputStreamWriter (s.getOutputStream()),true);

            while(true){
                frase = in.readLine();
                switch(frase){
                    case "fine":
                        out.println("fine");
                        s.close();
                        break;
                    case "maiuscole: on":
                        maiuscole = true;
                        System.out.println("maiuscole: on");
                        out.println();
                        break;
                    case "maiuscole: off":
                        maiuscole = false;
                        System.out.println("maiuscole: off");
                        out.println();
                        break;
                    default:
                        if(maiuscole == true){
                            frase = frase.toUpperCase();
                        }
                        out.println(frase);
                        break;
                }
            }
        }catch(IOException e){}
    }
}
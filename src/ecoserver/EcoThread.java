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
    String controllo, invio;
    boolean maiuscole = false;
    public EcoThread(Socket s){
        this.s=s;
    }
    public void run(){
        try{
            BufferedReader in=new BufferedReader(new InputStreamReader(s.getInputStream()));
            PrintWriter out=new PrintWriter(new OutputStreamWriter (s.getOutputStream()),true);

            while(true){
                switch(in.readLine()){
                    case "fine":
                        this.interrupt();
                        break;
                    case "Fine":
                        this.interrupt();
                        break;
                    case "FINE":
                        this.interrupt();
                        break;
                    case "maiuscole: on":
                        maiuscole = true;
                        break;
                    case "maiuscole: off":
                        maiuscole = false;
                        break;
                    default:
                        if(maiuscole == false){
                            out.println(invio);
                        }else if(maiuscole == true){
                            out.println(invio.toUpperCase());
                        }
                        break;
                }
            }
        }catch(IOException e){}
    }
}
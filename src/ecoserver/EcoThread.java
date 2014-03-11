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
                if(in.readLine() == "fine" || in.readLine() == "Fine" || in.readLine() == "FINE"){
                        this.interrupt();
                }
                if(in.readLine() == "maiuscole: on")
                        maiuscole = true;
                if(in.readLine() == "maiuscole: off")
                        maiuscole = false;
                if(maiuscole == false){
                    out.println(invio);
                }else if(maiuscole == true){
                    out.println(invio.toUpperCase());
                }
            }
        }catch(IOException e){}
    }
}
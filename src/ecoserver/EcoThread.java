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

            while(in.readLine() != "fine"){
                if(in.readLine().equals("maiuscole: on")){
                    maiuscole = true;
                    out.println("maiuscole: on");
                }else if(in.readLine().equals("maiuscole: off")){
                    maiuscole = false;
                    out.println("maiuscole: off");
                }else if(maiuscole == false){
                    invio = in.readLine();
                    out.println(invio);
                    out.flush();
                }else if(maiuscole == true){
                    invio = in.readLine();
                    out.println(invio.toUpperCase());
                    out.flush();
                }
            }
            s.close();
        }catch(IOException e){}
    }
}
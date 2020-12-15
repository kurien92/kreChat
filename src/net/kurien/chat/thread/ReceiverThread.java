package net.kurien.chat.thread;

import java.io.*;
import java.net.Socket;

public class ReceiverThread extends Thread {
    private Socket socket;

    public void run() {
        super.run();

        try {
            InputStream inputStream = socket.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

            while(true) {
                String inputString = "[input] " + bufferedReader.readLine();

                Thread.sleep(2000);

                System.out.println(inputString);
            }
        } catch(IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

}

package net.kurien.chat.thread;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class SenderThread extends Thread {
    private Socket socket;
    private String text;

    public void run() {
        super.run();

        try {
            OutputStream outputStream = socket.getOutputStream();
            PrintWriter printWriter = new PrintWriter(outputStream);

            while(true) {
                String outputString = "[output, " + socket.getLocalAddress() + ", " + socket.getLocalPort() + "] " + text;

                printWriter.println(outputString);

                printWriter.flush();

                Thread.sleep(2000);
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

    public void setText(String text) {
        this.text = text;
    }

}

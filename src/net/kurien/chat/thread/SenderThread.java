package net.kurien.chat.thread;

import java.io.*;
import java.net.Socket;

public class SenderThread extends Thread {
    private Socket socket;

    public void run() {
        super.run();

        BufferedReader bufferedReader = null;
        PrintWriter printWriter = null;

        try {
            bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            printWriter = new PrintWriter(socket.getOutputStream());

            while(true) {
                String outputString = bufferedReader.readLine();

                printWriter.println(outputString);

                printWriter.flush();

                if(outputString.equals("exit")) {
                    break;
                }
            }

            bufferedReader.close();
            printWriter.close();
        } catch(IOException e) {
            e.printStackTrace();
        } finally {
            if(socket != null && !socket.isClosed()) {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if(bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if(printWriter != null) {
                printWriter.close();
            }
        }
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }
}

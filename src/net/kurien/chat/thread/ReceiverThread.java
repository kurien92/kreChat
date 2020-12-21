package net.kurien.chat.thread;

import java.io.*;
import java.net.Socket;
import java.net.SocketException;

public class ReceiverThread extends Thread {
    private Socket socket;

    public void run() {
        super.run();

        BufferedReader bufferedReader = null;

        try {
            bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            while(true) {
                System.out.println("test");
                String inputString = bufferedReader.readLine();

                if(inputString == null || inputString.equals("exit")) {
                    System.out.println(socket.getRemoteSocketAddress());
                    break;
                }

                System.out.println(inputString);
            }
        } catch (SocketException e) {
            System.out.println("socket exit");
        } catch(IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if(socket != null && !socket.isClosed()) {
                    socket.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                if(bufferedReader != null) {
                    bufferedReader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

}

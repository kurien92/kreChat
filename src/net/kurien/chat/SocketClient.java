package net.kurien.chat;

import net.kurien.chat.thread.ReceiverThread;
import net.kurien.chat.thread.SenderThread;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

public class SocketClient {
    private static final String SERVER_IP = "127.0.0.1";
    private static final int SERVER_PORT = 5000;

    public static void main(String[] args) {
        Socket socket = null;

        try {
            // 1. 서버에 연결할 소켓을 생성한다.
            socket = new Socket();

            // 2. 연결될 서버의 정보를 입력한 후 연결한다.
            socket.connect(new InetSocketAddress(SERVER_IP, SERVER_PORT));

            // 3. 수신 쓰레드 생성
            ReceiverThread receiverThread = new ReceiverThread();
            receiverThread.setSocket(socket);

            // 4. 발신 쓰레드 생성
            SenderThread senderThread = new SenderThread();
            senderThread.setSocket(socket);
            senderThread.setText("send Client");

            receiverThread.start();
            senderThread.start();

            // 5. 더 이상 진행할 작업이 없으므로 프로그램이 종료된다.
        } catch(IOException e) {
            e.printStackTrace();
        } finally {
        }
    }
}

package net.kurien.chat;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketServer {
    public static void main(String[] args) {
	    final int SERVER_PORT = 5000;

        ServerSocket serverSocket = null;

        try {
            // 1.클라이언트에게 서비스를 제공할 서버 소켓을 생성한다.
            serverSocket = new ServerSocket();

            String serverAddress = "127.0.0.1";
            System.out.println(serverAddress);
            // 2. 서버 소켓에 접근할 수 있는 IP와 Port를 연결시켜준다.
            serverSocket.bind(new InetSocketAddress(serverAddress, SERVER_PORT));

            System.out.println("serverSocket.bind - serverAddress:" + serverAddress + ", serverPort:" + SERVER_PORT);

            ChatUserPool chatUserPool = new ChatUserPool();

            while(true) {
                // 3. 클라이언트가 연결될 때까지 대기하고, 클라이언트가 연결되면 클라이언트와 연결된 socket을 생성한다.
                Socket socket = serverSocket.accept();

                // 4. 클라이언트의 IP와 Port 정보를 가져온다.
                InetSocketAddress remoteSocketAddress = (InetSocketAddress) socket.getRemoteSocketAddress();
                String remoteAddress = remoteSocketAddress.getAddress().getHostAddress();
                int remotePort = remoteSocketAddress.getPort();

                System.out.println("serverSocket.accept -\n remoteAddress:" + remoteAddress + ", remotePort:" + remotePort);

                // 5. 수신/발신 쓰레드를 생성하는 채팅 커넥터를 생성한다.
                ChatUser chatUser = new ChatUser(socket);
                chatUserPool.addChatUser(remoteAddress + ":" + remotePort, chatUser);
            }

            // 6. 더 이상 진행할 작업이 없으므로 프로그램이 종료된다.
        } catch(IOException e) {
            e.printStackTrace();
        } finally {
        }
    }
}

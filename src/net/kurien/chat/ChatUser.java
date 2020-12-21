package net.kurien.chat;

import net.kurien.chat.thread.ReceiverThread;
import net.kurien.chat.thread.SenderThread;

import java.net.Socket;

public class ChatUser {
    private Socket socket = null;

    public ChatUser(Socket socket) {
        this.socket = socket;

        // 1. 수신 쓰레드 생성
        ReceiverThread receiverThread = new ReceiverThread();
        receiverThread.setSocket(socket);

        // 2. 발신 쓰레드 생성
        SenderThread senderThread = new SenderThread();
        senderThread.setSocket(socket);

        // 3. 수신/발신 쓰레드 시작
        receiverThread.start();
        senderThread.start();
    }
}

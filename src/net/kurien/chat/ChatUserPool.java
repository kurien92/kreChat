package net.kurien.chat;

import java.util.HashMap;
import java.util.Map;

public class ChatUserPool {
    private Map<String, ChatUser> chatUserPool = new HashMap<>();

    public void addChatUser(String id, ChatUser chatUser) {
        if(chatUserPool.containsKey(id)) {
            chatUserPool.remove(id);
        }

        chatUserPool.put(id, chatUser);
    }

    public void removeChatUser(String id) {
        if(chatUserPool.containsKey(id)) {
            chatUserPool.remove(id);
        }
    }
}

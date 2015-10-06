package com.pbattles.socketio;

import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.Configuration;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.listener.DataListener;

import java.util.List;

/**
 * Created by Nazar_Sheremeta on 5/13/14.
 */
public class SignalSocketIOServer {

    private SocketIOServer server;

    private String host;

    private int port;

    public void init() throws InterruptedException {
        Configuration config = createConfiguration();
        server = new SocketIOServer(config);
        addEventListeners();
        server.start();
    }

    public void close(){
        server.stop();
    }

    private void addEventListeners() {
        addJoinRoomEventListener(server);
        addMessageEventListener(server);
    }


    private Configuration createConfiguration() {
        Configuration config = new Configuration();
        config.setHostname(host);
        config.setPort(port);
        return config;
    }


    private void addJoinRoomEventListener(SocketIOServer server) {
        server.addEventListener("join",String.class, new DataListener<String>() {
            @Override
            public void onData(SocketIOClient client, String data, AckRequest ackSender) {
                client.joinRoom(data);
                System.out.println("User has connected and joined room "+data);
            }
        });
    }

    private void addMessageEventListener(final SocketIOServer server) {
        server.addEventListener("message", Object.class, new DataListener<Object>() {
            @Override
            public void onData(SocketIOClient client, Object data, AckRequest ackRequest) {
                String room = getCurrentRoom(client);
                server.getRoomOperations(room).sendEvent("message", data);
            }
        });
    }

    private String getCurrentRoom(SocketIOClient client) {
        List<String> rooms = client.getAllRooms();
        if (rooms == null) return null;
        return getLastElement(rooms);
    }

    private String getLastElement(List<String> rooms) {
        return rooms.get(rooms.size() - 1);
    }

    public void setPort(int port) {
        this.port = port;
    }

    public void setHost(String host) {
        this.host = host;
    }
}

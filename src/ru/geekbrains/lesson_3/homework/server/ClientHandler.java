package ru.geekbrains.lesson_3.homework.server;

import ru.geekbrains.lesson_3.homework.db.Service;
import ru.geekbrains.lesson_3.homework.entity.User;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Objects;

public class ClientHandler {
    private Server server;
    private Socket socket;
    private DataInputStream in;
    private DataOutputStream out;
    private Service clientService;
    private User thisUser;

    private String name;

    public String getName() {
        return name;
    }

    public ClientHandler(Server server, Socket socket) {
        try {
            this.server = server;
            this.socket = socket;
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());

            listenForClientMsg();

        } catch (IOException e){
            throw new RuntimeException("SWW during connection to server", e);
        }
    }

    private void doAuth() {
        try {
            while (true){
                String str = in.readUTF();
                if (str.startsWith("-auth")){
                    String[] parts = str.split("\\s");
                    server.getAuthenticationService().doAuthentication(parts[1], parts[2]).ifPresentOrElse(
                            user -> {
                                if (!server.isLoggedIn(user.getNickname())){
                                    server.subscribe(this);
                                    name = user.getNickname();
                                    sendMsg(name + " enters the chat");
                                    server.broadcastMsg(name + " is logged in");
                                } else {
                                    sendMsg("Authentication error. Account already logged in.");
                                }
                            },
                            () -> sendMsg("Wrong login or password")
                        );
                }
                return;
            }
        } catch (IOException e){
            throw new RuntimeException("SWW during auth", e);
        }
    }



    private void listenForClientMsg(){
        new Thread(()->{
                doAuth();
            try {
                receiveMsg();
            } catch (Exception ex){
                throw new RuntimeException("SWW during listenForClientMsg", ex);
            } finally {
                server.unsubscribe(this);
            }
        }).start();
    }

    private void receiveMsg(){
        try {
            while (true){
                StringBuilder msg = new StringBuilder(in.readUTF());
                if (msg.toString().equals("-exit")){
                    sendMsg(name + ": left the chat");
                    break;
                }
                if (msg.toString().startsWith("-c.nick")){
                    String[] parts = msg.toString().split("\\s");
                    if (parts.length > 2){
                        sendMsg("Wrong nickname. Please try again.");
                        receiveMsg();
                    }
                    String newName = parts[1];
                    int id = thisUser.getId();
//                    clientService.doChangeNickname(id, newName); //не работает
                    server.getChangeNickname().doChangeNickname(id, newName); //не работает
                    sendMsg("Nickname changed to: " + newName);
                }
                    if (msg.toString().startsWith("/w")){
                    String[] parts = msg.toString().split("\\s");
                    String nickname = parts[1];
                    msg = new StringBuilder();
                    for (int i = 0; i < parts.length; i++) {
                        if (i > 1){
                            msg.append(" ").append(parts[i]);
                        }
                    }
                    server.sendPrivateMessage(this, nickname, msg.toString());
                } else {
                    server.broadcastMsg(name + ": " + msg.toString());
                }
            }
        } catch (IOException e){
            throw new RuntimeException("SWW", e);
        }
    }
    public void sendMsg(String message){
        try {
            out.writeUTF(message);
        } catch (IOException e) {
            throw new RuntimeException("SWW", e);
        }
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClientHandler that = (ClientHandler) o;
        return Objects.equals(server, that.server) &&
                Objects.equals(socket, that.socket) &&
                Objects.equals(in, that.in) &&
                Objects.equals(out, that.out) &&
                Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(server, socket, in, out, name);
    }
}

import java.io.*;
import java.net.*;
import Model.GameMap;
import Model.Maps;

import java.util.Map;
import java.util.HashMap;

public class MudServer {

    static final int PORT = 9330;

    public static void main(String args[]) {
        ServerSocket serverSocket = null;
        Socket socket = null;
        Map<Integer, GameMap> maps = Maps.getInstance().getMaps();

        try {
            serverSocket = new ServerSocket(PORT);
        } catch (IOException e) {
            e.printStackTrace();

        }
        int id = 0;
        
        while (true) {
            try {
                socket = serverSocket.accept();
            } catch (IOException e) {
                System.out.println("I/O error: " + e);
            }
            // new thread for a client
            new ClientThread(socket, maps, id % 2).start();
            id += 1;
        }
    }
}

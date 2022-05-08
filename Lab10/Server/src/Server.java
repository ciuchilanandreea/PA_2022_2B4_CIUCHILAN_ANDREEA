import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class Server {
    public static final int port = 8101;
    private static final List<User> userList=new ArrayList<>();
    private static final List<ClientThread>activeThread=new ArrayList<>();

    public Server() {
        ServerSocket serverSocket = null;
        Socket socket = null;
        try {
            serverSocket = new ServerSocket(port);
            while (true) {
                if (!serverSocket.isClosed()) {
                    try {
                        System.out.println("Waiting for client...");
                        socket = serverSocket.accept();
                        System.out.println("New client connected");
                        ClientThread currentClient = new ClientThread(socket, serverSocket);
                        currentClient.start();
                        activeThread.add(currentClient);
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                    } catch (SocketException e) {
                        System.out.println("Socket closed");
                    }
                }

                if(serverSocket.isClosed()){
                    waitForClients();
                    System.out.println("Server stopped successfully.");
                    System.exit(0);
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (serverSocket != null) {
                    serverSocket.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void waitForClients() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for (ClientThread t:activeThread) {
            while (t.isClientConnected()) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    public static List<User> getUserList() {
        return userList;
    }

}

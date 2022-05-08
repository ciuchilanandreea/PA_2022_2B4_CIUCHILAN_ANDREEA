import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.StringTokenizer;

public class ClientThread extends Thread{
    private final Socket socket;
    private final ServerSocket serverSocket;
    private boolean clientDisconnect=false;
    User connectedUser=new User();

    public ClientThread(Socket socket,ServerSocket serverSocket){
        this.socket=socket;
        this.serverSocket=serverSocket;
    }
    public void run(){
        try{
            System.out.println("New thread created..");
            BufferedReader in=new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out;
            double minutes=1;
            socket.setSoTimeout((int)(minutes*60*1000));
            while(true){

                JGraph.main(Server.getUserList());
                clientDisconnect=false;
                String command;
                try{
                    command=in.readLine();
                    System.out.println("Server received the command: " + command );
                }catch(SocketTimeoutException e){
                    System.out.println("User "+this+" disconnected due to inactivity");
                    out=new PrintWriter(socket.getOutputStream(),true);
                    out.println("disconnect");
                    connectedUser.setConnected(false);
                    clientDisconnect=true;
                    in.close();
                    break;
                }
                out=new PrintWriter(socket.getOutputStream(),true);
                out.println("connection ok");
                String parameters=in.readLine();
                command=command.toLowerCase();
                switch (command) {
                    case "register":{
                        boolean userExists=false;
                        out=new PrintWriter(socket.getOutputStream(),true);
                        for (User person:Server.getUserList())
                            if(person.getName().equalsIgnoreCase(parameters)){
                                out.println("User "+parameters+" already exists.");
                                userExists=true;
                                break;
                            }
                        if(!userExists)
                        {
                            out.println("User "+parameters+" registered successfully.");
                            User person= new User(parameters,this);
                            Server.getUserList().add(person);
                        }
                    }
                    case "login":{
                        boolean connected=false;
                        out=new PrintWriter(socket.getOutputStream(),true);
                        for (User person:Server.getUserList())
                            if(person.getName().equalsIgnoreCase(parameters))
                                if(person.getThread()!=this && person.isConnected()){
                                    out.println("User already connected on another session. Please disconnect first.");
                                    connected=true;
                                    break;
                                }
                                else {
                                    connected = true;
                                    person.setThread(this);
                                    connectedUser=person;
                                    connectedUser.setConnected(true);
                                    out.println("Connected successfully.");
                                    break;
                                }
                        if(!connected)
                            out.println("Username not registered. Please use register command first.");

                    }
                    case "friend":{
                        out=new PrintWriter(socket.getOutputStream(),true);
                        if(!connectedUser.isConnected)
                            out.println("You are not connected. Please log in first.");
                        else
                        {
                            StringTokenizer defaultTokenizer=new StringTokenizer(parameters);
                            String personToAdd="something not null";
                            while(defaultTokenizer.hasMoreTokens()&&personToAdd!=null){
                                personToAdd=defaultTokenizer.nextToken();
                                boolean foundUser=false;
                                out.println("yes");
                                for(User person:Server.getUserList())
                                    if(personToAdd.equalsIgnoreCase(person.getName())) {
                                        foundUser=true;
                                        if (!personToAdd.equalsIgnoreCase(connectedUser.getName())) {
                                            if (!connectedUser.getFriends().contains(person)) {
                                                person.addFriend(connectedUser);
                                                connectedUser.addFriend(person);
                                                out.println("Username " + personToAdd + " added to your friend list.");
                                            } else out.println(personToAdd + " is already in your friend list.");
                                        }
                                        else out.println("You cannot add yourself to the friend list.");
                                    break;
                                    }

                                if(!foundUser)
                                    out.println("Username "+personToAdd+" does not exist.");
                            }
                            out.println("no");
                        }

                    }
                    case "send":{
                        out=new PrintWriter(socket.getOutputStream(),true);
                        for(User friend:connectedUser.getFriends()){
                            StringBuilder inbox=friend.getInbox();
                            inbox.insert(friend.getInbox().length(),connectedUser.getName());
                            inbox.append(" sent you: ").append(parameters).append(". ");

                        }
                        out.println("Message sent.");

                    }
                    case "read":{
                        out=new PrintWriter(socket.getOutputStream(),true);
                        if(connectedUser.getInbox().length()>0)
                            out.println(connectedUser.getInbox());
                        else
                            out.println("Mailbox empty.");
                    }
                    case "exit": {
                        in.close();
                        out.close();
                        System.out.println("Client "+this+" disconnected...");
                        connectedUser.setConnected(false);
                        clientDisconnect = true;
                    }
                    case "stop": {
                        out = new PrintWriter(socket.getOutputStream(), true);
                        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                        String msg="Server will stop when all connections end. No other connections are accepted.";
                        out.println(msg);
                        System.out.println(msg);
                        serverSocket.close();
                    }
                }
                if(clientDisconnect)
                    break;



            }

            //String raspuns = "The received command is " + command + "!";
            //out.println(raspuns);
            //out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            try {
                System.out.println("Thread closed...");
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean isClientConnected() {
        return !clientDisconnect;
    }

}

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketException;
import java.util.Objects;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        String serverAddress="192.168.0.118";
        int port=8101;
        try{
            Socket socket=new Socket();
            socket.connect(new InetSocketAddress(serverAddress,port),0);
            System.out.println("Connected to server");
            PrintWriter out=new PrintWriter(socket.getOutputStream(),true);
            BufferedReader in;
            Scanner request;
            while(true){
                boolean clientDisconnect=false;
                System.out.print("Enter a command: ");
                in=new BufferedReader(new InputStreamReader(socket.getInputStream()));
                request= new Scanner(System.in);
                String command= request.nextLine();
                String serviceName=getServiceName(command);
                out.println(serviceName);
                try{
                    String willDisconnect=in.readLine();
                }catch(SocketException e){
                    System.out.println("You were disconnected due to inactivity...");
                    socket.close();
                    out.close();
                    in.close();
                    break;
                }

                String parameters=null;
                if(!socket.isClosed()) {
                    if(serviceName.equalsIgnoreCase("-1"))
                        continue;
                    if(command.length()>serviceName.length()){
                        parameters=command.substring(serviceName.length()+1);
                    }
                    out=new PrintWriter(socket.getOutputStream(),true);
                    out.println(parameters);
                    switch (serviceName) {
                        case "register":{
                            String msg = in.readLine();
                            System.out.println(msg);
                        }
                        case "login" :{
                            String msg = in.readLine();
                            System.out.println(msg);
                        }
                        case "stop" :{
                            String msg = in.readLine();
                            System.out.println(msg);
                        }
                        case "send":{
                            String msg = in.readLine();
                            System.out.println(msg);
                        }
                        case "read" :{
                            String msg = in.readLine();
                            System.out.println(msg);
                        }
                        case "friend":{
                            String stillAdding;
                            do {
                                stillAdding = in.readLine();
                                if (stillAdding != null) {
                                    if (stillAdding.equalsIgnoreCase("yes")) {
                                        String msg = in.readLine();
                                        System.out.println(msg);
                                    }
                                } else
                                    break;

                            } while (Objects.requireNonNull(stillAdding).equalsIgnoreCase("yes"));
                        }
                        case "exit": clientDisconnect = true;
                        default: printInvalidCommand();
                    }
                }
                else{
                    System.out.println("You were disconnected due to inactivity...");
                    clientDisconnect=true;
                }

                if(clientDisconnect){
                    socket.close();
                    out.close();
                    in.close();
                    System.out.println("Exited server...");
                    break;
                }
            }

        } catch (IOException e) {
            System.err.println("No server listening..."+e);
        }
    }
    public static String getServiceName(String command){
        int spaceIndex=-1;
        command=command.toLowerCase();
        for(int i=0;i<command.length();i++)
            if(command.charAt(i)==' '){
                if(spaceIndex==-1)
                    spaceIndex=i;
            }
            else if(command.charAt(i)<'a'||command.charAt(i)>'z'){
                printInvalidCommand();
                return "-1";
            }
        String serviceName=null;
        if(spaceIndex!=-1)
            serviceName=command.substring(0,spaceIndex);
        else
            serviceName=command;
        return serviceName;
    }
    public static void printInvalidCommand(){
        System.out.println("Please check the documentation. Command not valid.");
    }
}


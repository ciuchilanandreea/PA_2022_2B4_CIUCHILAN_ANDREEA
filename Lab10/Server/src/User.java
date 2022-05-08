import java.util.ArrayList;

public class User {
    private String name;
    private ClientThread thread;
    boolean isConnected=false;
    ArrayList<User>friends=new ArrayList<>();
    StringBuilder inbox=new StringBuilder();

    public User(String name, ClientThread thread){
        this.name=name;
        this.thread=thread;
    }

    public User() {
    }
    public User(User copyUser){
        this.name=copyUser.name;
        this.thread=copyUser.thread;
    }

    public String getName() {
        return name;
    }

    public ClientThread getThread() {
        return thread;
    }

    public void setThread(ClientThread thread) {
        this.thread = thread;
    }

    public ArrayList<User> getFriends() {
        return friends;
    }
    public void addFriend(User friend){
        this.friends.add(friend);
    }

    public boolean isConnected() {
        return isConnected;
    }

    public void setConnected(boolean connected) {
        isConnected = connected;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", thread=" + thread +
                ", isConnected=" + isConnected +
                ", friends=" + friends +
                '}';
    }

    public StringBuilder getInbox() {
        return inbox;
    }

    public void setInbox(StringBuilder inbox) {
        this.inbox = inbox;
    }
}

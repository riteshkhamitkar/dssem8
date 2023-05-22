import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;
public class ChatServer extends UnicastRemoteObject implements ChatService {
private List<String> messages;
public ChatServer() throws RemoteException {
super();
messages = new ArrayList<>();
}
@Override
public synchronized void sendMessage(String message) throws RemoteException {
messages.add(message);
System.out.println("New message: " + message);
}
@Override
public synchronized String receiveMessage() throws RemoteException {
if (messages.isEmpty()) {
return "No new messages.";
}
return messages.remove(0);
}
public static void main(String[] args) {
try {
ChatServer server = new ChatServer();
java.rmi.registry.LocateRegistry.createRegistry(1099);
java.rmi.Naming.rebind("//localhost/ChatService", server);
System.out.println("Chat Server is running.");
} catch (Exception e) {
System.err.println("Chat Server exception: " + e.toString());
e.printStackTrace();
}
}
}
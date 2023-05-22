import java.rmi.Naming;
import java.util.Scanner;
public class ChatClient {
public static void main(String[] args) {
try {
ChatService chatService = (ChatService) Naming.lookup("//localhost/ChatService");
Scanner scanner = new Scanner(System.in);
System.out.print("Enter your name: ");
String name = scanner.nextLine();
while (true) {
System.out.print("[" + name + "] Enter message (or 'exit' to quit): ");
String message = scanner.nextLine();
if (message.equalsIgnoreCase("exit")) {
break;
}

chatService.sendMessage("[" + name + "] " + message);
System.out.println("Received: " + chatService.receiveMessage());
}
System.out.println("Chat ended.");
} catch (Exception e) {
System.err.println("Chat Client exception: " + e.toString());
e.printStackTrace();
}
}
}
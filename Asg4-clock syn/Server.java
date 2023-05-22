import java.net.*;
import java.io.*;

public class Server {
    public static void main(String[] args) throws Exception {
        ServerSocket serverSocket = new ServerSocket(2000);
        System.out.println("Server started. Waiting for client connection...");

        // Accept client connection
        Socket client = serverSocket.accept();
        System.out.println("Client connected.");

        // Receive time request from client
        BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
        long clientTime = Long.parseLong(in.readLine());
        System.out.println("Received time request from client: " + clientTime);

        // Get server's current time
        long serverTime = System.currentTimeMillis();
        System.out.println("Server time: " + serverTime);

        // Send server's current time to client
        PrintWriter out = new PrintWriter(client.getOutputStream(), true);
        out.println(serverTime);

        // Receive clock difference from client
        long clockDifference = Long.parseLong(in.readLine());
        System.out.println("Received clock difference from client: " + clockDifference);

        // Calculate average clock difference
        long averageDifference = (clockDifference + serverTime - clientTime) / 2;
        System.out.println("Calculated average clock difference: " + averageDifference);

        // Send average clock difference to client
        out.println(averageDifference);

        // Close connections
        in.close();
        out.close();
        client.close();
        serverSocket.close();
    }
}

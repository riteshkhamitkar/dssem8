import java.net.*;
import java.io.*;

public class Client {
    public static void main(String[] args) throws Exception {
        Socket server = new Socket("localhost", 2000);

        // Get current time
        long currentTime = System.currentTimeMillis();
        System.out.println("Current client time: " + currentTime);

        // Send time request to server
        PrintWriter out = new PrintWriter(server.getOutputStream(), true);
        out.println(currentTime);

        // Receive server's current time
        BufferedReader in = new BufferedReader(new InputStreamReader(server.getInputStream()));
        long serverTime = Long.parseLong(in.readLine());
        System.out.println("Received server time: " + serverTime);

        // Calculate clock difference
        long clockDifference = serverTime - currentTime;
        System.out.println("Calculated clock difference: " + clockDifference);

        // Send clock difference to server
        out.println(clockDifference);

        // Receive average clock difference from server
        String averageClockDifference = in.readLine();
        long averageDifference = Long.parseLong(averageClockDifference);
        System.out.println("Received average clock difference: " + averageDifference);

        // Adjust client clock
        long adjustedTime = serverTime + averageDifference;
        System.out.println("Client adjusted time: " + adjustedTime);

        // Close connections
        in.close();
        out.close();
        server.close();
    }
}

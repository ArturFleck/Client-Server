import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

public class Client {
    public static void main(String[] args) {
        //String serverAddress = "localhost"; // Replace with the server's IP address or hostname
        String serverAddress = "192.168.2.134"; // Replace with the server's IP address or hostname
        int serverPort = 4444;

        try {
            // Create a socket connection to the server
            Socket socket = new Socket(serverAddress, serverPort);

            // Get the output stream of the socket
            OutputStream outputStream = socket.getOutputStream();

            // Send the message to the server
            //String message = "one";
            String message = "break";
            System.out.println(message);
            outputStream.write(message.getBytes());
            outputStream.flush();

            // Close the resources
            outputStream.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static void main(String[] args) {
        int serverPort = 4444;


        try {
            // Create a server socket and bind it to the specified port
            ServerSocket serverSocket = new ServerSocket(serverPort);
            System.out.println("Server listening on port " + serverPort);

            boolean shouldContinue = true;

            while (shouldContinue) {
                // Wait for a client connection
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client connected: " + clientSocket.getInetAddress().getHostAddress());

                // Get the input stream of the client socket
                InputStream inputStream = clientSocket.getInputStream();

                // Read the message sent by the client
                byte[] buffer = new byte[1024];
                int bytesRead = inputStream.read(buffer);
                String message = new String(buffer, 0, bytesRead);
                System.out.println("Received message: " + message);

                File log = new File("D:/_profile/Desktop/log.txt");
                if(!log.exists()){
                    log.createNewFile();
                }
                FileWriter fw = new FileWriter(log,true);
                fw.write(message+"\n");
                fw.close();

                if (message.trim().equals("mouse")) {
                    Point pm = MouseInfo.getPointerInfo().getLocation();
                    int x, y;
                    x = pm.x;
                    y = pm.y;
                    System.out.println("x=" + x + "  y=" + y);

                    int xCoord = 1817;
                    int yCoord = 43;

                    // Move the cursor
                    int mask = InputEvent.BUTTON1_DOWN_MASK;
                    Robot robot = new Robot();
                    robot.mouseMove(xCoord, yCoord);
                    robot.mousePress(mask);
                    Thread.sleep(500);
                    robot.mouseRelease(mask);
                }

                if (message.trim().equals("mouse2")) {
                    Point pm = MouseInfo.getPointerInfo().getLocation();
                    int x, y;
                    x = pm.x;
                    y = pm.y;
                    System.out.println("x=" + x + "  y=" + y);

                    int xCoord = 1000;
                    int yCoord = 40;

                    // Move the cursor
                    int mask = InputEvent.BUTTON1_DOWN_MASK;
                    Robot robot = new Robot();
                    robot.mouseMove(xCoord, yCoord);
                    robot.mousePress(mask);
                    Thread.sleep(50);
                    robot.mouseRelease(mask);

                    robot.keyPress(KeyEvent.VK_CONTROL);
                    robot.keyPress(KeyEvent.VK_A);
                    Thread.sleep(50);
                    robot.keyRelease(KeyEvent.VK_CONTROL);
                    robot.keyRelease(KeyEvent.VK_A);
                    Thread.sleep(50);
                    robot.keyPress(KeyEvent.VK_DELETE);
                    Thread.sleep(50);
                    robot.keyRelease(KeyEvent.VK_DELETE);

                    Toolkit toolkit = Toolkit.getDefaultToolkit();
                    Clipboard clipboard = toolkit.getSystemClipboard();
                    StringSelection strSel = new StringSelection(message);
                    clipboard.setContents(strSel, null);

                    robot.keyPress(KeyEvent.VK_CONTROL);
                    robot.keyPress(KeyEvent.VK_V);
                    Thread.sleep(50);
                    robot.keyRelease(KeyEvent.VK_CONTROL);
                    robot.keyRelease(KeyEvent.VK_V);
                    Thread.sleep(50);
                    robot.keyPress(KeyEvent.VK_ENTER);
                    robot.keyRelease(KeyEvent.VK_ENTER);

                }


                // Check if the message is '1'
                if (message.trim().equals("break")) {
                    shouldContinue = false; // Break the loop if message is '1'
                }

                // Close the resources
                inputStream.close();
                clientSocket.close();
            }

            // Close the server socket
            serverSocket.close();
            System.out.println("Server stopped.");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (AWTException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}

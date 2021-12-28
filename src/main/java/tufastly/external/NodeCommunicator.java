// https://stackoverflow.com/questions/13928226/how-to-pass-continuous-data-from-java-class-to-nodejs
// need install: $npm install ws

package tufastly.external;

import tufastly.model.RealtorResponseItem;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.io.*;

class NodeCommunicator {
    private static int numProperty = 10;
    public static void main(String[] args) {
        try {
            RealtorClient client = new RealtorClient();
            RealtorResponseItem res = client.searchProperty(numProperty, null, null);
            Socket nodejs = new Socket("localhost", 18080);
            Thread.sleep(100);
            for(int i=1; i< numProperty; i++){
                Thread.sleep(500);
                sendMessage(nodejs, i + " " + res.properties.get(i).address.line);
                System.out.println(i + " has been sent to server");
            }

        } catch (Exception e) {
            System.out.println("Server Closed..Something went Wrong..");
            e.printStackTrace();
        }

    }
    public static void sendMessage(Socket s, String message) throws IOException {
        System.out.println("In SendMesage"+message);
        s.getOutputStream().write(message.getBytes("UTF-8"));
        s.getOutputStream().flush();
    }
}

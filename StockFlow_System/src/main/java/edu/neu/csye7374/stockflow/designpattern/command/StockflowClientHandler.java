package edu.neu.csye7374.stockflow.designpattern.command;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import edu.neu.csye7374.stockflow.utils.AppLogger;

public class StockflowClientHandler {
   
    private final int port = 345;

    public void ServiceRunner() throws IOException {
        AppLogger.info("[Command Pattern] " + "ServiceRunner() : listening on UDP port " + port);
        DatagramSocket ds = new DatagramSocket(port);
        byte[] receive = new byte[10000];
        DatagramPacket dpPacket = null;

        while (dpPacket == null 
               || !data(receive).toString().equalsIgnoreCase("bye")) {
            dpPacket = new DatagramPacket(receive, receive.length);
            ds.receive(dpPacket);
            String msg = data(receive).toString();
            AppLogger.info("[Command Pattern] " + "ServiceRunner() : received='" + msg + "'");

            if ("bye".equalsIgnoreCase(msg)) {
                AppLogger.info("[Command Pattern] " + "ServiceRunner() : got 'bye', shutting down");
                break;
            }
            receive = new byte[10000];
        }

        ds.disconnect();
        ds.close();
        AppLogger.info("[Command Pattern] " + "ServiceRunner() : socket closed");
    }

    public void ClientHandler(String msg) throws IOException {
        AppLogger.info("[Command Pattern] " + "ClientHandler() : sending='" + msg + "'");
        DatagramSocket ds = new DatagramSocket();
        InetAddress ip = InetAddress.getLocalHost();
        byte[] buf = msg.getBytes();
        DatagramPacket dpSend = new DatagramPacket(buf, buf.length, ip, port);
        ds.send(dpSend);
        ds.disconnect();
        ds.close();
        AppLogger.info("[Command Pattern] " + "ClientHandler() : send complete, socket closed");
    }

    private static StringBuilder data(byte[] a) {
        if (a == null) return null;
        StringBuilder ret = new StringBuilder();
        int i = 0;
        while (i < a.length && a[i] != 0) {
            ret.append((char) a[i++]);
        }
        return ret;
    }
}

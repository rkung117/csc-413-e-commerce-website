package demo;

import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.annotations.*;
import java.io.*;
import java.util.*;
import java.util.concurrent.*;

@WebSocket
public class WebSocketHandler {

  // uses multiple threads
  // username for key
  static Map<Session,Session> sessionMap = new ConcurrentHashMap<>();

  // send a string to all active clients
  public static void broadcast(String message){
    sessionMap.keySet().forEach(session -> {
      try {
        session.getRemote().sendString(message);
      } catch (IOException e) {
        e.printStackTrace();
      }
    });
  }

  public static Integer getClientCount() {
    return sessionMap.keySet().size();
  }

  @OnWebSocketConnect
  public void connected(Session session) throws IOException {
    System.out.println("A client has connected");
    sessionMap.put(session, session);
    System.out.println("Total connections: " + sessionMap.keySet().size());
  }

  @OnWebSocketClose
  public void closed(Session session, int statusCode, String reason) {
    System.out.println("A client has disconnected");
    sessionMap.remove(session);
    System.out.println("Total connections: " + sessionMap.keySet().size());
  }

  @OnWebSocketMessage
  public void message(Session session, String message) throws IOException {
    System.out.println("Got: " + message);   // Print message
  }
}
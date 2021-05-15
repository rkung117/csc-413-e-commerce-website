package demo;

import static spark.Spark.*;
import static com.mongodb.client.model.Filters.*;


import com.google.gson.Gson;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

public class SparkDemo {
  static List<MessageDto> messageList = new ArrayList<>();
  static Gson gson = new Gson();

  public static void main(String[] args) {
    port(1235);
    webSocket("/ws", WebSocketHandler.class);

    // http://localhost:1235/test-endpoint?name=brian
    get("/test-endpoint", (req, res) -> {
      System.out.println(req.queryMap("name").value());
      return "Hello " + req.queryMap("name").value();
    });

    post("/submit-message", (req, res) -> {
      String bodyString = req.body();
      System.out.println(bodyString);
      MessageDto newMessage = gson.fromJson(bodyString, MessageDto.class);
      messageList.add(newMessage);


      BroadcastDto broadCastDto = new BroadcastDto(messageList, WebSocketHandler.getClientCount());
      WebSocketHandler.broadcast(gson.toJson(broadCastDto));
      return messageList.size();
    });

    get("/get-messages", (req, res) -> {
      return gson.toJson(messageList);
    });


  }
}

package demo;

import static spark.Spark.*;
<<<<<<< HEAD
import com.google.gson.Gson;
=======
import static com.mongodb.client.model.Filters.*;


import com.google.gson.Gson;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import java.util.List;
>>>>>>> React
import java.util.ArrayList;
import java.util.List;

public class SparkDemo {

<<<<<<< HEAD
  public static List<MessageDto> messageList = new ArrayList<>();
  public static Gson gson = new Gson();

  public static void main(String[] args) {
    port(1235);

    webSocket("/ws", WebSocketHandler.class);

    post("/postListing", (req, res) -> {
      String body = req.body();
      System.out.println(body);
      MessageDto newMessage = gson.fromJson(body, MessageDto.class);
      messageList.add(newMessage);

      BroadcastDto broadcastDto = new BroadcastDto(messageList,
              WebSocketHandler.getClientCount());
      WebSocketHandler.broadcast(gson.toJson(broadcastDto));

      return messageList.size();

    });

    get("/viewListings", (req, res) -> {
      return gson.toJson(messageList);
    });

=======
  static List<MessageDto> listingList = new ArrayList<>();
  static Gson gson = new Gson();

  public static void main(String[] args) {
    port (1235);
    webSocket("/ws", WebSocketHandler.class);

    // why do post and get still work even though endpoints are different
    post("/submit-listing", (req, res) -> {
      String bodyString = req.body();
      System.out.println(bodyString);
      MessageDto newListing = gson.fromJson(bodyString, MessageDto.class);
      listingList.add(newListing);

      // alert all other clients that something has changed
      BroadcastDto broadcastDto = new BroadcastDto(listingList,
              WebSocketHandler.getClientCount());
      WebSocketHandler.broadcast((gson.toJson(broadcastDto)));

      return listingList.size();
    });

    get("/get-listing", (req, res) -> {
      return gson.toJson(listingList);
    });
>>>>>>> React
  }
}

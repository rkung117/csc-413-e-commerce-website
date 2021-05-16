package demo;

import static spark.Spark.*;
import static com.mongodb.client.model.Filters.*;
import com.google.gson.Gson;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import java.util.List;
import java.util.ArrayList;

public class SparkDemo {

  static List<MessageDto> listingList = new ArrayList<>();
  static Gson gson = new Gson();

  public static void main(String[] args) {
    port (1235);
    webSocket("/ws", WebSocketHandler.class);

    post("/submit-listing", (req, res) -> {
      String bodyString = req.body();
      System.out.println(bodyString);
      MessageDto newListing = gson.fromJson(bodyString, MessageDto.class);
      listingList.add(newListing);

      BroadcastDto broadcastDto = new BroadcastDto(listingList,
              WebSocketHandler.getClientCount());
      WebSocketHandler.broadcast((gson.toJson(broadcastDto)));

      return listingList.size();
    });

    get("/get-listing", (req, res) -> {
      return gson.toJson(listingList);
    });
  }
}
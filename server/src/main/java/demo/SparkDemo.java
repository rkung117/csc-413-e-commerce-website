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
  static List<listingDto> itemsList = new ArrayList<listingDto>();

  static Gson gson = new Gson();

  public static void main(String[] args) {
    port(1235);
//      Uncomment the below line when combing with websocket
//    webSocket("/ws", WebSocketHandler.class);


    post("/submit-listing", (req, res) -> {
      String bodyString = req.body();
      System.out.println(bodyString);

      listingDto newItem = gson.fromJson(bodyString, listingDto.class);
      itemsList.add(newItem);

      return  itemsList.size();

    });

    get("/get-listings", (req, res) -> {

      return gson.toJson(itemsList);

    });



  }
}

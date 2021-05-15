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
  static List<listingDto> emailList = new ArrayList<>();
  static List<listingDto> titleList = new ArrayList<>();
  static List<listingDto> descriptionList = new ArrayList<>();
  static List<listingDto> priceList = new ArrayList<>();
  static Gson gson = new Gson();

  public static void main(String[] args) {
    port(1235);
    webSocket("/ws", WebSocketHandler.class);

    // http://localhost:1235/test-endpoint?
//    get("/test-endpoint", (req, res) -> {
//      System.out.println(req.queryMap("name").value());
//      return "Hello " + req.queryMap("name").value();
//    });

    post("/submit-listing", (req, res) -> {
      String bodyString = req.body();
      System.out.println(bodyString);
//      listingDto newEmail = gson.fromJson(bodyString, listingDto.class);
//      emailList.add(newEmail);
      listingDto newTitle = gson.fromJson(bodyString, listingDto.class);
      titleList.add(newTitle);
//      listingDto newDescription = gson.fromJson(bodyString, listingDto.class);
//      descriptionList.add(newDescription);
//      listingDto newPrice = gson.fromJson(bodyString, listingDto.class);
//      priceList.add(newPrice);
return  titleList;

    });

    get("/get-listings", (req, res) -> {
      return gson.toJson(titleList);

    });


  }
}

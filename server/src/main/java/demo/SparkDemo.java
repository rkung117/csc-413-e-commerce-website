package demo;

import static spark.Spark.*;
import static com.mongodb.client.model.Filters.*;


import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import java.util.ArrayList;
import org.bson.Document;

public class SparkDemo {
  public static void main(String[] args) {
    port(1234);

    webSocket("/ws", WebSocketHandler.class);

    get("/viewListings", (req, res) -> {
      System.out.println(req.queryMap("name").value());
      return "Hello " + req.queryMap("name").value();
    });

  }
}

package demo;

import static spark.Spark.*;
import static com.mongodb.client.model.Filters.*;


import Processor.*;
import com.google.gson.Gson;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.eclipse.jetty.websocket.server.WebSocketHandler;
import spark.Request;
import spark.Response;

public class SparkDemo {
  static List<listingDto> itemsList = new ArrayList<listingDto>();

  static Gson gson = new Gson();

  public static void main(String[] args) {
    port(1235);
//      Uncomment the below line when combing with websocket
    webSocket("/ws", WebSocketHandler.class);


    post("/submit-listing", (req, res) -> {
      String bodyString = req.body();
      System.out.println(bodyString);

      listingDto newItem = gson.fromJson(bodyString, listingDto.class);
      itemsList.add(newItem);

      return itemsList.size();

    });

    get("/get-listings", (req, res) -> {

      return gson.toJson(itemsList);

    });


//
//
//  }
//}
//    webSocket("/ws", WebSocketHandler.class);
    post("/auth/signin", SparkDemo::signin);
    post("/auth/signup", SparkDemo::signup);
//    post("/auth/updateUser", SparkDemo::updateUser);

//    get("/allUsers", SparkDemo::getAllUsers);
//  }
//
  }
  private static Object signin(Request req, Response res) {
    return new Gson().toJson(new SignInProcessor(req, res).process());
  }

  private static Object signup(Request req, Response res) {
    return new Gson().toJson(new SignUpProcessor(req, res).process());
  }
}
//
//  private static Object updateUser(Request req, Response res) {
//    return new Gson().toJson(new UserUpdateProcessor(req, res).process());
//  }
//

//
//  private static Object getAllUsers(Request req, Response res){
//    return new Gson().toJson(new UsersProcessor().process());
//  }
//}
//

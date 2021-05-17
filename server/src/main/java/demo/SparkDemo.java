package demo;

import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;
import static spark.Spark.*;
import static com.mongodb.client.model.Filters.*;
import com.google.gson.Gson;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.Conventions;
import org.bson.codecs.pojo.PojoCodecProvider;

import javax.print.Doc;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

public class SparkDemo {

  static List<MessageDto> listingList = new ArrayList<>();
  static Gson gson = new Gson();

  public static void main(String[] args) {
    port (1235);
    webSocket("/ws", WebSocketHandler.class);

    PojoCodecProvider provider = PojoCodecProvider.builder().
            conventions(Arrays.asList(Conventions.ANNOTATION_CONVENTION)).automatic(true).build();
    CodecRegistry pojoCodecRegistry = fromRegistries(MongoClient.getDefaultCodecRegistry(),
            fromProviders(provider));
    // open connection
    MongoClient mongoClient = new MongoClient("localhost", 27017);
    // get ref to database
    MongoDatabase database = mongoClient.getDatabase("NewDatabase");
    database = database.withCodecRegistry(pojoCodecRegistry);
    MongoCollection<Document> myCollection = database.getCollection("newCollection");

    post("/submit-listing", (req, res) -> {
      String bodyString = req.body();
      System.out.println(bodyString);
      MessageDto newListing = gson.fromJson(bodyString, MessageDto.class);
      listingList.add(newListing);

      Document doc = new Document("name", "ViewListings")
        .append("email", newListing.email)
        .append("product", newListing.product)
        .append("description", newListing.description)
        .append("price", newListing.price);

      // insert document into collection
      myCollection.insertOne(doc);

      BroadcastDto broadcastDto = new BroadcastDto(listingList,
              WebSocketHandler.getClientCount());
      WebSocketHandler.broadcast((gson.toJson(broadcastDto)));

      return listingList.size();
    });

    get("/get-listing", (req, res) -> {
      List<Document> docs = myCollection.find().limit(100).into(new ArrayList<Document>());
      return gson.toJson(docs);
    });

    post("/delete-listing", (req, res) -> {
      String bodyString = res.body();
      System.out.println("THIS " + bodyString);
      MessageDto newListing = gson.fromJson(bodyString, MessageDto.class);

      Document doc = myCollection.find(eq("email", newListing.email)).first();

      // insert document into collection
      myCollection.deleteOne(doc);

      BroadcastDto broadcastDto = new BroadcastDto(listingList,
              WebSocketHandler.getClientCount());
      WebSocketHandler.broadcast((gson.toJson(broadcastDto)));

      listingList.remove(newListing);
      return listingList.size();
    });

  }


}
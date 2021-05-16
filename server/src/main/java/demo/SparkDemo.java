package demo;

import static spark.Spark.*;
import static com.mongodb.client.model.Filters.*;


import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

public class SparkDemo {
  public static void main(String[] args) {
    // open connection
    MongoClient mongoClient = new MongoClient("localhost", 27011);
    // get ref to database
    MongoDatabase db = mongoClient.getDatabase("MyDatabase");
    // get ref to collection
    MongoCollection<Document> myColection = db.getCollection("myCollection");
    // create a new document

    Document doc = new Document("name", "MongoDB")
            .append("type", "database")
            .append("count", 1)
            .append("info", new Document("x", 203).append("y", 102));
    // insert document into collection
    myColection.insertOne(doc);

    // count all documents in collection
    System.out.println("Total Documents :" +  myColection.count());

    // iterate some documents

    List<Document> docs = myColection.find().limit(100).into(new ArrayList<Document>());
    docs.forEach(document -> {
      System.out.println(document.getString("name"));
    });

    // fetching a value from a search

    Document search = myColection.find(eq("name", "Jose")).first();
    System.out.println(search.getInteger("favoriteNumber"));

    // updating a value
    myColection.updateOne(eq("count", 1), new Document("$set", new Document("count", 110)));
    /*
     * {
     *    $set : {
     *     count: 110
     *     }
     * }
     */
  }
}

package demo;

import static spark.Spark.*;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.List;

public class SparkDemo {

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

  }
}

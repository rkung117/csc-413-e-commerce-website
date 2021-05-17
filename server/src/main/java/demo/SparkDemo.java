package demo;

import static spark.Spark.*;


import com.google.gson.Gson;

import java.util.List;
import java.util.ArrayList;

public class SparkDemo {

  static List<MessageDto> listingList = new ArrayList<>();
  static List<TitleDto> titleList = new ArrayList<>();
  static List<PriceDto> priceList = new ArrayList<>();
  static List<TypeDto> typeList = new ArrayList<>();
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
      TitleDto newTitle = gson.fromJson(bodyString, TitleDto.class);
      titleList.add(newTitle);
      PriceDto newPrice = gson.fromJson(bodyString, PriceDto.class);
      priceList.add(newPrice);
      TypeDto newType = gson.fromJson(bodyString, TypeDto.class);
      typeList.add(newType);

      // alert all other clients that something has changed
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

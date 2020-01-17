package com.softserve.lv460.device.config;

import com.mongodb.Block;
import com.mongodb.MongoClient;
import com.mongodb.client.*;
import com.mongodb.client.model.changestream.ChangeStreamDocument;
import org.bson.Document;
import org.springframework.stereotype.Service;
import javax.annotation.PostConstruct;

@Service
public class ChangeEventConfig {
  private final MongoClient mongoClient;

  public ChangeEventConfig(MongoClient mongoClient) {
    this.mongoClient = mongoClient;
  }

  @PostConstruct
  public void subscribe() {
    Thread thread = new Thread(() -> {
      MongoDatabase db = mongoClient.getDatabase("DeviceInfoDB");
      MongoCollection<Document> collection = db.getCollection("deviceData");
      Block<ChangeStreamDocument<Document>> printBlock = new Block<ChangeStreamDocument<Document>>() {
        @Override
        public void apply(final ChangeStreamDocument<Document> changeStreamDocument) {
        System.out.println(changeStreamDocument.getFullDocument());
        }
      };
      collection.watch().forEach(printBlock);
    });
    thread.start();
  }
}


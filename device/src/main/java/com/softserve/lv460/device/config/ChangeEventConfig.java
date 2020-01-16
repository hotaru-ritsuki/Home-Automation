package com.softserve.lv460.device.config;

import com.mongodb.Block;
import com.mongodb.MongoClient;
import com.mongodb.client.*;
import com.mongodb.client.model.Aggregates;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.changestream.ChangeStreamDocument;
import com.mongodb.client.model.changestream.FullDocument;
import org.bson.BsonDocument;
import org.bson.Document;
import org.springframework.stereotype.Service;
import javax.annotation.PostConstruct;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

@Service
public class ChangeEventConfig {
  private final MongoClient mongoClient;

  public ChangeEventConfig(MongoClient mongoClient) {
    this.mongoClient = mongoClient;
  }

  @PostConstruct
  public void subscribe() {
    Thread thread = new Thread(() -> {
      System.out.println(Thread.currentThread());
      MongoDatabase db = mongoClient.getDatabase("DeviceInfoDB");
      MongoCollection<Document> collection = db.getCollection("deviceData");
      Block<ChangeStreamDocument<Document>> printBlock = new Block<ChangeStreamDocument<Document>>() {
        @Override
        public void apply(final ChangeStreamDocument<Document> changeStreamDocument) {
        System.out.println("+++");
        System.out.println(changeStreamDocument.getFullDocument());
        }
      };
      System.out.println(Thread.currentThread());
      collection.watch().forEach(printBlock);
      System.out.println(Thread.currentThread());
    });
    thread.start();
  }
}


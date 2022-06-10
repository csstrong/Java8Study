package com.chensi.mongodb;

import com.mongodb.Block;
import com.mongodb.MongoClient;
import com.mongodb.MongoException;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import java.util.Arrays;
import java.util.regex.Pattern;

import org.bson.Document;

/***********************************
 * @author chensi
 * @date 2022/5/25 9:03
 ***********************************/

// Requires official Java MongoDB Driver 3.6+

public class TestAndOrCondition {

    public static void main(String[] args) {

        try (MongoClient client = new MongoClient("192.168.200.105", 27017)) {

            MongoDatabase database = client.getDatabase("db_wayeal_pipenet");
            MongoCollection<Document> collection = database.getCollection("base_site_info");

            // Created with Studio 3T, the IDE for MongoDB - https://studio3t.com/

            Document query = new Document();
            query.append("Name", Pattern.compile("^.*\u6C34.*$", Pattern.CASE_INSENSITIVE));
            query.append("$or", Arrays.asList(
                new Document()
                    .append("ProvinceID", "41"),
                new Document()
                    .append("ProvinceID", "11")
                )
            );

            Block<Document> processBlock = new Block<Document>() {
                @Override
                public void apply(final Document document) {
                    System.out.println(document);
                }
            };

            collection.find(query).forEach(processBlock);

        } catch (MongoException e) {
            // handle MongoDB exception
        }
    }

}
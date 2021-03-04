/**
 * Copyright 2010-2019 Amazon.com, Inc. or its affiliates. All Rights Reserved.
 *
 * <p>This file is licensed under the Apache License, Version 2.0 (the "License"). You may not use
 * this file except in compliance with the License. A copy of the License is located at
 *
 * <p>http://aws.amazon.com/apache2.0/
 *
 * <p>This file is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.amazonaws.codesamples.gsg;

import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.PutItemOutcome;
import com.amazonaws.services.dynamodbv2.document.Table;
import java.util.HashMap;
import java.util.Map;

public class MoviesItemOps01 {
  // Add an item

  public static void main(String[] args) throws Exception {

    AmazonDynamoDB client =
        AmazonDynamoDBClientBuilder.standard()
            .withEndpointConfiguration(
                new AwsClientBuilder.EndpointConfiguration("http://localhost:8000", "us-west-2"))
            .build();

    DynamoDB dynamoDB = new DynamoDB(client);

    Table table = dynamoDB.getTable("Movies");

    int year = 2015;
    String title = "The Big New Movie";

    final Map<String, Object> infoMap = new HashMap<String, Object>();
    infoMap.put("plot", "Nothing happens at all.");
    infoMap.put("rating", 0);

    try {
      System.out.println("Adding a new item...");
      PutItemOutcome outcome =
          table.putItem(
              new Item().withPrimaryKey("year", year, "title", title).withMap("info", infoMap));

      System.out.println("PutItem succeeded:\n" + outcome.getPutItemResult());

    } catch (Exception e) {
      System.err.println("Unable to add item: " + year + " " + title);
      System.err.println(e.getMessage());
    }
  }
}

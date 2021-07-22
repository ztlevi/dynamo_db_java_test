package com.example.dynamo;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.local.embedded.DynamoDBEmbedded;
import com.amazonaws.services.dynamodbv2.model.AttributeDefinition;
import com.amazonaws.services.dynamodbv2.model.CreateTableRequest;
import com.amazonaws.services.dynamodbv2.model.KeySchemaElement;
import com.amazonaws.services.dynamodbv2.model.KeyType;
import com.amazonaws.services.dynamodbv2.model.ProvisionedThroughput;
import com.amazonaws.services.dynamodbv2.model.ScalarAttributeType;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class DynamoDBLocalTest {
    private static AmazonDynamoDB client;
    private final static String tableName = "TestTable";
    private final static String primaryKey = "code";

    @BeforeAll
    static void beforeAll() {
        System.setProperty("sqlite4java.library.path", "native-libs");
        client = DynamoDBEmbedded.create().amazonDynamoDB();
    }

    @AfterAll
    static void afterAll() {
        client.deleteTable(tableName);
        System.out.println("Delete ddb");
        System.out.println(client.listTables());
    }

    @Test
    public void testDynamoDBLocal() {
        AttributeDefinition warehouseCodeAttributeDefinition = new AttributeDefinition()
            .withAttributeName(primaryKey)
            .withAttributeType(ScalarAttributeType.S);
        KeySchemaElement hashKey = new KeySchemaElement()
            .withAttributeName(primaryKey)
            .withKeyType(KeyType.HASH);
        CreateTableRequest createTableRequest = new CreateTableRequest()
            .withTableName(tableName)
            .withAttributeDefinitions(warehouseCodeAttributeDefinition)
            .withKeySchema(hashKey)
            .withProvisionedThroughput(new ProvisionedThroughput()
                .withReadCapacityUnits(100L)
                .withWriteCapacityUnits(100L));
        client.createTable(createTableRequest);
        System.out.println("Created ddb");
        System.out.println(client.listTables());
    }
}

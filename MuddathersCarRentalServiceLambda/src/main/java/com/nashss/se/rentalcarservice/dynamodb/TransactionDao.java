package com.nashss.se.rentalcarservice.dynamodb;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;

public class TransactionDao {

    private final DynamoDBMapper mapper;

    public TransactionDao(DynamoDBMapper mapper) {
        this.mapper = mapper;
    }


}

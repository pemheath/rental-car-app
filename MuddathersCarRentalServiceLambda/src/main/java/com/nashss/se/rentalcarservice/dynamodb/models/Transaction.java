package com.nashss.se.rentalcarservice.dynamodb.models;

import com.amazonaws.services.dynamodbv2.datamodeling.*;
import com.amazonaws.services.lambda.runtime.Client;
import com.nashss.se.rentalcarservice.converters.ZonedDateTimeConverter;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.Objects;

@DynamoDBTable(tableName = "transactions")
public class Transaction {

    private String clientId;
    private String transactionId;
    private Client client;
    private String VIN;
    private BigDecimal costPerDay;
    private ZonedDateTime dateOut;
    private ZonedDateTime dateIn;
    private BigDecimal totalCost;

    public Transaction(String clientId, String transactionId, Client client, String VIN, BigDecimal costPerDay, ZonedDateTime dateOut, ZonedDateTime dateIn, BigDecimal totalCost) {
        this.clientId = clientId;
        this.transactionId = transactionId;
        this.client = client;
        this.VIN = VIN;
        this.costPerDay = costPerDay;
        this.dateOut = dateOut;
        this.dateIn = dateIn;
        this.totalCost = totalCost;
    }

    @DynamoDBHashKey(attributeName = "clientId")
    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    @DynamoDBRangeKey(attributeName = "transactionId")
    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    @DynamoDBTypeConvertedJson
    @DynamoDBAttribute(attributeName = "client")
    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    @DynamoDBAttribute(attributeName = "VIN")
    public String getVIN() {
        return VIN;
    }

    public void setVIN(String VIN) {
        this.VIN = VIN;
    }

    @DynamoDBAttribute(attributeName = "costPerDay")
    public BigDecimal getCostPerDay() {
        return costPerDay;
    }

    public void setCostPerDay(BigDecimal costPerDay) {
        this.costPerDay = costPerDay;
    }

    @DynamoDBTypeConverted(converter = ZonedDateTimeConverter.class)
    @DynamoDBAttribute(attributeName = "dateOut")
    public ZonedDateTime getDateOut() {
        return dateOut;
    }

    public void setDateOut(ZonedDateTime dateOut) {
        this.dateOut = dateOut;
    }

    @DynamoDBTypeConverted(converter = ZonedDateTimeConverter.class)
    @DynamoDBAttribute(attributeName = "dateIn")
    public ZonedDateTime getDateIn() {
        return dateIn;
    }

    public void setDateIn(ZonedDateTime dateIn) {
        this.dateIn = dateIn;
    }

    @DynamoDBAttribute(attributeName = "totalCost")
    public BigDecimal getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(BigDecimal totalCost) {
        this.totalCost = totalCost;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "clientId='" + clientId + '\'' +
                ", transactionId='" + transactionId + '\'' +
                ", client=" + client +
                ", VIN='" + VIN + '\'' +
                ", costPerDay=" + costPerDay +
                ", dateOut=" + dateOut +
                ", dateIn=" + dateIn +
                ", totalCost=" + totalCost +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Transaction)) return false;
        Transaction that = (Transaction) o;
        return getClientId().equals(that.getClientId()) && getTransactionId().equals(that.getTransactionId()) && getClient().equals(that.getClient()) && getVIN().equals(that.getVIN()) && getCostPerDay().equals(that.getCostPerDay()) && getDateOut().equals(that.getDateOut()) && Objects.equals(getDateIn(), that.getDateIn()) && Objects.equals(getTotalCost(), that.getTotalCost());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getClientId(), getTransactionId(), getClient(), getVIN(), getCostPerDay(), getDateOut(), getDateIn(), getTotalCost());
    }
}

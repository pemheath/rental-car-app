package com.nashss.se.rentalcarservice.models;

import com.amazonaws.services.lambda.runtime.Client;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.Objects;

public class TransactionModel {

    private String clientId;
    private String transactionId;
    private Client client;
    private String VIN;
    private BigDecimal costPerDay;
    private ZonedDateTime dateOut;
    private ZonedDateTime dateIn;
    private BigDecimal totalCost;

    public TransactionModel(String clientId, String transactionId, Client client, String VIN, BigDecimal costPerDay, ZonedDateTime dateOut, ZonedDateTime dateIn, BigDecimal totalCost) {
        this.clientId = clientId;
        this.transactionId = transactionId;
        this.client = client;
        this.VIN = VIN;
        this.costPerDay = costPerDay;
        this.dateOut = dateOut;
        this.dateIn = dateIn;
        this.totalCost = totalCost;
    }

    public String getClientId() {
        return clientId;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public Client getClient() {
        return client;
    }

    public String getVIN() {
        return VIN;
    }

    public BigDecimal getCostPerDay() {
        return costPerDay;
    }

    public ZonedDateTime getDateOut() {
        return dateOut;
    }

    public ZonedDateTime getDateIn() {
        return dateIn;
    }

    public BigDecimal getTotalCost() {
        return totalCost;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TransactionModel)) return false;
        TransactionModel that = (TransactionModel) o;
        return getClientId().equals(that.getClientId()) && getTransactionId().equals(that.getTransactionId()) && getClient().equals(that.getClient()) && getVIN().equals(that.getVIN()) && getCostPerDay().equals(that.getCostPerDay()) && getDateOut().equals(that.getDateOut()) && Objects.equals(getDateIn(), that.getDateIn()) && Objects.equals(getTotalCost(), that.getTotalCost());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getClientId(), getTransactionId(), getClient(), getVIN(), getCostPerDay(), getDateOut(), getDateIn(), getTotalCost());
    }

    //CHECKSTYLE:OFF:Builder
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private String clientId;
        private String transactionId;
        private Client client;
        private String VIN;
        private BigDecimal costPerDay;
        private ZonedDateTime dateOut;
        private ZonedDateTime dateIn;
        private BigDecimal totalCost;

        public Builder withClientId(String clientId) {
            this.clientId = clientId;
            return this;
        }

        public Builder withTransactionId(String transactionId) {
            this.transactionId = transactionId;
            return this;
        }

        public Builder withClient(Client client) {
            this.client = client;
            return this;
        }

        public Builder withVIN(String VIN) {
            this.VIN = VIN;
            return this;
        }

        public Builder withCostPerDay(BigDecimal costPerDay) {
            this.costPerDay = costPerDay;
            return this;
        }

        public Builder withDateOut(ZonedDateTime dateOut) {
            this.dateOut = dateOut;
            return this;
        }

        public Builder withDateIn(ZonedDateTime dateIn) {
            this.dateIn = dateIn;
            return this;
        }

        public Builder withTotalCost(BigDecimal totalCost) {
            this.totalCost = totalCost;
            return this;
        }

        public TransactionModel build() {
            return new TransactionModel(clientId, transactionId, client, VIN, costPerDay, dateOut, dateIn, totalCost);
        }
    }
}

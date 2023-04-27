# Unit 5 Project Intro - Welcome! 

### The website! https://d2r11z12vu3xbx.cloudfront.net/index.html


Our team created the above website, hosted by [CloudFront](https://aws.amazon.com/cloudfront/), that talks to an Amazon API Gateway endpoint. The Amazon API Gateway connects to a Lambda service, which stores its data in DynamoDB. The website uses a rental car service that we developed using Java. Our team developed an inventory management service for a rental car agency.

### Technologies used: 

Java, AWS Cloudfront, AWS Lambda, AWS DynamoDB, Javascript, CSS, HTML

### Contributors: NSS and classmates (group of four) : Frazier Dyson, Kaden Cannon, Muddather Babiker 


# Rental Car Management Service Design

### Problem Statement

Car rental companies need efficient ways to manage their inventory.

What Wheels Are Where is a rental car management service that will provide management functionality suitable for a business with dynamic inventory. 
It is designed to allow a company to view, add, update, remove, and check-out cars to rental car customers. 

### Definitions

- With respect to this project, a "user" is an owner or employee of a car rental agency 
- A "client" is a customer of that business, who will be served by the business but  never interact with this application.
- The "business" is a small scale, single location car rental company. 
- For this project, a "car" is an vehicle in our inventory of rental vehicles. While we will have some "trucks" and "vans", we will refer to all vehicles as cars (who calls it a vehicle rental company?).
- For the sake of this project, anyone with the link to our website has read access to inventory, but in order to have write access, the user must be logged in to an account. 
- We will not concern ourselves with verifying that a user making an account is actually an employee, but this would need to be addressed for security.
- In other words, we will assume that anyone with access to the application is allowed to access all of its functionality.


## Use Cases

_This is where we work backwards from the customer and define what our customers would like to do (and why). You may also include use cases for yourselves (as developers), or for the organization providing the product to customers._

U1. As a [rental car management service] customer, I want to add a new car to inventory.

U2. As a [rental car management service] customer, remove a car permanently from inventory.

U3. As a [rental car management service] customer, I want to mark a car as rented.

U4. As a [rental car management service] customer, I want to mark a car as available.

U5. As a [rental car management service] customer, I want to view inventory filtered by availability status and class of vehicle (SUV, sedan, etc).

U6. As a [rental car management service] customer, I want to Flag a car from for maintenance or repairs.

U7. As a [rental car management service] customer, View and change the price per day of a vehicle.


##  Project Scope

### In Scope

_What constitutes our minimum viable product, or MVP?_

The use cases 1-5 constitute the core functionality of our product. This is the functionality we will focus on first. Use cases 6-13 will be additional features that we can add if time permits. 

- [] Creating, retrieving, and updating a list of car inventory
- [] Managing a list of current available/unavailable cars
- [] Check cars in and out and update their status accordingly 
- [] Operate in and out of one location.

### 4.2. Out of Scope

This service is not for customers of the rental car company. The service is an internal application for the company to manage their inventory. 
Extending the scope to include rental car customer facing functionality is beyond the scope of this project.

- []  Operating with multiple locations and the ability to do one way car rentals
- []  Offering a car-customer facing side of the service
- []  We will not concern ourselves with verifying that a user making an account is actually an employee, but this would need to be addressed for security.
- []  For this project, we are assuming access = permission. 
- []  For this project, we will assume that the driver's license will not change. Should it change, the fix would need to be manual by an engineer in the database.


#  Proposed Architecture Overview

This initial iteration will provide the minimum viable product  (MVP) including adding and removing vehicles from inventory, as well as renting and returning vehicles and viewing available inventory.

We will use API Gateway and Lambda to create five endpoints (`ViewInventory`,  `AddVehicle`, `RemoveVehicle`, `UpdateVehicle`, `Search`) that will handle the addition, removal, renting, and maintenance of vehicles in inventory.

We will store the inventory in a table of Cars in DynamoDB.

The project will also provide a web interface for users to manage and view their inventory. We will use Cognito to authenticate users. 

We will have a main page that populates with a list view of all available cars and a links to pages to manage/rent/return cars.

#  API

## 6.1. Public Models

```
//Cars (model)

String VIN; 
String make; 
String model;
ENUM classOfVehicle; 
BigDecimal costPerDay;
ENUM availability;
Integer year; 
Integer capacity; 

```


```
//ClientModel (will not a dynamodb table)

String licenseNumber;
String name;
String email;
String phoneNumber;


```

```
//Transactions (model) [have not build this out yet, but would like to explore this later]

String clientId; partition key
String transactionId; sort key
Client client;
String VIN;
BigDecimal costPerDay;   
ZonedDateTime dateOut;
ZonedDateTime dateIn;
BigDecimal totalCost;


```

###  `Search` 

* Accepts `GET` requests to `/cars`
* By default returns all inventory marked as available by querying the GSI.
* Accepts an optional class parameter, class. If optional parameter, the API will return the subset of available cars of that type.
  * If the class provided does not match any provided types, a `VehicleClassNotSupported` exception will be thrown, resulting in a 400 error. We hope to avoid this by utilizing a dropdown menu on the front end to limit class choice.
  
###  `ViewCar`

* Accepts `GET` requests to `/car/:vin`
* Accepts a VIN and returns the corresponding car model.
  * We will validate that the provided VIN is 17 characters and does not include illegal characters.
  * If the VIN is not validated, will throw an `InvalidVINException` (400 exception)
      * If the given VIN is valid but is not found, will throw a
        `CarNotFoundException` (404 exception)

###   `AddCar`
* Accepts `POST` requests to `/car`
* Accepts data to create a new car with a provided VIN, make, model, capacity, year, class of vehicle, cost per day. Availability will defaulst to "available".
* Return the newly generated car model.
* We will validate that the provided VIN is 17 characters and does not include illegal characters.
  * If the VIN is not validated, will throw an `InvalidVINException` (400 exception)
  * If the VIN is valid but already in the database, will through a `DuplicateInventoryItemException` ()



###   `UpdateCar`
* Accepts `PUT` requests to `/car/:vin`. 
* Accepts data to update the status or cost of a car.
* Returns the updated car model.
  * If the VIN is not validated, will throw an `InvalidVINException`
      * If the given VIN is valid but is not found, will throw a
        `CarNotFoundException`

###   `RemoveCar`

* Accepts `DELETE` requests to `/car/:vin`. 
* Returns a boolean (true if car was successfully deleted, false if car was not in inventory or not successfully deleted)
* We will validate that the provided VIN is 17 characters and does not include illegal characters.
    * If the VIN is not validated, will throw an `InvalidVINException`

#  Tables

```
//Transactions (table)

String clientId; partition key
String transactionId; sort key
String client; (json)
String VIN;
Number costPerDay;   
String dateOut;
String dateIn;
String totalCost;


//Cars (table)
String VIN; (partition key) 
String make; 
String model;
String classOfVehicle; 
Number costPerDay;
String availability;
String year; 
Number capacity; 

GSI//
 
 AvailabilityClassIndex
 
 The power of this index is to answer the question "What cars are available?" and "What sedans are available? or What SUVs are available?"
 We can then query specific cars from the base table by specifying the VIN.
 
 String availability partition key;
 String classOfVehicle sort key;
 String VIN  string;

```

This project has two code bases:

One code base  is for our Lambda service code (MuddathersRentalCarServiceLambda). This  contains code that designs and runs our service APIs, as well as packages to interact with and test your service.

The second contains code for the website. I had limited front end exposure at the beginning of this project, and in two weeks managed to get comfortable with:

* HTML
* CSS
* JavaScript

For simplicity I kept them in the same repository for this project.
_

### Deploying

We used GitHub Actions to deploy the code to AWS. 







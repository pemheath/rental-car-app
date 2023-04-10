# Design Document

## Rental Car Management Service Design

## 1. Problem Statement

Car rental companies need efficient ways to manage their inventory.

What Wheels Are Where is a rental car management service that will provide management functionality suitable for a business with dynamic inventory. 
It is designed to allow a company to view, add, update, remove, and check-out cars to rental car customers. 

# Definitions

- With respect to this project, a "user" is an owner or employee of a car rental agency 
- A "client" is a customer of that business, who will be served by the business but  never interact with this application.
- The "business" is a small scale, single location car rental company. 
- For this project, a "car" is an vehicle in our inventory of rental vehicles. While we will have some "trucks" and "vans", we will refer to all vehicles as cars (who calls it a vehicle rental company?).
- For the sake of this project, anyone with the link to our website has read access to inventory, but in order to have write access, the user must be logged in to an account. 
- We will not concern ourselves with verifying that a user making an account is actually an employee, but this would need to be addressed for security.
- In other words, we will assume that anyone with access to the application is allowed to access all of its functionality.

## 2. Top Questions to Resolve in Review

_List the most important questions you have about your design, or things that you are still debating internally that you might like help working through._

1. How will we uniquely identify our vehicles? (ie, what will the key structure be for the table) - assign a unique ID to every piece of inventory [UUID] --> the user will not need to care about this
2. What queries will I need to perform as a user? 
3. What should the main page contain? Should the main page be populated with a list of cars available in inventory when the application is opened, or should the main page have a menu of options for the user to select an action (what do they want to do?).
4. Do we need to worry about people who are not our customer trying to create an account? Is there anything in the log in process to prohibit a random person from creating an account and manipulating inventory.

## 3. Use Cases

_This is where we work backwards from the customer and define what our customers would like to do (and why). You may also include use cases for yourselves (as developers), or for the organization providing the product to customers._

U1. As a [rental car management service] customer, I want to add a new car to inventory.

U2. As a [rental car management service] customer, remove a car permanently from inventory.

U3. As a [rental car management service] customer, I want to mark a car as rented, with time stamp of check-out recorded.

U4. As a [rental car management service] customer, I want to mark a car as available, with time stamp of check-in time.

U5. As a [rental car management service] customer, I want to view all available cars.

U8. As a [rental car management service] customer, I want to Check a car back in and calculate cost to charger customer based on length of rental.

U6. As a [rental car management service] customer, I want to View a subset of all cars by filtering based on status, capacity, and class.

U9. As a [rental car management service] customer, Update a car's status as it moves from rented to under-prep to available back to rented.

U7. As a [rental car management service] customer, I want to Flag a car from for maintenance or repairs  (available, rented, broke).

U10. As a [rental car management service] customer, View level of gas the car checks in and out with.

U11. As a [rental car management service] customer, View and change the price per day of a vehicle.

U12.As a [rental car management service] customer, Determine who is renting a specific vehicle.



## 4. Project Scope

### 4.1. In Scope

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



# 5. Proposed Architecture Overview

This initial iteration will provide the minimum viable product  (MVP) including adding and removing vehicles from inventory, as well as renting and returning vehicles and viewing available inventory.

We will use API Gateway and Lambda to create five endpoints (`ViewInventory`,  `AddVehicle`, `RentVehcile`, `ReturnVehicle`, `RemoveVehicle`) that will handle the addition, removal, renting, and returning of vehicles to our rental car inventory. 

We will store the inventory in a table of Cars in DynamoDB. We will also store customers and transactions in respective tables in DynamoDB.

What Wheels are Where will also provide a web interface for users to manage and view their inventory. We will use Cognito to authenticate users. 

We will have a main page that populates with a list view of all available cars and a links to pages to manage/rent/return cars.

# 6. API

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
//Transactions (model)

String clientId; partition key
String transactionId; sort key
Client client;
String VIN;
BigDecimal costPerDay;   
ZonedDateTime dateOut;
ZonedDateTime dateIn;
BigDecimal totalCost;


```

### 6.2. `ViewAvailableInventory` 

* Accepts `GET` requests to `/availableInventory`
* By default returns all inventory marked as available by querying the GSI. (associated with Use Case 5)
* _This next version of the endpoint is associated with stretch user story US 6_ 
* 6.2.stretch: Accepts an optional class parameter, class. If optional parameter, the API will return the subset of available cars of that type.
  * If the class provided does not match any provided types, a `VehicleClassNotSupported` exception will be thrown, resulting in a 400 error. We hope to avoid this by 
  * utilizing a dropdown menu on the front end to limit class choice.
  
### 6.2.b `ViewCar`

* Accepts `GET` requests to `/car/:vin`
* Accepts a VIN and returns the corresponding car model.
  * We will validate that the provided VIN is 17 characters and does not include illegal characters.
  * If the VIN is not validated, will throw an `InvalidVINException` (400 exception)
      * If the given VIN is valid but is not found, will throw a
        `CarNotFoundException` (404 exception)

### 6.3  `AddCar`
* Accepts `POST` requests to `/car`
* Accepts data to create a new car with a provided VIN, make, model, capacity, year, class of vehicle, cost per day. Availability will defaulst to "available".
* Return the newly generated car model.
* We will validate that the provided VIN is 17 characters and does not include illegal characters.
  * If the VIN is not validated, will throw an `InvalidVINException` (400 exception)
  * If the VIN is valid but already in the database, will through a `DuplicateInventoryItemException` ()



### 6.4a  `UpdateCar`
* Accepts `PUT` requests to `/car/:vin`. 
* Accepts data to update the status or cost of a car.
* Returns the updated car model.
  * If the VIN is not validated, will throw an `InvalidVINException`
      * If the given VIN is valid but is not found, will throw a
        `CarNotFoundException`

### 6.4b  `CreateTransaction`
* Accepts `POST` requests to `/transac†ions`.
* Accepts data to create a new transaction:
  Including client Id, VIN, All other parameters are optional, or set, by default, to: 
* isActive = true
* dateOut = current local date
* costPerDay is retrieved from the car object accessed by the vin
* dateIn, totalCost are left empty
* returns newly created transaction
*   * If the VIN is not validated, will throw an `InvalidVINException`
    * If the given VIN is valid but is not found, will throw a
      `CarNotFoundException`
    * If the client ID does not match the required format, will throw an `InvalidIDException`


### 6.5a  `UpdateTransaction`
* Accepts `PUT` requests to `/transactions/:id/transactionId`. **QUESTION! What would this endpoint be?
* Accept customerID and transaction ID.
* Sets dateIn to current date/time; sets isActive to false;
* Returns updated transaction
*   * If the VIN is not validated, will throw an `InvalidIDException`
    * If the given VIN is valid but is not found, will throw a
      `CarNotFoundException`



## 6.6  `RemoveCar`

* Accepts `DELETE` requests to `/car/:vin`. 
* Returns a boolean (true if car was successfully deleted, false if car was not in inventory or not successfully deleted)
* We will validate that the provided VIN is 17 characters and does not include illegal characters.
    * If the VIN is not validated, will throw an `InvalidVINException`

# 7. Tables

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



# 8. Pages
![The home page will automatically show a list of inventory, and give the user the chance to sign in.](../../../../Downloads/WireFrames/IMG_1477 Small.png)

![Once the user has signed in they can choose to manage reservations or manage inventory.](../../../../Downloads/WireFrames/IMG_1476 Small.png)

![](../../../../Downloads/WireFrames/IMG_1478 Small.png)

![](../../../../Downloads/WireFrames/IMG_1479 Small.png)
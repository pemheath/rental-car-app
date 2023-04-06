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

## 2. Top Questions to Resolve in Review

_List the most important questions you have about your design, or things that you are still debating internally that you might like help working through._

1. How will we uniquely identify our vehicles? (ie, what will the key structure be for the table) - assign a unique ID to every piece of inventory [UUID] --> the user will not need to care about this
2. What queries will I need to perform as a user? 
3. 

## 3. Use Cases

_This is where we work backwards from the customer and define what our customers would like to do (and why). You may also include use cases for yourselves (as developers), or for the organization providing the product to customers._

U1. As a [rental car management service] customer, I want to add a new car to inventory.

U2. As a [rental car management service] customer, remove a car permanently from inventory.

U3. As a [rental car management service] customer, I want to mark a car as rented, with time stamp of check-out recorded.

U4. As a [rental car management service] customer, I want to mark a car as available, with time stamp of check-in time.

U5. As a [rental car management service] customer, I want to view all available cars.

U6. As a [rental car management service] customer, I want to View a subset of all cars by filtering based on status, capacity, and class.

U7. As a [rental car management service] customer, I want to Flag a car from for maintenance or repairs  (available, rented, broke).

U8. As a [rental car management service] customer, I want to Check a car back in and calculate cost to charger customer based on length of rental.

U9. As a [rental car management service] customer, Update a car's status as it moves from rented to under-prep to available back to rented.

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


# 5. Proposed Architecture Overview

_Describe broadly how you are proposing to solve for the requirements you described in Section 2. This may include class diagram(s) showing what components you are planning to build. You should argue why this architecture (organization of components) is reasonable. That is, why it represents a good data flow and a good separation of concerns. Where applicable, argue why this architecture satisfies the stated requirements._

# 6. API

## 6.1. Public Models

_Define the data models your service will expose in its responses via your *`-Model`* package. These will be equivalent to the *`PlaylistModel`* and *`SongModel`* from the Unit 3 project._

## 6.2. _First Endpoint_

_Describe the behavior of the first endpoint you will build into your service API. This should include what data it requires, what data it returns, and how it will handle any known failure cases. You should also include a sequence diagram showing how a user interaction goes from user to website to service to database, and back. This first endpoint can serve as a template for subsequent endpoints. (If there is a significant difference on a subsequent endpoint, review that with your team before building it!)_

_(You should have a separate section for each of the endpoints you are expecting to build...)_

## 6.3 _Second Endpoint_

_(repeat, but you can use shorthand here, indicating what is different, likely primarily the data in/out and error conditions. If the sequence diagram is nearly identical, you can say in a few words how it is the same/different from the first endpoint)_

# 7. Tables

_Define the DynamoDB tables you will need for the data your service will use. It may be helpful to first think of what objects your service will need, then translate that to a table structure, like with the *`Playlist` POJO* versus the `playlists` table in the Unit 3 project._

# 8. Pages

_Include mock-ups of the web pages you expect to build. These can be as sophisticated as mockups/wireframes using drawing software, or as simple as hand-drawn pictures that represent the key customer-facing components of the pages. It should be clear what the interactions will be on the page, especially where customers enter and submit data. You may want to accompany the mockups with some description of behaviors of the page (e.g. “When customer submits the submit-dog-photo button, the customer is sent to the doggie detail page”)_

# Java Car Rental System

## Introduction

This repository will be used for developing the Car Rental System for our OOD with Java module

## Current Idea of the Flow

1. A Dealership class will contain a list of Dealers (the workers) and a list of cars that can be rented.
2. Cars have more than one type, like 6 seaters, sedans, SUVs, etc. We will create a class for each of this (at least 3) and make them inherit from the Car class
3. Dealers will be separated into normal Dealers and admin Dealers, where admin Dealers can edit Car information and read Reports generated by the Dealership class.
4. Customers will need to enter basic personal information when they first register and can edit these information after registration.
5. Customers will first book the car that they want to rent by browsing through the catalog. The catalog will only show Cars that can be booked.
6. Dealers will need to approve the booking in order for the customer to collect the car in the appointed date. Once approved, the customer will receive a notification (either via email or text message) about the approval or rejection.
   1. I think approval will be changed to be done automatically instead of being done by an admin/dealer. If the car is already booked, then the system will not show the car at all in the catalog.
7. When customers return the rented car, the dealer will also need to approve the return. If approved, a notification is sent only. If rejected, a notification is sent and a fine is imposed on the customer.

## Collaborators

1. Bryan Hor Jin Hao
2. Lim Phin Shuen

## TODOS

- [x] Prepare UML Use Case and Class Diagrams
  - [ ] Edit the UML based on completed code afterwards
  - [ ] Prepare UML descriptions
- [ ] Write out code for all the classes
  - [ ] Dealership/Store class
  - [ ] Car class, with inheritors
  - [ ] Customer class
  - [ ] Dealer/Worker class
- [ ] Add a Test.java file to run tests for the functions of each class
- [ ] Join all classes in the Main class
- [ ] Write out Documentation

## Notes

1. Should do Test Driven Development, prepare separate Test cases based on the functions that are developed for each class.

### Text file formats

   1. They will be stored as JSON strings, achieved by using GSON library

#### Users data storage formats

1. Information about admins are stored in this order:
   1. Fullname
   2. Identification Card no. (listed as Identification)
   3. Email
   4. Contact number (Phone number)
   5. Username
   6. Password

2. Any extra information will be stored after the first 6 listed as above, the constructor method can be inherited from the User class.

#### Car data storage formats

1. Information about cars (include price per day, number of seats)
   1. Hatchback
      1. Perodua Myvi (150, 5)
      2. Perodua Axia (140, 5)
      3. Proton Iriz (170, 5)
      4. Toyota Yaris (200, 5)
   2. Minivan
      1. Toyota Hiace (450, 10)
      2. Proton Exora (370, 7)
      3. Toyota Inova (400, 7)
      4. Hyundai Starex (500, 8)
   3. Sedan
      1. Perodua Bezza (200, 5)
      2. Honda City (260, 5)
      3. Toyota Camry (320, 5)
      4. Nissan Almera (250, 5)
   4. SUV
      1. Proton X50 (350, 5)
      2. Honda CX-5 (500, 5)
      3. Honda HR-V (390, 5)
      4. Nissan X-Trail (370, 5)

Phin Shuen, you can add the format that you use to store the data here. 
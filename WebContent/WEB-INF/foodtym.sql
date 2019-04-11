CREATE DATABASE FoodTym;

USE FoodTym;

CREATE TABLE NcrRegions (

    NcrRegionID SMALLINT UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,

    NcrRegionName VARCHAR(60) NOT NULL
);

CREATE TABLE Localities (

    LocalityID SMALLINT UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,

    LocalityName VARCHAR(50) NOT NULL,

    NcrRegionID SMALLINT UNSIGNED NOT NULL,

    FOREIGN KEY (NcrRegionID) REFERENCES NcrRegions(NcrRegionID)

);

CREATE TABLE RestaurantOwners (

    RestaurantOwnerID SMALLINT UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,

    FirstName VARCHAR(20) NOT NULL,

    LastName VARCHAR(20) NOT NULL,

    DOB DATE NOT NULL,

    Picture MEDIUMBLOB,

    Gender ENUM('FEMALE','MALE') DEFAULT 'MALE' NOT NULL,

    MobileNumber CHAR(10) UNIQUE NOT NULL,

    EmailAddress VARCHAR(65) UNIQUE NOT NULL,

    Address VARCHAR(100) NOT NULL

);


CREATE TABLE Restaurants (

    RestaurantID SMALLINT UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,

    Name VARCHAR(50) NOT NULL,

    LocalityID SMALLINT UNSIGNED NOT NULL,

    Banner MEDIUMBLOB,

    FSSAIRegistrationNumber CHAR(14),

    Note VARCHAR(200),

    RestaurantOwnerID SMALLINT UNSIGNED NOT NULL,

    Address VARCHAR(100) NOT NULL,

    OpenTime TIME NOT NULL,

    CloseTime TIME NOT NULL,

    RegistrationDate DATE NOT NULL,

    RegistrationAmount DECIMAL NOT NULL,

    MobileNumber CHAR(10) UNIQUE NOT NULL,

    EmailAddress VARCHAR(65) UNIQUE NOT NULL,

    Password CHAR(40) NOT NULL,

    FOREIGN KEY (LocalityID) REFERENCES Localities(LocalityID),

    FOREIGN KEY (RestaurantOwnerID) REFERENCES RestaurantOwner(RestaurantOwnerID)

);



CREATE TABLE Customers (

    CustomerID INT UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,

    Name VARCHAR(40) NOT NULL,

    EmailAddress VARCHAR(65) UNIQUE NOT NULL,

    MobileNumber CHAR(10) UNIQUE NOT NULL,

    Password CHAR(40) NOT NULL

);


CREATE TABLE FoodCategories (

    FoodCategoryID TINYINT UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,

    Name VARCHAR(30) NOT NULL

);



CREATE TABLE FoodSubCategories (

    FoodSubCategoryID SMALLINT UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,

    FoodCategoryID TINYINT UNSIGNED NOT NULL,

    Name VARCHAR(30) NOT NULL,

    FOREIGN KEY (FoodCategoryID) REFERENCES FoodCategories(FoodCategoryID)

);




CREATE TABLE FoodItems (

    FoodItemID INT UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,

    Title VARCHAR(40),

    FoodSubCategoryID SMALLINT UNSIGNED NOT NULL,

    Image MEDIUMBLOB,

    FoodItemDescription VARCHAR(100),

    RestaurantID SMALLINT UNSIGNED NOT NULL,

    Availability ENUM('AVAILABLE','UNAVAILABLE') NOT NULL DEFAULT 'AVAILABLE',
    
    PriceBasis ENUM('HALF_FULL','PCS','KG') NOT NULL,

    PreparingTime SMALLINT UNSIGNED NOT NULL,

    FoodType ENUM('VEG','NONVEG') NOT NULL,

    FOREIGN KEY (RestaurantID) REFERENCES Restaurants(RestaurantID)

);



CREATE TABLE FoodItemPrices (

    FoodItemPriceID INT UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,

    FoodItemID INT UNSIGNED NOT NULL,

    PriceType ENUM('HALF','FULL','QTR','PCS','HALF_KG','FULL_KG') NOT NULL,

    RestaurntPrice DECIMAL UNSIGNED NOT NULL,

    Quantity TINYINT UNSIGNED NOT NULL DEFAULT 1,

    FoodTymPrice DECIMAL UNSIGNED NOT NULL,

    FOREIGN KEY (FoodItemID) REFERENCES FoodItems(FoodItemID)

);



CREATE TABLE DeliveryPersons (

    DeliveryPersonID SMALLINT UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,

    FirstName VARCHAR(20) NOT NULL,

    LastName VARCHAR(20) NOT NULL,

    DOB DATE NOT NULL,
    
    Picture MEDIUMBLOB,

    Salary DECIMAL(5,2) UNSIGNED NOT NULL,

    VehicalNumber VARCHAR(25) NOT NULL,

    Address VARCHAR(60) NOT NULL,
   
    MobileNumber CHAR(10) UNIQUE NOT NULL,

    EmailAddress VARCHAR(65) UNIQUE NOT NULL,

    Password CHAR(40) NOT NULL,

    Note VARCHAR(255),

    FatherName VARCHAR(25) NOT NULL,

    Gender ENUM('FEMALE','MALE') DEFAULT 'MALE' NOT NULL ,

    JoinDate DATE NOT NULL,

    CommissionSalary DECIMAL NOT NULL

);


CREATE TABLE DeliveryAreas (

    DeliveryAreaID INT UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,

    DeliveryPersonID SMALLINT UNSIGNED NOT NULL,

    LocalityID SMALLINT UNSIGNED NOT NULL,

    FOREIGN KEY (DeliveryPersonID) REFERENCES DeliveryPersons(DeliveryPersonID),

    FOREIGN KEY (LocalityID) REFERENCES Localities(LocalityID)

);


CREATE TABLE Orders (

    OrderID BIGINT UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,

    CustomerID INT UNSIGNED NOT NULL, /*Customer*/

    RestaurantID SMALLINT UNSIGNED NOT NULL,

    OrderStatus ENUM('NEW_UNPAID_ORDER','WAITING_FOR_RESTAURANT_CONFIRMATION','WAITING_FOR_DELIVERY_PERSON_CONFIRMATION','PREPARING',
'OUT_FOR_DELIVERY',
'DELIVERED') NOT NULL,

    DeliveryAddress VARCHAR(100) NOT NULL,

    LocalityID SMALLINT UNSIGNED NOT NULL,

    DeliveryPersonID SMALLINT UNSIGNED,

    DeliveryCharge DECIMAL UNSIGNED NOT NULL,

    FOREIGN KEY (CustomerID) REFERENCES Customers(CustomerID),

    FOREIGN KEY (RestaurantID) REFERENCES Restaurants(RestaurantID),

    FOREIGN KEY (LocalityID) REFERENCES Localities(LocalityID),

    FOREIGN KEY (DeliveryPersonID) REFERENCES DeliveryPersons(DeliveryAreaID)

);


CREATE TABLE OrderItems (

    OrderItemsID BIGINT UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,

    OrderID BIGINT UNSIGNED NOT NULL,

    FoodItemPriceID INT UNSIGNED NOT NULL,

    Quantity TINYINT UNSIGNED NOT NULL,

    FOREIGN KEY (OrderID) REFERENCES Orders(Orders),

    FOREIGN KEY (FoodItemPriceID) REFERENCES FoodItemPrices(FoodItemPriceID)

);


CREATE TABLE Payments (

    OrderID BIGINT UNSIGNED NOT NULL,

    Amount DECIMAL UNSIGNED NOT NULL,

    Status ENUM('PAID','UNPAID') NOT NULL,

    FOREIGN KEY (OrderID) REFERENCES Orders(OrderID)


);



CREATE TABLE FoodTymAdmin (

    AdminID SMALLINT UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,

    FirstName VARCHAR(20) NOT NULL,

    LastName VARCHAR(20) NOT NULL,

    Username VARCHAR(20) NOT NULL,

    Password CHAR(40) NOT NULL

);




INSERT INTO FoodTymAdmin (FirstName,LastName,Username,Password) values (
    'Sachin','Singh','sachin13',SHA1('root123')
);
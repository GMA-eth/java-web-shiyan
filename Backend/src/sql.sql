DROP DATABASE IF EXISTS Canteens;
CREATE DATABASE IF NOT EXISTS Canteens;

Use Canteens;
CREATE TABLE Users (
                       UserID INT AUTO_INCREMENT PRIMARY KEY,
                       Email VARCHAR(255),
                       Password VARCHAR(255) NOT NULL,
                       Role ENUM('admin', 'user', 'merchant') NOT NULL,
                       CreationTime TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE Canteens (
                          CanteenID INT AUTO_INCREMENT PRIMARY KEY,
                          CanteenName VARCHAR(255) NOT NULL,
                          Location VARCHAR(255) NOT NULL,
                          BusinessHours VARCHAR(255),
                          Description TEXT,
                          CreationTime TIMESTAMP DEFAULT  CURRENT_TIMESTAMP
);

CREATE TABLE Dishes (
                        DishID INT AUTO_INCREMENT PRIMARY KEY,
                        CanteenID INT,
                        DishName VARCHAR(255) NOT NULL,
                        Price DECIMAL(10, 2) NOT NULL,
                        DishType VARCHAR(255),
                        CreationTime TIMESTAMP DEFAULT  CURRENT_TIMESTAMP,
                        FOREIGN KEY (CanteenID) REFERENCES Canteens(CanteenID)
);

CREATE TABLE Reviews (
                         ReviewID INT AUTO_INCREMENT PRIMARY KEY,
                         UserID INT,
                         DishID INT,
                         Rating INT CHECK (Rating >= 1 AND Rating <= 5),
                         Comment TEXT,
                         CreationTime TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                         FOREIGN KEY (UserID) REFERENCES Users(UserID),
                         FOREIGN KEY (DishID) REFERENCES Dishes(DishID)
);
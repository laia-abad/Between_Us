CREATE DATABASE IF NOT EXISTS BETWEEN_US;
USE BETWEEN_US;
DROP TABLE IF EXISTS Results CASCADE;
DROP TABLE IF EXISTS Matches CASCADE;
DROP TABLE IF EXISTS Users CASCADE;

DROP TABLE IF EXISTS Game_Result CASCADE;
DROP TABLE IF EXISTS Game_Match CASCADE;
DROP TABLE IF EXISTS Game_User CASCADE;


CREATE TABLE Users
(
	username VARCHAR(255) PRIMARY KEY,
    email VARCHAR(255),
    password VARCHAR(255)
);

CREATE TABLE Matches
(
	name VARCHAR(255) ,
    username_player	VARCHAR(255),
    color_player VARCHAR(255),
    n_impostors INTEGER,
    n_players INTEGER,
    map VARCHAR(255),
    ended BOOLEAN,
    filename VARCHAR(255),
    PRIMARY KEY (name, username_player),
    FOREIGN KEY (username_player) REFERENCES Users (username)
);

CREATE TABLE Results
(
	username VARCHAR(255) ,
    match_name VARCHAR(255),
    won VARCHAR(255),
    FOREIGN KEY (username) REFERENCES Users (username),
    FOREIGN KEY (match_name) REFERENCES Matches (name)
);

SELECT * FROM Users;
SELECT * FROM Matches;
SELECT * FROM Results;
DROP TABLE IF EXISTS Images;
DROP TABLE IF EXISTS ImageHistory;
DROP TABLE IF EXISTS Messages;
DROP TABLE IF EXISTS Users;
CREATE TABLE Images (id INTEGER PRIMARY KEY, hashcode BIGINT NOT NULL);
CREATE TABLE ImageHistory (id_image INTEGER REFERENCES Images(id), username VARCHAR(25), created TIMESTAMP);
CREATE TABLE Messages (id INTEGER PRIMARY KEY, id_image INTEGER REFERENCES Images(id), username VARCHAR(25), message TEXT, created TIMESTAMP);
CREATE TABLE Users (id INTEGER PRIMARY KEY, username VARCHAR(25), passHashed TEXT, salt TEXT, nOfComments INTEGER);
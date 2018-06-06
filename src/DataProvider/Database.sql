DROP DATABASE IF EXISTS  complete;
CREATE DATABASE complete;

USE complete; 

/* Tables */

CREATE TABLE users (
	id int(11) NOT NULL AUTO_INCREMENT,
	username varchar(255) NOT NULL,
	password varchar(255) NOT NULL,
	regionId int(11) NOT NULL,
	firstName varchar(255) DEFAULT "xXfirstnameXx",
	lastName varchar(255) DEFAULT "xXlastnameXx",
	address varchar(255) DEFAULT "xXaddressXx",
	PRIMARY KEY (id),
	UNIQUE KEY username (username)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;;

CREATE TABLE stickers (
	id int(11) NOT NULL AUTO_INCREMENT,
	name varchar(255) NOT NULL,
	team varchar(255) NOT NULL,
	PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;;

CREATE TABLE regions (
	id int(11) NOT NULL,
	name varchar(255) NOT NULL,
	country varchar(255) NOT NULL,
	PRIMARY KEY (id)
);

CREATE TABLE userStickerRef (
	userId int(11) NOT NULL,
	stickerId int(11) NOT NULL,
	available int(4) NOT NULL DEFAULT 0,
	needed int(4) NOT NULL DEFAULT 0,
	PRIMARY KEY (userId, stickerId)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;;

CREATE TABLE transactions (
	id int(11) NOT NULL AUTO_INCREMENT,
	giverId int(11) NOT NULL,
	takerId int(11) NOT NULL,
	stickerId int(11) NOT NULL,
	status int(4) NOT NULL,
	PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;;

/* Constraints */

ALTER TABLE users
ADD CONSTRAINT users_1 FOREIGN KEY (regionId) REFERENCES regions (id) ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE userStickerRef
ADD CONSTRAINT userStickerRef_1 FOREIGN KEY (userId) REFERENCES users (id) ON DELETE CASCADE ON UPDATE CASCADE;
ALTER TABLE userStickerRef
ADD CONSTRAINT userStickerRef_2 FOREIGN KEY (stickerId) REFERENCES stickers (id) ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE transactions
ADD CONSTRAINT transactions_1 FOREIGN KEY (giverId) REFERENCES users (id) ON DELETE CASCADE ON UPDATE CASCADE;
ALTER TABLE transactions
ADD CONSTRAINT transactions_2 FOREIGN KEY (takerId) REFERENCES users (id) ON DELETE CASCADE ON UPDATE CASCADE;
ALTER TABLE transactions
ADD CONSTRAINT transactions_3 FOREIGN KEY (stickerId) REFERENCES stickers (id) ON DELETE CASCADE ON UPDATE CASCADE;


/* Inserts */
	
/* Inserts: regions */
INSERT INTO regions (id, name, country) VALUES (1, "Upper Austria", "Austria");
INSERT INTO regions (id, name, country) VALUES (2, "Lower Austria", "Austria");
INSERT INTO regions (id, name, country) VALUES (3, "Styria", "Austria");

/* Inserts: users */
INSERT INTO users (username, password, regionId, firstName, lastName, address)
			VALUES ("moh", "a", 1, "Most", "Dipf", "Berghain 3");
INSERT INTO users (username, password, regionId, firstName, lastName, address)
			VALUES ("clk", "a", 1, "Clemens", "Ko", "Heimweg 4");
INSERT INTO users (username, password, regionId, firstName, lastName, address)
			VALUES ("albi", "a", 1, "Alex", "Bisasam", "Couchweg 24b");
INSERT INTO users (username, password, regionId, firstName, lastName, address)
			VALUES ("weitweg", "a", 2, "Walter", "Weg", "Weitenfernt 1");
INSERT INTO users (username, password, regionId, firstName, lastName, address)
			VALUES ("zeifried", "a", 2, "Zulu", "Zoolander", "Zoostraße 7");
INSERT INTO users (username, password, regionId)
			VALUES ("defaultName", "a", 3);
			
/* Inserts: stickers */
INSERT INTO stickers (id, name, team) VALUES (0, "defaultSticker", "none");
INSERT INTO stickers (name, team) VALUES ("David Alaba", "Austria");
INSERT INTO stickers (name, team) VALUES ("Arno Arnautovic", "Austria");
INSERT INTO stickers (name, team) VALUES ("David Hasslehoff", "Austria");
INSERT INTO stickers (name, team) VALUES ("Arnold Schwarzenegger", "Austria");
INSERT INTO stickers (name, team) VALUES ("Max Hummels", "Germany");
INSERT INTO stickers (name, team) VALUES ("Mike Krüger", "Germany");
INSERT INTO stickers (name, team) VALUES ("Jan Böhmermann", "Germany");
INSERT INTO stickers (name, team) VALUES ("Oli Schulz", "Germany");
INSERT INTO stickers (name, team) VALUES ("Max Hummels", "Germany");
INSERT INTO stickers (name, team) VALUES ("Antoine Saint-Exuperi", "France");
INSERT INTO stickers (name, team) VALUES ("Alexandre Dumas", "France");
INSERT INTO stickers (name, team) VALUES ("Charles DeGaulles", "France");
INSERT INTO stickers (name, team) VALUES ("Wagner Vaz", "Brazil");
INSERT INTO stickers (name, team) VALUES ("Alessandro Bruckheimer", "Brazil");

		
/* Inserts: userStickerRef */
INSERT INTO userStickerRef (userId, stickerId, available) VALUES (1, 1, 3);
INSERT INTO userStickerRef (userId, stickerId, available) VALUES (1, 2, 4);
INSERT INTO userStickerRef (userId, stickerId, available) VALUES (1, 3, 5);
INSERT INTO userStickerRef (userId, stickerId, available) VALUES (1, 4, 2);
INSERT INTO userStickerRef (userId, stickerId, needed)		VALUES (1, 5, 1);
INSERT INTO userStickerRef (userId, stickerId, needed)		VALUES (1, 6, 1);

INSERT INTO userStickerRef (userId, stickerId, needed)		VALUES (2, 1, 1);
INSERT INTO userStickerRef (userId, stickerId, needed)		VALUES (2, 2, 1);
INSERT INTO userStickerRef (userId, stickerId, available) VALUES (2, 3, 2);
INSERT INTO userStickerRef (userId, stickerId, available) VALUES (2, 4, 2);
INSERT INTO userStickerRef (userId, stickerId, available) VALUES (2, 5, 2);
INSERT INTO userStickerRef (userId, stickerId, available) VALUES (2, 6, 2);

INSERT INTO userStickerRef (userId, stickerId, needed)		VALUES (3, 2, 1);
INSERT INTO userStickerRef (userId, stickerId, needed)		VALUES (3, 3, 1);
INSERT INTO userStickerRef (userId, stickerId, needed)		VALUES (3, 9, 1);
INSERT INTO userStickerRef (userId, stickerId, available) VALUES (3, 4, 2);
INSERT INTO userStickerRef (userId, stickerId, available) VALUES (3, 5, 3);
INSERT INTO userStickerRef (userId, stickerId, available) VALUES (3, 7, 7);
INSERT INTO userStickerRef (userId, stickerId, available) VALUES (3, 8, 3);

INSERT INTO userStickerRef (userId, stickerId, needed)		VALUES (4, 2, 2);
INSERT INTO userStickerRef (userId, stickerId, needed)		VALUES (4, 3, 2);
INSERT INTO userStickerRef (userId, stickerId, available) VALUES (4, 4, 4);
INSERT INTO userStickerRef (userId, stickerId, available) VALUES (4, 8, 5);
INSERT INTO userStickerRef (userId, stickerId, available) VALUES (4, 9, 3);
INSERT INTO userStickerRef (userId, stickerId, available) VALUES (4, 10, 2);

INSERT INTO userStickerRef (userId, stickerId, needed)		VALUES (5, 7, 1);
INSERT INTO userStickerRef (userId, stickerId, needed)		VALUES (5, 8, 1);
INSERT INTO userStickerRef (userId, stickerId, available) VALUES (5, 2, 4);
INSERT INTO userStickerRef (userId, stickerId, available) VALUES (5, 3, 3);
INSERT INTO userStickerRef (userId, stickerId, available) VALUES (5, 6, 2);
INSERT INTO userStickerRef (userId, stickerId, available) VALUES (5, 9, 2);

INSERT INTO userStickerRef (userId, stickerId, needed)		VALUES (6, 4, 1);
INSERT INTO userStickerRef (userId, stickerId, available) VALUES (6, 2, 5);
INSERT INTO userStickerRef (userId, stickerId, available) VALUES (6, 5, 3);
INSERT INTO userStickerRef (userId, stickerId, available) VALUES (6, 6, 4);
INSERT INTO userStickerRef (userId, stickerId, available) VALUES (6, 7, 2);

/* Inserts: transactions */
INSERT INTO transactions (giverId, takerId, stickerId, status) 
									VALUES (   1,   		2, 				4,				1	 );
INSERT INTO transactions (giverId, takerId, stickerId, status) 
									VALUES (   1,   		2, 				4,				1	 );
INSERT INTO transactions (giverId, takerId, stickerId, status) 
									VALUES (   2,   		1, 				9,				1	 );
INSERT INTO transactions (giverId, takerId, stickerId, status) 
									VALUES (   2,   		3, 				2,				2	 );
INSERT INTO transactions (giverId, takerId, stickerId, status) 
									VALUES (   1,   		2, 				4,				2	 );
INSERT INTO transactions (giverId, takerId, stickerId, status) 
									VALUES (   2,   		1, 				9,				3	 );





-- Default password for john.doe@gmail.com is: asd@123456
INSERT INTO USER("ID", "EMAIL", "FIRSTNAME", "LASTNAME", "PASSWORD") VALUES
(10001, 'john.doe@gmail.com', 'Jane', 'Doe', '$2a$10$Zj38WYKI.gGbhQWuATpY2.nI8CowD/ujyC7c2MozoyMBGNi8z6bui');

INSERT INTO ROLE(id, name) VALUES(10001, 'ROLE_USER');
INSERT INTO ROLE(id, name) VALUES(10002, 'ROLE_ADMIN');

INSERT INTO USER_ROLES(USER_ID, ROLE_ID) VALUES
	(10001, 10001),
	(10001, 10002);
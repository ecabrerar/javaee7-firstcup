create table "BOOKUSER".BOOK
(
	ISBN VARCHAR(15) not null,
	TITLE VARCHAR(100) not null,
	AUTHOR VARCHAR(75) not null,
	PUBLISHER VARCHAR(75) not null,
	EDITION VARCHAR(15) not null,
	PRICE DOUBLE not null,
	DESCRIPTION VARCHAR(200) not null,
	ID INTEGER default AUTOINCREMENT: start 1 increment 1 not null primary key
)

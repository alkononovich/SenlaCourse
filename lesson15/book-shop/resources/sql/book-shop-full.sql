INSERT INTO book VALUES (1, '451F', 150, 0, "2002-02-02",CURDATE());
INSERT INTO book VALUES (2, 'Repka', 170, 1, "2003-02-02", CURDATE());
INSERT INTO book VALUES (3, 'LordOfTheRings', 160, 3, "2002-02-03", CURDATE());
INSERT INTO book VALUES (4, 'Hobbyt', 185, 2, "2002-03-03", CURDATE());
INSERT INTO book VALUES (5, 'Witcher', 190, 3, "2005-02-03", CURDATE());

INSERT INTO _order VALUES (1, 3, 'Lena', null, 'o');
INSERT INTO _order VALUES (2, 2, 'Vasili', "2017-12-04", 'c');
INSERT INTO _order VALUES (3, 5, 'Kostik', null, 'o');

INSERT INTO claim VALUES (1, 1, 'o');
INSERT INTO claim VALUES (2, 3, 'c');
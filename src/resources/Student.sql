drop table customer if exists;


CREATE TABLE customer (
    id INT AUTO_INCREMENT PRIMARY KEY,
    login VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(100) NOT NULL
);

-- データの挿入
INSERT INTO customer VALUES (NULL, 'ayukawa', 'SweetfishRiver1');
INSERT INTO customer VALUES (NULL, 'samejima', 'SharkIsland2');
INSERT INTO customer VALUES (NULL, 'wanibuchi', 'CrocodileChasm3');
INSERT INTO customer VALUES (NULL, 'ebihara', 'ShrimpField4');
INSERT INTO customer VALUES (NULL, 'kanie', 'CrubBay5');
INSERT INTO customer VALUES (NULL, '大原', 'Pass');
);


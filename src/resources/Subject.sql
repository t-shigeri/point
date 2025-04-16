drop table customer if exists;

CREATE TABLE SUBJECT (
    SCHOOL_CD CHAR(10),
    CD CHAR(3),
    NAME VARCHAR(20),
    PRIMARY KEY (SCHOOL_CD, CD)
);

INSERT INTO SUBJECT VALUES ('oom','A02','国語');
drop table customer if exists;

CREATE TABLE TEACHER (
    ID VARCHAR(10) PRIMARY KEY,
    PASSWORD VARCHAR(30),
    NAME VARCHAR(10),
    SCHOOL_CD CHAR(3)
);

INSERT INTO TEACHER VALUES ('admin','password','大原花子','oom');

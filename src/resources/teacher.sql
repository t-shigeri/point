drop table teacher if exists;

CREATE TABLE TEACHER (
    ID VARCHAR(10) PRIMARY KEY,
    PASSWORD VARCHAR(30),
    NAME VARCHAR(10),
    SCHOOL_CD CHAR(3)
);

INSERT INTO teacher VALUES ('admin','password','大原花子','oom');

DROP TABLE USER_ROLES IF EXISTS;
DROP TABLE TRANSACTIONS IF EXISTS;
DROP TABLE BILLS IF EXISTS;
DROP TABLE USERS IF EXISTS;


CREATE TABLE USERS
(
    ID         BIGINT          GENERATED BY DEFAULT AS IDENTITY (START WITH 1000 INCREMENT BY 1) PRIMARY KEY,
    ACTIVE     BOOLEAN         NOT NULL,
    USERNAME   VARCHAR(255)    NOT NULL,
    PASSWORD   VARCHAR(255)    NOT NULL
);

CREATE UNIQUE INDEX users_unique_username_idx ON USERS (USERNAME);


CREATE TABLE USER_ROLES
(
    USER_ID       BIGINT          NOT NULL,
    ROLES         VARCHAR(255)    NOT NULL,
    CONSTRAINT user_roles_idx UNIQUE (USER_ID, ROLES),
    FOREIGN KEY (USER_ID) REFERENCES USERS (ID) ON DELETE CASCADE
);


CREATE TABLE BILLS
(
    ID       BIGINT     GENERATED BY DEFAULT AS IDENTITY (START WITH 1000 INCREMENT BY 1) PRIMARY KEY,
    USER_ID  BIGINT     NOT NULL,
    MONEY    INT        NOT NULL,
    FOREIGN KEY (USER_ID) REFERENCES USERS (ID) ON DELETE CASCADE
);


CREATE TABLE TRANSACTIONS
(
    ID       BIGINT     GENERATED BY DEFAULT AS IDENTITY (START WITH 1000 INCREMENT BY 1) PRIMARY KEY,
    BILL_FROM  BIGINT,
    BILL_TO  BIGINT,
    MONEY    INT        NOT NULL,
    FOREIGN KEY (BILL_FROM) REFERENCES BILLS (ID) ON DELETE CASCADE,
    FOREIGN KEY (BILL_TO) REFERENCES BILLS (ID) ON DELETE CASCADE
);

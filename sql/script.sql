CREATE USER DONATOR_PROJECT IDENTIFIED BY oracle;
GRANT CONNECT TO DONATOR_PROJECT;
GRANT CONNECT, RESOURCE, DBA TO DONATOR_PROJECT;
GRANT CREATE SESSION TO DONATOR_PROJECT;
GRANT DBA TO DONATOR_PROJECT;
GRANT CREATE VIEW, CREATE PROCEDURE, CREATE SEQUENCE to DONATOR_PROJECT;
GRANT UNLIMITED TABLESPACE TO DONATOR_PROJECT;
GRANT CREATE MATERIALIZED VIEW TO DONATOR_PROJECT;
GRANT CREATE TABLE TO DONATOR_PROJECT;
GRANT GLOBAL QUERY REWRITE TO DONATOR_PROJECT;
GRANT SELECT ANY TABLE TO DONATOR_PROJECT;

-- -----------------------------------------------------
-- Table DONATOR_PROJECT.CATEGORY
-- -----------------------------------------------------
CREATE TABLE  DONATOR_PROJECT.CATEGORY (
  id_category NUMBER NOT NULL,
  name VARCHAR2(45) UNIQUE NOT NULL,
  category_description VARCHAR2(100) NOT NULL,
  
  PRIMARY KEY (id_category)
);
	CREATE SEQUENCE DONATOR_PROJECT.category_seq
	 START WITH     1
	 INCREMENT BY   1
	 NOCACHE
	 NOCYCLE;	
	
-- -----------------------------------------------------
-- Table DONATOR_PROJECT.BANK_ACCOUNT
-- -----------------------------------------------------
CREATE TABLE  DONATOR_PROJECT.BANK_ACCOUNT (
  id_bank_account NUMBER NOT NULL,
  account_number VARCHAR2(7) NOT NULL,
  agency VARCHAR2(4) NOT NULL,
  
  PRIMARY KEY (id_bank_account)
);
	CREATE SEQUENCE DONATOR_PROJECT.bank_account_seq
	 START WITH     1
	 INCREMENT BY   1
	 NOCACHE
	 NOCYCLE;

-- -----------------------------------------------------
-- Table DONATOR_PROJECT.USER
-- -----------------------------------------------------
CREATE TABLE  DONATOR_PROJECT.USERS (
  id_user NUMBER NOT NULL,
  name VARCHAR2(45) NOT NULL,
  email VARCHAR2(70) NOT NULL,
  password VARCHAR2(2000) NOT NULL,
  type NUMBER NOT NULL,
  document VARCHAR2(18) NOT NULL,
  
  PRIMARY KEY (id_user)
);
	CREATE SEQUENCE DONATOR_PROJECT.users_seq
	 START WITH     1
	 INCREMENT BY   1
	 NOCACHE
	 NOCYCLE;
		
-- -----------------------------------------------------
-- Table DONATOR_PROJECT.REQUEST
-- -----------------------------------------------------
CREATE TABLE  DONATOR_PROJECT.REQUEST (
  id_request NUMBER NOT NULL,
  title VARCHAR2(45) NOT NULL,
  request_description VARCHAR2(2000) NOT NULL,
  goal NUMBER NOT NULL,
  reached_value NUMBER NOT NULL,
  id_category NUMBER NOT NULL,
  id_bank_account NUMBER NOT NULL,
  id_user NUMBER NOT NULL,
  
  PRIMARY KEY (id_request),
  CONSTRAINT fk_Request_Category
    FOREIGN KEY (id_category)
    REFERENCES DONATOR_PROJECT.CATEGORY (id_category),
  CONSTRAINT fk_Request_BankAccount1
    FOREIGN KEY (id_bank_account)
    REFERENCES DONATOR_PROJECT.BANK_ACCOUNT (id_bank_account),
  CONSTRAINT fk_REQUEST_USER1
    FOREIGN KEY (id_user)
    REFERENCES DONATOR_PROJECT.USERS (id_user)
);
	CREATE SEQUENCE DONATOR_PROJECT.request_seq
	 START WITH     1
	 INCREMENT BY   1
	 NOCACHE
	 NOCYCLE;	

-- -----------------------------------------------------
-- Table DONATOR_PROJECT.DONATE
-- -----------------------------------------------------
CREATE TABLE  DONATOR_PROJECT.DONATE (
  id_donate NUMBER NOT NULL,
  id_request NUMBER NOT NULL,
  donator_name VARCHAR2(45) NOT NULL,
  donator_email VARCHAR2(70) NOT NULL,
  donate_value NUMBER NOT NULL,
  donate_description VARCHAR2(2000),
  
  PRIMARY KEY (id_donate),
  CONSTRAINT fk_Request
    FOREIGN KEY (id_request)
    REFERENCES DONATOR_PROJECT.REQUEST (id_request)

);
	CREATE SEQUENCE DONATOR_PROJECT.donate_seq
	 START WITH     1
	 INCREMENT BY   1
	 NOCACHE
	 NOCYCLE;
	 
-- -----------------------------------------------------
-- INSERT DONATOR_PROJECT.CATEGORY
-- -----------------------------------------------------	
INSERT INTO DONATOR_PROJECT.CATEGORY (id_category , name, category_description) 
VALUES (DONATOR_PROJECT.category_seq.nextval, 'COMBATE_A_FOME', 'Combate a Fome');
INSERT INTO DONATOR_PROJECT.CATEGORY (id_category , name, category_description) 
VALUES (DONATOR_PROJECT.category_seq.nextval, 'CRIANÇAS', 'Crianças');
INSERT INTO DONATOR_PROJECT.CATEGORY (id_category , name, category_description) 
VALUES (DONATOR_PROJECT.category_seq.nextval, 'ENFERMOS', 'Enfermos');
INSERT INTO DONATOR_PROJECT.CATEGORY (id_category , name, category_description) 
VALUES (DONATOR_PROJECT.category_seq.nextval, 'COMBATE_A_COVID_19', 'Combate a COVID-19');
INSERT INTO DONATOR_PROJECT.CATEGORY (id_category , name, category_description) 
VALUES (DONATOR_PROJECT.category_seq.nextval, 'CAUSAS_AMBIENTAIS', 'Causas Ambientais');
INSERT INTO DONATOR_PROJECT.CATEGORY (id_category , name, category_description) 
VALUES (DONATOR_PROJECT.category_seq.nextval, 'SOBREVIVENTES_DE_GUERRA', 'Sobreviventes de Guerra');
INSERT INTO DONATOR_PROJECT.CATEGORY (id_category , name, category_description) 
VALUES (DONATOR_PROJECT.category_seq.nextval, 'ANIMAIS', 'Animais');
INSERT INTO DONATOR_PROJECT.CATEGORY (id_category , name, category_description) 
VALUES (DONATOR_PROJECT.category_seq.nextval, 'SONHOS', 'Sonhos');
INSERT INTO DONATOR_PROJECT.CATEGORY (id_category , name, category_description) 
VALUES (DONATOR_PROJECT.category_seq.nextval, 'POBREZA', 'Pobreza');
INSERT INTO DONATOR_PROJECT.CATEGORY (id_category , name, category_description) 
VALUES (DONATOR_PROJECT.category_seq.nextval, 'OUTROS', 'Outros');

-- -----------------------------------------------------
-- INSERT DONATOR_PROJECT.BANK_ACCOUNT
-- -----------------------------------------------------
INSERT INTO DONATOR_PROJECT.BANK_ACCOUNT (id_bank_account, account_number, agency)
VALUES (DONATOR_PROJECT.bank_account_seq.nextval, '220252', '1005');
INSERT INTO DONATOR_PROJECT.BANK_ACCOUNT (id_bank_account, account_number, agency)
VALUES (DONATOR_PROJECT.bank_account_seq.nextval, '1010256', '1008');
INSERT INTO DONATOR_PROJECT.BANK_ACCOUNT (id_bank_account, account_number, agency)
VALUES (DONATOR_PROJECT.bank_account_seq.nextval, '7000255', '1205');
INSERT INTO DONATOR_PROJECT.BANK_ACCOUNT (id_bank_account, account_number, agency)
VALUES (DONATOR_PROJECT.bank_account_seq.nextval, '220252', '1005');
INSERT INTO DONATOR_PROJECT.BANK_ACCOUNT (id_bank_account, account_number, agency)
VALUES (DONATOR_PROJECT.bank_account_seq.nextval, '1010256', '1008');
INSERT INTO DONATOR_PROJECT.BANK_ACCOUNT (id_bank_account, account_number, agency)
VALUES (DONATOR_PROJECT.bank_account_seq.nextval, '7000255', '1205');
	
-- -----------------------------------------------------
-- INSERT DONATOR_PROJECT.USER
-- -----------------------------------------------------
INSERT  INTO DONATOR_PROJECT.USERS (id_user, name, email, password, type, document)
VALUES (DONATOR_PROJECT.users_seq.nextval, 'Daniele', 'dani@gmail', '1234', 1, '123.456.789-00');
INSERT  INTO DONATOR_PROJECT.USERS (id_user, name, email, password, type, document)
VALUES (DONATOR_PROJECT.users_seq.nextval, 'Liane', 'liane@gmail', '1234', 1, '123.456.789-01');
INSERT  INTO DONATOR_PROJECT.USERS (id_user, name, email, password, type, document)
VALUES (DONATOR_PROJECT.users_seq.nextval, 'Claudia', 'claudia@gmail', '1234', 1, '123.456.789-02');
INSERT  INTO DONATOR_PROJECT.USERS (id_user, name, email, password, type, document)
VALUES (DONATOR_PROJECT.users_seq.nextval, 'Rodrigo', 'rodrigo@gmail', '1234', 2, '11.111.111/1111-11');

-- -----------------------------------------------------
-- INSERT DONATOR_PROJECT.REQUEST
-- -----------------------------------------------------
INSERT INTO DONATOR_PROJECT.REQUEST (id_request, title, request_description, goal, reached_value, id_category, id_bank_account, id_user)
VALUES (DONATOR_PROJECT.request_seq.nextval, 'Criança faminta', ' Não temos dinheiro para alimentar nossa filha de 3 anos', 50000, 0, 2, 1, 1);
INSERT INTO DONATOR_PROJECT.REQUEST (id_request, title, request_description, goal, reached_value, id_category, id_bank_account, id_user)
VALUES (DONATOR_PROJECT.request_seq.nextval, 'Meu pai está morrendo', ' Não temos dinheiro para os remédios', 100000, 0, 3, 2, 2);
INSERT INTO DONATOR_PROJECT.REQUEST (id_request, title, request_description, goal, reached_value, id_category, id_bank_account, id_user)
VALUES (DONATOR_PROJECT.request_seq.nextval, 'Anne não tem fámilia, país e comida', ' Uma refugiada Ucraniana de 7 anos, precisamos de dinheiro pra manter a ong', 200000, 0, 6, 3, 3);

-- -----------------------------------------------------
-- INSERT DONATOR_PROJECT.DONATE
-- -----------------------------------------------------	
INSERT INTO DONATOR_PROJECT.DONATE (id_donate, id_request , donator_name, donator_email, donate_value, donate_description)
VALUES (DONATOR_PROJECT.donate_seq.nextval, 1, 'Ana', 'ana@gmail.com', 500.0, 'Boa sorte');
INSERT INTO DONATOR_PROJECT.DONATE (id_donate, id_request ,donator_name, donator_email, donate_value, donate_description)
VALUES (DONATOR_PROJECT.donate_seq.nextval, 3, 'Nicolas', 'nicolas@gmail.com', 100.0, 'melhoras');
INSERT INTO DONATOR_PROJECT.DONATE (id_donate, id_request , donator_name, donator_email, donate_value, donate_description)
VALUES (DONATOR_PROJECT.donate_seq.nextval, 2, 'Lucas', 'lucas@gmail.com', 100.0, NULL);
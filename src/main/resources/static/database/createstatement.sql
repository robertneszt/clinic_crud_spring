CREATE DATABASE medical_clinic;

USE medical_clinic;
DROP TABLE IF EXISTS doctor
CREATE TABLE doctor
  (
     id         INT auto_increment PRIMARY KEY,
     first_name VARCHAR(50) NOT NULL,
     last_name  VARCHAR(50) NOT NULL,
     email      VARCHAR(100) NOT NULL,
     phone      VARCHAR(10) NOT NULL,
     password   VARCHAR(50) NOT NULL,
     role       ENUM('doctor', 'patient') NOT NULL DEFAULT 'doctor'
  );
DROP TABLE IF EXISTS patient
CREATE TABLE patient
  (
     id         INT auto_increment PRIMARY KEY,
     first_name VARCHAR(50) NOT NULL,
     last_name  VARCHAR(50) NOT NULL,
     email      VARCHAR(50) NOT NULL,
     phone      VARCHAR(10) NOT NULL,
     password   VARCHAR(10) NOT NULL,
     role       ENUM('doctor', 'patient') NOT NULL DEFAULT 'patient'
  );
DROP TABLE IF EXISTS medical_record
CREATE TABLE medical_record
  (
     id                   INT auto_increment PRIMARY KEY,
     doc_id               INT NOT NULL,
     patient_id           VARCHAR(12) NOT NULL,
     appointment_datetime DATETIME NOT NULL,
     appointment_status   ENUM('online', 'in-person') NOT NULL,
     FOREIGN KEY (doc_id) REFERENCES doctor(id),
     FOREIGN KEY (patient_id) REFERENCES patient(id)
  );

CREATE TABLE prescription
  (
     id                INT auto_increment PRIMARY KEY,
     doc_id            INT NOT NULL,
     patient_id        VARCHAR(12) NOT NULL,
     prescription_date DATETIME NOT NULL,
     medication_name   VARCHAR(50) NOT NULL,
     diagnosis         VARCHAR(100) NOT NULL,
     FOREIGN KEY (doc_id) REFERENCES doctor(id),
     FOREIGN KEY (patient_id) REFERENCES patient(id)
  );

INSERT INTO doctor
            (id,  first_name,  last_name,  email,  phone,  password,  role)
VALUES     (1, 'John', 'Doe', 'john.doe@example.com', '5145678901', 'doctor123', 'doctor'),
 (2,  'Jane',  'Smith',  'jane.smith@example.com',  '4385678902',  'doctor456',  'doctor'),
 (3,  'Mark',  'Lee',  'mark.lee@example.com',  '5145678903',  'doctor789',  'doctor'),
 (4,  'Emily',  'Chen',  'emily.chen@example.com',  '5145678904',  'doctor111',  'doctor'),
 (5,  'David',  'Kim',  'david.kim@example.com',  '4385678905',  'doctor777',  'doctor');

INSERT INTO patient
            (first_name,  last_name,  email,  phone,  password,  role)
VALUES     ('Alice', 'Johnson', 'alice.johnson@example.com', '5141234567', 'patient111', 'patient'),
 ('Bob',  'Smith',  'bob.smith@example.com',  '4382345678',  'patient222',  'patient'),
 ('Charlie',  'Lee',  'charlie.lee@example.com',  '5143456789',  'patient333',  'patient'),
 ('David',  'Davis',  'david.davis@example.com',  '4384567890',  'patient444',  'patient'),
 ('Elizabeth',  'Kim',  'elizabeth.kim@example.com',  '5145678901',  'patient555',  'patient'),
 ('Frank',  'Wilson',  'frank.wilson@example.com',  '4386789012',  'patient666',  'patient'),
 ('Grace',  'Brown',  'grace.brown@example.com',  '5147890123',  'patient777',  'patient'),
 ('Henry',  'Taylor',  'henry.taylor@example.com',  '4388901234',  'patient888',  'patient'),
 ('Isabella',  'Anderson',  'isabella.anderson@example.com',  '5149012345',  'patient999',  'patient'),
 ('Jack',  'Martin',  'jack.martin@example.com',  '4380123456',  'patient100',  'patient'),
 ('Kelly',  'Lee',  'kelly.lee@example.com',  '5141234567',  'patient110',  'patient'),
 ('Lily',  'Wilson',  'lily.wilson@example.com',  '4382345678',  'patient112',  'patient'),
 ('Mike',  'Brown',  'mike.brown@example.com',  '5143456789',  'patient113',  'patient'),
 ('Nicole',  'Taylor',  'nicole.taylor@example.com',  '4384567890',  'patient114',  'patient'),
 ('Olivia',  'Anderson',  'olivia.anderson@example.com',  '5145678901',  'patient115',  'patient'),
 ('Peter',  'Martin',  'peter.martin@example.com',  '4386789012',  'patient116',  'patient'),
 ('Queenie',  'Lee',  'queenie.lee@example.com',  '5147890123',  'patient117',  'patient'),
 ('Rachel',  'Wilson',  'rachel.wilson@example.com',  '4388901234',  'patient118',  'patient'),
 ('Sam',  'Brown',  'sam.brown@example.com',  '5149012345',  'patient119',  'patient'),
 ('Tom',  'Taylor',  'tom.taylor@example.com',  '4380123456',  'patient120',  'patient');
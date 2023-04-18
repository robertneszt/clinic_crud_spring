CREATE DATABASE medical_clinic;

USE medical_clinic;

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

CREATE TABLE patient
  (
     id         VARCHAR(12) PRIMARY KEY,
     first_name VARCHAR(50) NOT NULL,
     last_name  VARCHAR(50) NOT NULL,
     email      VARCHAR(50) NOT NULL,
     phone      VARCHAR(10) NOT NULL,
     password   VARCHAR(10) NOT NULL,
     role       ENUM('doctor', 'patient') NOT NULL DEFAULT 'patient'
  );

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
            (id,  first_name,  last_name,  email,  phone,  password,  role)
VALUES     ('ABC123', 'Alice', 'Johnson', 'alice.johnson@example.com', '5141234567', 'patient111', 'patient'),
 ('DEF456',  'Bob',  'Smith',  'bob.smith@example.com',  '4382345678',  'patient222',  'patient'),
 ('GHI789',  'Charlie',  'Lee',  'charlie.lee@example.com',  '5143456789',  'patient333',  'patient'),
 ('JKL012',  'David',  'Davis',  'david.davis@example.com',  '4384567890',  'patient444',  'patient'),
 ('MNO345',  'Elizabeth',  'Kim',  'elizabeth.kim@example.com',  '5145678901',  'patient555',  'patient'),
 ('PQR678',  'Frank',  'Wilson',  'frank.wilson@example.com',  '4386789012',  'patient666',  'patient'),
 ('STU901',  'Grace',  'Brown',  'grace.brown@example.com',  '5147890123',  'patient777',  'patient'),
 ('VWX234',  'Henry',  'Taylor',  'henry.taylor@example.com',  '4388901234',  'patient888',  'patient'),
 ('YZAB56',  'Isabella',  'Anderson',  'isabella.anderson@example.com',  '5149012345',  'patient999',  'patient'),
 ('CDE789',  'Jack',  'Martin',  'jack.martin@example.com',  '4380123456',  'patient100',  'patient'),
 ('FGH012',  'Kelly',  'Lee',  'kelly.lee@example.com',  '5141234567',  'patient110',  'patient'),
 ('IJK345',  'Lily',  'Wilson',  'lily.wilson@example.com',  '4382345678',  'patient112',  'patient'),
 ('LMN678',  'Mike',  'Brown',  'mike.brown@example.com',  '5143456789',  'patient113',  'patient'),
 ('OPQ901',  'Nicole',  'Taylor',  'nicole.taylor@example.com',  '4384567890',  'patient114',  'patient'),
 ('RST234',  'Olivia',  'Anderson',  'olivia.anderson@example.com',  '5145678901',  'patient115',  'patient'),
 ('UVW567',  'Peter',  'Martin',  'peter.martin@example.com',  '4386789012',  'patient116',  'patient'),
 ('XYZ890',  'Queenie',  'Lee',  'queenie.lee@example.com',  '5147890123',  'patient117',  'patient'),
 ('ABD123',  'Rachel',  'Wilson',  'rachel.wilson@example.com',  '4388901234',  'patient118',  'patient'),
 ('CEF456',  'Sam',  'Brown',  'sam.brown@example.com',  '5149012345',  'patient119',  'patient'),
 ('GHL789',  'Tom',  'Taylor',  'tom.taylor@example.com',  '4380123456',  'patient120',  'patient');
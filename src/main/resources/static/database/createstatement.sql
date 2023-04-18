CREATE database our_clinic;

use our_clinic;

CREATE TABLE doctor (

  id INT AUTO_INCREMENT PRIMARY KEY,
  first_name VARCHAR(50) NOT NULL,
  last_name VARCHAR(50) NOT NULL,
  email VARCHAR(100) NOT NULL,
  phone VARCHAR(10) NOT NULL,
  password VARCHAR(50) NOT NULL,
  role ENUM('doctor', 'patient') NOT NULL DEFAULT 'doctor'
);

CREATE TABLE patient (
  id VARCHAR(12) PRIMARY KEY,
  first_name VARCHAR(50) NOT NULL,
  last_name VARCHAR(50) NOT NULL,
  email VARCHAR(50) NOT NULL,
  phone VARCHAR(10) NOT NULL,
  password VARCHAR(10) NOT NULL,
  role ENUM('doctor', 'patient') NOT NULL DEFAULT 'patient'
);

CREATE TABLE medical_record (
  id INT AUTO_INCREMENT PRIMARY KEY,
  doc_id INT NOT NULL,
  patient_id VARCHAR(12) NOT NULL,
  appointment_datetime DATETIME NOT NULL,
  appointment_status ENUM('online', 'in-person') NOT NULL,
  FOREIGN KEY (doc_id) REFERENCES doctor(id),
  FOREIGN KEY (patient_id) REFERENCES patient(id)
);

CREATE TABLE prescription (
  id INT AUTO_INCREMENT PRIMARY KEY,
  doc_id INT NOT NULL,
  patient_id VARCHAR(12) NOT NULL,
  prescription_date DATETIME NOT NULL,
  medication_name VARCHAR(50) NOT NULL,
  diagnosis VARCHAR(100) NOT NULL,
  FOREIGN KEY (doc_id) REFERENCES doctor(id),
  FOREIGN KEY (patient_id) REFERENCES patient(id)
);


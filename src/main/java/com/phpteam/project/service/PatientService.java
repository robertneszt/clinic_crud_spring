package com.phpteam.project.service;

import com.phpteam.project.model.Doctor;
import com.phpteam.project.model.Patient;

import java.util.List;

public interface PatientService {

    List<Patient> getAllPatients();

    // Will enable this if we implement crud for patient
//    Long save(Patients patient);

    Patient getPatientById(Long patId);

    // Will enable this if we implement crud for patient
//    void deletePatient(Long patId);
    Patient getPatientByEmail(String patEmail);

    void savePatient(Patient patient);
}

package com.phpteam.project.service;

import com.phpteam.project.model.Doctor;

import java.util.List;

public interface DoctorService {

    List<Doctor> getAllDoctors();

    // Will enable this if we implement crud for doctor
//    Long save(Doctor doctor);

    Doctor getDoctorById(Long docId);
    Doctor getDoctorByName(String name);
    Doctor getDoctorByEmail(String emailId);

    // Will enable this if we implement crud for doctor
//    void deleteDoctor(Long docId);

}

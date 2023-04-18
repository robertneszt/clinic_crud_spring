package com.phpteam.project.service;

import com.phpteam.project.entity.DoctorEntity;
import com.phpteam.project.entity.PatientEntity;
import com.phpteam.project.mapper.MapperHelper;
import com.phpteam.project.model.Patient;
import com.phpteam.project.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PatientServiceImpl implements PatientService {

    private final PatientRepository patientRepository;
    private final MapperHelper mapperHelper;
    @Autowired
    public PatientServiceImpl(PatientRepository patientRepository, MapperHelper mapperHelper) {
        this.patientRepository = patientRepository;
        this.mapperHelper = mapperHelper;
    }

    @Override
    public List<Patient> getAllPatients() {
        var patientEntities= patientRepository.findAll();
        return mapperHelper.convertPatientEntityListToPatientList(patientEntities);
    }

    @Override
    public Patient getPatientById(Long patId) {
        Optional<PatientEntity> foundPat = patientRepository.findById(patId);
        return foundPat.map(mapperHelper::convertPatientEntityToPatient).orElse(null);
    }
    public Patient getPatientByEmail(String patEmail) {
        Optional<PatientEntity> foundPat = patientRepository.findByEmail(patEmail);
        return foundPat.map(mapperHelper::convertPatientEntityToPatient).orElse(null);
    }
}


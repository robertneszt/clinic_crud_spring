package com.phpteam.project.service;

import com.phpteam.project.entity.DoctorEntity;
import com.phpteam.project.mapper.DoctorMapperHelper;
import com.phpteam.project.model.Doctor;
import com.phpteam.project.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DoctorServiceImpl implements DoctorService{

    private final DoctorRepository doctorRepository;
    private final DoctorMapperHelper mapperHelper;

    @Autowired
    public DoctorServiceImpl(DoctorRepository doctorRepository, DoctorMapperHelper mapperHelper) {
        this.doctorRepository = doctorRepository;
        this.mapperHelper = mapperHelper;
    }

    @Override
    public List<Doctor> getAllDoctors() {
        List<DoctorEntity> doctorEntities = doctorRepository.findAll();
        return mapperHelper.convertDoctorEntityListToDoctorList(doctorEntities);
    }

//    @Override
//    public Long save(Doctor doctor) {
//        DoctorEntity entity = mapperHelper.convertDoctorToDoctorEntity(doctor);
//        var result = doctorRepository.save(entity);
//        return result.getId();
//    }

    @Override
    public Doctor getDoctorById(Long docId){
        Optional<DoctorEntity> foundDoc = doctorRepository.findById(docId);
        return foundDoc.map(mapperHelper::convertDoctorEntityToDoctor).orElse(null);
    }

    @Override
    public void deleteDoctor(Long docId) {
        doctorRepository.deleteById(docId);
    }

}

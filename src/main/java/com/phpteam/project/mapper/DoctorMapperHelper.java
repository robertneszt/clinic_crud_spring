package com.phpteam.project.mapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.phpteam.project.entity.DoctorEntity;
import com.phpteam.project.model.Doctor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DoctorMapperHelper {

    @Autowired
    public ObjectMapper mapper;

    public DoctorMapperHelper(ObjectMapper mapper) {
        this.mapper = mapper;
    }

    public List<Doctor> convertDoctorEntityListToDoctorList(List<DoctorEntity> entities){
        List<Doctor> doctors = new ArrayList<>();
        for(DoctorEntity entity: entities){
            doctors.add(mapper.convertValue(entity, Doctor.class));
        }
        return doctors;
    }

    public DoctorEntity convertDoctorToDoctorEntity(Doctor doctor){
        return mapper.convertValue(doctor, DoctorEntity.class);
    }

    public Doctor convertDoctorEntityToDoctor(DoctorEntity doctorEntity){
        return mapper.convertValue(doctorEntity, Doctor.class);
    }

}

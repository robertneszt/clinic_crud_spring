package com.phpteam.project.service;

import com.phpteam.project.entity.AppointmentEntity;
import com.phpteam.project.entity.PatientEntity;
import com.phpteam.project.exception.EntityNotFoundException;
import com.phpteam.project.mapper.MapperHelper;
import com.phpteam.project.model.Appointment;
import com.phpteam.project.repository.AppointmentRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AppointmentServiceImpl implements AppointmentService {
    private final AppointmentRepository appointmentRepository;
    private final MapperHelper mapperHelper;

    public AppointmentServiceImpl(AppointmentRepository appointmentRepository, MapperHelper mapperHelper) {
        this.appointmentRepository = appointmentRepository;
        this.mapperHelper = mapperHelper;
    }

    @Override
    public List<Appointment> getAllAppointments() {
        return null;
    }

    @Override
    public void saveAppointment(Appointment appointment) {
//        var result=appointmentRepository.save(mapperHelper.convertAppointmentToAppointEntity(appointment));
//        return result.getId();
        AppointmentEntity appointmentEntity = mapperHelper.convertAppointmentToAppointEntity(appointment);
        appointmentRepository.save(appointmentEntity);
    }

    @Override
    public Appointment getAppointmentById(Long aptId) {
        Optional<AppointmentEntity> foundApt = appointmentRepository.findById(aptId);
        return foundApt.map(mapperHelper::convertAppointmentEntityToAppointment).orElse(null);
    }

    @Override
    public List<Appointment> getAppointmentsByDocId(Integer docId) {
        var foundApt = appointmentRepository.findAllDoctor(docId.longValue());

        if (foundApt.isEmpty()) {
            throw new EntityNotFoundException("The appointment could not be found for this doctor");
        } else {

            return mapperHelper.convertAppointmentEntityListToAppointmentList(foundApt);
        }
    }


//    @Override
//    public List<Appointment> getAppointmentsByDocId(Integer docId) {
//        var foundApt = (appointmentRepository.findAllByDo(docId));
//        List<AppointmentEntity> appointmentList= new ArrayList<>();
//        if(foundApt.isEmpty()){
//            throw new EntityNotFoundException("the appointment could not be found for this doctor -> ");
//        }else {
//            appointmentList.add(foundApt.get());
//            return mapperHelper.convertAppointmentEntityListToAppointmentList(appointmentList);
//        }
//    }

    @Override
    public List<Appointment> getAppointmentsByPatId(Integer patId) {
        var foundApt = (appointmentRepository.findByPatientId(patId));
        List<AppointmentEntity> appointmentList = new ArrayList<>();
        if (foundApt.isEmpty()) {
            throw new EntityNotFoundException("No appointments found for this patient");
        } else {
            appointmentList.add(foundApt.get());
            return mapperHelper.convertAppointmentEntityListToAppointmentList(appointmentList);
        }


    }

    @Override
    public List<Appointment> getAppointmentsByDocPhone(String docPhone) {
        return null;
    }

    @Override
    public List<Appointment> getAppointmentsByPatPhone(String patPhone) {
        return null;
    }

    @Override
    public List<Appointment> getAppointmentsByDocEmail(String docEmail) {
        return null;
    }

    @Override
    public List<Appointment> getAppointmentsByPatEmail(String patEmail) {
        return null;
    }

    @Override
    public void deleteAppointment(Long aptId) {
        Optional<AppointmentEntity> foundApt = appointmentRepository.findById(aptId);

        if(foundApt.isPresent()){
            AppointmentEntity appointmentEntity = foundApt.get();
            appointmentRepository.delete(appointmentEntity);
        } else {
            throw new EntityNotFoundException("The appointment is not found, please enter an existing appointment ID. ");
        }
    }
}

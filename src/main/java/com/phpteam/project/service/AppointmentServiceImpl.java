package com.phpteam.project.service;

import com.phpteam.project.mapper.MapperHelper;
import com.phpteam.project.model.Appointment;
import com.phpteam.project.repository.AppointmentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppointmentServiceImpl implements AppointmentService{
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
    public Long saveAppointment(Appointment appointment) {
        var result=appointmentRepository.save(mapperHelper.convertAppointmentToAppointEntity(appointment));
        return result.getId();
    }

    @Override
    public Appointment getAppointmentById(Long theId) {
        return null;
    }

    @Override
    public List<Appointment> getAppointmentsByDocId(Long docId) {
        return null;
    }

    @Override
    public List<Appointment> getAppointmentsByPatId(Long patId) {
        return null;
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
    public void deleteAppointment(Long appId) {

    }
}

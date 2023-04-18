package com.phpteam.project.service;

import com.phpteam.project.model.Appointment;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppointmentServiceImpl implements AppointmentService{
    @Override
    public List<Appointment> getAllAppointments() {
        return null;
    }

    @Override
    public Long saveAppointment(Appointment appointment) {
        return null;
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

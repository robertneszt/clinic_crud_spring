package com.phpteam.project.service;

import com.phpteam.project.model.Appointment;
import com.phpteam.project.model.Doctor;

import java.util.List;

public interface AppointmentService {

    List<Appointment> getAllAppointments();

    Long saveAppointment(Appointment appointment);

    Appointment getAppointmentById();

    List<Appointment> getAppointmentsByDocId(Long docId);

    List<Appointment> getAppointmentsByPatId(Long patId);

    List<Appointment> getAppointmentsByDocPhone(String docPhone);

    List<Appointment> getAppointmentsByPatPhone(String patPhone);

    List<Appointment> getAppointmentsByDocEmail(String docEmail);

    List<Appointment> getAppointmentsByPatEmail(String patEmail);

    void deleteAppointment(Long appId);


}

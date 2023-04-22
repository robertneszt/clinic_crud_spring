package com.phpteam.project.controller;

import com.phpteam.project.exception.EntityNotFoundException;
import com.phpteam.project.model.Appointment;
import com.phpteam.project.model.Doctor;
import com.phpteam.project.model.Patient;
import com.phpteam.project.service.AppointmentService;
import com.phpteam.project.service.DoctorService;
import com.phpteam.project.service.PatientService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RequestMapping("/clinic")
public class MainController {

    private final DoctorService doctorService;
    private final PatientService patientService;
    private final AppointmentService appointmentService;
    private final HttpSession session;

    @Autowired
    public MainController(DoctorService doctorService, PatientService patientService, AppointmentService appointmentService, HttpSession session) {
        this.doctorService = doctorService;
        this.patientService = patientService;
        this.appointmentService = appointmentService;
        this.session = session;
    }

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/list-doctors")
    public String listDoctors(Model theModel) {
        List<Doctor> theDoctors = doctorService.getAllDoctors();
        theModel.addAttribute("doctors", theDoctors);
        return "doctor/list-doctors";
    }

    @GetMapping("/doctor")
    public String getDoctorById(@RequestParam("docId") Long docId, Model theModel) {
        Doctor existingDoc = doctorService.getDoctorById(docId);
        theModel.addAttribute("doctor", existingDoc);
        return "doctor/doctor-detail";
    }

    //trying 3 different login methods, id, email and name just as test TODO
    // made email as unique and will use email to login
    // doctor can view his appointments, all appointments, book an appointment and modify an appointment.
    // DONE: based upon this we can crate login of patient and give some options.
    // DONE: patients can login TODO: an can see just there appointments or can modify there appointments
    // TODO: 2023-04-18
    @RequestMapping("/doctorLogin")
    public String getDoctorByEmail(@RequestParam(value = "docEmail") String docEmail, Model theModel, HttpServletRequest request) {


        HttpSession session = request.getSession();
        try {
            Doctor existingDoc = doctorService.getDoctorByEmail(docEmail);
            var name = existingDoc.getFirstName();
            session.setAttribute("user", "my test doc");
            session.setAttribute("userId", existingDoc.getId());
            theModel.addAttribute("doctor", existingDoc);
            return "doctor/doctor-detail";

        } catch (EntityNotFoundException exception) {
            theModel.addAttribute("doctor", null);
            theModel.addAttribute("exceptionMessage", exception.getMessage());
            return "error";
        }

    }

    @RequestMapping("/logout")
    public String logout() {
        // Invalidate session
        session.invalidate();

        return "index";
    }

    //    @RequestMapping("/doctorLogin2")
//    public String getDoctorByName(@RequestParam(value = "docName") String name, Model theModel){
//
//        try{
//            Doctor existingDoc = doctorService.getDoctorByName(name);
//            theModel.addAttribute("doctor", existingDoc);
//            return "doctor/doctor-detail";
//
//        }catch (EntityNotFoundException exception){
//            theModel.addAttribute("doctor", null);
//            theModel.addAttribute("exceptionMessage", exception.getMessage());
//            return "error";
//        }
//
//    }
    @RequestMapping("/doctorHome")
    public String getDoctorHome(Model theModel, HttpServletRequest request) {

        HttpSession session = request.getSession();
        var docId = (Long) session.getAttribute("userId");
        Doctor existingDoc = doctorService.getDoctorById(docId);
        theModel.addAttribute("doctor", existingDoc);
        return "doctor/doctor-detail";
    }

    @RequestMapping("/patientLogin")
    public String getPatientByEmail(@RequestParam(value = "patEmail") String patEmail, Model theModel) {

        try {
            Patient existingPat = patientService.getPatientByEmail(patEmail);
            theModel.addAttribute("patient", existingPat);
            return "patient/patient-detail";

        } catch (EntityNotFoundException exception) {
            theModel.addAttribute("patient", null);
            theModel.addAttribute("exceptionMessage", exception.getMessage());
            return "error";
        }
    }

    @GetMapping("/list-patients")
    //@RequestParam("docId") Long theDocId
    public String listPatients(Model theModel, HttpServletRequest request) {
        System.out.println("listing patients");
        HttpSession session = request.getSession();
        var id = (Long) session.getAttribute("userId");
        List<Patient> thePatients = patientService.getAllPatients();
        Doctor doctorFound = doctorService.getDoctorById(id);
        theModel.addAttribute("theDoctor", doctorFound);
        theModel.addAttribute("patients", thePatients);
        return "patient/patient-list";
    }

    @GetMapping("/patient")
    public String getPatientById(@RequestParam("patId") Long patId, Model theModel, HttpSession session) {
        Patient existingPat = patientService.getPatientById(patId);
        theModel.addAttribute("patient", existingPat);
        return "patient/patient-detail";
    }

    // Show create patient form
    @GetMapping("patient/create-patient-form")
    public String showCreatePatientForm(Model model, HttpSession session) {
        Patient patient = new Patient();
        model.addAttribute("patient", patient);
        return "patient/create-patient";
    }

    // Handle create patient form submission
    @PostMapping("patient/create-patient")
    public String createPatient(@Valid @ModelAttribute("patient") Patient patient, BindingResult result, HttpSession session) {
        patientService.savePatient(patient);
        return "redirect:/clinic/list-patients";
    }

    @GetMapping("/doctor-appointments")
    public String getDoctorAppointments(Model theModel, HttpServletRequest request) {

        HttpSession session = request.getSession();
        Long id = (Long) session.getAttribute("userId");

        try {
            List<Appointment> theAppointments = appointmentService.getAppointmentsByDocId(id.intValue());
            for (final Appointment appointment: theAppointments ){
                Doctor doctor = doctorService.getDoctorById(appointment.getDoctor().longValue());
                Patient patient = patientService.getPatientById(appointment.getPatient().longValue());
                appointment.setDoctorName(doctor.getFirstName()+ " " + doctor.getLastName());
                appointment.setPatientName(patient.getFirstName()+ " " + patient.getLastName());
            }
            theModel.addAttribute("appointments", theAppointments);
            return "appointments/list-appointments";

        } catch (EntityNotFoundException exception) {
            theModel.addAttribute("exceptionMessage", exception.getMessage());
            return "error";
        }


    }

    @GetMapping("/patient-appointments")
    public String getPatientAppointments(@RequestParam("patId") Long patId, Model theModel) {
        List<Appointment> theAppointments = appointmentService.getAppointmentsByPatId(patId.intValue());
        theModel.addAttribute("appointments", theAppointments);
        return "appointment/list-appointments";
    }

    @GetMapping("/appointment-by-doctor-phone")
    public String getAppointmentByDoctorPhone(@RequestParam("docPhone") String thePhone, Model theModel) {
        List<Appointment> theAppointments = appointmentService.getAppointmentsByDocPhone(thePhone);
        theModel.addAttribute("appointments", theAppointments);
        return "appointment/list-appointments";
    }

    @GetMapping("/appointment-by-patient-phone")
    public String getAppointmentByPatientPhone(@RequestParam("patPhone") String thePhone, Model theModel) {
        List<Appointment> theAppointments = appointmentService.getAppointmentsByPatPhone(thePhone);
        theModel.addAttribute("appointments", theAppointments);
        return "appointment/list-appointments";
    }

    @GetMapping("/appointment-by-doctor-email")
    public String getAppointmentByDoctorEmail(@RequestParam("docEmail") String theEmail, Model theModel) {
        List<Appointment> theAppointments = appointmentService.getAppointmentsByDocEmail(theEmail);
        theModel.addAttribute("appointments", theAppointments);
        return "appointment/list-appointments";
    }

    @GetMapping("/appointment-by-patient-email")
    public String getAppointmentByPatientEmail(@RequestParam("patEmail") String theEmail, Model theModel) {
        List<Appointment> theAppointments = appointmentService.getAppointmentsByPatEmail(theEmail);
        theModel.addAttribute("appointments", theAppointments);
        return "appointment/list-appointments";
    }


    @GetMapping("/appointment/create")
    public String showCreateAppointmentForm(@RequestParam("patId") Long thePatId, @RequestParam("docId") Long theDocId, Model theModel) {
        Appointment appointment = new Appointment();
        appointment.setDoctor(theDocId.intValue());
        appointment.setPatient(thePatId.intValue());
        theModel.addAttribute("appointment", appointment);
        theModel.addAttribute("patient", thePatId.intValue());
        theModel.addAttribute("doctor", theDocId.intValue());
        return "appointments/create-appointment";
    }


    @PostMapping("/appointment/save")
    public String saveAppointment(@Valid @ModelAttribute("appointment") Appointment theAppointment, BindingResult result, HttpServletRequest request) {
        if (result.hasErrors()) {
            return "error";
        }
        System.out.println("distinct");
        System.out.println(theAppointment.getPatient());
        //        theAppointment.getDoctor().setId(theAppointment.getDoctor().getId());
//        theAppointment.getPatient().setId(theAppointment.getPatient().getId());
//        doctorService.save(theAppointment.getDoctor());
//        patientService.save(theAppointment.getPatient());
        appointmentService.saveAppointment(theAppointment);
        HttpSession session = request.getSession();
        var id = (Long) session.getAttribute("userId");

        return "redirect:/clinic/doctor-appointments";
    }

    @GetMapping("/appointment/edit/{aptId}")
    public String showEditAppointmentForm(Model theModel, @PathVariable("aptId") Long aptId) {
        Appointment appointment = appointmentService.getAppointmentById(aptId);
        List<Patient> patients = patientService.getAllPatients();
        List<Doctor> doctors = doctorService.getAllDoctors();
        theModel.addAttribute("editAppointment", appointment);
        theModel.addAttribute("patients", patients);
        theModel.addAttribute("doctors", doctors);
        return "appointments/edit-appointment";
    }

    // UPDATE APPOINTMENT
    @GetMapping("/appointment/update/{aptId}")
    public String showUpdateAppointmentForm(@PathVariable("aptId") Long aptId, Model model) {
        Appointment appointment = appointmentService.getAppointmentById(aptId);
        model.addAttribute("appointment", appointment);


        return "appointments/update-appointment";
    }



    @PostMapping("/appointment/update/{aptId}")
    public String updateAppointment(@PathVariable("aptId") Long aptId, @ModelAttribute("editAppointment") Appointment theAppointment) {
        theAppointment.setId(aptId);
        appointmentService.saveAppointment(theAppointment);
        return "redirect:/clinic/list-appointments";
    }
            // DELETE APPOINTMENT
    @GetMapping("/appointment/delete/{aptId}")
    public String deleteAppointment(@PathVariable("aptId") Long aptId, HttpSession session) {
        Integer userId = ((Long) session.getAttribute("userId")).intValue();
        Appointment appointment = appointmentService.getAppointmentById(aptId);
       if (userId==appointment.getDoctor() || userId== appointment.getPatient())
       {
          appointmentService.deleteAppointment(aptId);
       }
        return "redirect:/clinic/doctor-appointments";


    }

    //delete patient routing
    @GetMapping("patient/delete/{patId}")
    public String deletePatient(@PathVariable("patId") Long patId, HttpSession session) {
        patientService.deletePatient(patId);
        return "redirect:/clinic/list-patients";

    }

    // Show update patient form
    @GetMapping("patient/update-patient/{patId}")
    public String showUpdatePatientForm(@PathVariable("patId") Long patId, Model model, HttpSession session) {
        Patient patient = patientService.getPatientById(patId); // Retrieve the existing patient from the database
        model.addAttribute("patient", patient);
        return "patient/update-patient";
    }

    // updates patient and brings doctor back to patient-list
    @PostMapping("patient/update-patient/{patId}")
    public String updatePatient(Patient patient, HttpSession session) {
        patientService.savePatient(patient);
        return "redirect:/clinic/list-patients";
    }


}

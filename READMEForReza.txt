0. Update application.properties with your proper db setting (root password etc.)
1. run sql query from resources folder
2. Use a provided doctor email to login to doctor session(
no admin that can create doctors currently available, shubh@gmail.com and sam@gmail.com are our hard coded doctors)
3. can CRUD patients as doctor, and can CRUD an appointment for patients. Doctors can manage their own appointments only.
4. log into any created patient
5. Patients can view their own booked appointment(s) only,
can delete appointments and update them but cannot create, (only doctor can create patients).

TODO:
-Many features we wanted, even many validations and use of password missing due to
excessive amounts of time spent troubleshooting various data management issues. These are short fixes
now that most issues have been troubleshooted and can be added to the project in the following days
now that data management issues have been resolved.

-Adding master admin that can CRUD doctors would also be quick to implement now.

-Unit tests: we tried a few but ran out of time/energy
in debugging other issues and could not complete them sufficiently.

-BUG: Header and footers we were trying to use caused unexpected errors with crud operations, 
did not have time to investigate fully, but seemed to conflict with something with thymeleaf.
Probably another quick fix.
-BUG: creating a patient with the same email as a previous patient is not handled
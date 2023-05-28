package model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.RepeatedTest;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import java.time.LocalDate;

/**
 * Implement and test {Programme.addStudent } that respects the considtion given
 * the assignment specification
 * NOTE: You are expected to verify that the constraints to add a new student to
 * a programme are met.
 *
 * Each test criteria must be in an independent test method .
 *
 * Initialize the test object with initialise method.
 */
@DisplayName("Testing AddStudent")
public class AddStudent {
    private Student student; //student added to the program
    private Programme program; //program applied to
    private LocalDate applicationDate; //Stores current application date
    private Football football;

    //Initialisation of program, student, football and applicatoinDate
    @BeforeEach
    void initialise() {
        football = new Football();
        applicationDate = LocalDate.now();//Date of application
        program = new Programme("BSC Computer Science", 3056);
        student = new Student("Rashik Raj", 3931830);
    }


    //Returns true if student added successfuly
    @Test
    void true_studentAdded() throws IllegalStudentEnrollException{
        assertEquals(true, program.addStudent(student, applicationDate, football),
                "New Student added to the programme");
    }



    /*--------EDGE CASES BELOW THIS----------*/

    // Throws an exception if Student is already enrolled
    @Test
    void throwsException_ifStudentAlreadyEnrolled() throws IllegalStudentEnrollException {
        program.addStudent(student, applicationDate, football); //adding the same student before trying to add him again
        assertThrows(IllegalStudentEnrollException.class, () -> program.addStudent(student, applicationDate, football), 
        "Throws excpetion (IllegalStudentEnrollException) when student is already enrolled in the program");
    }

    // Returns false if there are already more than 250 students in the programme
    @Test
    void false_ifProgrammeFull() throws IllegalStudentEnrollException{

        //adding more than 250 students to the programme before trying to add student
        for(int i = 0; i<250; i++){
            student = new Student("newRandomStudent", 121);
            program.addStudent(student, applicationDate, football);
        }

        student = new Student("Kawish Raj", 3931853); //Creating a new student to add after 250 students added to the program
        assertEquals(false, program.addStudent(student, applicationDate, football));
    }   

    // Returns false if the start date of the program has passed has already passed
    @Test
    void flase_ifStartDatePassed() throws IllegalStudentEnrollException{
        program.setStartDate(LocalDate.of(2022, 7, 25)); //Updating startDate to have passed.
        assertEquals(false, program.addStudent(student, applicationDate, football),
        "returns flase if start date for program already passed");
    }
}
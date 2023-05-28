package model;

import java.util.ArrayList;
import java.time.LocalDate;
import java.util.List;

/**
 * Programmes offered by a university
 */
public class Programme {
    /**
     * Name and id of the programme
     */
    private String name;
    private int pID;

    /**
     * Start date of the programme
     */
    private LocalDate startDate;

    /**
     * End date of the programme
     */
    private LocalDate dueDate;

    /**
     * Estimated duration of the course in months
     */
    private int estimatedDuration;

    /**
     * Students allocated to the programme
     */
    private List<Student> enrolled = new ArrayList<Student>();

    /**
     * Constructor for the programme class
     * @param name: stores the name of the program
     * @param pID: stores the program ID
     * @return
     */
    public Programme(String name, int pID){
        this.name = name;
        this.pID = pID;
        this.startDate = LocalDate.of(2024, 8, 17);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getID() {
        return pID;
    }

    public void setID(int ID) {
        this.pID = ID;
    }


    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public int getEstimatedDuration() {
        return estimatedDuration;
    }

    public void setEstimatedDuration(int estimatedDuration) {
        this.estimatedDuration = estimatedDuration;
    }

    public List<Student> getEnrollments() {
        return enrolled;
    }

    
    public boolean removeEnrolledStudent(Student student) {
    
    	return false;
    	   
    }


    /**
     * Add a new student to the programme
     * @param student: to enroll  to student in a programme 
     * @param date: date of applicaton for enrollment by the student
     * @param football: stores the football match object
     * @return true if the student is successfully enrolled, false otherwise
     */

    public boolean addStudent(Student student, LocalDate date, Football football) throws IllegalStudentEnrollException{
        boolean isAdded = true; //To store the return value
        int comparison = this.getStartDate().compareTo(date); //Comparing Application Date and Start Date of the programme

        //Trowing excpetion if student is already enrolled
        if(enrolled.contains(student)){
            throw new IllegalStudentEnrollException(student.getName() + " is already enrolled in the program.");
        }
        //Checking to see if start date of the programme has passed or not
        if(comparison < 0) {
            isAdded = false;
        }
        //Checking if the number of students in the program are less than 250 or not
        if(enrolled.size() >= 250){
            isAdded = false;
        } 
        //enrolling the student in the program
        if(isAdded){
            enrolled.add(student);
            football.addAvailStudent(student);
        }
        
    	return isAdded;
    }




}

import java.util.ArrayList;
import java.util.List;

class Course {
    private String code;
    private String title;
    private String description;
    private int capacity;
    private List<String> schedule;
    private List<Student> enrolledStudents;

    public Course(String code, String title, String description, int capacity, List<String> schedule) {
        this.code = code;
        this.title = title;
        this.description = description;
        this.capacity = capacity;
        this.schedule = schedule;
        this.enrolledStudents = new ArrayList<>();
    }

    public String getCode() {
        return code;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getCapacity() {
        return capacity;
    }

    public List<String> getSchedule() {
        return schedule;
    }

    public List<Student> getEnrolledStudents() {
        return enrolledStudents;
    }

    public boolean addStudent(Student student) {
        if (enrolledStudents.size() < capacity) {
            enrolledStudents.add(student);
            return true;
        } else {
            return false; // Course is full
        }
    }

    public void removeStudent(Student student) {
        enrolledStudents.remove(student);
    }

    @Override
    public String toString() {
        return "Course Code: " + code + "\nTitle: " + title + "\nDescription: " + description + "\nCapacity: " + capacity
                + "\nSchedule: " + schedule;
    }
}

class Student {
    private String studentID;
    private String name;
    private List<Course> registeredCourses;

    public Student(String studentID, String name) {
        this.studentID = studentID;
        this.name = name;
        this.registeredCourses = new ArrayList<>();
    }

    public String getStudentID() {
        return studentID;
    }

    public String getName() {
        return name;
    }

    public List<Course> getRegisteredCourses() {
        return registeredCourses;
    }

    public void registerCourse(Course course) {
        if (course.addStudent(this)) {
            registeredCourses.add(course);
        } else {
            System.out.println("Course " + course.getCode() + " is full. Registration failed.");
        }
    }

    public void dropCourse(Course course) {
        course.removeStudent(this);
        registeredCourses.remove(course);
    }

    @Override
    public String toString() {
        return "Student ID: " + studentID + "\nName: " + name + "\nRegistered Courses: " + registeredCourses;
    }
}

public class StudentCourseRegistrationSystem {
    public static void main(String[] args) {
        // Create some courses
        Course course1 = new Course("CS101", "Introduction to Programming", "Learn programming basics", 30,
                List.of("Mon, Wed 9:00 AM - 10:30 AM"));
        Course course2 = new Course("MATH201", "Calculus I", "First-year calculus course", 40,
                List.of("Tue, Thu 11:00 AM - 12:30 PM"));

        // Create some students
        Student student1 = new Student("1001", "Alice");
        Student student2 = new Student("1002", "Bob");

        // Register students for courses
        student1.registerCourse(course1);
        student1.registerCourse(course2);
        student2.registerCourse(course1);

        // Display course and student information
        System.out.println("Courses:");
        System.out.println(course1);
        System.out.println();
        System.out.println(course2);
        System.out.println();

        System.out.println("Students:");
        System.out.println(student1);
        System.out.println();
        System.out.println(student2);
        System.out.println();

        // Drop a course
        student1.dropCourse(course1);
        System.out.println("After dropping a course:");
        System.out.println(student1);
        System.out.println(course1);
    }
}

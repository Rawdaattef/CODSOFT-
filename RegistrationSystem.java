import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Course {
    private String code;
    private String title;
    private String description;
    private int capacity;
    private int enrolled;

    public Course(String code, String title, String description, int capacity) {
        this.code = code;
        this.title = title;
        this.description = description;
        this.capacity = capacity;
        this.enrolled = 0;
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

    public int getEnrolled() {
        return enrolled;
    }

    public void enrollStudent() {
        if (enrolled < capacity) {
            enrolled++;
        } else {
            System.out.println("Course is already full. Cannot enroll more students.");
        }
    }

    public void removeStudent() {
        if (enrolled > 0) {
            enrolled--;
        } else {
            System.out.println("No students enrolled in this course.");
        }
    }

    public String toString() {
        return "Course Code: " + code +
                "\nTitle: " + title +
                "\nDescription: " + description +
                "\nCapacity: " + capacity +
                "\nEnrolled: " + enrolled;
    }
}

class Student {
    private int studentId;
    private String name;
    private List<Course> registeredCourses;

    public Student(int studentId, String name) {
        this.studentId = studentId;
        this.name = name;
        this.registeredCourses = new ArrayList<>();
    }

    public int getStudentId() {
        return studentId;
    }

    public String getName() {
        return name;
    }

    public List<Course> getRegisteredCourses() {
        return registeredCourses;
    }

    public void registerForCourse(Course course) {
        if (!registeredCourses.contains(course)) {
            registeredCourses.add(course);
            course.enrollStudent();
            System.out.println(name + " has successfully registered for " + course.getTitle());
        } else {
            System.out.println(name + " is already registered for " + course.getTitle());
        }
    }

    public void dropCourse(Course course) {
        if (registeredCourses.contains(course)) {
            registeredCourses.remove(course);
            course.removeStudent();
            System.out.println(name + " has dropped " + course.getTitle());
        } else {
            System.out.println(name + " is not registered for " + course.getTitle());
        }
    }

    public String toString() {
        return "Student ID: " + studentId +
                "\nName: " + name +
                "\nRegistered Courses: " + registeredCourses.size();
    }
}

public class RegistrationSystem {
    public static void main(String[] args) {
        List<Course> courses = new ArrayList<>();
        List<Student> students = new ArrayList<>();

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nCourse Registration System Menu:");
            System.out.println("1. Add Course");
            System.out.println("2. List Available Courses");
            System.out.println("3. Register Student");
            System.out.println("4. Drop Course");
            System.out.println("5. List Students");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    System.out.print("Enter course code: ");
                    String code = scanner.nextLine();
                    System.out.print("Enter course title: ");
                    String title = scanner.nextLine();
                    System.out.print("Enter course description: ");
                    String description = scanner.nextLine();
                    System.out.print("Enter course capacity: ");
                    int capacity = scanner.nextInt();
                    scanner.nextLine(); // Consume the newline character

                    Course course = new Course(code, title, description, capacity);
                    courses.add(course);
                    break;
                case 2:
                    System.out.println("\nAvailable Courses:");
                    for (Course c : courses) {
                        System.out.println("\n" + c.toString());
                    }
                    break;
                case 3:
                    System.out.print("Enter student ID: ");
                    int studentId = scanner.nextInt();
                    scanner.nextLine(); // Consume the newline character
                    System.out.print("Enter student name: ");
                    String studentName = scanner.nextLine();

                    Student student = new Student(studentId, studentName);
                    students.add(student);

                    System.out.println("\nAvailable Courses:");
                    for (int i = 0; i < courses.size(); i++) {
                        Course availableCourse = courses.get(i);
                        System.out.println((i + 1) + ". " + availableCourse.getTitle());
                    }

                    System.out.print("Enter the course number to register: ");
                    int courseNumber = scanner.nextInt();
                    scanner.nextLine(); // Consume the newline character

                    if (courseNumber >= 1 && courseNumber <= courses.size()) {
                        Course selectedCourse = courses.get(courseNumber - 1);
                        student.registerForCourse(selectedCourse);
                    } else {
                        System.out.println("Invalid course number.");
                    }
                    break;
                case 4:
                    System.out.print("Enter student ID to drop course: ");
                    int studentIdToDrop = scanner.nextInt();
                    scanner.nextLine(); // Consume the newline character

                    System.out.print("Enter course code to drop: ");
                    String courseCodeToDrop = scanner.nextLine();

                    Student studentToDrop = null;
                    for (Student s : students) {
                        if (s.getStudentId() == studentIdToDrop) {
                            studentToDrop = s;
                            break;
                        }
                    }

                    if (studentToDrop != null) {
                        Course courseToDrop = null;
                        for (Course c : studentToDrop.getRegisteredCourses()) {
                            if (c.getCode().equals(courseCodeToDrop)) {
                                courseToDrop = c;
                                break;
                            }
                        }

                        if (courseToDrop != null) {
                            studentToDrop.dropCourse(courseToDrop);
                        } else {
                            System.out.println("Student is not registered for the specified course.");
                        }
                    } else {
                        System.out.println("Student not found.");
                    }
                    break;
                case 5:
                    System.out.println("\nRegistered Students:");
                    for (Student s : students) {
                        System.out.println("\n" + s.toString());
                    }
                    break;
                case 6:
                    System.out.println("Exiting the program.");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
            }
        }
    }
}

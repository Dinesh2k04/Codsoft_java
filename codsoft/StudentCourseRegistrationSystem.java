import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
class Course 
{
    String[] data;
    Course(String courseCode, String title, int capacity) 
    {
        data = new String[4];
        data[0] = courseCode;
        data[1] = title;
        data[2] = Integer.toString(capacity);
        data[3] = "0";
    }
    boolean enrollStudent() 
    {
        int capacity = Integer.parseInt(data[2]);
        int enrolled = Integer.parseInt(data[3]);
        if (enrolled < capacity) 
	{
            data[3] = Integer.toString(enrolled + 1);
            return true;
        }
        return false;
    }
    boolean dropStudent() {
        int enrolled = Integer.parseInt(data[3]);
        if (enrolled > 0) {
            data[3] = Integer.toString(enrolled - 1);
            return true;
        }
        return false;
    }
    @Override
    public String toString() {
        return data[0] + " - " + data[1] + " (Capacity: " + data[2] + ", Enrolled: " + data[3] + ")";
    }
}

class Student {
    String[] data; 
    List<Course> registeredCourses;
    Student(String studentID, String name) 
    {
        data = new String[2];
        data[0] = studentID;
        data[1] = name;
        registeredCourses = new ArrayList<>();
    }

    boolean registerCourse(Course course) 
    {
        if (!registeredCourses.contains(course)) 
	{
            registeredCourses.add(course);
            return course.enrollStudent();
        }
        return false;
    }

    boolean dropCourse(Course course) 
    {
        if (registeredCourses.contains(course)) 
	{
            registeredCourses.remove(course);
            return course.dropStudent();
        }
        return false;
    }
    @Override
    public String toString() 
    {
        StringBuilder sb = new StringBuilder();
        sb.append(data[0]).append(" - ").append(data[1]).append("\nRegistered Courses:\n");
        for (Course course : registeredCourses) 
	{
            sb.append(course.data[0]).append(" - ").append(course.data[1]).append("\n");
        }
        return sb.toString();
    }
}
public class StudentCourseRegistrationSystem 
{
    public static void main(String[] args) 
    {
        List<Course> courses = new ArrayList<>();
        List<Student> students = new ArrayList<>();

        courses.add(new Course("CSE101", "Introduction to JAVA programming", 2));
        courses.add(new Course("MAT102", "Enginnering Mathematics-2", 3));

        students.add(new Student("S123", "Dinesh"));
        students.add(new Student("S124", "Virat"));

        Scanner sc = new Scanner(System.in);
        int option;

        do 
	{
            System.out.println("\nMenu:");
            System.out.println("1. Display Courses");
            System.out.println("2. Register for a Course");
            System.out.println("3. Drop a Course");
            System.out.println("4. Display Registered Courses");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");
            option = sc.nextInt();
            sc.nextLine(); 
            switch (option) 
	    {
                case 1:
                    for (Course course : courses) 
		    {
                        System.out.println(course);
                    }
                    break;
                case 2:
                    System.out.print("Enter Student ID: ");
                    String studentID = sc.nextLine().trim();
                    System.out.print("Enter Course Code: ");
                    String courseCode = sc.nextLine().trim();
                    Student student = findStudent(students, studentID);
                    Course course = findCourse(courses, courseCode);
                    if (student != null && course != null) 
		    {
                        if (student.registerCourse(course)) 
			{
                            System.out.println("Registered successfully");
                        } 
			else 
			{
                            System.out.println("Registration failed");
                        }
                    } 
		    else 
		    {
                        System.out.println("Wrong Student ID or Course code");
                    }
                    break;
                case 3:
                    System.out.print("Enter Student ID: ");
                    studentID = sc.nextLine().trim();
                    System.out.print("Enter Course Code: ");
                    courseCode = sc.nextLine().trim();
                    student = findStudent(students, studentID);
                    course = findCourse(courses, courseCode);
                    if (student != null && course != null) 
		    {
                        if (student.dropCourse(course)) 
			{
                            System.out.println("Dropped successfully");
                        } 
			else 
			{
                            System.out.println("Drop failed");
                        }
                    } 
		    else 
		    {
                        System.out.println("Wrong Student ID or Course code");
                    }
                    break;
                case 4:
                    System.out.print("Enter Student ID: ");
                    studentID = sc.nextLine().trim();
                    student = findStudent(students, studentID);
                    if (student != null) 
		    {
                        System.out.println(student);
                    } 
		    else 
		    {
                        System.out.println("Invalid student ID.");
                    }
                    break;
                case 5:
                    System.out.println("Exit");
                    break;
                default:
                    System.out.println("Enter the valid option");
            }
        } 
	while (option != 5);
        sc.close();
    }

    private static Student findStudent(List<Student> students, String studentID) 
    {
        for (Student student : students) 
	{
            if (student.data[0].equalsIgnoreCase(studentID)) 
	    {
                return student;
            }
        }
        return null;
    }

    private static Course findCourse(List<Course> courses, String courseCode) 
    {
        for (Course course : courses) 
	{
            if (course.data[0].equalsIgnoreCase(courseCode)) 
	    {
                return course;
            }
        }
        return null;
    }
}

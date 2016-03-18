package solved.level29.lesson15.big01.human;

import java.util.ArrayList;
import java.util.List;

public class University {

    protected int age;
    protected String name;
    private List<Student> students = new ArrayList<>();

    public void setStudents(List<Student> students)
    {
        this.students = students;
    }

    public int getAge()
    {
        return age;
    }

    public void setAge(int age)
    {
        this.age = age;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public List<Student> getStudents()
    {
        return students;
    }

    public University(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public Student getStudentWithAverageGrade(double averageGrade) {
        Student resultStudent = null;

        for (Student student : students)
            if (student.getAverageGrade() == averageGrade)
                resultStudent = student;

        return resultStudent;
    }

    public Student getStudentWithMaxAverageGrade() {
        double maxAverageGrade = 0;
        Student resultStudent = null;

        for (Student student : students)
            if (student.getAverageGrade() > maxAverageGrade)
            {
                maxAverageGrade = student.getAverageGrade();
                resultStudent = student;
            }

        return resultStudent;
    }

    public Student getStudentWithMinAverageGrade() {
        Student resultStudent = null;
        double minAverageGrade = getStudentWithMaxAverageGrade().getAverageGrade();

        for (Student student : students)
            if (student.getAverageGrade() < minAverageGrade)
            {
                resultStudent = student;
                minAverageGrade = student.getAverageGrade();
            }

        return resultStudent;
    }

    public void expel(Student student)
    {
        students.remove(student);
    }
}

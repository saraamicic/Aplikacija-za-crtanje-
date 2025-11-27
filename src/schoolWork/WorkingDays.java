package schoolWork;

public class WorkingDays {
	public static void main(String[] args)
	{
		
		Student student1 = new Student("Petar", 21, 9.90);
		
		Teacher teacher1 = new Teacher("Jovana" , 26, 120000, "math");
		
		
		System.out.println(student1);
		System.out.println(teacher1);
		
		System.out.println(teacher1.assignSubject());
		
		student1.setClassTeacher(teacher1);
		
		student1.getClassTeacher().setSubject("history");
		
		System.out.println(student1.getClassTeacher().assignSubject());
		System.out.println(student1.evaluateTheStudent(5));
		System.out.println(student1.evaluateTheStudent(3, "grade needs to be improved"));
		
	}

}

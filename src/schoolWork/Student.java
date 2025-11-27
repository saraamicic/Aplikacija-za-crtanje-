package schoolWork;

public class Student extends SchoolMember {
 
	private double avgGrade;
	private Teacher classTeacher;
	
	
	//konstruktori
	
	public Student ()
	{ 
		
	}
	
	public Student (String name, int age, double avgGrade)
	{
		super(name, age);
		this.avgGrade = avgGrade;
		
	}
	
	//metode pristupa
	
	public double getAvgGrade()
	{
		return avgGrade;
	}
	public void setAvgGrade(double avgGrade)
	{
		this.avgGrade = avgGrade;
	}
	public Teacher getClassTeacher()
	{
		return classTeacher;
		
	}
	public void setClassTeacher(Teacher classTeacher)
	{
		this.classTeacher = classTeacher;
	}
	
	
	public String toString()
	{
		return super.toString() + ", grade:" + avgGrade;
	}
	
	
	public String evaluateTheStudent(int grade) 
	{
		return classTeacher.assignSubject() + " and student " + this.getName() + " get " + grade;
	}
	
	public String evaluateTheStudent(int grade, String note)
	{
		return evaluateTheStudent(grade) + " --> note:" + note;
	}

	//ovde imamo overloading odnosno preklapaju nam se nazivi metoda, zato one moraju imati razlicit potpis (razlikuju se po broju ili tipovima parametara)
	
}


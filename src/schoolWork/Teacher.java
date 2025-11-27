package schoolWork;

public class Teacher extends SchoolMember {

	private int salary;
	private String subject;
	
	//konstruktori 
	
	public Teacher()
	{
		
	}
	
	public Teacher(String name, int age, int salary)
	{
		super(name, age);
		this.salary = salary;
	}
	
	public Teacher(String name, int age, int salary, String subject)
	{
		this(name, age, salary);
		this.subject = subject;
	}
	
	//metode pristupa
	
	public int getSalary()
	{
		return salary;
	}
	public void setSalary( int salary)
	{
		this.salary = salary;
	}
	
	public String getSubject()
	{
		return subject;
	}
	
	public void setSubject(String subject)
	{
		this.subject = subject;
	}
	
	public String toString()
	{
		return super.toString()+ ", salary:" + salary;
	}
	
	public String assignSubject()
	{
		return "Subject " + subject  +" was assigned to the teacher " + this.getName();
	}
	
	
}

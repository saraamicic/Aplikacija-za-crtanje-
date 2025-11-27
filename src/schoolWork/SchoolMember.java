package schoolWork;

public class SchoolMember {

	private String name;
	private int age;
	
	//konstruktori
	
	public SchoolMember ()
	{
		
	}
	public SchoolMember (String name, int age)
	{
		this.name = name;
		this.age = age;
	}
	
	
	//get i set metode/metode pristupa
	
	public String getName()
	{
		return name;
		
	}
	public void setName(String name)
	{
		this.name = name;
	}
	
	
	public int getAge()
	{
		return age;
	}
	public void setAge(int age)
	{
		this.age = age;
	}
	
	
	public String toString()
	{
		return "Person:" + name + ", age:" + age;
	}
	
	
	
	
}

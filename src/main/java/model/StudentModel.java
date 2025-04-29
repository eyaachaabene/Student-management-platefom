package model;

public class StudentModel extends User{

    private String name;
    private String dateOfBirth;
    private String address;
    private String level;

    // Constructor
    public StudentModel( int id,String email, String password,String name, String dateOfBirth, String address, String level) {
    	super(id, email, password, address, "Student");
        
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.address = address;
        this.level = level;
    }

   
  


	public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }
}

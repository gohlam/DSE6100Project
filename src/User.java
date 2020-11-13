import java.util.Date;

public class User {
    protected String email;
    protected String password;
    protected String password2;
    protected String firstName;
    protected String lastName;
    protected Date birthday;
    protected String gender;
 
    public User() {
    }
 
    public User(String email) {
        this.email = email;
    }
 
    public User(String email, String firstName, String lastName, String password, String password2, Date birthday, String gender) {
        this(firstName, lastName, password, birthday, gender);
        this.email = email;
        this.password2 = password2;
    }
    
    public User(String email, String firstName, String lastName, String password, Date birthday, String gender) {
        this(firstName, lastName, password, birthday, gender);
        this.email = email;
    }
     
    public User(String firstName, String lastName, String password, Date birthday, String gender) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.birthday = birthday;
        this.gender = gender;
    }
 
    public String getEmail() {
        return email;
    }
 
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getFirstName() {
        return firstName;
    }
 
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    
    public String getLastName() {
        return lastName;
    }
 
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    
    public String getPassword() {
        return password;
    }
 
    public void setPassword(String password) {
        this.password = password;
    }
    
    public String getBirthday() {
        return birthday.toString();
    }
 
    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }
    
    public String getGender() {
        return gender;
    }
 
    public void setGender(String gender) {
        this.gender = gender;
    }
    
    public String getPassword2() {
    	return password2;
    }
    
    public void setPassword2(String password2) {
    	this.password2 = password2;
    }
}
    
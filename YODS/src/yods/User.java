package yods;

/**
 *
 * @author Mehmed Emirhan AMAÇ
 *
a. Dizide Nesne saklama-ekleme- silme +
b. Constructor (Yapıcı) metot kullanımı (Overloading yapılmalı) +
c. Encapsulation (Sarmalama) ilkeleri uygulanmalıdır. (Private/Public ve Get/Set Metotları)+
d. Array List içerisinde nesne saklama-ekleme- silme +
e. Array ve Array list üzerinde toplam ve ortalama benzeri işlemler.+
f. Kalıtım ile alt sınıf türetme.+
g. Polymorphism (Çok Biçimlilik)+
h. Over loading +
i. Overriding+
 */

class Admin extends User{

    public Admin() {
    }

    public Admin(String name, String no, String password) {
        super(name, no, password);
    }
    
    
    public String getPassword(User u) {//user getPassword metodunun ustune override
        return u.password;
        
    }
    public void setPassword(User u,String Password) {//user setPassword metodunun ustune override
        u.password=Password;
    }
}
class Advisor extends User{
    String language;
    public Advisor(){
    
    }

    public Advisor(String language) {
        this.language = language;
    }

    public Advisor(String language, String name, String no, String password) {
        super(name, no, password);
        this.language = language;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }
    
    

}
class Student extends User{
    String language;

    public Student() {
        
    }

    public Student(String language,String name, String no, String password) {
        super(name, no, password);
        this.language=language;
        
        
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }
    
}
public class User {
    private String name;
    private String no;
    protected String password;

    public User() {
        
    }
    
    public User(String name, String no, String password) {
        this.name = name;
        this.no = no;
        this.password=password;
    }
           
   

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    
    

    
   
    
}

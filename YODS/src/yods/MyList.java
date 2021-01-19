package yods;

import java.util.ArrayList;

/**
 *
 * @author Mehmed Emirhan AMAÇ
 */
public class MyList {
    ArrayList<User> us=new ArrayList();
    ArrayList<Student> std=new ArrayList();
    ArrayList<Advisor> adv=new ArrayList();

    public MyList() {
    }
    
    public void add_obj(User u){//overloading
        us.add(u);
    }
    public void add_obj(Student s){
        std.add(s);
        us.add(s);
    }
    public void add_obj(Advisor a){
       adv.add(a);
       us.add(a);
    }
    public void remove_obj(User u){
        us.remove(u);
    }
    public void remove_obj(Student s){
        std.remove(s);
        us.remove(s);
    }
    public void remove_obj(Advisor a){
       adv.remove(a);
       us.remove(a);
    }
    public void islemler(){
        
        System.out.println("Toplam "+us.size()+" adet kullanici var.");
        System.out.println("Kullanicilarin %"+(100*std.size())/us.size()+ " ogrenci.");
        System.out.println("Kullanicilarin %"+(100*adv.size())/us.size()+ " danisman.");
    }
    public void Print(String which){
        if(which=="Users"){
            System.out.println("Kullanicilar:");
        for (int i = 0; i < us.size(); i++) {
            System.out.println(us.get(i).getName()+" "+us.get(i).getNo()+" ");
            
        }
        }
        else if(which=="Students"){
            System.out.println("Ogrenciler:");
        for (int i = 0; i < std.size(); i++) {
            System.out.println(std.get(i).getName()+" "+std.get(i).getNo()+" "+std.get(i).getLanguage());
            
        }
        }
        else if(which=="Advisors"){
            System.out.println("Danismanlar:");
        for (int i = 0; i < adv.size(); i++) {
            System.out.println(adv.get(i).getName()+" "+adv.get(i).getNo()+" "+adv.get(i).getLanguage());
            
        }
        }
        else
            System.out.println("Yanlis parametre girdiniz.'Users','Students' veya 'Advisors' yazmayi deneyin.");
        
    }
    
}

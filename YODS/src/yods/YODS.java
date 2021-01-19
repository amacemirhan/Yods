package yods;

/**
 *
 * @author Mehmed Emirhan AMAÇ
 * Yabanci Ogrenci Denetim Sistemi
 *
 */
public class YODS {

    /**
     * @param args the command line arguments
     */
    
    public static void main(String[] args) {
        MyList lis;
        lis = new MyList();
        Student ogr=new Student("English","Emirhan Amac","900","12345");
        Admin admin=new Admin("Ahmet","Adali","123456");
        Advisor ad=new Advisor("German","Mehmet Baltekin","1000","sifredeneme");
        Advisor ad1=new Advisor("Polish","Salih Yazici","1001","salih123");
        Advisor ad2=new Advisor("Chinese","Yamatoso Gerwan","1002","chingo");
        //Polymorphishm
        User ogr2=new Student("Turkish","Abdullah Cayci","901","abcy");
        
        lis.add_obj(ogr);
        lis.add_obj(ogr2);
        lis.add_obj(ad);
        lis.add_obj(ad1);
        lis.add_obj(ad2);
        System.out.println("Admin tarafindan degistirilmeden once danismanin sifesi: "+admin.getPassword(ad));
        admin.setPassword(ad, "sifredegisti");//
        System.out.println("Admin tarafindan degistirildikten sonra danismanin sifesi: "+admin.getPassword(ad));
        
        lis.Print("Students");
        lis.Print("Advisors");
        lis.Print("Users");
        lis.islemler();
        
        User [] dizi=new User[6];//dizide nesne saklama
        dizi[0]=admin;
        System.out.println(dizi[0].getName());
        dizi[0]=null;
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yods;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Mehmed Emirhan AMAÇ
 */
public class DBControl {
    private Connection conn;
    String url="jdbc:derby://localhost:1527/yodsdb";
    String user="abc";
    String pass="123";
    public Connection baglan() {
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            System.out.println("Baglanti basarili");
            try {
                conn=DriverManager.getConnection(url,user,pass);
            } catch (SQLException ex) {
                Logger.getLogger(DBControl.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (ClassNotFoundException ex) {
            System.out.println("Baglantida sorun var");
        }
        return conn;
    }
    
    public void kullaniciList(){
        if(conn==null){
            System.out.println("Baglanti yok.Baglaniliyor..");
            baglan();
            
        }
        try {
            Statement stmt=conn.createStatement();
            ResultSet rs=stmt.executeQuery("select * from users");
            System.out.println("Ad \t sifre \t kullanici tipi");
            while(rs.next()){
                System.out.println(""+rs.getString(1)+ "\t"+rs.getString(2)+
                        "\t"+rs.getString(3));
            }
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    public boolean kullaniciKontrol(String kullaniciAdi,String sifre,String tip){
        if(conn==null){
            System.out.println("Baglanti yok.Baglaniliyor..");
            baglan();
            
        }
        try {
            Statement stmt=conn.createStatement();
            ResultSet rs=stmt.executeQuery("select password from users where name='"+kullaniciAdi+"' and type='"+tip+"'");
            rs.next();
            if(sifre.equals(rs.getString(1))){
            return true;}
            
        } catch (SQLException ex) {
        }
        return false;
    }
    public boolean kayitEkle(String kullaniciAdi,String sifre,String type){
    if(conn==null){
            System.out.println("Baglanti yok.Baglaniliyor..");
            baglan();
            
        }
    PreparedStatement stmt=null;
    if(kullaniciKontrol(kullaniciAdi,sifre,type)==true)
        return false;
        try {
            String sql="INSERT INTO users(NAME,PASSWORD,TYPE,INSIDE) VALUES (?, ?, ?,?)";

            stmt=(PreparedStatement) conn.prepareStatement(sql);
            if(type==null)
                return false;
            stmt.setString(1, kullaniciAdi);
            stmt.setString(2, sifre);
            stmt.setString(3, type);
            stmt.setBoolean(4, false);
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            System.out.println(ex.toString());
        }
        return false;
    }
    public void Giris(String name,String pass,String type){
        if(conn==null){
            System.out.println("Baglanti yok.Baglaniliyor..");
            baglan();
            
        }
        try {
            Statement stmt=conn.createStatement();
            String sql="UPDATE USERS SET INSIDE=true WHERE NAME='"+name+"' and PASSWORD='"+pass+"' and TYPE='"+type+"'";
            stmt.execute(sql);
            
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    
    }
    public void Cikis(){
        if(conn==null){
            System.out.println("Baglanti yok.Baglaniliyor..");
            baglan();
            
        }
        try {
            Statement stmt=conn.createStatement();
            String sql="UPDATE USERS SET INSIDE=false";
            stmt.execute(sql);
            
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    
    }
    public String WhoIsInside(){
    if(conn==null){
            System.out.println("Baglanti yok.Baglaniliyor..");
            baglan();
            
        }
        try {
            Statement stmt=conn.createStatement();
            
            ResultSet rs=stmt.executeQuery("select NAME from USERS WHERE INSIDE=true");
            rs.next();
            return rs.getString(1);
        } catch (SQLException ex) {
            
        }
        return null;
    }
    public void IstekYolla(String ogrenciIsim,String ogretmenIsim){
        if(conn==null){
            System.out.println("Baglanti yok.Baglaniliyor..");
            baglan();}
        PreparedStatement stmt=null;
            try {
            
            String sql="INSERT INTO contacts(OGRT,OGRC,ISOK) VALUES (?, ?, ?)";
            stmt=(PreparedStatement) conn.prepareStatement(sql);
            
            stmt.setString(1, ogretmenIsim);
            stmt.setString(2, ogrenciIsim);
            stmt.setInt(3, 0);
            stmt.execute();
            
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
    public void IstekKabul(String ogretmenIsim,String ogrenciIsim){
        if(conn==null){
            System.out.println("Baglanti yok.Baglaniliyor..");
            baglan();}
        
            try {
            Statement stmt=conn.createStatement();
            String sql="UPDATE CONTACTS SET ISOK=1 WHERE OGRT='"+ogretmenIsim+"' and OGRC='"+ogrenciIsim+"'";
            stmt.execute(sql);
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
    public String MevcutDanisman(String OgrName){
        if(conn==null){
            System.out.println("Baglanti yok.Baglaniliyor..");
            baglan();}
        try {
            Statement stmt=conn.createStatement();
            String sql="select OGRT from CONTACTS WHERE ISOK=1 and OGRC='"+OgrName+"'";
            ResultSet rs=stmt.executeQuery(sql);
            rs.next();
            return rs.getString(1);
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    return null;}
    public ArrayList Ogretmenler(){
    ArrayList ar=new ArrayList();
    if(conn==null){
            System.out.println("Baglanti yok.Baglaniliyor..");
            baglan();}
    try {
            Statement stmt=conn.createStatement();
            String sql="select NAME from USERS WHERE TYPE='ogt'";
            ResultSet rs=stmt.executeQuery(sql);
            while(rs.next()==true){
            ar.add(rs.getString(1));
            }  
            
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    return ar;}
    public ArrayList Ogrenciler(){
    ArrayList ar=new ArrayList();
    if(conn==null){
            System.out.println("Baglanti yok.Baglaniliyor..");
            baglan();}
    try {
            Statement stmt=conn.createStatement();
            String sql="select NAME from USERS WHERE TYPE='ogr'";
            ResultSet rs=stmt.executeQuery(sql);
            while(rs.next()==true){
            ar.add(rs.getString(1));
            }  
            
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    return ar;}
    public ArrayList Istekler(String ogretmenIsim){
    ArrayList ar=new ArrayList();
    if(conn==null){
            System.out.println("Baglanti yok.Baglaniliyor..");
            baglan();}
     try {
            Statement stmt=conn.createStatement();
            String sql="select OGRC from CONTACTS WHERE OGRT='"+ogretmenIsim+"' and ISOK=0";
            ResultSet rs=stmt.executeQuery(sql);
            while(rs.next()==true){
            ar.add(rs.getString(1));
            }  
            
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    return ar;
    }
    public ArrayList MevcutOgrenciler(String ogretmenIsim){
    ArrayList ar=new ArrayList();
    if(conn==null){
            System.out.println("Baglanti yok.Baglaniliyor..");
            baglan();}
    try {
            Statement stmt=conn.createStatement();
            String sql="select OGRC from CONTACTS WHERE OGRT='"+ogretmenIsim+"' and ISOK=1";
            ResultSet rs=stmt.executeQuery(sql);
            while(rs.next()==true){
            ar.add(rs.getString(1));
            }  
            
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    return ar;}
    public static void main(String[] args) {
        DBControl db = new DBControl();
        ArrayList ar1=new ArrayList();
        ar1=db.Istekler("tugkan");
        
        for (int i = 0; i < ar1.size(); i++) {
           if(ar1.get(i)==null){
           ar1.remove(i);
           i--;
           }
        }
        for (int i = 0; i < ar1.size(); i++) {
            System.out.println(ar1.get(i));
        }
        
    }
}

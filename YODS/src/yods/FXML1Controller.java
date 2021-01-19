/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yods;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Mehmed Emirhan AMAÇ
 */
public class FXML1Controller implements Initializable {
    DBControl db=new DBControl();
    String name=db.WhoIsInside();
    ArrayList ar1 = new ArrayList();
    
    @FXML
    private Button KayitOgr;

    @FXML
    private CheckBox ogr1;

    @FXML
    private Label Message;

    @FXML
    private CheckBox ogr6;

    @FXML
    private CheckBox ogr7;

    @FXML
    private CheckBox ogr8;

    @FXML
    private CheckBox ogr9;

    @FXML
    private Button ReturnButon;

    @FXML
    private CheckBox ogr2;

    @FXML
    private Label MevcutDan;

    @FXML
    private CheckBox ogr3;

    @FXML
    private CheckBox ogr4;

    @FXML
    private CheckBox ogr5;

    @FXML
    private Label OgrIsim;

    @FXML
    private CheckBox ogr10;

    @FXML
    private CheckBox ogr11;

    @FXML
    private CheckBox ogr12;
    
    

    
    
    
    @FXML
    void GirisDon(ActionEvent event) throws IOException {
        db.Cikis();
        Parent girisEkrani = FXMLLoader.load(getClass().getResource("FXML.fxml"));
        Scene girisScene= new Scene(girisEkrani);
        Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(girisScene);
        window.show();
    }
    
    String CheckBoxKontrol(){
        boolean[] a= new boolean[12];
        a[0]=ogr1.isSelected();
        a[1]=ogr2.isSelected();
        a[2]=ogr3.isSelected();
        a[3]=ogr4.isSelected();
        a[4]=ogr5.isSelected();
        a[5]=ogr6.isSelected();
        a[6]=ogr7.isSelected();
        a[7]=ogr8.isSelected();
        a[8]=ogr9.isSelected();
        a[9]=ogr10.isSelected();
        a[10]=ogr11.isSelected();
        a[11]=ogr12.isSelected();
        int top=0;
        for (int i = 0; i < 12; i++) {
            if(a[i]==true)
                top++;
            
        }
        if(top>1)
            return "fazla";
        else if(a[0]==true)
            return ogr1.getText();
        else if(a[1]==true)
            return ogr2.getText();
        else if(a[2]==true)
            return ogr3.getText();
        else if(a[3]==true)
            return ogr4.getText();
        else if(a[4]==true)
            return ogr5.getText();
        else if(a[5]==true)
            return ogr6.getText();
        else if(a[6]==true)
            return ogr7.getText();
        else if(a[7]==true)
            return ogr8.getText();
        else if(a[8]==true)
            return ogr9.getText();
        else if(a[9]==true)
            return ogr10.getText();
        else if(a[10]==true)
            return ogr11.getText();
        else if(a[11]==true)
            return ogr12.getText();
        else
            return null;
        
        
    
    }
    
    @FXML
    void OgretmenKaydet(ActionEvent event) {
        
        if(CheckBoxKontrol()==null)
            Message.setText("Lutfen bir danisman seciniz.");
        else if(CheckBoxKontrol()=="fazla")
            Message.setText("Lutfen en fazla bir adet danisman seciniz.");
        else if(CheckBoxKontrol()=="Bos")
            Message.setText("Bos alan secemezsiniz.");
        else{
            Message.setText(CheckBoxKontrol()+" adli danismana istek gönderildi.");
            db.IstekYolla(name, CheckBoxKontrol());
        }
        
    }
    
    
   void OgretmenleriYaz(){
        ar1=db.Ogretmenler();
        
        for (int i = 0; i < ar1.size(); i++) {
           if(ar1.get(i)==null){
           ar1.remove(i);
           i--;
           }
        }
        for (int i = 0; i < 12; i++) {
            System.out.println(ar1.get(i));
            if(ar1.size()<12)
                ar1.add("Bos");
        }
        
        ogr1.setText(ar1.get(0).toString());
        ogr2.setText(ar1.get(1).toString());
        ogr3.setText(ar1.get(2).toString());
        ogr4.setText(ar1.get(3).toString());
        ogr5.setText(ar1.get(4).toString());
        ogr6.setText(ar1.get(5).toString());
        ogr7.setText(ar1.get(6).toString());
        ogr8.setText(ar1.get(7).toString());
        ogr9.setText(ar1.get(8).toString());
        ogr10.setText(ar1.get(9).toString());
        ogr11.setText(ar1.get(10).toString());
        ogr12.setText(ar1.get(11).toString());  
   }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        OgrIsim.setText(name);
        OgretmenleriYaz();
        if(db.MevcutDanisman(name)!=null)
            MevcutDan.setText(db.MevcutDanisman(name));
    }    
    
}

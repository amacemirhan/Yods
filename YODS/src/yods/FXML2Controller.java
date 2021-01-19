/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yods;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
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
public class FXML2Controller implements Initializable {
    DBControl db=new DBControl();
    ArrayList ar1 = new ArrayList();
    ArrayList ar2 = new ArrayList();
    ArrayList ar3 = new ArrayList();
    String name;
    
    @FXML
    private Label sis2;

    @FXML
    private Label sis1;

    @FXML
    private Label sis4;

    @FXML
    private Button Onyla;

    @FXML
    private Label sis3;

    @FXML
    private Label OgrtIsim;

    @FXML
    private Label sis9;

    @FXML
    private Label sis6;

    @FXML
    private Label dan3;

    @FXML
    private Label sis5;

    @FXML
    private Label sis8;

    @FXML
    private Label dan1;

    @FXML
    private Label sis7;

    @FXML
    private Label dan2;

    @FXML
    private Button returnBut;

    @FXML
    private CheckBox ist1;

    @FXML
    private CheckBox ist3;

    @FXML
    private CheckBox ist2;

    @FXML
    private CheckBox ist5;

    @FXML
    private CheckBox ist4;

    @FXML
    private CheckBox ist7;

    @FXML
    private CheckBox ist6;

    @FXML
    private Label MessageOgrt;

    @FXML
    private CheckBox ist8;
    
    @FXML
    void GeriDon(ActionEvent event) throws IOException {
        db.Cikis();
        Parent girisEkrani = FXMLLoader.load(getClass().getResource("FXML.fxml"));
        Scene girisScene= new Scene(girisEkrani);
        Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(girisScene);
        window.show();
    }
    void IstekleriYaz(){
        ar2=db.Istekler(name);
        ar2.add("Bos");
        for (int i = 0; i < 8; i++) {
            System.out.println(ar2.get(i));
            if(ar2.size()<8)
                ar2.add("Bos");
        }
        ist1.setText(ar2.get(0).toString());
        ist2.setText(ar2.get(1).toString());
        ist3.setText(ar2.get(2).toString());
        ist4.setText(ar2.get(3).toString());
        ist5.setText(ar2.get(4).toString());
        ist6.setText(ar2.get(5).toString());
        ist7.setText(ar2.get(6).toString());
        ist8.setText(ar2.get(7).toString());
    }
    void MevcutlariYaz(){
        ar3=db.MevcutOgrenciler(name);
        ar3.add("Yok");
        for (int i = 0; i < 3; i++) {
            System.out.println(ar3.get(i));
            if(ar3.size()<3)
                ar3.add("Yok");
        
        }
        dan1.setText(ar3.get(0).toString());
        dan2.setText(ar3.get(1).toString());
        dan3.setText(ar3.get(2).toString());
    }
    void OgrencileriYaz(){
        ar1=db.Ogrenciler();
        
        for (int i = 0; i < ar1.size(); i++) {
           if(ar1.get(i)==null){
           ar1.remove(i);
           i--;
           }
        }
        for (int i = 0; i < 9; i++) {
            System.out.println(ar1.get(i));
            if(ar1.size()<9)
                ar1.add("");
        }
        
        sis1.setText(ar1.get(0).toString());
        sis2.setText(ar1.get(1).toString());
        sis3.setText(ar1.get(2).toString());
        sis4.setText(ar1.get(3).toString());
        sis5.setText(ar1.get(4).toString());
        sis6.setText(ar1.get(5).toString());
        sis7.setText(ar1.get(6).toString());
        sis8.setText(ar1.get(7).toString());
        sis9.setText(ar1.get(8).toString());
        
   }
    String CheckBoxKontrol(){
        boolean[] a= new boolean[8];
        a[0]=ist1.isSelected();
        a[1]=ist2.isSelected();
        a[2]=ist3.isSelected();
        a[3]=ist4.isSelected();
        a[4]=ist5.isSelected();
        a[5]=ist6.isSelected();
        a[6]=ist7.isSelected();
        a[7]=ist8.isSelected();
        int top=0;
        for (int i = 0; i < 8; i++) {
            if(a[i]==true)
                top++;
            
        }
        if(top>1)
            return "fazla";
        else if(a[0]==true)
            return ist1.getText();
        else if(a[1]==true)
            return ist2.getText();
        else if(a[2]==true)
            return ist3.getText();
        else if(a[3]==true)
            return ist4.getText();
        else if(a[4]==true)
            return ist5.getText();
        else if(a[5]==true)
            return ist6.getText();
        else if(a[6]==true)
            return ist7.getText();
        else if(a[7]==true)
            return ist8.getText();
        else
            return null;
        
        
    
    }
     @FXML
    void Onayla(ActionEvent event) {
        if(CheckBoxKontrol()==null)
            MessageOgrt.setText("Onaylamak icin ogrenci secmediniz..");
        else if("fazla".equals(CheckBoxKontrol()))
            MessageOgrt.setText("Lutfen tek tek onaylayiniz.");
        else if("Bos".equals(CheckBoxKontrol()))
            MessageOgrt.setText("Bos alan onaylayamazsiniz.");
        else if("Yok".equals(dan1.getText()) || "Yok".equals(dan2.getText()) ||"Yok".equals(dan3.getText())){
            MessageOgrt.setText(CheckBoxKontrol()+" adli ogrenci danisilanlara eklendi.");
            db.IstekKabul(name,CheckBoxKontrol());
            MevcutlariYaz();
        }
        else
            MessageOgrt.setText("Yeterince ogrenciye danismanlik yapiyorsunuz.");
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        name=db.WhoIsInside();
        OgrencileriYaz();
        IstekleriYaz();
        MevcutlariYaz();
        OgrtIsim.setText(name);
    }    
    
}


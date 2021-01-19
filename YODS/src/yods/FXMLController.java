package yods;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Mehmed Emirhan AMAÇ
 */
public class FXMLController implements Initializable {
    DBControl db=new DBControl();
    ObservableList<String> list= FXCollections.observableArrayList("Ogrenci","Ogretmen");
    @FXML
    private ComboBox GirisCombo;
    @FXML
    private ComboBox KayitCombo;
    @FXML
    private TextField GirisSifre;
    @FXML
    private TextField GirisIsim;
    @FXML
    private TextField KayitIsim;
    @FXML
    private TextField KayitSifre;
    @FXML
    private Button KayitButon;
    @FXML
    private Button GirisButon;
    @FXML
    private Label Etiket;
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        GirisCombo.getItems().addAll("Ogrenci","Ogretmen");
        KayitCombo.getItems().addAll("Ogrenci","Ogretmen");
        
    }    

    @FXML
    public void ClickGiris(javafx.event.ActionEvent event) throws IOException {
        db.baglan();
        String name=GirisIsim.getText();
        String pass=GirisSifre.getText();
        String tip = null;
        if(GirisCombo.getValue()=="Ogrenci")
            tip="ogr";
        else if(GirisCombo.getValue()=="Ogretmen")
            tip="ogt";
        if(db.kullaniciKontrol(name,pass,tip)==true && tip=="ogr"){
        db.Giris(name, pass, tip);
        Parent ogrenciEkrani = FXMLLoader.load(getClass().getResource("FXML1.fxml"));
        Scene ogrenciScene = new Scene(ogrenciEkrani);
        Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(ogrenciScene);
        window.show();
                }
        else if(db.kullaniciKontrol(name,pass,tip)==true && tip=="ogt"){
        db.Giris(name, pass, tip);
        Parent ogretmenEkrani = FXMLLoader.load(getClass().getResource("FXML2.fxml"));
        Scene ogretmenScene = new Scene(ogretmenEkrani);
        Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(ogretmenScene);
        window.show();
        }
        else
        Etiket.setText("Kullanici bulunamadi!");
        
    }
    
    @FXML
    private void ClickKayit(javafx.event.ActionEvent event) {
        db.baglan();
        String name=KayitIsim.getText();
        String pass=KayitSifre.getText();
        String tip = null;
        if(KayitCombo.getValue()=="Ogrenci")
            tip="ogr";
        else if(KayitCombo.getValue()=="Ogretmen")
            tip="ogt";
        if(db.kullaniciKontrol(name,pass,tip)==true)
            Etiket.setText("Kullanici zaten kayitli.");
        else if(db.kayitEkle(name, pass, tip)==true)
            Etiket.setText("Kayit Yapidi");
        else
            Etiket.setText("Kayit Yapilamadi");
    }
    
}

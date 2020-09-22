/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package firwanaa;

import java.io.File;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import prog24178.labs.objects.CookieInventoryItem;
import prog24178.labs.objects.Cookies;

/**
 *
 * @author QQ
 */
public class Assign5 extends Application {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Application.launch(args);
        
        CookieInventoryFile f1 = new CookieInventoryFile();

    File f = new File("cookies.dat");
    //f1.loadFromFile(f);
    //f1.WriteToFile(f);
    
    }

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("Assign5FXML.fxml"));
        Scene s = new Scene(root);
        stage.setScene(s);
        stage.setTitle("Cookie Inventory");
        stage.setResizable(false);
        stage.show();
    }

}

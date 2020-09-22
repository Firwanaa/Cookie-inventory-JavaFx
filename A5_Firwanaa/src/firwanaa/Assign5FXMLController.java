/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package firwanaa;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import prog24178.labs.objects.Cookies;

/**
 * FXML Controller class
 *
 * @author QQ
 */
public class Assign5FXMLController implements Initializable {
/**
 * Connect FXML variables
 */
    @FXML
    private Button btnSell, btnAdd, btnExit, btnPrint;
    @FXML
    private Label lblSell, lblAdd;
    @FXML
    private TextField txtfSell, txtfAdd;
    @FXML
    private ComboBox comboxMenue;
/**
 * A Method that return selected item id
 * @return index value
 */
    public int comboOptionSelected() {
        int i = comboxMenue.getSelectionModel().getSelectedIndex();
        int index = 0;
        for (Cookies c : Cookies.values()) {
            if (i == c.ordinal()) {
                index = c.getId();
            }
        }
        return index;
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        File inv = new File("cookies.dat");

/**
 * Observable list for ComboBox
 */
        ObservableList<Cookies> obsCombList = FXCollections.observableArrayList();
        comboxMenue.setItems(obsCombList);
        for (Cookies c : Cookies.values()) {
            obsCombList.add(c);

        }
        comboxMenue.getSelectionModel().select(3);
        //int i = comboxMenue.getSelectionModel().getSelectedIndex();
/**
 * Add Button that Validate user input in the text field before add it to the file.
 */
        btnAdd.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                boolean control = true;
                int slectedIndex = comboOptionSelected();
                //Testing
                System.out.println("From combobox"+comboOptionSelected());
                if (txtfAdd.getText().trim().isEmpty()) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Data Entery Error");
                    alert.setContentText("Please enter the number of cookies added.");
                    alert.showAndWait();
                    control = false;
                }
                if (control) {
                    try {
                        int numAdd = Integer.parseInt(txtfAdd.getText());
                        boolean flag = true;
                        if (numAdd < 0) {
                            Alert alert = new Alert(Alert.AlertType.ERROR);
                            alert.setTitle("Data Entery Error");
                            alert.setHeaderText("You must enter a quantity that is greater than 0.");
                            //alert.setContentText(err1);

                            alert.showAndWait();
                            flag = false;
                            txtfAdd.requestFocus();
                        } else {
                            FileWriter fw = new FileWriter(inv, true);
                            BufferedWriter bw = new BufferedWriter(fw);
                            PrintWriter output = new PrintWriter(bw);
                            output.println(comboOptionSelected() + "|" + numAdd);
                            output.close();

                        }

                    } catch (NumberFormatException e) {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Data Entery Error");
                        //alert.setHeaderText("You must enter a valid numeric value.");
                        alert.setContentText("You must enter a valid numeric value.");
                        alert.showAndWait();
                        txtfAdd.requestFocus();

                    } catch (IOException ioe) {
                        String err1 = ioe.getMessage();
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Data Entery Error");
                        alert.setContentText(err1);
                        alert.showAndWait();
                        txtfAdd.requestFocus();
                    }
                }

            }
        });

/**
 * Sell button that Validate user input before bring it from the file.
 */
        btnSell.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                boolean control = true;
                if (txtfSell.getText().trim().isEmpty()) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Data Entery Error");
                    //alert.setHeaderText("Please enter the number of cookies sold.");
                    alert.setContentText("Please enter the number of cookies sold.");
                    alert.showAndWait();
                    control = false;
                    System.out.println("Test 0");

                }
                if (control) {
                    try {
                        int numSell = Integer.parseInt(txtfSell.getText());
                        boolean flag = true;
                        if (numSell < 0) {
                            Alert alert = new Alert(Alert.AlertType.ERROR);
                            alert.setTitle("Data Entery Error");
                            //alert.setHeaderText("You must enter a quantity that is greater than 0.");
                            alert.setContentText("You must enter a quantity that is greater than 0.");

                            alert.showAndWait();
                            flag = false;
                            txtfSell.requestFocus();
                            System.out.println("Test 1");
                        } else if (flag && inv.exists()) {
                            System.out.println("Test 2");
                            Scanner sc = new Scanner(inv);
                            while (sc.hasNextLine()) {
                                String record = sc.nextLine();
                                String[] fields = record.split("\\|");
                                if (comboOptionSelected() == Integer.parseInt(fields[0])) {
                                    if (Integer.parseInt(fields[1]) > numSell) {
                                        System.out.println("Test 3");
                                    } else if (Integer.parseInt(fields[1]) < numSell) {
                                        int q = 0;
                                        String s = "";
                                        System.out.println("Test 4");
                                        for (Cookies ck : Cookies.values()) {
                                            if (comboOptionSelected() == ck.getId()) {
                                                s = ck.getName();
                                                q = Integer.parseInt(fields[1]);
                                                System.out.println("Test 5");
                                            }
                                        }
                                        Alert alert = new Alert(Alert.AlertType.ERROR);
                                        alert.setTitle("Data Entery Error");
                                        alert.setContentText("Sorry,there are not enough " + s + " cookies to sell. You only have " + q + " left");
                                        alert.showAndWait();
                                        txtfSell.requestFocus();
                                        System.out.println("Test 6");
                                        break;

                                    } else if (Integer.parseInt(fields[1]) == numSell) {
                                        String s = "";
                                        System.out.println("Test 5.1");
                                        for (Cookies ck : Cookies.values()) {
                                            if (comboOptionSelected() != ck.getId()) {
                                                s = ck.getName();
                                                System.out.println("Test 5.2");
                                            }
                                        }
                                        Alert alert = new Alert(Alert.AlertType.ERROR);
                                        alert.setTitle("Data Entery Error");
                                        alert.setContentText("Sorry,there are no  " + s + "  to sell.");
                                        alert.showAndWait();
                                        txtfSell.requestFocus();
                                        System.out.println("Test 6");
                                    }
                                } else {
                                    String name = "";
                                    for (Cookies c : Cookies.values()) {
                                        if (c.getId() == comboOptionSelected()) {
                                            name = c.getName();
                                        }
                                    }
                                    Alert alert = new Alert(AlertType.ERROR);
                                    alert.setTitle("Data Entry Error");
                                    alert.setContentText("Sorry, there are not enough " + name + " cookies available to sell.");
                                    alert.showAndWait();
                                    System.out.println("Test 7");
                                    break;

                                }
                            }
                        }else {

                                    String name = "";
                                    for (Cookies c : Cookies.values()) {
                                        if (c.getId() == comboOptionSelected()) {
                                            name = c.getName();
                                        }
                                    }
                                    Alert alert = new Alert(AlertType.ERROR);
                                    alert.setTitle("Data Entry Error");
                                    alert.setContentText("Sorry,there are not enough " + name + " cookies available to sell.");
                                    alert.showAndWait();
                                    System.out.println("Test 7.2");
                                }
                       System.out.println("Test 8");
                    } catch (NumberFormatException e) {
                        String err1 = e.getMessage();
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Data Entery Error");
                        alert.setContentText("You must enter a valid numeric value.");
                        alert.showAndWait();
                        txtfSell.requestFocus();
                        System.out.println("Test 9");

                    } catch (IOException ioe) {
                        String err1 = ioe.getMessage();
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Data Entery Error");
                        alert.setContentText(err1);
                        alert.showAndWait();
                        txtfSell.requestFocus();
                        System.out.println("Test 10");
                    }
                }

            }
        });
/**
 * Exit button that confirm and Exit
 */
        btnExit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Exit");
                alert.setHeaderText("Do you want to Exit? ");
                alert.setContentText("Click Yes to Exit or No To Go Back");
                ButtonType okButton = new ButtonType("Yes", ButtonBar.ButtonData.YES);
                ButtonType noButton = new ButtonType("No", ButtonBar.ButtonData.NO);
                alert.getButtonTypes().setAll(okButton, noButton);
                alert.showAndWait().ifPresent(type -> {
                    if (type == okButton) {
                        System.exit(0);
                    } else if (type == noButton) {
                    } else {
                    }
                });
            }
        });
        
        /**
         *Incomplete Print button logic. 
         * no time.
         */
//        btnPrint.setOnAction(new EventHandler<ActionEvent>() {
//            @Override
//            public void handle(ActionEvent t) {
//                
//            }
//        });
        
    }

}

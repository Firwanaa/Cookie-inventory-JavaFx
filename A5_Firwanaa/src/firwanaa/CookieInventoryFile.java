package firwanaa;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import prog24178.labs.objects.CookieInventoryItem;
import prog24178.labs.objects.Cookies;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author QQ
 */
public class CookieInventoryFile extends ArrayList<CookieInventoryItem> {

    public CookieInventoryFile() {
    }


    public CookieInventoryFile(File file) {
        loadFromFile(file);
    }

    /**
     * A Method to find item and return it's id
     * @param id
     * @return 
     */
    public CookieInventoryItem find(int id) {
        try {
            CookieInventoryItem cItem = new CookieInventoryItem();
            cItem.cookie = Cookies.getCookie(id);
            return cItem;
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * A method that load file content
     * @param file 
     */
    public void loadFromFile(File file) {
        if (file.exists()) {
            try {
                Scanner sc = new Scanner(file);
                while (sc.hasNextLine()) {
                    String record = sc.nextLine();
                    String[] fields = record.split("\\|");
                    CookieInventoryItem cItem = new CookieInventoryItem();
                    for (Cookies c : Cookies.values()) {
                        if (Integer.parseInt(fields[0]) == c.getId()) {
                            cItem.cookie = c;
                            cItem.setQuantity(Integer.parseInt(fields[1]));
                            super.add(cItem);
                        }

                    }
                }
                sc.close();
            } catch (Exception e) {

            }
        }
        System.out.println(super.get(0));
        System.out.println(super.get(1));
    }
/**
 * A method that save to a file
 * @param file 
 */
    public void WriteToFile(File file) {
        CookieInventoryItem cItem = new CookieInventoryItem();
        try {
            FileWriter fw = new FileWriter(file, true);
            PrintWriter pw = new PrintWriter(fw);
            for (CookieInventoryItem c : this) {
                c.toFileString();
                pw.println(c.toFileString());

            }
            pw.close();

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

}

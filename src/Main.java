
import GUI.*;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new start_menu_GUI().setVisible(true);
            }
        });

//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new picture_GUI().setVisible(true);
//            }
//        });
    }
}

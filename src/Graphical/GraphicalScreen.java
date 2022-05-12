package Graphical;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GraphicalScreen extends JFrame {
    private JTextField TFvalor1;
    private JTextField TFvalor02;
    private JButton btnRUN;
    private JButton bntSTEP;
    private JPanel mainPanel;
    private JLabel VMtittle;

    public GraphicalScreen(){
        setContentPane(mainPanel);
        setTitle("Virtual Machine SIC-HEX");
        setSize(500,500);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);


        //AÇÕES BOTAO RUN
        btnRUN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String run = TFvalor1.getText();


            }
        });

        //AÇÕES BOTAO STEP
        bntSTEP.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

    }

    public static void main(String[]args){
        GraphicalScreen myFrame = new GraphicalScreen();
    }



}

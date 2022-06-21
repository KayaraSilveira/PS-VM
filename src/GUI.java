import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;


public class GUI extends JFrame {

    private JPanel panelMain;       //COMPONENTES TELA
    private JButton runButton;
    private JLabel iconBG;
    private JButton stepButton;
    private JButton clearButton;
    private JScrollPane scrollMemory;
    private JScrollBar scrollBar1;

    private JLabel outA;        //SAIDAS DE DADOS REGs
    private JLabel outX;
    private JLabel outL;
    private JLabel outB;
    private JLabel outS;
    private JLabel outT;
    private JLabel outF;
    private JLabel outPC;
    private JLabel outSW;

    private JLabel TittleA;     //TITULOS REGS
    private JLabel TittleX;
    private JLabel TittleL;
    private JLabel TittleB;
    private JLabel TittleS;
    private JLabel TittleT;
    private JLabel TittleF;
    private JLabel TittlePC;
    private JLabel TittleSW;
    private JList list1;        //mem


     private JTextArea textArea1;

    CPU cpu = new CPU();


   public GUI() {
        super("UNIVERSIDADE FEDERAL DE PELOTAS");
       textArea1.setEditable(false);

       //WINDOW
       this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       this.setContentPane(this.panelMain);
       this.setSize(1000, 700);
       setLocationRelativeTo(null);    // centers on screen

       runButton.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               try {
                   Loader.load(cpu, "src/test.obj");
               } catch (FileNotFoundException e1) {
                   System.out.println(e1);
                   return;
               }

               int i = 0;

               while (i < cpu.mem.getInstructionCount()) {
                   cpu.execute();

                   outA.setText("out:" + cpu.reg.getA());
                   outX.setText("out:" + cpu.reg.getX());
                   outL.setText("out:" + cpu.reg.getL());
                   outB.setText("out:" + cpu.reg.getB());
                   outS.setText("out:" + cpu.reg.getS());
                   outT.setText("out:" + cpu.reg.getT());
                   outF.setText("out:" + cpu.reg.getF());
                   outPC.setText("out:" + cpu.reg.getPC());
                   outSW.setText("out:" + cpu.reg.getSW());

                   i++;

                  // redirectSystemStreams();
               }
           }
       });
   }

}

//CODIGOS CASO PRECISE

/*
/ATUALIZA O TEXTAREA1


private void updateTextArea(final String text) {
        SwingUtilities.invokeLater(new Runnable() {
public void run() {
        textArea1.append(text);

        }
        });
        }
*/

/*
    //REDIRECIONA PARA O JTEXTAREA
    private void redirectSystemStreams() {
        OutputStream out = new OutputStream() {
            @Override
            public void write(int b) throws IOException {
                updateTextArea(String.valueOf((char) b));
            }

            @Override
            public void write(byte[] b, int off, int len) throws IOException {
                updateTextArea(new String(b, off, len));
            }

            @Override
            public void write(byte[] b) throws IOException {
                write(b, 0, b.length);
            }
        };

        System.setOut(new PrintStream(out, true));
        System.setErr(new PrintStream(out, true));
    }
*/

/*
        try {
            Loader.load(cpu, "src/PS/test.obj");
        } catch (FileNotFoundException e1) {
            System.out.println(e1);
            return;
        }

        int i = 0;

        while (i < cpu.mem.getInstructionCount()) {
            cpu.execute();

            System.out.println("REGISTER A: " + cpu.reg.getA());
            System.out.println("REGISTER X: " + cpu.reg.getX());
            System.out.println("REGISTER L: " + cpu.reg.getL());
            System.out.println("REGISTER B: " + cpu.reg.getB());
            System.out.println("REGISTER S: " + cpu.reg.getS());
            System.out.println("REGISTER T: " + cpu.reg.getT());
            System.out.println("REGISTER F: " + cpu.reg.getF());
            System.out.println("REGISTER PC: " + cpu.reg.getPC());
            System.out.println("REGISTER SW: " + cpu.reg.getSW());
            System.out.println("\n");

            i++;
        }
*/



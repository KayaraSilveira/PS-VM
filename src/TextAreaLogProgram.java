import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.OutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.io.FileNotFoundException;


public class TextAreaLogProgram  extends JFrame {

    private JPanel panelMain;
    private JButton runButton;
    private JList RegsList;
    private JFormattedTextField INPUTFormattedTextField;
    private JButton enter;
    private JLabel iconBG;
    private JButton stepButton;
    private JButton clearButton;
    private JScrollPane scrollMemory;
    private JTextArea textArea1;         //IMPRIME REGISTRADORES
    private JScrollBar scrollBar1;

    // private ArrayList<Registers> regs;
    CPU cpu = new CPU();


   public TextAreaLogProgram() {
        super("UNIVERSIDADE FEDERAL DE PELOTAS");
       textArea1.setEditable(false);

       //WINDOW
       this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       this.setContentPane(this.panelMain);
       this.setSize(650, 750);
       setLocationRelativeTo(null);    // centers on screen
       this.pack();

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

                   redirectSystemStreams();
               }
           }
       });
   }


 //ATUALIZA O TEXTAREA1
    private void updateTextArea(final String text) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                textArea1.append(text);
            }
        });
    }

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

}



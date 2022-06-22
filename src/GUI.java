import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.io.FileNotFoundException;
import java.io.IOException;


public class GUI extends JFrame {

    private JPanel panelMain;       //COMPONENTES TELA
    private JButton runButton;
    private JLabel iconBG;
    private JButton stepButton;

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



    // private JTextArea textArea1;
    private JLabel saidamem;
    private JTextPane saidamem2;

    private JTextArea inmacro;
    private JTextArea outmacro;
    private JScrollBar scrollBar2;

    Macro ob = new Macro();
    Montador mon = new Montador();
    public int count = 0;


   public GUI() {
        super("UNIVERSIDADE FEDERAL DE PELOTAS");
      // textArea1.setEditable(false);

       //WINDOW
       this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       this.setContentPane(this.panelMain);
       this.setSize(1000, 1200);
       setLocationRelativeTo(null);    // centers on screen



       //RUN BUTTON
       runButton.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {

               /*----------MACRO----------*/

               try {
                   ob.loadMacro("src/inputMacro.txt");
               } catch (
                       FileNotFoundException e1) {
                   System.out.println(e1);
                   return;
               }

               //MACRO THINGS
               ob.namesTable(ob.macro);
               ob.callMacros(ob.macro);
               ob.labels();
               ob.definitionTable(ob.macro);
               ob.expandCallMacro();
               ob.substituiParam();
               ob.removeNameMacro();
               ob.expandMacros();
               ob.removeCallMacro();
               ob.saidaFinal();

               try {
                   ob.writeMacro();
               } catch (
                       IOException e1) {
                   System.out.println(e1);
                   return;
               }

               /*----------MONTADOR----------*/

               try {
                   mon.loadMontador("src/inputMontador.txt");
               } catch (
                       FileNotFoundException e1) {
                   System.out.println(e1);
                   return;
               }

               mon.verifyLabel();
               mon.opToBinary();


               try {
                   mon.writeMontador();
                   mon.writeSymbolTable();
               } catch (
                       IOException e1) {
                   System.out.println(e1);
                   return;
               }

               /*----------MACHINE----------*/

               CPU cpu = new CPU();

               try {
                   Loader.load(cpu, "src/inputMachine.txt");
               } catch (FileNotFoundException e1) {
                   System.out.println(e1);
                   return;
               }

               /*----------SAÍDAS----------*/

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
               }

                        saidamem2.setText(cpu.imprimeMem());
               }


       });


       //STEP BUTTON
       stepButton.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               if (count == 0){
                   /*----------MACRO----------*/

                   try {
                       ob.loadMacro("src/inputMacro.txt");
                   } catch (
                           FileNotFoundException e1) {
                       System.out.println(e1);
                       return;
                   }
                   String text = new String();

                   for (int j = 0; j < ob.macro.size(); j++) {
                       text = text + ob.getMacro(j) + "\n";
                   }

                   inmacro.setText(text);

                   //MACRO THINGS
                   ob.namesTable(ob.macro);
                   ob.callMacros(ob.macro);
                   ob.labels();
                   ob.definitionTable(ob.macro);
                   ob.expandCallMacro();
                   ob.substituiParam();
                   ob.removeNameMacro();
                   ob.expandMacros();
                   ob.removeCallMacro();
                   ob.saidaFinal();

                   try {
                       ob.writeMacro();
                   } catch (
                           IOException e1) {
                       System.out.println(e1);
                       return;
                   }

                   try {
                       mon.loadMontador("src/inputMontador.txt");
                   } catch (
                           FileNotFoundException e1) {
                       System.out.println(e1);
                       return;
                   }

                   String text1 = new String();

                   for (int j = 0; j < mon.montador.size(); j++) {
                       text1 = text1 + mon.getMontador(j) + "\n";
                   }

                   outmacro.setText(text1);
                   count ++;
               }

               else if (count == 1){
                   String text = new String();

                   for (int j = 0; j < mon.montador.size(); j++) {
                       text = text + mon.getMontador(j) + "\n";
                   }

                   inmacro.setText(text);
                   mon.verifyLabel();
                   mon.opToBinary();


                   try {
                       mon.writeMontador();
                       mon.writeSymbolTable();
                   } catch (
                           IOException e1) {
                       System.out.println(e1);
                       return;
                   }

                   String text1 = new String();
                   for (int j = 0; j < mon.montadorFinal.size(); j++) {
                       text1 = text1 + mon.getMontadorFinal(j) + "\n";
                   }

                   outmacro.setText(text1);
                   count++;
               }
               else if(count == 2) {
                   String text = new String();
                   for (int j = 0; j < mon.montadorFinal.size(); j++) {
                       text = text + mon.getMontadorFinal(j) + "\n";
                   }
                   inmacro.setText(text);
                   outmacro.setText("");
                   CPU cpu = new CPU();

                   try {
                       Loader.load(cpu, "src/inputMachine.txt");
                   } catch (FileNotFoundException e1) {
                       System.out.println(e1);
                       return;
                   }

                   /*----------SAÍDAS----------*/

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
                   }

                   saidamem2.setText(cpu.imprimeMem());
            }
           }
       });

       //scrollBar2.addAdjustmentListener(new AdjustmentListener() {
         //  @Override
         //  public void adjustmentValueChanged(AdjustmentEvent e) {
        //       inmacro.setEditable(true);
           }
       //});
   //}


    public static void main(String[] args) throws IOException {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                //INVOCA SCREEN
                new GUI().setVisible(true);

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
*/



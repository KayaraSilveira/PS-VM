import java.io.FileNotFoundException;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        Macro ob = new Macro();
        Montador mon = new Montador();

        try {
            ob.loadMacro("src/macro.txt");
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
        ob.writeMacro();

        try {
            mon.loadMontador("src/entradaMontador.txt");
        } catch (
                FileNotFoundException e1) {
            System.out.println(e1);
            return;
        }

        mon.verifyLabel();
        mon.opToBinary();
        mon.writeMontador();
        mon.writeSymbolTable();

        CPU cpu = new CPU();

        try {
            Loader.load(cpu, "src/saidaMontador.txt");
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
        cpu.imprimeMem();


    }
}

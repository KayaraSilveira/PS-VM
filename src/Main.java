import java.io.FileNotFoundException;

public class Main {

    public static void main(String[] args) {
        CPU cpu = new CPU();

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
        }
    }
}

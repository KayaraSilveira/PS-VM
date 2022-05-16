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

    }
}

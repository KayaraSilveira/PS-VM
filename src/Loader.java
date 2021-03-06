import java.io.*;
import java.util.ArrayList;

public class Loader {

    public static String readString(Reader r, int len) throws IOException {
        StringBuilder buf = new StringBuilder();
        while (len-- > 0) buf.append((char)r.read());
        return buf.toString();
    }

    public static int readByte(Reader r) throws IOException {
        return Conversion.stringBinaryToInt(readString(r, 8));
    }

    public static String readWord(Reader r) throws IOException {
        return readString(r, 24);
    }

    public static boolean load(CPU cpu, String filename) throws  FileNotFoundException {
        BufferedReader r = new BufferedReader (new FileReader(filename));

        try {

            Memory mem = cpu.mem;
            //Registers reg = cpu.reg;
            String line = r.readLine();
            int memoryLine = 0;
            while(line != null) {
                if(line.length() == 16){
                    mem.setByte(Conversion.intToStringBinary(memoryLine), line.substring(0,8));
                    memoryLine += 1;
                    mem.setByte(Conversion.intToStringBinary(memoryLine), line.substring(8,16));
                    line = r.readLine();
                    memoryLine += 1;
                    mem.incInstructionCount();
                }else {
                    mem.setWord(Conversion.intToStringBinary(memoryLine), line);
                    line = r.readLine();
                    memoryLine += 3;
                    mem.incInstructionCount();
                }
            }
            r.close();

        } catch (IOException e) {
            System.out.println(e);
            return false;
        }
        return true;
    }

}
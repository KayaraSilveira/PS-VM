import java.io.*;

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

            /*if (r.read() == '\r') // eol
                r.read();

            while (line.contains("0")) {
                line = readWord(r);
                if (line.contains("0")) {
                }
                if (r.read() == '\r') // eol
                    r.read();
            }*/




            //mem.setByte(Conversion.intToStringBinary(reg.getPC()), line);

            //String line2 = readWord(r);

            //System.out.println(line2);

            //mem.setByte(Conversion.intToStringBinary(reg.getPC()), line2);
            //reg.incPC();

            //String line3 = readWord(r);

            //System.out.println(line3);

            //mem.setByte(Conversion.intToStringBinary(reg.getPC()), line3);
            //reg.incPC();

            //System.out.println(reg.getA());




            //int len = readByte(r);
            /*while (len-- > 0) {
                if (loc < start || loc >= start + length) return false;
                String val = Conversion.intToStringBinary(readByte(r));
                mem.setByte(Conversion.intToStringBinary(loc++), val);
            }
            if (r.read() == '\r') // eol
                r.read();
            ch = r.read();


            // modification records
            while (ch == 'M') {
                readWord(r);	// addr
                readByte(r);	// len
                if (r.read() == '\r') // EOL
                    r.read();
                ch = r.read();
            }

            // load end record
            if (ch != 'E') return false;
            reg.setPC(Conversion.intToStringBinary(readWord(r)));

            System.out.println("Finished loading!");*/
        } catch (IOException e) {
            System.out.println(e);
            return false;
        }
        return true;
    }
}
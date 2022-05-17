import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

public class Loader {
    public static String readString(Reader r, int len) throws IOException {
        StringBuilder buf = new StringBuilder();
        while (len-- > 0) buf.append((char)r.read());
        return buf.toString();
    }

    public static int readByte(Reader r) throws IOException {
        return Conversion.stringBinaryToInt(readString(r, 8));
    }

    public static int readWord(Reader r) throws IOException {
        return Conversion.stringBinaryToInt(readString(r, 24));
    }

    public static boolean load(CPU cpu, String filename) throws  FileNotFoundException {
        Reader r = new FileReader(filename);

        try {
            // check if it is a header record
            readString(r, 6);	// ignore name
            if (r.read() != 'H') return false;
            int start = readByte(r);
            int length = readByte(r);
            if (r.read() == '\r') // eol
                r.read();

            Memory mem = cpu.mem;
            Registers reg = cpu.reg;

            // if it is text records
            int ch = r.read();
            while (ch == 'T') {
                int loc = readWord(r);
                int len = readByte(r);
                while (len-- > 0) {
                    if (loc < start || loc >= start + length) return false;
                    String val = Conversion.intToStringBinary(readByte(r));
                    mem.setByte(Conversion.intToStringBinary(loc++), val);
                }
                if (r.read() == '\r') // eol
                    r.read();
                ch = r.read();
            }

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

            System.out.println("Finished loading!");
        } catch (IOException e) {
            System.out.println(e);
            return false;
        }
        return true;
    }
}
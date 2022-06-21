import java.lang.reflect.Array;
import java.util.ArrayList;

public class Opcode {
    // Formato 2 SIC/XE
    public ArrayList<String> op = new ArrayList<>();
    public static final String ADDR = "10010000";
    public static final String SUBR = "10010100";
    public static final String MULR = "10011000";
    public static final String DIVR = "10011100";
    public static final String COMPR = "10100000";
    public static final String SHIFTL = "10100100";
    public static final String SHIFTR = "10101000";
    public static final String RMO = "10101100";
    public static final String CLEAR = "10110100";
    public static final String TIXR = "10111000";

    // Formato SICXE 3 e 4
    public static final String STA = "001100";
    public static final String STX = "010000";
    public static final String STL = "010100";
    public static final String STCH = "101010";
    // STB mudou por conflito com J
    public static final String STB = "000011";
    public static final String STS = "111110";
    public static final String STT = "100001";
    public static final String JEQ = "110000";
    // JGT mudou por conflito com LDB
    public static final String JGT = "001011";
    public static final String JLT = "111000";
    public static final String J = "111100";
    public static final String RSUB = "100110";
    // JSUB mudou por conflito com DIV e STT
    public static final String JSUB = "011110";
    public static final String LDA = "000000";
    public static final String LDX = "000100";
    public static final String LDL = "001000";

    public static final String LDCH = "101000";
    public static final String LDB = "110100";
    public static final String LDS = "110110";
    public static final String LDT = "111010";
    public static final String ADD = "011000";
    public static final String SUB = "011100";
    public static final String MUL = "100000";
    public static final String DIV = "100100";
    // AND mudou por conflito com MUL
    public static final String AND = "000001";
    public static final String OR = "100010";
    public static final String COMP = "101000";
    public static final String TIX = "101100";


    public Opcode() {
        op.add("ADDR");
        op.add("SUBR");
        op.add("MULR");
        op.add("DIVR");
        op.add("COMPR");
        op.add("SHIFTL");
        op.add("SHIFTR");
        op.add("RMO");
        op.add("CLEAR");
        op.add("TIXR");
        op.add("STA");
        op.add("STX");
        op.add("STL");
        op.add("STCH");
        op.add("STB");
        op.add("STS");
        op.add("STT");
        op.add("JEQ");
        op.add("JGT");
        op.add("JLT");
        op.add("J");
        op.add("RSUB");
        op.add("JSUB");
        op.add("LDA");
        op.add("LDX");
        op.add("LDL");
        op.add("LDCH");
        op.add("LDB");
        op.add("LDS");
        op.add("LDT");
        op.add("ADD");
        op.add("SUB");
        op.add("MUL");
        op.add("DIV");
        op.add("AND");
        op.add("OR");
        op.add("COMP");
        op.add("TIX");
        op.add("+STA");
        op.add("+STX");
        op.add("+STL");
        op.add("+STCH");
        op.add("+STB");
        op.add("+STS");
        op.add("+STT");
        op.add("+JEQ");
        op.add("+JGT");
        op.add("+JLT");
        op.add("+J");
        op.add("+RSUB");
        op.add("+JSUB");
        op.add("+LDA");
        op.add("+LDX");
        op.add("+LDL");
        op.add("+LDCH");
        op.add("+LDB");
        op.add("+LDS");
        op.add("+LDT");
        op.add("+ADD");
        op.add("+SUB");
        op.add("+MUL");
        op.add("+DIV");
        op.add("+AND");
        op.add("+OR");
        op.add("+COMP");
        op.add("+TIX");
    }

    public String getOp(int position) {
        return op.get(position);
    }
}

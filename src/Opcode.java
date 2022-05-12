public class Opcode {
    // load and store
    public static final String LDA = "000000";
    public static final String LDX = "000100";
    public static final String LDL = "001000";
    public static final String STA = "001100";
    public static final String STX = "010000";
    public static final String STL = "010100";

    //Operacoes aritmeticas
    public static final String ADD = "011000";
    public static final String SUB = "011100";
    public static final String MUL = "100000";
    public static final String DIV = "100100";
    public static final String COMP = "101000";
    public static final String TIX = "101100";

    // Pulos Condicionais
    public static final String JEQ = "110000";
    public static final String JGT = "110100";
    public static final String JLT = "111000";
    public static final String J = "111100";

    // Manipulacao de bit
    //++++++ Opcode maior que 6 bits +++++++
    public static final String AND = "1000000";
    public static final String OR = "1000100";

    // Pulo de Subrotina
    public static final String JSUB = "1001000";
    public static final String RSUB = "1001100";
    //Load e Store de inteiros
    public static final String LDCH = "1010000";
    public static final String STCH = "1010100";

    // Formato SICXE 3 e 4

    // load and store
    public static final String LDB = "1101000";
    public static final String LDS = "1101100";
    public static final String LDT = "1110100";
    public static final String STB = "1111000";
    public static final String STS = "1111100";
    public static final String STT = "10000100";

    // Formato 2 SIC/XE
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

}

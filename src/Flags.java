
public class Flags {

    // ni flags
    public static final String IMMEDIATE = "01"; //o operando vai ta escrito
    public static final String INDIRECT     = "10"; //passa um registrador e dentro dele vai ter o endereço de outro registrador
    public static final String DIRECT       = "11"; //instrução diz o registrador
    public static final String SIC          = "00";
    // xbpe
    // x - x = 1 (endereçamento indexado)
    // b - b = 1 e p = 0 (enderaçmento de base e deslocamento)
    // p - b = 0 e p = 1 (enderaçmento relativo do pc)
    // e - quantos bytes (e = 1 4 bytes, e = 0 3 bytes)

    // flags
    private String ni;         // ...ni: only lower two bits are used
    private String x;       // ...xbpe----: second four bits are used
    private String b;
    private String p;
    private String e;

    Conversion conversion = new Conversion();

    public Flags(String ni, String x, String b, String p, String e) {
        this.ni = ni;
        this.x = x;
        this.b = b;
        this.p = p;
        this.e = e;
    }

    // ************ ni

    public String get_ni() {
        return ni;
    }

    public void set_ni(String ni) {
        this.ni = ni;
    }

    public boolean is_ni(String what) {
        return ni == what;
    }

    public boolean isSic() {
        return is_ni(SIC);
    }

    public boolean isImmediate() {
        return is_ni(IMMEDIATE);
    }

    public boolean isIndirect() {
        return is_ni(INDIRECT);
    }

    public boolean isDirect() {
        return is_ni(DIRECT);
    }

    public String combineWithOpcode(String opcode) {
        return (opcode + ni + x + b + p + e);
    }

    // ************ xbpe

    public String get_x() {
        return x;
    }

    public String get_b() {
        return b;
    }

    public String get_p() {
        return p;
    }

    public String get_e() {
        return e;
    }

    public void set_x(String x) {
        this.x = x;
    }
    public void set_b(String b) {
        this.b = b;
    }
    public void set_p(String p) {
        this.p = p;
    }
    public void set_e(String e) {
        this.e = e;
    }

    public boolean isIndexed() {
        return x == "1";
    }

    public void setIndexed() {
        x = "1";
    }

    public boolean isBaseRelative() {
        return b == "1";
    }

    public void setBaseRelative() {
        b = "1";
    }

    public boolean isPCRelative() {
        return p == "1";
    }

    public void setPCRelative() {
        p = "1";
    }

    public boolean isRelative() {
        return isPCRelative() || isBaseRelative();
    }

    public boolean isAbsolute() {
        return (x == "0" && b == "0" && p == "0" && e == "0");
    }

    public boolean isExtended() {
        return e == "1";
    }

    public void setExtended() {
        e = "1";
    }

    // ************ operands

    /*public String operandSic(String a, String b) {
        // 15-bit address
        int aInt;
        aInt = conversion.stringBinaryToInt(a);
        aInt = aInt << 8;
        a = conversion.intToStringBinary(aInt);
        return a | b;
    }
    /*public int operandF3(int a, int b) {
        // 12-bit address
        return (a & 0x0F) << 8 | b;
    }
    public int operandF4(int a, int b, int c) {
        // 20-bit address
        return (a & 0x0F) << 16 | b << 8 | c;
    }
    public int operandPCRelative(int op) {
        // 12-bit signed integer
        return op >= 2048 ? op - 4096 : op;
    }
    public int minOperand() {
        if (isExtended()) return SICXE.MIN_ADDR;
        else return isImmediate() ? SICXE.MIN_SDISP : SICXE.MIN_DISP;
    }
    public int maxOperand() {
        if (isExtended()) return SICXE.MAX_ADDR;
        else return SICXE.MAX_DISP;
    }*/

}
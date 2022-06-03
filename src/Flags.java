
public class Flags {

    // ni flags
    public static final String IMMEDIATE    = "01"; //o operando vai ta escrito
    public static final String INDIRECT     = "10"; //passa um registrador e dentro dele vai ter o endereço de outro registrador
    public static final String DIRECT       = "11"; //instrução diz o registrador
    public static final String SIC          = "00";
    // xbpe
    // x - x = 1 (endereçamento indexado)
    // b - b = 1 e p = 0 (enderaçamento de base e deslocamento)
    // p - b = 0 e p = 1 (enderaçmento relativo do pc)
    // e - quantos bytes (e = 1 4 bytes, e = 0 3 bytes)

    // flags
    private String ni;         // ...ni: only lower two bits are used
    private String x;       // ...xbpe----: second four bits are used
    private String b;
    private String p;
    private String e;

    public Flags(String ni, String x, String b, String p, String e) {
        this.ni = ni;
        this.x = x;
        this.b = b;
        this.p = p;
        this.e = e;
    }

    // ************ ni

    public String getNi() {
        return ni;
    }

    public void setNi(String ni) {
        this.ni = ni;
    }

    public boolean isNi(String what) {
        return getNi().equals(what);
    }

    public boolean isSic() {
        return isNi(SIC);
    }

    public boolean isImmediate() {
        return isNi(IMMEDIATE);
    }

    public boolean isIndirect() {
        return isNi(INDIRECT);
    }

    public boolean isDirect() {
        return isNi(DIRECT);
    }

    public String combineWithOpcode(String opcode) {
        return (opcode + ni + x + b + p + e);
    }

    // ************ xbpe

    public String getX() {
        return x;
    }

    public String getB() {
        return b;
    }

    public String getP() {
        return p;
    }

    public String getE() {
        return e;
    }

    public void setX(String x) {
        this.x = x;
    }
    public void setB(String b) {
        this.b = b;
    }
    public void setP(String p) {
        this.p = p;
    }
    public void setE(String e) {
        this.e = e;
    }

    public boolean isIndexed() {
        return x.equals("1");
    }

    public void setIndexed() {
        x = "1";
    }

    public boolean isBaseRelative() {
        return b.equals("1");
    }

    public void setBaseRelative() {
        b = "1";
    }

    public boolean isPCRelative() {
        return p.equals("1");
    }

    public void setPCRelative() {
        p = "1";
    }

    public boolean isRelative() {
        return isPCRelative() || isBaseRelative();
    }

    public boolean isAbsolute() {
        return (x.equals("0") && b.equals("0") && p.equals("0") && e.equals("0"));
    }

    public boolean isExtended() {
        return e.equals("1");
    }

    public void setExtended() {
        e = "1";
    }

    // ************ operands

    public String operandSic(String a, String b) {
        a = a.substring(1, a.length());

        //retorna 15 bits
        return a + b;
    }
    public String operandF3(String a, String b) {
        a = a.substring(4,a.length());

        return a + b;
    }
    public String operandF4(String a, String b, String c) {

        a = a.substring(4, a.length() );

        return a + b + c;
    }
   /* public int operandPCRelative(int op) {
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
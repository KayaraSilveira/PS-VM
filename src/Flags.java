
public class Flags {

    // ni flags
    public static final int IMMEDIATE    = 01; //o operando vai ta escrito
    public static final int INDIRECT     = 10; //passa um registrador e dentro dele vai ter o endereço de outro registrador
    public static final int DIRECT       = 11; //instrução diz o registrador
    public static final int SIC          = 00;
    // xbpe
    // x - x = 1 (endereçamento indexado)
    // b - b = 1 e p = 0 (enderaçmento de base e deslocamento)
    // p - b = 0 e p = 1 (enderaçmento relativo do pc)
    // e - quantos bytes (e = 1 4 bytes, e = 0 3 bytes)

    // flags
    private int ni;         // ...ni: only lower two bits are used
    private int x;       // ...xbpe----: second four bits are used
    private int b;
    private int p;
    private int e;

    public Flags(int ni, int x, int b, int p, int e) {
        this.ni = ni;
        this.x = x;
        this.b = b;
        this.p = p;
        this.e = e;
    }

    // ************ ni

    public int get_ni() {
        return ni;
    }

    public void set_ni(int ni) {
        this.ni = ni;
    }

    public boolean is_ni(int what) {
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

    /*public byte combineWithOpcode(int opcode) {
        return (byte)(opcode & 0xFC | ni & MASK_NI);
    }*/

    // ************ xbpe

    public int get_x() {
        return x;
    }

    public int get_b() {
        return b;
    }

    public int get_p() {
        return p;
    }

    public int get_e() {
        return e;
    }

    public void set_x(int x) {
        this.x = x;
    }
    public void set_b(int b) {
        this.b = b;
    }
    public void set_p(int p) {
        this.p = p;
    }
    public void set_e(int e) {
        this.e = e;
    }

    /*public int get_x() {
        return (byte)(xbpe & 0x80);
    }*/

    public boolean isIndexed() {
        return x == 1;
    }

    public void setIndexed() {
        x = 1;
    }

    public boolean isBaseRelative() {
        return b == 1;
    }

    public void setBaseRelative() {
        b = 1;
    }

    public boolean isPCRelative() {
        return p == 1;
    }

    public void setPCRelative() {
        p = 1;
    }

    public boolean isRelative() {
        return isPCRelative() || isBaseRelative();
    }

    public boolean isAbsolute() {
        return (x == 0 && b == 0 && p == 0 && e == 0);
    }

    public boolean isExtended() {
        return e == 1;
    }

    public void setExtended() {
        e = 1;
    }

    // ************ operands

    /*public int operandSic(int a, int b) {
        // 15-bit address
        return (a & 0x7F) << 8 | b & 0xFF;
    }
    public int operandF3(int a, int b) {
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
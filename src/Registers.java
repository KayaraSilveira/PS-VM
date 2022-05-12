public class Registers {

    // SIC commands counter
    private String PC;
    // SIC 24-bit registers
    private String A, X, L;
    // SIC/XE 24-bit registers
    private String S, T, B;
    // SIC/XE 48-bit float register
    private String F;
    // condition code of status word register
    private String CC;

    Conversion conversion = new Conversion();

    // ***** getters/setters ********************
    // get   ... unsigned
    // get_s ... signed

    public String getPC() {
        return PC;
    }

    public void setPC(String val) {
        PC = val;
    }

    public void incPC() {
        int pcInt;
        pcInt = conversion.stringBinaryToInt(PC);
        if (++pcInt > 1023) {
            System.out.printf("PC overflow");
            pcInt = 0;
        }
        PC = conversion.intToStringBinary(pcInt);
    }

    public String getA() {
        return A;
    }

    public void setA(String val) {
        A = val;
    }

    public String getX() {
        return X;
    }

    public void setX(String val) {
        X = val;
    }

    public String getL() {
        return L;
    }

    public void setL(String val) {
        L = val;
    }

    public String getS() {
        return S;
    }

    public void setS(String val) {
        S = val;
    }

    public String getT() {
        return T;
    }

    public void setT(String val) {
        T = val;
    }

    public String getB() {
        return B;
    }

    public void setB(String val) {
        B = val;
    }

    public String getF() {
        return F;
    }

    public void setF(String val) {
        F = val;
    }

    public String getSW() {
        return CC;
    }

    public void setSW(String value) { //MEU DEUS MEU SENHOR ME AJUDA POR FAVOR
        int valueInt;
        valueInt = conversion.stringBinaryToInt(value);

        if (valueInt == -1) CC = "-1";
        else if (valueInt == 1) CC = "1";
        else CC = "0";
    }

    public void setSWAfterCompare(String compare) {
        CC = compare;
    }

    // ***** getter/setter by register index ****

    public static final int rA = 0;
    public static final int rX = 1;
    public static final int rL = 2;
    public static final int rB = 3;
    public static final int rS = 4;
    public static final int rT = 5;
    public static final int rF = 6;
    public static final int rPC = 8;
    public static final int rSW = 9;

    public String get(int idx) {
        switch (idx) {
            case rA: return getA();
            case rX: return getX();
            case rL: return getL();
            case rB: return getB();
            case rS: return getS();
            case rT: return getT();
            case rF: return getF();    // TODO
            case rPC: return getPC();       // TODO
            case rSW: return getSW();       // TODO
        }
        return "0";
    }

    public void set(int idx, String value) {
        switch (idx) {
            case rA: setA(value); break;
            case rX: setX(value); break;
            case rL: setL(value); break;
            case rB: setB(value); break;
            case rS: setS(value); break;
            case rT: setT(value); break;
            case rF: setF(value); break;
            case rPC: setPC(value); break;
            case rSW: setSW(value); break;
        }
    }

    // ************ other methods ????

    public void reset() {
        PC = "0";
        A = X = L = "0";
        B = S = T = "0";
        F = "0";
        CC = "0";
    }

    public Registers() {
        reset();
    }

}

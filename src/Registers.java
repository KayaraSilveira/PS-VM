public class Registers {
    public final Memory mem;

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
    

    // ***** getters/setters ********************
    // get   ... unsigned
    // get_s ... signed

    public int getPC() {
        return Conversion.stringBinaryToInt(PC);
    }

    public void setPC(String val) {
        PC = val;
    }

    public void incPC() {
        int pcInt;
        pcInt = Conversion.stringBinaryToInt(PC);
        if (++pcInt > 1023) {
            System.out.printf("PC overflow");
            pcInt = 0;
        }
        PC = Conversion.intToStringBinary(pcInt);
    }

    public int getA() {return Conversion.stringBinaryToInt(A);}

    public String setVal(int val){
            if(getPC() > val){// return val

               return Conversion.intToStringBinary(val);
           } else if (mem.getWord(Conversion.intToStringBinary(val)).contains("null")) {

                return Conversion.intToStringBinary(val);
           }else{
                return mem.getWord(Conversion.intToStringBinary(val));
           }
    }
    public void setA(String val) {
         A = val;
    }

    public int getX() {
        return Conversion.stringBinaryToInt(X);
    }

    public void setX(String val) {
        X = val;
    }

    public int getL() {
        return Conversion.stringBinaryToInt(L);
    }

    public void setL(String val) {
        L = val;
    }

    public int getS() {
        return Conversion.stringBinaryToInt(S);
    }

    public void setS(String val) {
        S = val;
    }

    public int getT() {
        return Conversion.stringBinaryToInt(T);
    }

    public void setT(String val) {
        T = val;
    }

    public int getB() {
        return Conversion.stringBinaryToInt(B);
    }

    public void setB(String val) {
        B = val;
    }

    public int getF() {
        return Conversion.stringBinaryToInt(F);
    }

    public void setF(String val) {
        F = val;
    }

    public int getSW() {
        return Conversion.stringBinaryToInt(CC);
    }

    public void setSW(String value) { //MEU DEUS MEU SENHOR ME AJUDA POR FAVOR
        int valueInt;
        valueInt = Conversion.stringBinaryToInt(value);

        if (valueInt == -1) CC = "-1";
        else if (valueInt == 1) CC = "1";
        else CC = "0";
    }
    public boolean isLower() {
        return Integer.parseInt(CC) < 0;
    }

    public boolean isEqual() {
        return Integer.parseInt(CC) == 0;
    }

    public boolean isGreater() {
        return Integer.parseInt(CC) > 0;
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

    public int get(int idx) {
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
        return 0;
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
    public Registers(Memory mem) {
        this.mem = mem;
        reset();
    }

}

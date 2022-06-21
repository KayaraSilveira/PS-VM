public class CPU {
    public final Registers reg;
    public final Memory mem;
    public final Carregador carre;
    public CPU() {
        this.mem = new Memory();
        this.reg = new Registers(this.mem);
        this.carre = new Carregador(this.mem);
        carre.carregaMemoria();
    }

    public void imprimeMem() {
        for(int i = 0; i < 1024; i++) {
            System.out.println(i + ": " + mem.getByte(Conversion.intToStringBinary(i)));
        }
    }
    private boolean F2(String opcode, String operand) {
        //System.out.println("Entrou F2: "+opcode);
        //System.out.println("Opernado F2: " + operand);

        // Formato 2: Op op1, op2
        int op1 = Conversion.stringBinaryToInt(operand.substring(0, operand.length()/2));
        int op2 = Conversion.stringBinaryToInt(operand.substring(operand.length()/2));

        switch (opcode) {
            case Opcode.ADDR:   reg.set(op2, Conversion.intToStringBinary(reg.get(op2) + reg.get(op1))); break;
            case Opcode.SUBR:   reg.set(op2, Conversion.intToStringBinary(reg.get(op2) - reg.get(op1))); break;
            case Opcode.MULR:	reg.set(op2, Conversion.intToStringBinary(reg.get(op2) * reg.get(op1))); break;
            case Opcode.DIVR:	reg.set(op2, Conversion.intToStringBinary(reg.get(op2) / reg.get(op1))); break;
            case Opcode.COMPR:  reg.setSWAfterCompare(Conversion.intToStringBinary(reg.get(op1) - reg.get(op2))); break;
            //++++++++++++
            case Opcode.SHIFTL:
                reg.set(op1, Conversion.ShiftL(Conversion.intToStringBinary(reg.get(op1)),op2+1)); break; //| Conversion.ShiftR(Conversion.intToStringBinary(reg.get(op1)),24-op2-1)));break;
            case Opcode.SHIFTR:	reg.set(op1, Conversion.ShiftR(Conversion.intToStringBinary(reg.get(op1)),op2+1)); break;
            //+++++++++++++
            case Opcode.RMO:	reg.set(op2, Conversion.intToStringBinary(reg.get(op1))); break;
            case Opcode.CLEAR:
                //System.out.println("X ANTES: "+ reg.getX());
                reg.set(op1, "0");
                //System.out.println("X Depois: "+ reg.getX());
                break;
            case Opcode.TIXR:	reg.setX(Conversion.intToStringBinary(reg.getX()+1));
                reg.setSWAfterCompare(Conversion.intToStringBinary(reg.getX() - reg.get(op1)));
                break;
            default: return false;
        }
        return true;
    }
    private String storeAddr(Flags flags, String addr) {
        System.out.println("ADDR:" + addr);
        if(flags.isIndirect()){
            return mem.getWord(addr);
        }else{
            return addr;
        }
    }
    private String loadWord(Flags flags, String operand) {
        if (flags.isImmediate()) return operand;
        System.out.println("Operando Entrada: "+operand);

        operand = mem.getWord(operand);
        System.out.println("Operando meio: "+operand);

        if (flags.isIndirect()) operand = mem.getWord(operand);
        System.out.println("OPERANDO Saida: "+operand);

        System.out.println("Valor do 30: "+operand);
        return operand;
    }
    private boolean F3F4(String opcode, Flags flags, String operand)  {
        // Formats: SIC, F3, F4
        switch (opcode.substring(0, opcode.length() - 2)) {
            // ***** immediate addressing not possible *****
            // stores
            case Opcode.STA:    mem.setWord(storeAddr(flags, operand), Conversion.intToStringBinary(reg.getA())); break;
            case Opcode.STX:
                mem.setWord(storeAddr(flags, operand), Conversion.intToStringBinary(reg.getX()));
                break;
            case Opcode.STL:	mem.setWord(storeAddr(flags, operand), Conversion.intToStringBinary(reg.getL())); break;
            case Opcode.STCH:	mem.setByte(storeAddr(flags, operand), Conversion.intToStringBinary(reg.getA())); break;
            case Opcode.STB:	mem.setWord(storeAddr(flags, operand), Conversion.intToStringBinary(reg.getB())); break;
            case Opcode.STS:	mem.setWord(storeAddr(flags, operand), Conversion.intToStringBinary(reg.getS()));	break;
            case Opcode.STT:	mem.setWord(storeAddr(flags, operand), Conversion.intToStringBinary(reg.getT())); break;
            // jumps
            case Opcode.JEQ:
                System.out.println("estou no JEQ");
                if (reg.isEqual()){
                    System.out.println("isqual: "+reg.isEqual());
                    reg.setPC(storeAddr(flags, operand)); break;}
            case Opcode.JGT:	if (reg.isGreater()) reg.setPC(storeAddr(flags, operand)); break;
            case Opcode.JLT:	if (reg.isLower()) reg.setPC(storeAddr(flags, operand)); break;
            case Opcode.J:		reg.setPC(storeAddr(flags, operand)); break;
            case Opcode.RSUB:	reg.setPC(Conversion.intToStringBinary(reg.getL()));  break;
            case Opcode.JSUB:	reg.setL(Conversion.intToStringBinary(reg.getPC())); reg.setPC(storeAddr(flags, operand)); break;
            // ***** immediate addressing possible *****
            // loads
            case Opcode.LDA:    reg.setA(reg.setVal(Conversion.stringBinaryToInt(operand), flags)); break;
            case Opcode.LDX:
                System.out.println("X: "+reg.getX());
                reg.setX(reg.setVal(Conversion.stringBinaryToInt(operand), flags));
                System.out.println("X: "+reg.getX());break;
            case Opcode.LDL:	reg.setL(reg.setVal(Conversion.stringBinaryToInt(operand), flags)); break;
            //case Opcode.LDCH:	reg.setALo(loadByte(flags, operand)); break;
            case Opcode.LDB:    reg.setB(reg.setVal(Conversion.stringBinaryToInt(operand), flags)); break;
            case Opcode.LDS:    reg.setS(reg.setVal(Conversion.stringBinaryToInt(operand), flags)); break;
            case Opcode.LDT:	reg.setT(reg.setVal(Conversion.stringBinaryToInt(operand), flags)); break;
            // arithmetic
            case Opcode.ADD:    reg.setA(Conversion.intToStringBinary (reg.getA()  + Conversion.stringBinaryToInt(reg.setVal(Conversion.stringBinaryToInt(operand), flags)))); break;
            case Opcode.SUB:    reg.setA(Conversion.intToStringBinary(reg.getA() - Conversion.stringBinaryToInt(reg.setVal(Conversion.stringBinaryToInt(operand), flags)))); break;
            case Opcode.MUL:	reg.setA(Conversion.intToStringBinary(reg.getA() * Conversion.stringBinaryToInt(reg.setVal(Conversion.stringBinaryToInt(operand), flags)))); break;
            case Opcode.DIV:	reg.setA(Conversion.intToStringBinary(reg.getA() / Conversion.stringBinaryToInt(reg.setVal(Conversion.stringBinaryToInt(operand), flags)))); break;
            case Opcode.AND:	reg.setA(Conversion.intToStringBinary(reg.getA() & Conversion.stringBinaryToInt(reg.setVal(Conversion.stringBinaryToInt(operand), flags)))); break;
            case Opcode.OR:		reg.setA(Conversion.intToStringBinary(reg.getA() | Conversion.stringBinaryToInt(reg.setVal(Conversion.stringBinaryToInt(operand), flags)))); break;
            case Opcode.COMP:	reg.setSWAfterCompare(Conversion.intToStringBinary(reg.getA() - Conversion.stringBinaryToInt(loadWord(flags, operand)))); break;
            case Opcode.TIX:    reg.setX(Conversion.intToStringBinary((reg.getX() + 1)));
                int sub = Conversion.stringBinaryToInt(loadWord(flags, operand));
                reg.setSWAfterCompare(Conversion.intToStringBinary(reg.getX() - sub)); break;
            default: return false;
        }
        return true;
    }

    public String fetch() {
        String b = mem.getByte(Conversion.intToStringBinary(reg.getPC()));
        reg.incPC();
        return b;
    }
    private void notvalid() {
        System.out.println("Invalid addressing.");
    }
    public void execute(){
        // Carrega 1 byte da memoria
        String opcode = fetch();

        String op = fetch();
        System.out.println("PC: "+reg.getPC());
        System.out.println("OPCODE: "+opcode);
        System.out.println("Memoria: "+mem.getByte("1100"));
        // Formato 2
        if (F2(opcode, op)) return;
        // SIC, F3,  F4

        Flags flags = new Flags(opcode.substring(opcode.length() - 2), op.substring(0,1), op.substring(1,2), op.substring(2,3), op.substring(3,4));

        String operand;
        if (flags.isSic()) {
            operand = flags.operandSic(op, fetch());
            // Verifica F4
        } else if (flags.isExtended()) {
            operand = flags.operandF4(op, fetch(), fetch());
            if (flags.isRelative()) notvalid();
        } else {

            operand = flags.operandF3(op, fetch());
            /*if (flags.isPCRelative())
                operand = Conversion.intToStringBinary(flags.operandPCRelative(Conversion.stringBinaryToInt(operand)) + reg.getPC());
            */
            if (flags.isBaseRelative()){
                int a;
                a = Conversion.stringBinaryToInt(operand);
                a += reg.getB();
                operand = Conversion.intToStringBinary(a);
            } else if (!flags.isAbsolute()) notvalid();  // both PC and base at the same time
        }
        // SIC, F3, F4 -- all support indexed addressing, but only when simple TA calculation used
        if (flags.isIndexed())
            if (flags.isDirect()) {
                int n = Conversion.stringBinaryToInt(operand);
                n += reg.getX();
                operand = Conversion.intToStringBinary(n);
            }
            else notvalid();
        // try to execute
        if (F3F4(opcode, flags, operand)) return;
        System.out.println("Opcode invalido " + opcode);
    }
}

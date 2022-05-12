public class CPU {
    public final Registers reg;
    public final Memory mem;
    public CPU() {
        this.reg = new Registers();
        this.mem = new Memory();
    }
    private boolean F2(String opcode, String operand) {
        //
        //  Formato 2: Op op1, op2
        int op1 = Conversion.stringBinaryToInt(operand.substring(0, operand.length()/2));
        int op2 = Conversion.stringBinaryToInt(operand.substring(operand.length()/2));


        switch (opcode) {
            case Opcode.ADDR:	reg.set(op2, Conversion.intToStringBinary(reg.get(op2) + reg.get(op1))); break;
            case Opcode.SUBR:	reg.set(op2, Conversion.intToStringBinary(reg.get(op2) - reg.get(op1))); break;
            case Opcode.MULR:	reg.set(op2, Conversion.intToStringBinary(reg.get(op2) * reg.get(op1))); break;
            case Opcode.DIVR:	reg.set(op2, Conversion.intToStringBinary(reg.gets(op2) / reg.gets(op1))); break;
            case Opcode.COMPR: reg.setSWAfterCompare(Conversion.intToStringBinary(reg.gets(op1) - reg.gets(op2))); break;
            //++++++++++++
            case Opcode.SHIFTL:

                reg.set(op1, Conversion.ShiftL(Conversion.intToStringBinary(reg.get(op1)),op2+1));break; //| Conversion.ShiftR(Conversion.intToStringBinary(reg.get(op1)),24-op2-1)));break;
                case Opcode.SHIFTR:	reg.set(op1, Conversion.ShiftR(Conversion.intToStringBinary(reg.gets(op1)),op2+1)); break;
            //+++++++++++++
            case Opcode.RMO:	reg.set(op2, Conversion.intToStringBinary(reg.get(op1))); break;
            case Opcode.CLEAR:	reg.set(op1, "0"); break;
            case Opcode.TIXR:	reg.setX(Conversion.intToStringBinary(reg.getX()+1));
                reg.setSWAfterCompare(Conversion.intToStringBinary(reg.getXs() - reg.gets(op1)));
                break;
            default: return false;
        }
        return true;
    }
    private String storeAddr(Flags flags, String addr) {
        return flags.isIndirect() ? mem.getWord(addr) : addr;
    }
    private boolean F3F4(int opcode, Flags flags, String operand)  {
        // Formats: SIC, F3, F4
        switch (opcode) {
            // ***** immediate addressing not possible *****
            // stores
            case Opcode.STA:	mem.setWord(storeAddr(flags, operand), Conversion.intToStringBinary(reg.getA())); break;
            case Opcode.STX:	mem.setWord(storeAddr(flags, operand), Conversion.intToStringBinary(reg.getX())); break;
            case Opcode.STL:	mem.setWord(storeAddr(flags, operand), Conversion.intToStringBinary(reg.getL())); break;
            case Opcode.STCH:	mem.setByte(storeAddr(flags, operand), Conversion.intToStringBinary(reg.getA())); break;
            case Opcode.STB:	mem.setWord(storeAddr(flags, operand), Conversion.intToStringBinary(reg.getB())); break;
            case Opcode.STS:	mem.setWord(storeAddr(flags, operand), Conversion.intToStringBinary(reg.getS()));	break;
            case Opcode.STT:	mem.setWord(storeAddr(flags, operand), Conversion.intToStringBinary(reg.getT())); break;
            // jumps
            case Opcode.JEQ:	if (reg.isEqual()) reg.setPC(storeAddr(flags, operand)); break;
            case Opcode.JGT:	if (reg.isGreater()) reg.setPC(storeAddr(flags, operand)); break;
            case Opcode.JLT:	if (reg.isLower()) reg.setPC(storeAddr(flags, operand)); break;
            case Opcode.J:		reg.setPC(storeAddr(flags, operand)); break;
            case Opcode.RSUB:	reg.setPC(reg.getL()); popJSUB(); break;
            case Opcode.JSUB:	reg.setL(reg.getPC()); pushJSUB(); reg.setPC(storeAddr(flags, operand)); break;
            // ***** immediate addressing possible *****
            // loads
            case Opcode.LDA:	reg.setA(loadWord(flags, operand)); break;
            case Opcode.LDX:	reg.setX(loadWord(flags, operand)); break;
            case Opcode.LDL:	reg.setL(loadWord(flags, operand)); break;
            case Opcode.LDCH:	reg.setALo(loadByte(flags, operand)); break;
            case Opcode.LDB:	reg.setB(loadWord(flags, operand)); break;
            case Opcode.LDS:	reg.setS(loadWord(flags, operand)); break;
            case Opcode.LDT:	reg.setT(loadWord(flags, operand)); break;
            // arithmetic
            case Opcode.ADD:	reg.setA(reg.getA() + loadWord(flags, operand)); break;
            case Opcode.SUB:	reg.setA(reg.getA() - loadWord(flags, operand)); break;
            case Opcode.MUL:	reg.setA(reg.getA() * loadWord(flags, operand)); break;
            case Opcode.DIV:	reg.setA(reg.getAs() / SICXE.swordToInt(loadWord(flags, operand))); break;
            case Opcode.AND:	reg.setA(reg.getA() & loadWord(flags, operand)); break;
            case Opcode.OR:		reg.setA(reg.getA() | loadWord(flags, operand)); break;
            case Opcode.COMP:	reg.setSWAfterCompare(reg.getAs() - SICXE.swordToInt(loadWord(flags, operand))); break;
            case Opcode.TIX:	reg.setX(reg.getX() + 1);
                reg.setSWAfterCompare(reg.getXs() - SICXE.swordToInt(loadWord(flags, operand))); break;
            default: return false;
        }
        return true;
    }
}



//import flags
//import memory
//import reg
//import opcode

public class CPU {
    //public final reg reg;
    //public final Memory memory;
    public CPU(){
        //this.reg = new reg();
        //this.memory = new Memory(MAX_ADDRESS+1);
    }
    private boolean F2(String opcode, String operand) {
        // Format 2: OP o1, o2 - two 4-bit operands
        String o1 = operand;
        String o2 = operand;
        switch (opcode) {
            case Opcode.ADDR:	reg.set(o2, reg.get(o2) + reg.get(o1)); break;
            case Opcode.SUBR:	reg.set(o2, reg.get(o2) - reg.get(o1)); break;
            case Opcode.MULR:	reg.set(o2, reg.get(o2) * reg.get(o1)); break;
            case Opcode.DIVR:	reg.set(o2, reg.gets(o2) / reg.gets(o1)); break;
            case Opcode.COMPR:	reg.setSWAfterCompare(reg.gets(o1) - reg.gets(o2)); break;
            case Opcode.SHIFTL:	reg.set(o1, reg.get(o1) << (o2 + 1) | reg.get(o1) >> (24 - o2 - 1)); break;
            case Opcode.SHIFTR:	reg.set(o1, reg.gets(o1) >> (o2 + 1)); break;
            case Opcode.RMO:	reg.set(o2, reg.get(o1)); break;
            case Opcode.CLEAR:	reg.set(o1, 0); break;
            case Opcode.TIXR:	reg.setX(reg.getX()+1);
                reg.setSWAfterCompare(reg.getXs() - reg.gets(o1));
                break;
            default: return false;
        }
        return true;
    }
    private boolean F3F4(int opcode, Flags flags, int operand) throws DataBreakpointException {
        // Formats: SIC, F3, F4
        switch (opcode) {
            // ***** immediate addressing not possible *****
            // stores
            case Opcode.STA:	memory.setWord(storeAddr(flags, operand), reg.getA()); break;
            case Opcode.STX:	memory.setWord(storeAddr(flags, operand), reg.getX()); break;
            case Opcode.STL:	memory.setWord(storeAddr(flags, operand), reg.getL()); break;
            case Opcode.STCH:	memory.setByte(storeAddr(flags, operand), reg.getA()); break;
            case Opcode.STB:	memory.setWord(storeAddr(flags, operand), reg.getB()); break;
            case Opcode.STS:	memory.setWord(storeAddr(flags, operand), reg.getS());	break;
            case Opcode.STF:	memory.setFloat(storeAddr(flags, operand), reg.getF()); break;
            case Opcode.STT:	memory.setWord(storeAddr(flags, operand), reg.getT()); break;
            case Opcode.STSW:	memory.setWord(storeAddr(flags, operand), reg.getSW()); break;
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
            case Opcode.LDF:	reg.setF(loadFloat(flags, operand)); break;
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
            // input/output
            case Opcode.RD:		reg.setALo(devices.read(loadByte(flags, operand)));  break;
            case Opcode.WD:		devices.write(loadByte(flags, operand), reg.getALo()); break;
            case Opcode.TD:		reg.setSWAfterCompare(devices.test(loadByte(flags, operand)) ? -1 : 0); break;
            // floating point arithmetic
            case Opcode.ADDF:	reg.setF(reg.getF() + loadFloat(flags, operand)); break;
            case Opcode.SUBF:	reg.setF(reg.getF() - loadFloat(flags, operand)); break;
            case Opcode.MULF:	reg.setF(reg.getF() * loadFloat(flags, operand)); break;
            case Opcode.DIVF:	reg.setF(reg.getF() / loadFloat(flags, operand)); break;
            case Opcode.COMPF:  double sub = reg.getF() - loadFloat(flags, operand);
                reg.setSWAfterCompare(sub > 0 ? 1 : (sub < 0 ? -1 : 0));
                break;
            // others
            case Opcode.LPS:	notImplemented("LPS"); break;
            case Opcode.STI:	notImplemented("STI"); break;
            case Opcode.SSK:	notImplemented("SSK"); break;
            default: return false;
        }
        return true;
}


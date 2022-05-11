import java.util.Arrays;

public class Memory{

    public final byte[] memoria;

    public Memory(){
        this.memoria = new byte[1024];
    }

    public void reseta(){
        Arrays.fill(memoria, (byte)0);
    }

    public boolean confereEndereco(int endereco){
        boolean verifica = endereco < 0 || endereco >= memoria.length;
        if(verifica){
            System.out.println("Endereço de memória é inválido.");
        }

        return verifica;
    }

    public int getByteRaw(int address) {
        if (checkAddress(address)) {
            return 0;
        }
        return ((int)memory[address]);
    }

    public void setByteRaw(int address, int value) {
        if (checkAddress(address)) {
            return;
        }
        memory[address] = (byte)(value);
    }


    /*public int getWordRaw(int address) {
        return getByteRaw(address + 2) | getByteRaw(address + 1) << 8 | getByteRaw(address) << 16;
    }

    public void setWordRaw(int address, int value) {
        setByteRaw(address, value >> 16);
        setByteRaw(address + 1, value >> 8);
        setByteRaw(address + 2, value);
    }




    public static long floatToBits(double value) {
        return Double.doubleToLongBits(value) >> 16;
    }

    public static double bitsToFloat(long bits) {
        return Double.longBitsToDouble(bits << 16);
    }

    public double getFloatRaw(int address) {
        long bits = (long)getByteRaw(address)  << 40 | (long)getByteRaw(address+1) << 32 |
                (long)getByteRaw(address+2) << 24 | getByteRaw(address+3) << 16 |
                getByteRaw(address+4) << 8  | getByteRaw(address+5);
        return SICXE.bitsToFloat(bits);
    }

    public void setFloatRaw(int address, double value) {
        long bits = SICXE.floatToBits(value);
        setByteRaw(address, (int)(bits >> 40));
        setByteRaw(address + 1, (int)(bits >> 32));
        setByteRaw(address + 2, (int)(bits >> 24));
        setByteRaw(address + 3, (int)(bits >> 16));
        setByteRaw(address + 4, (int)(bits >> 8));
        setByteRaw(address + 5, (int)(bits));
    }*/

}

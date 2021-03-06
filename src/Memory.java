import java.util.Arrays;

public class Memory{

    public final String[][] memory;
    private int instructionCount = 0;

    public void incInstructionCount() {
        this.instructionCount++;
    }
    public int getInstructionCount() {
        return this.instructionCount;
    }


    public Memory(){
        this.memory = new String[1024][8];
    }

    public void reset(){
        Arrays.fill(memory, "0");
    }

    public boolean checkAddress(String address){
        int addressInt;
        addressInt = Conversion.stringBinaryToInt(address);
        boolean check = addressInt < 0 || addressInt >= 1024;
        if(check){
            System.out.println("Endereço de memória é inválido.");
        }
        return check;
    }

    public String getByte(String address) {
        int addressInt, i;
        String byteReturn = "";
        checkAddress(address);
        addressInt = Conversion.stringBinaryToInt(address);
        for(i = 0; i < 8; i++) {
            byteReturn =  byteReturn + memory[addressInt][i];
        }
        return byteReturn;
    }

    public void setByte(String address, String value) {
        if (checkAddress(address)) {
            System.out.println("endereço inválido!");
            return;
        }
        int addressInt, i;
        addressInt = Conversion.stringBinaryToInt(address);
        //System.out.println(addressInt);
        //System.out.println(value);
        for(i = 0; i < 8; i++) {
            memory[addressInt][i] =  value.substring(i, i + 1);
        }
    }
    public String getWord(String address) {
        int addressInt, i,j;
        String byteReturn = "";
        addressInt = Conversion.stringBinaryToInt(address);
        for (j = 0; j < 3; j++) {
            for (i = 0; i < 8; i++) {
                byteReturn = byteReturn + memory[addressInt + j][i];
            }
        }
        return byteReturn;
    }

    public void setWord(String address, String value) {
        value = Conversion.padLeftZeros(value, 24);
        /*
        System.out.println(value);
        System.out.println(address);
        System.out.println(Conversion.intToStringBinary(Conversion.stringBinaryToInt(address)+1));
        System.out.println(Conversion.intToStringBinary(Conversion.stringBinaryToInt(address)+2));
        System.out.println(value.substring(0, 8));
        System.out.println(value.substring(8, 16));
        System.out.println(value.substring(16, 24));*/

        //System.out.println(address);
        //System.out.println(value);
        //System.out.println(value.length());

        if (value.length() == 24) {
            setByte(address, value.substring(0, 8));
            setByte(Conversion.intToStringBinary(Conversion.stringBinaryToInt(address)+1), value.substring(8, 16));
            setByte(Conversion.intToStringBinary(Conversion.stringBinaryToInt(address)+2), value.substring(16, 24));
        }

        if (value.length() == 16) {
            setByte(address, value.substring(0, 8));
            setByte(Conversion.intToStringBinary(Conversion.stringBinaryToInt(address)+1), value.substring(8, 16));
        }
    }

}
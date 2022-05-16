import java.util.Arrays;

public class Memory{

    public final String[][] memoria;


    public Memory(){
        this.memoria = new String[1024][8];
    }

    public void reset(){
        Arrays.fill(memoria, "0");
    }

    public boolean confereEndereco(String endereco){
        int enderecoInt;
        enderecoInt = Conversion.stringBinaryToInt(endereco);
        boolean verifica = enderecoInt < 0 || enderecoInt >= 1024;
        if(verifica){
            System.out.println("Endereço de memória é inválido.");
        }
        return verifica;
    }

    public String getByte(String address) {
        int addressInt, i;
        String byteReturn = "";
        confereEndereco(address);
        addressInt = Conversion.stringBinaryToInt(address);
        for(i = 0; i < 8; i++) {
            byteReturn =  byteReturn + memoria[addressInt][i];
        }
        return byteReturn;
    }

    public void setByte(String address, String value) {
        if (confereEndereco(address)) {
            return;
        }
        int addressInt, i;
        addressInt = Conversion.stringBinaryToInt(address);
        for(i = 0; i < 8; i++) {
            memoria[addressInt][i] =  value.substring(0 + i, 0 + i + 1);
        }
    }
    public String getWord(String address) {
        int addressInt, i,j;
        String byteReturn = "";
        addressInt = Conversion.stringBinaryToInt(address);
        for (j=0; j<3; j++) {
            for (i = 0; i < 8; i++) {
                byteReturn = byteReturn + memoria[addressInt][i];
            }
        }
        return byteReturn;
    }

    public void setWord(String address, String value){

        setByte(address, Conversion.ShiftR(value, 16));
        setByte(Conversion.intToStringBinary(Conversion.stringBinaryToInt(address)+1), Conversion.ShiftR(value, 8));
        setByte(Conversion.intToStringBinary(Conversion.stringBinaryToInt(address)+2), value);
    }



}

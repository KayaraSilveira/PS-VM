import java.util.Arrays;

public class Memory{

    public final String[][] memoria;

    Conversion conversion = new Conversion();

    public Memory(){
        this.memoria = new String[1024][8];
    }

    public void reseta(){
        Arrays.fill(memoria, "0");
    }

    public boolean confereEndereco(String endereco){
        int enderecoInt;
        enderecoInt = conversion.stringBinaryToInt(endereco);
        boolean verifica = enderecoInt < 0 || enderecoInt >= 1024;
        if(verifica){
            System.out.println("Endereço de memória é inválido.");
        }
        return verifica;
    }

    public String getByteRaw(String address) {
        int addressInt, i;
        String byteReturn = "";
        confereEndereco(address);
        addressInt = conversion.stringBinaryToInt(address);
        for(i = 0; i < 8; i++) {
            byteReturn =  byteReturn + memoria[addressInt][i];
        }
        return byteReturn;
    }

    public void setByteRaw(String address, String value) {
        if (confereEndereco(address)) {
            return;
        }
        int addressInt, i;
        addressInt = conversion.stringBinaryToInt(address);
        for(i = 0; i < 8; i++) {
            memoria[addressInt][i] =  value.substring(0 + i, 0 + i + 1);
        }
    }


}

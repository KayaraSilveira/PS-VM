import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class Carregador {

    public final Memory mem;
    public int positionA = 512;

    Opcode opList = new Opcode();
    public ArrayList<String> montador = new ArrayList<>();
    public Carregador(Memory mem) {
        this.mem = mem;
        try {
            readTable("src/inputMontador.txt");
        } catch (FileNotFoundException e1) {
            System.out.println(e1);
            return;
        }
    }

    public void readTable(String filename) throws FileNotFoundException {
        BufferedReader r = new BufferedReader(new FileReader(filename));
        try {
            String line = r.readLine();
            while (line != null) {
                montador.add(line);
                line = r.readLine();
            }
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public void carregaMemoria() {
        String[] line;
        for(int i = 0; i < montador.size(); i++) {
            int flag = 0;
            line = montador.get(i).split(" ");
            if(line[0].equals("START")) {
                flag++;
            }
            else {
                for (int j = 0; j < opList.op.size(); j++) {
                    if(line[0].equals(opList.getOp(j))) {
                        flag++;
                    }
                }
            }
            if(flag == 0) {
                if(line[1].equals("WORD")) {
                    float sizeAux;
                    int size;
                    String binary;
                    binary = Conversion.intToStringBinary(Integer.parseInt(line[2]));
                    sizeAux = binary.length()/8;
                    size = (int)sizeAux;
                    if(((sizeAux % 8) != 0) || (size == 0)) {
                        size++;
                    }
                    binary = Conversion.padLeftZeros(binary, 8);
                    mem.setByte(Conversion.intToStringBinary(positionA), binary);
                    positionA += size;
                } else if(line[1].equals("RESB")) {
                    positionA += Integer.parseInt(line[2]);
                } else {
                    positionA += 3 * Integer.parseInt(line[2]);
                }
            }
        }
    }


}

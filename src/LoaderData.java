import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class LoaderData {

    public final Memory mem;
    public int positionA = 512;

    Opcode opList = new Opcode();
    public ArrayList<String> montador = new ArrayList<>();
    public LoaderData(Memory mem) {
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

    public void load() {
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
                    binary = Conversion.padLeftZeros(binary, 3 * 8);
                    mem.setWord(Conversion.intToStringBinary(positionA), binary);
                    positionA += 3;
                } else if(line[1].equals("RESB")) {
                    positionA += 3 * Integer.parseInt(line[2]);
                } else {
                    positionA += 3 * Integer.parseInt(line[2]);
                }
            }
        }
    }




}

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

public class Montador {
    private int positionA = 512;
    private HashMap<String,Integer> tableSymbol = new HashMap<>();

    Opcode opList = new Opcode();
    public ArrayList<String> montador = new ArrayList<>();
    public ArrayList<String> montadorFinal = new ArrayList<>();

    public boolean loadMontador(String filename) throws FileNotFoundException {
        BufferedReader r = new BufferedReader(new FileReader(filename));
        try {
            String line = r.readLine();
            while (line != null) {
                montador.add(line);
                line = r.readLine();
            }
        } catch (IOException e) {
            System.out.println(e);
            return false;
        }
        return true;
    }

    public void verifyLabel() {
        String[] line;
        for(int i = 0; i < montador.size(); i++) {
            int flag = 0;
            line = getMontador(i).split(" ");
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
                tableSymbol.put(line[0], positionA);
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
                    positionA += 3 * size;
                } else if(line[1].equals("RESB")) {
                    positionA += 3 * Integer.parseInt(line[2]);
                } else {
                    positionA += 3 * Integer.parseInt(line[2]);
                }
            }
        }
    }

    public void opToBinary() {
        String[] line;
        int j = -1;
        for(int i = 0; i < montador.size(); i++) {
            line = getMontador(i).split(" ");
            j++;
            /*COLOCANDO OPCODES*/
            switch (line[0]) {
                case "ADDR":
                    montadorFinal.add(Opcode.ADDR);
                    nixbpeToBinary(j, i, false);
                    break;
                case "SUBR":
                    montadorFinal.add(Opcode.SUBR);
                    nixbpeToBinary(j, i, false);
                    break;
                case "MULR":
                    montadorFinal.add(Opcode.MULR);
                    nixbpeToBinary(j, i, false);
                    break;
                case "DIVR":
                    montadorFinal.add(Opcode.DIVR);
                    nixbpeToBinary(j, i, false);
                    break;
                case "COMPR":
                    montadorFinal.add(Opcode.COMPR);
                    nixbpeToBinary(j, i, false);
                    break;
                case "SHIFTL":
                    montadorFinal.add(Opcode.SHIFTL);
                    nixbpeToBinary(j, i, false);
                    break;
                case "SHIFTR":
                    montadorFinal.add(Opcode.SHIFTR);
                    nixbpeToBinary(j, i, false);
                    break;
                case "RMO":
                    montadorFinal.add(Opcode.RMO);
                    nixbpeToBinary(j, i, false);
                    break;
                case "CLEAR":
                    montadorFinal.add(Opcode.CLEAR);
                    nixbpeToBinary(j, i, false);
                    break;
                case "TIXR":
                    montadorFinal.add(Opcode.TIXR);
                    nixbpeToBinary(j, i, false);
                    break;
                case "STA":
                    montadorFinal.add(Opcode.STA);
                    nixbpeToBinary(j, i, false);
                    break;
                case "STX":
                    montadorFinal.add(Opcode.STX);
                    nixbpeToBinary(j, i, false);
                    break;
                case "STL":
                    montadorFinal.add(Opcode.STL);
                    nixbpeToBinary(j, i, false);
                    break;
                case "STCH":
                    montadorFinal.add(Opcode.STCH);
                    nixbpeToBinary(j, i, false);
                    break;
                case "STB":
                    montadorFinal.add(Opcode.STB);
                    nixbpeToBinary(j, i, false);
                    break;
                case "STS":
                    montadorFinal.add(Opcode.STS);
                    nixbpeToBinary(j, i, false);
                    break;
                case "STT":
                    montadorFinal.add(Opcode.STT);
                    nixbpeToBinary(j, i, false);
                    break;
                case "JEQ":
                    montadorFinal.add(Opcode.JEQ);
                    nixbpeToBinary(j, i, false);
                    break;
                case "JGT":
                    montadorFinal.add(Opcode.JGT);
                    nixbpeToBinary(j, i, false);
                    break;
                case "JLT":
                    montadorFinal.add(Opcode.JLT);
                    nixbpeToBinary(j, i, false);
                    break;
                case "J":
                    montadorFinal.add(Opcode.J);
                    nixbpeToBinary(j, i, false);
                    break;
                case "RSUB":
                    montadorFinal.add(Opcode.RSUB);
                    nixbpeToBinary(j, i, false);
                    break;
                case "JSUB":
                    montadorFinal.add(Opcode.JSUB);
                    nixbpeToBinary(j, i, false);
                    break;
                case "LDA":
                    montadorFinal.add(Opcode.LDA);
                    nixbpeToBinary(j, i, false);
                    break;
                case "LDX":
                    montadorFinal.add(Opcode.LDX);
                    nixbpeToBinary(j, i, false);
                    break;
                case "LDL":
                    montadorFinal.add(Opcode.LDL);
                    nixbpeToBinary(j, i, false);
                    break;
                case "LDCH":
                    montadorFinal.add(Opcode.LDCH);
                    nixbpeToBinary(j, i, false);
                    break;
                case "LDB":
                    montadorFinal.add(Opcode.LDB);
                    nixbpeToBinary(j, i, false);
                    break;
                case "LDS":
                    montadorFinal.add(Opcode.LDS);
                    nixbpeToBinary(j, i, false);
                    break;
                case "LDT":
                    montadorFinal.add(Opcode.LDT);
                    nixbpeToBinary(j, i, false);
                    break;
                case "ADD":
                    montadorFinal.add(Opcode.ADD);
                    nixbpeToBinary(j, i, false);
                    break;
                case "SUB":
                    montadorFinal.add(Opcode.SUB);
                    nixbpeToBinary(j, i, false);
                    break;
                case "MUL":
                    montadorFinal.add(Opcode.MUL);
                    nixbpeToBinary(j, i, false);
                    break;
                case "DIV":
                    montadorFinal.add(Opcode.DIV);
                    nixbpeToBinary(j, i, false);
                    break;
                case "AND":
                    montadorFinal.add(Opcode.AND);
                    nixbpeToBinary(j, i, false);
                    break;
                case "OR":
                    montadorFinal.add(Opcode.OR);
                    nixbpeToBinary(j, i, false);
                    break;
                case "COMP":
                    montadorFinal.add(Opcode.COMP);
                    nixbpeToBinary(j, i, false);
                    break;
                case "TIX":
                    montadorFinal.add(Opcode.TIX);
                    nixbpeToBinary(j, i, false);
                    break;
                case "+STA":
                    montadorFinal.add(Opcode.STA);
                    nixbpeToBinary(j, i, true);
                    break;
                case "+STX":
                    montadorFinal.add(Opcode.STX);
                    nixbpeToBinary(j, i, true);
                    break;
                case "+STL":
                    montadorFinal.add(Opcode.STL);
                    nixbpeToBinary(j, i, true);
                    break;
                case "+STCH":
                    montadorFinal.add(Opcode.STCH);
                    nixbpeToBinary(j, i, true);
                    break;
                case "+STB":
                    montadorFinal.add(Opcode.STB);
                    nixbpeToBinary(j, i, true);
                    break;
                case "+STS":
                    montadorFinal.add(Opcode.STS);
                    nixbpeToBinary(j, i, true);
                    break;
                case "+STT":
                    montadorFinal.add(Opcode.STT);
                    nixbpeToBinary(j, i, true);
                    break;
                case "+JEQ":
                    montadorFinal.add(Opcode.JEQ);
                    nixbpeToBinary(j, i, true);
                    break;
                case "+JGT":
                    montadorFinal.add(Opcode.JGT);
                    nixbpeToBinary(j, i, true);
                    break;
                case "+JLT":
                    montadorFinal.add(Opcode.JLT);
                    nixbpeToBinary(j, i, true);
                    break;
                case "+J":
                    montadorFinal.add(Opcode.J);
                    nixbpeToBinary(j, i, true);
                    break;
                case "+RSUB":
                    montadorFinal.add(Opcode.RSUB);
                    nixbpeToBinary(j, i, true);
                    break;
                case "+JSUB":
                    montadorFinal.add(Opcode.JSUB);
                    nixbpeToBinary(j, i, true);
                    break;
                case "+LDA":
                    montadorFinal.add(Opcode.LDA);
                    nixbpeToBinary(j, i, true);
                    break;
                case "+LDX":
                    montadorFinal.add(Opcode.LDX);
                    nixbpeToBinary(j, i, true);
                    break;
                case "+LDL":
                    montadorFinal.add(Opcode.LDL);
                    nixbpeToBinary(j, i, true);
                    break;
                case "+LDCH":
                    montadorFinal.add(Opcode.LDCH);
                    nixbpeToBinary(j, i, true);
                    break;
                case "+LDB":
                    montadorFinal.add(Opcode.LDB);
                    nixbpeToBinary(j, i, true);
                    break;
                case "+LDS":
                    montadorFinal.add(Opcode.LDS);
                    nixbpeToBinary(j, i, true);
                    break;
                case "+LDT":
                    montadorFinal.add(Opcode.LDT);
                    nixbpeToBinary(j, i, true);
                    break;
                case "+ADD":
                    montadorFinal.add(Opcode.ADD);
                    nixbpeToBinary(j, i, true);
                    break;
                case "+SUB":
                    montadorFinal.add(Opcode.SUB);
                    nixbpeToBinary(j, i, true);
                    break;
                case "+MUL":
                    montadorFinal.add(Opcode.MUL);
                    nixbpeToBinary(j, i, true);
                    break;
                case "+DIV":
                    montadorFinal.add(Opcode.DIV);
                    nixbpeToBinary(j, i, true);
                    break;
                case "+AND":
                    montadorFinal.add(Opcode.AND);
                    nixbpeToBinary(j, i, true);
                    break;
                case "+OR":
                    montadorFinal.add(Opcode.OR);
                    nixbpeToBinary(j, i, true);
                    break;
                case "+COMP":
                    montadorFinal.add(Opcode.COMP);
                    nixbpeToBinary(j, i, true);
                    break;
                case "+TIX":
                    montadorFinal.add(Opcode.TIX);
                    nixbpeToBinary(j, i, true);
                    break;
                default:
                    j--;
                    break;
            }
        }
    }

    public void nixbpeToBinary(int j, int i, boolean e) {
        /*COLOCANDO O NIX*/
        String ni;
        if(montadorFinal.get(j).length() != 8) {
            if(getMontador(i).contains("#")) {
                montadorFinal.set(j, montadorFinal.get(j) + "01");
                ni = "01";
                montadorFinal.set(j, montadorFinal.get(j) + "0");
            } else if(getMontador(i).contains("@")) {
                montadorFinal.set(j, montadorFinal.get(j) + "10");
                ni = "10";
                montadorFinal.set(j, montadorFinal.get(j) + "0");
            } else {
                montadorFinal.set(j, montadorFinal.get(j) + "11");
                ni = "11";
                if(getMontador(i).contains(",X")) {
                    montadorFinal.set(j, montadorFinal.get(j) + "1");
                }
                else {
                    montadorFinal.set(j, montadorFinal.get(j) + "0");
                }
            }
            montadorFinal.set(j, montadorFinal.get(j) + "00");
            if(e == true) {
                montadorFinal.set(j, montadorFinal.get(j) + "1");
            }
            else {
                montadorFinal.set(j, montadorFinal.get(j) + "0");
            }
        } else {
            ni = "XX";
        }

        operandToBinary(j, i, ni, e);

    }

    public void operandToBinary(int j, int i, String ni, Boolean e) {
        String[] line;
        String aux;
        int intaux;
        line = getMontador(i).split(" ");
        if(ni.equals("XX")) {
            for(int z = 1; z < 3; z++) {
                switch (line[z]) {
                    case "A":
                        intaux = 0;
                        break;
                    case "X":
                        intaux = 1;
                        break;
                    case "L":
                        intaux = 2;
                        break;
                    case "B":
                        intaux = 3;
                        break;
                    case "S":
                        intaux = 4;
                        break;
                    case "T":
                        intaux = 5;
                        break;
                    case "F":
                        intaux = 6;
                        break;
                    default:
                        intaux = 0;
                }
                aux = Conversion.intToStringBinary(intaux);
                aux = Conversion.padLeftZeros(aux, 4);
                montadorFinal.set(j, montadorFinal.get(j) + aux);
            }
        } else if(ni.equals("01")) {
            aux = Conversion.intToStringBinary(Integer.parseInt(line[1].substring(1)));
            if(e) {
                aux = Conversion.padLeftZeros(aux, 20);
            } else {
                aux = Conversion.padLeftZeros(aux, 12);
            }
            montadorFinal.set(j, montadorFinal.get(j) + aux);
        }
        else {
            if(tableSymbol.containsKey(line[1])) {
                intaux = tableSymbol.get(line[1]);
                aux = Conversion.intToStringBinary(intaux);
                if(e) {
                    aux = Conversion.padLeftZeros(aux, 20);
                } else {
                    aux = Conversion.padLeftZeros(aux, 12);
                }
                montadorFinal.set(j, montadorFinal.get(j) + aux);
            }
            else {
                aux = Conversion.intToStringBinary(Integer.parseInt(line[1].substring(1)));
                if(e) {
                    aux = Conversion.padLeftZeros(aux, 20);
                } else {
                    aux = Conversion.padLeftZeros(aux, 12);
                }
                montadorFinal.set(j, montadorFinal.get(j) + aux);
            }
        }
    }
    public String getMontador(int position) {
        return montador.get(position);
    }

    public void writeMontador() throws IOException {
        BufferedWriter buffWrite = new BufferedWriter(new FileWriter("src/inputMachine.txt"));
        for(int i=0; i<montadorFinal.size();i++)
            buffWrite.append(montadorFinal.get(i) + "\n");

        buffWrite.close();

    }

    public void writeSymbolTable() throws IOException {
        FileWriter arq = new FileWriter("src/SymbolTable.txt");
        PrintWriter gravarArq = new PrintWriter(arq);

        tableSymbol.entrySet().forEach(entry -> {
            gravarArq.printf(entry.getKey() + " " + entry.getValue() + "\n");
        });

        arq.close();
    }

}

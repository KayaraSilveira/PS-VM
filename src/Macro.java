import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Macro {
    public ArrayList<String> macro = new ArrayList<>();
    public ArrayList<String> label = new ArrayList<>();
    public String[][] macroNames;
    public String[][] defTable;
    public int countLine = 0;
    public int countColumn = 0;
    public ArrayList<String> arrayNames = new ArrayList<>();
    public ArrayList<String> functionCall = new ArrayList<>();
    public ArrayList<String> extendedMacro = new ArrayList<>();
    public ArrayList<String> finalMacro = new ArrayList<>();
    public ArrayList<String> entradaMontador = new ArrayList<>();
    //
    public String[] macroNameAux;
    public String aux;

    public String getMacro(int position) {
        return macro.get(position);
    }
    public String getLabel(int position) {
        return label.get(position);
    }

    public String getMacroNames(int line, int column) {
        return macroNames[line][column];
    }

    public void setExtendedMacro(int position, String conteudo) {
        extendedMacro.set(position, conteudo);
    }

    public void setFinalMacro(int position, String conteudo) {
        finalMacro.set(position, conteudo);
    }

    public String getDefTable(int line, int column) {
        if(defTable[line][column] == null || defTable[line][column].isEmpty())
            return null;
        else
            return defTable[line][column];
    }

    public String getArraynames(int position) {
        return arrayNames.get(position);
    }

    public String getFunctionCall(int position) {
        return functionCall.get(position);
    }

    public String getExtendedMacro(int position) {
        return extendedMacro.get(position);
    }

    public String getFinalMacro(int position) {
        return finalMacro.get(position);
    }
    public String getEntradaMontador(int position) {
        return entradaMontador.get(position);
    }

    public boolean loadMacro(String filename) throws FileNotFoundException {
        BufferedReader r = new BufferedReader(new FileReader(filename));
        try {
            String line = r.readLine();
            while (line != null) {
                macro.add(line);
                line = r.readLine();
            }
        } catch (IOException e) {
            System.out.println(e);
            return false;
        }
        return true;
    }

    public void writeMacro() throws IOException {
        BufferedWriter buffWrite = new BufferedWriter(new FileWriter("src/entradaMontador.txt"));
        for(int i=0; i<entradaMontador.size();i++)
            buffWrite.append(getEntradaMontador(i) + "\n");

        buffWrite.close();
    }

    public void namesTable(ArrayList<String> macro) {
        int count = 0;
        /*Pegando o nome de cada macro e seus parâmetros e adicionando numa
        matriz, onde cada linha representa a ordem em que foi encontrada a chamada da
        definição da macro no txt
         */
        /*Primeiro adiciona num array de strings*/
        for (int i = 0; i < macro.size(); i++) {
            if (getMacro(i).equals("MACRO")) {
                aux = getMacro(i + 1);
                macroNameAux = aux.split(" ");
                for (int k = 0; k < macroNameAux.length; k++) {
                    arrayNames.add(macroNameAux[k]);
                }
                arrayNames.add(";");
            }
        }
        /*contando a quantidade de ;, isso se refere a quantidade de nomes de macros pra nossa matriz
        ou seja, quantidade de linhas*/

        for (int i = 0; i < arrayNames.size(); i++) {
            if (getArraynames(i).equals(";"))
                count++;
        }

        /*Sim, a matriz vai ficar com espaço desnecessário sobrando nas colunas, mas é a vida*/
        macroNames = new String[count][arrayNames.size()];

        /*depois coloca na matriz, bem bonitinha. Não vou tirar as virgula agora*/
        for (int i = 0; i < arrayNames.size(); i++) {
            if (getArraynames(i).equals(";")) {
                countLine++;
                countColumn = 0;
            } else {
                macroNames[countLine][countColumn] = getArraynames(i);
                countColumn++;
            }
        }

    }

    public ArrayList<String> callMacros(ArrayList<String> macro) {
        int contador = 0;
        for (int i = 0; i < macro.size(); i++) {
            if (getMacro(i).equals("MACRO"))
                contador++;
        }
        for (int i = 0; i < macro.size(); i++) {
            if (getMacro(i).equals("MEND"))
                contador--;
            if (contador == 0 && i < macro.size()) {
                for (int j = i + 1; j < macro.size(); j++) {
                    if (getMacro(i).equals("\n"))
                        functionCall.add("\n");
                    else
                        functionCall.add(getMacro(j));
                }
                break;
            }
        }
        return functionCall;
    }

    public void definitionTable(ArrayList<String> macro) {
        int cLine = 0, flag=0;
        defTable = new String[countLine][macro.size()];
        for (int i = 0; i < macro.size(); i++) {
            if (getMacro(i).equals("MACRO")) {
                flag++;
                countColumn = 0;
                for (int j = i + 1; flag != 0; j++) {
                    if(getMacro(j).equals("MACRO")){
                        flag++;
                    }
                    else if((getMacro(j).equals("MEND"))){
                        flag--;
                    }
                    else if(((getMacro(j).equals("MACRO")) == false) && flag == 1){
                        defTable[cLine][countColumn] = getMacro(j);
                        countColumn++;
                    }
                }
                cLine++;
            }
        }
    }

    public void expandCallMacro() {
        String[] aux;
        int contColuna = 0;
        for (int i = 0; i < functionCall.size(); i++) {
            aux = getFunctionCall(i).split(" ");
            for (int j = 0; j < macroNames.length; j++) {
                if (aux[0].equals(getMacroNames(j, 0))) {
                    for (contColuna = 0; contColuna < macro.size(); contColuna++) {
                        if(getDefTable(j, contColuna) != null)
                            extendedMacro.add(getDefTable(j, contColuna));
                    }
                    extendedMacro.add("\n");
                }
            }
        }
    }

    public void substituiParam() {
        int inc = 0, flag;
        String[] aux, auxNameMacros, auxRealParam;
        auxNameMacros = getExtendedMacro(0).split(" ");
        auxRealParam = getFunctionCall(inc).split(" ");
        inc++;
        for (int j = 0; j < auxNameMacros.length; j++) {
            auxNameMacros[j] = auxNameMacros[j].replace(",", "");
            auxRealParam[j] = auxRealParam[j].replace(",", "");
        }
        for (int i = 0; i < extendedMacro.size(); i++) {
            if ((i >= 1) && ((getExtendedMacro(i - 1).equals("\n")))) {
                auxNameMacros = getExtendedMacro(i).split(" ");
                auxRealParam = getFunctionCall(inc).split(" ");
                inc++;
                for (int j = 0; j < auxNameMacros.length; j++) {
                    auxNameMacros[j] = auxNameMacros[j].replaceAll(",", "");
                    auxRealParam[j] = auxRealParam[j].replaceAll(",", "");
                }
            } else if (getExtendedMacro(i).equals("\n") == false) {
                flag = 0;
                aux = getExtendedMacro(i).split(" ");
                for (int j = 0; j < aux.length; j++) {
                    aux[j] = aux[j].replaceAll(",", "");
                }

                for (int j = 0; j < aux.length; j++) {
                    for (int k = 0; k < auxNameMacros.length; k++) {
                        if (aux[j].equals(auxNameMacros[k])) {
                            aux[j] = auxRealParam[k];
                            flag++;
                        }
                    }
                }

                if (flag != 0) {
                    String result = String.join(" ", aux);
                    setExtendedMacro(i, result);
                }
            }


        }
    }

    public void removeNameMacro() {
        for (int i = 0; i < extendedMacro.size(); i++) {
            if (i == 0)
                extendedMacro.remove(i);
            else if (getExtendedMacro(i - 1).equals("\n"))
                extendedMacro.remove(i);
        }
    }

    public void expandMacros() {
        String[] aux, auxDois;
        auxDois = new String[macro.size()];
        int contColuna, cont = 0;
        for (int i = 0; i < extendedMacro.size(); i++) {
            aux = getExtendedMacro(i).split(" ");
            for (int j = 0; j < macroNames.length; j++) {
                if (aux[0].equals(getMacroNames(j, 0))) {
                    for (contColuna = 0; contColuna < macro.size(); contColuna++) {
                        if ((getDefTable(j, contColuna) != null)) {
                            auxDois[cont] = getDefTable(j, contColuna);
                            cont++;
                        }
                    }
                } else if (cont != 0){
                    String[] auxNameMacros, auxRealParam, auxTres;
                    int flag = 0;
                    auxNameMacros = auxDois[0].split(" ");
                    auxRealParam = getExtendedMacro(i).split(" ");
                    for (int k = 0; k < auxNameMacros.length; k++) {
                        auxNameMacros[k] = auxNameMacros[k].replace(",", "");
                    }

                    for (int k = 0; k < cont; k++) {
                        auxTres = auxDois[k].split(" ");
                        for (int l = 0; l < auxTres.length; l++) {
                            auxTres[l] = auxTres[l].replace(",", "");
                        }

                        for (int n = 0; n < auxTres.length; n++) {
                            for (int m = 0; m < auxNameMacros.length; m++) {
                                if (auxTres[n].equals(auxNameMacros[m])) {
                                    auxTres[n] = auxRealParam[m];
                                    flag++;
                                }
                            }
                        }

                        if (flag != 0) {
                            String result = String.join(" ", auxTres);
                            if (result.equals(getExtendedMacro(i))) {
                                continue;
                            } else {
                                finalMacro.add(result);
                            }
                            flag = 0;
                        } else {
                            finalMacro.add(auxDois[k]);
                        }

                    }

                    cont = 0;
                }

            }

            if (cont == 0) {
                finalMacro.add(getExtendedMacro(i));
            }

        }
    }

    public void removeCallMacro() {
        String[] aux;

        for (int i = 0; i < finalMacro.size(); i++) {
            aux = getFinalMacro(i).split(" ");
            for (int j = 0; j < macroNames.length; j++) {
                if (aux[0].equals(getMacroNames(j, 0))) {
                    finalMacro.remove(i);
                }
            }

            if(getFinalMacro(i).equals("\n")){
                finalMacro.remove(i);
            }
        }

    }

    public void labels(){
        int contador = 0, flag = 0;
        String[] aux;
        for (int i = 0; i < macro.size(); i++) {
            if (getMacro(i).equals("MACRO"))
                contador++;
        }
        for (int i = 0; i < macro.size(); i++) {
            if (getMacro(i).equals("MEND"))
                contador--;
            if (contador == 0 && i < macro.size()) {
                for (int j = i + 1; j < macro.size(); j++) {
                    aux = getMacro(j).split(" ");
                    for(int k=0; k< macroNames.length; k++){
                        if(aux[0].equals(getMacroNames(k, 0))){
                            flag++;
                        }
                    }
                    if(flag == 0){
                        label.add(getMacro(j));
                    }
                    else{
                        flag = 0;
                    }
                }
                break;
            }
        }
    }

    public void saidaFinal(){
        for(int i=0; i< finalMacro.size(); i++){
            entradaMontador.add(getFinalMacro(i));
        }
        for(int j=0; j< label.size(); j++){
            entradaMontador.add(getLabel(j));
        }
    }
}
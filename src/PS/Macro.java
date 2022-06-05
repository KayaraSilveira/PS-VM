import java.io.*;
import java.util.ArrayList;

public class Macro {
    public ArrayList<String> macro= new ArrayList<>();
    public ArrayList<String> definition= new ArrayList<>();
    public ArrayList<String> functionCallFragmented= new ArrayList<>();
    public ArrayList<String> functionCall= new ArrayList<>();

    public String[] macroNameAux; ;
    public String a;
    public ArrayList<String> macroName= new ArrayList<>();
    public ArrayList<String> macroNameReal= new ArrayList<>();

    public String getMacro(int position) {
        return macro.get(position);
    }
    public String getDefinition(int position) {
        return definition.get(position);
    }
    public String getFunctionCallFragmented(int position) {
        return functionCallFragmented.get(position);
    }
    public String getFunctionCall(int position) {
        return functionCall.get(position);
    }
    public String getMacroName(int position) {
        return macroName.get(position);
    }
    public String getMacroNameReal(int position) {
        return macroNameReal.get(position);
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

    public void definitionTable(ArrayList<String> macro){
        String guarda = " ";
        int flag = 0;
        for(int i=0; i< macro.size(); i++){
            if(getMacro(i).equals("MACRO")){
                a = getMacro(i+1);
                macroNameAux = a.split(" ");
                for(int k=0; k<macroNameAux.length; k++) {
                    macroName.add(macroNameAux[k]);
                }
            }
        }

        for(int i=(macro.size() - 1); !(getMacro(i).equals("MEND")); i--){
            functionCall.add(getMacro(i));
            a = getMacro(i);
            macroNameAux = a.split(" ");
            for(int k=0; k<macroNameAux.length; k++) {
                functionCallFragmented.add(macroNameAux[k]);
            }
            macro.remove(getMacro(i));
        }


        for(int i=0; i<macro.size(); i++){
            if((getMacro(i).equals("MACRO")) || (getMacro(i).equals("MEND"))){
                definition.add(" ");
            }
            else{
                definition.add(getMacro(i));
            }
        }
        for(int j=0; j< functionCallFragmented.size(); j++){
            for(int i=0; i<macroName.size(); i++){
                if((getFunctionCallFragmented(j).contains(getMacroName(i))) && (getFunctionCallFragmented(j).equals(getMacroName(i)))){
                    guarda = getMacroName(i);
                    macroNameReal.add(guarda);
                }
            }
        }

        for(int i=0; i< macroName.size(); i++){
            for(int j=0; j<macroNameReal.size(); j++){
                if(getMacroName(i).contains(getMacroNameReal(j))){
                    guarda = getMacroNameReal(j);
                    String finalGuarda = guarda;
                    macroName.removeIf(nome -> nome.equals(finalGuarda)); // ????
                    flag++;

                    if(flag > 1)
                        macroName.add(i, ";");
                }
            }
        }

//        for(int i=0; i< definition.size(); i++){
//
//        }
    }
}
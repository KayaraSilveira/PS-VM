import java.io.FileNotFoundException;

public class MainMacro{
    public static void main(String[] args) {

        Macro ob = new Macro();
        try {
            ob.loadMacro("src/macro.txt");
        } catch (
                FileNotFoundException e1) {
            System.out.println(e1);
            return;
        }

        for (int j = 0; j < ob.macro.size(); j++) {
            System.out.println(ob.getMacro(j));
        }

        System.out.println("\n");
        ob.definitionTable(ob.macro);

        for (int j = 0; j < ob.definition.size(); j++) {
            System.out.println(ob.getDefinition(j));
        }

        System.out.println("\n");

        for (int j = 0; j < ob.functionCall.size(); j++) {
            System.out.println(ob.getFunctionCall(j));
        }

        System.out.println("\nMACRO NAME: \n");

        for (int j = 0; j < ob.macroName.size(); j++) {
            System.out.println(ob.getMacroName(j));
        }

        System.out.println("\n");

        for (int j = 0; j < ob.macroNameReal.size(); j++) {
            System.out.println(ob.getMacroNameReal(j));
        }
    }
}
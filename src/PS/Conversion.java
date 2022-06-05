package PS;

public class Conversion {

    public Conversion() {

    }
    public static int stringBinaryToInt (String text) {
        int number;
        number = Integer.parseInt(text, 2);
        return number;
    }

    public static String intToStringBinary (int number) {
        String text;
        text = Integer.toBinaryString(number);
        return  text;
    }
    public static String ShiftL(String n, int k){
        int i = Integer.parseInt(n, 2);
        String shiftedL = Integer.toBinaryString(i<<k);
        return shiftedL;
    }
    public static String ShiftR(String n, int k){
        int i = Integer.parseInt(n, 2);
        String shiftedR = Integer.toBinaryString(i>>k);
        return shiftedR;
    }

}

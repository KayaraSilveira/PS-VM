public class Conversion {

    public Conversion() {

    }
    public static int stringBinaryToInt (String text) {
        int number;
        number = Integer.parseInt(text, 2);
        return number;
    }
    public static String padLeftZeros(String inputString, int length) {
        if (inputString.length() >= length) {
            return inputString;
        }
        StringBuilder sb = new StringBuilder();
        while (sb.length() < length - inputString.length()) {
            sb.append('0');
        }
        sb.append(inputString);

        return sb.toString();
    }
    public static String intToStringBinary (int number) {
        String text;
        text = Integer.toBinaryString(number);
        return  text;
    }
    public static String ShiftL(String n, int k){
        int count;
        count = n.length();
        n=n.substring(k-1, count);
        while(n.length()<count) {
            n = n +"0";
        }
        return n;
    }
    public static String ShiftR(String n, int k){
        int count;
        count = n.length();
        n=n.substring(0, count - (k-1));
        n = Conversion.padLeftZeros(n,count);
        return n;
    }



}
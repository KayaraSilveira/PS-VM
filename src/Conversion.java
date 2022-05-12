public class Conversion {

    public Conversion() {

    }
    public int stringBinaryToInt (String text) {
        int number;
        number = Integer.parseInt(text, 2);
        return number;
    }

    public String intToStringBinary (int number) {
        String text;
        text = Integer.toBinaryString(number);
        return  text;
    }

}

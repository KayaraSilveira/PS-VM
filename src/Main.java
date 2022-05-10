
public class Main {

    public static void main(String[] args) {
        boolean sera;
        int hm;
        Flags batata = new Flags(10, 0, 1, 0, 1);
        batata.set_b(0);
        batata.set_e(0);
        sera = batata.isAbsolute();
        hm = batata.get_ni();
        System.out.println(sera);
        System.out.println(hm);
    }
}

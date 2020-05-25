package myOverPackage.myPackage.myUnderPackage;

public class UnderPWriter {
    public void writeText(String text){
        System.out.println("\r\n\r\nUnderPWriter writes: " +text + " !!!\r\n\r\n");
    }

    public static void writeTextStatic(String text){
        System.out.println("\r\n\r\nUnderPWriter writes static: " +text + " !!!\r\n\r\n");
    }
}

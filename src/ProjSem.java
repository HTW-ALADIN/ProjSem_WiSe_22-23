public class ProjSem {

    public static void main(String[] args) {
        //wenn testModus == true ist, müssen die Parameter nicht händisch bei jedem Start eingegeben werden
        boolean testModus = true;
        EPK epk = new EPK(new Parameters(testModus));
        insertText insertText = new insertText(epk);
        Edotor edotor = new Edotor(insertText);
    }
}
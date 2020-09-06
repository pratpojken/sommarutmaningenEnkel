import java.util.Scanner;

public class sommarutmaningenOlaEnkel {

    public static void main(String[] args) {
        int spelareDragetKort;
        int spelareKortSumma = 0;
        int husetDragetKort;
        int husetKortSumma = 0;

        String vem ;
        String andre ;

        System.out.println("VÄLKOMMEN TILL OLAS BLACK JACK");
        System.out.println();

        //Drag husets dolda Kort.
        husetDragetKort = dragKort();
        husetKortSumma = beraknaSumma(husetDragetKort, husetKortSumma);

        //Drag nu husets andra Kort och presentera detta.
        husetDragetKort = dragKort();
        husetKortSumma = beraknaSumma(husetDragetKort, husetKortSumma);
        System.out.println("Huset drar ett kort med värdet " + husetDragetKort + " och ett kort med ett dolt värde.");
        System.out.println("Husets totala summa är dold för tillfället.");

        //Drag spelares första Kort.
        spelareDragetKort = dragKort();
        System.out.println("Kort1: " + spelareDragetKort);
        spelareKortSumma = beraknaSumma(spelareDragetKort, spelareKortSumma);
        System.out.println("Summa1: " + spelareKortSumma);

        //Drag nu spelarens andra Kort och presentera detta.
        spelareDragetKort = dragKort();
        System.out.println("Kort1: " + spelareDragetKort);
        spelareKortSumma = beraknaSumma(spelareDragetKort, spelareKortSumma);
        System.out.println("Summa1: " + spelareKortSumma);

        System.out.println("Du drar ett kort med värdet " + spelareDragetKort +
                " och ett andra kort med värdet: " + (spelareKortSumma - spelareDragetKort));
        System.out.println("Din hand är värd: " + spelareKortSumma);
        System.out.println();

        vem = "Du";
        andre = "Huset";

        kollaTjock(vem, andre, spelareKortSumma);

        //Har spelaren inte blivit tjock har vi nått hit och spelare frågas om denna vill dra igen.
        spelareKortSumma = draIgen(vem, spelareKortSumma);

        //Nu har spelaren sagt stopp utan att bli tjock.
        //Nu presenterar vi husets dolda kort
        husetsDoldaKort (husetDragetKort, husetKortSumma);

        //Nu fortsätter huset att dra tills det är över 17 i totalpoäng
        vem = "Huset";
        andre = "Du";
        husetKortSumma = draIgen (vem, husetKortSumma);

        //Kommer vi hela vägen hit så har varken spelare eller hus blivit tjocka.
        //Vi kontrollerar vem som vunnit.

        kollaVinnare(husetKortSumma, spelareKortSumma);

    }

    public static int dragKort() {
        return  (2 + (int) (Math.random() * 10));
    }

    public static int beraknaSumma(int dragetKort, int kortSumma) {

        return (kortSumma + dragetKort);
    }

    public static void kollaTjock(String vem, String andre, int kortSumma) {
        if (kortSumma > 21) {
            System.out.println(vem + " blev tjock.");
            System.out.println(andre + " vann!");
            System.exit(0);
        }

    }

    public static int draIgen (String vem, int kortSumma){
        if (vem.equals("Huset")){
            kortSumma = draIgenSpelare(kortSumma);
        }else if (vem.equals("Du")){
            kortSumma = draIgenHuset(kortSumma);
        }
        return kortSumma;
    }

    public static int draIgenSpelare(int kortSumma) {
        int dragetKort;
        String valDra = "dra";
        String vem;
        String andre;

        while (valDra.equals("dra")) {
            System.out.println("Vill du dra ett nytt kort skriv 'dra' och för att sluta skriver du 'stopp'");
            Scanner input = new Scanner(System.in);
            valDra = input.nextLine();

            if ((valDra.equals("dra"))) {
                dragetKort = dragKort();
                kortSumma = beraknaSumma(dragetKort, kortSumma);
                System.out.println("Du drar ett kort med värdet " + dragetKort);
                System.out.println("Det totala värdet på dina kort är nu: " + kortSumma);
                System.out.println();
                vem = "Du";
                andre = "Huset";
                kollaTjock(vem, andre, kortSumma);
            } else if ((valDra.equals("stopp"))) {
                System.out.println("Det total värdet på dina kort stannar på: " + kortSumma);
            }
        }

        return kortSumma;
    }

    public static void husetsDoldaKort(int husetsDragnaKort, int husetsSumma){
        System.out.println("Husets dolda kort hade värdet: "+(husetsSumma-husetsDragnaKort));
        System.out.println("Husets hand är värd: "+husetsSumma);
    }

    public static int draIgenHuset (int kortSumma){
        int dragetKort;
        String vem;
        String andre;

        while (kortSumma < 17){
            dragetKort = dragKort();
            kortSumma = beraknaSumma(dragetKort, kortSumma);
            System.out.println("Huset drar ett kort med värdet: "+dragetKort);
            System.out.println("Husets hand har nu värdet: "+kortSumma);

            vem = "Huset";
            andre = "Du";

            kollaTjock(vem, andre, kortSumma);
        }

        return kortSumma;

    }

    public static void kollaVinnare (int summaHuset, int summaSpelare){

        System.out.println();
        //Om spelare har fler poäng än huset här så vinner spelaren, annars vinner huset.
        if (summaSpelare > summaHuset){
            System.out.println("Du vann!");
        }else{
            System.out.println("Huset vann!");
        }

        System.exit(0);
    }
}




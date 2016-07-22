import java.util.Scanner;

public class KalulatorONP {

	public static void main(String[] args) {
		System.out.print("Podaj równanie: ");
		Scanner odczyt = new Scanner(System.in);
//		System.out.println(Character.getNumericValue(1));
		Rownanie przyklad = new Rownanie(odczyt.nextLine());
		odczyt.close();
//		Rownanie przyklad = new Rownanie("2*4 +5 - 6*2+9-12");
//		Rownanie przyklad = new Rownanie("2-4");
		System.out.println("Postaæ pierwotna: " + przyklad.getPostacPierwotna());
		System.out.println("Postaæ ONP: " + przyklad.toString());
		System.out.println("Wynik: " + przyklad.oblicz());
		//test
		System.out.println("Z git");
		System.out.println("Jakieœ tam g³upoty wpisuje");
	}

}

import java.util.Scanner;

public class KalulatorONP {

	public static void main(String[] args) {
		System.out.print("Podaj równanie: ");
		Scanner odczyt = new Scanner(System.in);
		Rownanie przyklad = new Rownanie(odczyt.nextLine());
		odczyt.close();
		System.out.println("Postaæ pierwotna: " + przyklad.getPostacPierwotna());
		System.out.println("Postaæ ONP: " + przyklad.toString());
		System.out.println("Wynik: " + przyklad.oblicz());
	}

}

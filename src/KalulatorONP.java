import java.util.Scanner;

public class KalulatorONP {

	public static void main(String[] args) {
		System.out.print("Podaj r�wnanie: ");
		Scanner odczyt = new Scanner(System.in);
		Rownanie przyklad = new Rownanie(odczyt.nextLine());
		odczyt.close();
		System.out.println("Posta� pierwotna: " + przyklad.getPostacPierwotna());
		System.out.println("Posta� ONP: " + przyklad.toString());
		System.out.println("Wynik: " + przyklad.oblicz());
	}

}

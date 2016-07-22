import java.util.ArrayList;

public class Rownanie {
	private ArrayList<String> stosONP = new ArrayList<String>();
	private ArrayList<String> stosPomocniczy = new ArrayList<String>();
	private ArrayList<String> operacje = new ArrayList<String>(7);

	private String postacPierwotna;

	public Rownanie(String rownanie) {
		wypelnijOperacje();
		postacPierwotna = rownanie;
		this.doOnp();
	}

	private void doOnp() {
		// ArrayList<String> bufor = new ArrayList<String>(); //
		String odczyt = "";
		int priorytet;
		for (int i = 0; i < postacPierwotna.length(); i++) { // petla zczytujaca
																// po kolei
																// znaki z
																// wejscia
			int j = i + 1;
			String znak = postacPierwotna.substring(i, i + 1);

			if (postacPierwotna.charAt(i) >= '0' && postacPierwotna.charAt(i) <= '9') { // jeœli
																						// liczba

				odczyt = odczyt + znak;

				if (j < postacPierwotna.length()) { // jesli nie dotarlismy do
													// konca rownania
					if (postacPierwotna.charAt(j) < '0' || postacPierwotna.charAt(j) > '9') { // sprawdzamy
																								// czy
																								// koniec
																								// liczby
						stosONP.add(odczyt);
						odczyt = "";
					}
				} else {
					stosONP.add(odczyt);
					odczyt = "";
				}

			}

			if (operacje.contains(znak)) { // jesli operator
				priorytet = ustalPriorytet(znak); // aktualny priorytet
				switch (znak) {
				case "(":
					stosPomocniczy.add(znak);
					break;
				case "+":
					this.dopiszOperacje(i, znak, priorytet);
					break;

				case "*":
					this.dopiszOperacje(i, znak, priorytet);
					break;

				case "-":
					this.dopiszOperacje(i, znak, priorytet);
					break;

				case "/":
					this.dopiszOperacje(i, znak, priorytet);
					break;

				default:
					break;
				}

			}

			if (postacPierwotna.charAt(i) == ' ') {

			}

		}
		this.oproznijStos();
	}

	public String toString() {
		String wyjscie = "";
		for (String x : stosONP) {
			wyjscie = wyjscie + x + " ";
		}
		return wyjscie;
	}

	private int priorytetStosu() { /**
									 * Pobiera najwyzszy priorytet sposrod
									 * elementow stosu
									 */
		int maxPriorytet = 0;
		int obecnyPriorytet = 0;

		for (String x : stosPomocniczy) {
			obecnyPriorytet = ustalPriorytet(x);
			if (obecnyPriorytet > maxPriorytet) {
				maxPriorytet = obecnyPriorytet;
			}
			return maxPriorytet;
		}

		return maxPriorytet;
	}

	public String oblicz() {

		int skladnik1 = 0;
		int skladnik2 = 0;
		int wartTymczasowa = 0;
		int i = 0;
		// String operacja;
		// int i = 0;
		while (i < stosONP.size() && stosONP.size() > 1) {
			
			if (czyLiczba(stosONP.get(i))) {
				skladnik2 = skladnik1;
				skladnik1 = Integer.parseInt(stosONP.get(i));
				i++;
			} else {
				System.out.println(stosONP);
				switch (stosONP.get(i).charAt(0)) {
				case '+':
					wartTymczasowa = skladnik2 + skladnik1;
					i = i - 2;
					stosONP.set(i, Integer.toString(wartTymczasowa));
					stosONP.remove(i + 1);
					stosONP.remove(i + 1);
					i = 0;
					break;
				case '-':
					wartTymczasowa = skladnik2 - skladnik1;
					i = i - 2;
					stosONP.set(i, Integer.toString(wartTymczasowa));
					stosONP.remove(i + 1);
					stosONP.remove(i + 1);
					i = 0;
					break;
				case '*':
					wartTymczasowa = skladnik2 * skladnik1;
					i = i - 2;
					stosONP.set(i, Integer.toString(wartTymczasowa));
					stosONP.remove(i + 1);
					stosONP.remove(i + 1);
					i = 0;
					break;
				case '/':
					if (skladnik1 != 0){
						wartTymczasowa = skladnik2 / skladnik1;
						i = i - 2;
						stosONP.set(i, Integer.toString(wartTymczasowa));
						stosONP.remove(i + 1);
						stosONP.remove(i + 1);
						i = 0;
					}
					else {
						System.out.println("Dzielenie przez 0");
						System.exit(1);
					}
					break;
				default:
					break;
				}
			}
		}
		return stosONP.get(0);
	}

	public String getPostacPierwotna() {
		return postacPierwotna;
	}

	private void przlozElement() {
		if (stosPomocniczy.size() > 0) {
			stosONP.add(stosPomocniczy.get(stosPomocniczy.size() - 1));
			stosPomocniczy.remove(stosPomocniczy.size() - 1);
		} else {
			System.out.println("Nic do przesuniecia");
		}
	}

	private void oproznijStos() {
		while (stosPomocniczy.size() > 0) {
			stosONP.add(stosPomocniczy.get(stosPomocniczy.size() - 1));
			stosPomocniczy.remove(stosPomocniczy.size() - 1);
		}
	}

	private void wypelnijOperacje() {
		operacje.add("+");
		operacje.add("-");
		operacje.add("*");
		operacje.add("/");
		operacje.add("(");
		operacje.add(")");
	}

	private int ustalPriorytet(String operator) {
		switch (operator) {
		case "(":
			return 0;
		case "+":
			return 1;
		case "-":
			return 1;
		case "*":
			return 2;
		case "/":
			return 2;
		default:
			return 100;
		}
	}

	private void dopiszOperacje(int elem, String znak, int priorytet) {
		if (stosPomocniczy.size() == 0) { // pierwszy element
			stosPomocniczy.add(znak);
		} else if (priorytet <= this.priorytetStosu()) { // srodkowe elementy
			while (priorytet <= this.priorytetStosu()){
				this.przlozElement();
			}
			stosPomocniczy.add(znak);
		} else if (elem == this.postacPierwotna.length() - 1) { // ostatni
																// element
			this.przlozElement();
			stosPomocniczy.add(znak);
		} else {
			stosPomocniczy.add(znak);
		}
//		System.out.println("Stos glówny" + stosONP);
//		System.out.println("Stos pomocniczy" + stosPomocniczy);
	}

	private Boolean czyLiczba(String param) {
		Boolean tylkoLiczby = true;
		for (int i = 0; i < param.length(); i++) {
			if (param.charAt(i) < '0' || param.charAt(i) > '9') {
				tylkoLiczby = false;
			}
		}
		return tylkoLiczby;
	}

}

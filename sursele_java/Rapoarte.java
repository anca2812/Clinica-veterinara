import java.time.LocalDate;
import java.util.ArrayList;

public class Rapoarte implements ListaRapoarte{
	private ArrayList<Programare> listaProgramari;
	private ArrayList<Factura> listaFacturi;

	public Rapoarte() {
		this.listaProgramari = new ArrayList<>();
		this.listaFacturi = new ArrayList<>();
	}

	public Rapoarte(ArrayList<Programare> listaProgramari, ArrayList<Factura> listaFacturi) {
		this.listaProgramari = listaProgramari;
		this.listaFacturi = listaFacturi;
	}

	//adaugarea unei programari
	public void addProgramare(Programare programare) {
		if(programare != null) {
			listaProgramari.add(programare);
		}
	}

	//adaugarea unei facturi
	public void addFactura(Factura factura) {
		if(factura != null) {
			listaFacturi.add(factura);
		}
	}

	//lista programarilor pe zi
	@Override
	public void raportZi(LocalDate data) {
		System.out.println("-------------------------------------------------");
		System.out.println("LISTA DE PROGRAMARI data " + data);

		for(Programare p : listaProgramari) {
			if(p.getData().equals(data)) {
				System.out.println(p);
			}
		}

		System.out.println("-------------------------------------------------\n");
	}

	//lista programarilor pe saptamana
	@Override
	public void raportSaptamana(LocalDate start, LocalDate end) {
		System.out.println("-------------------------------------------------");
		System.out.println("LISTA DE PROGRAMARI datele " + start + " - " + end);

		for(Programare p : listaProgramari) {
			if(!p.getData().isBefore(start) && !p.getData().isAfter(end)) {
				System.out.println(p);
			}
		}

		System.out.println("-------------------------------------------------\n");
	}

	//afisarea animalelor cu vaccinari viitoare intr-un anumit interval
	//(ex. urmatoarele 30 de zile)
	public void raportVaccinariViitoare(ArrayList<Animal> animale, int intervalZile) {
		LocalDate start = LocalDate.now();
		LocalDate end = start.plusDays(intervalZile);

		System.out.println("-------------------------------------------------");
		System.out.println("LISTA PROGRAMARILOR in intervalul " + start + " - " + end);

		boolean find =  false;

		for(Animal a : animale) {
			for(IstoricMedical im : a.getIstoricMedical()) {
				if(im instanceof Vaccinare v) {
					if(!v.getData().isBefore(start) && !v.getData().isAfter(end)) {
						System.out.println("	* " + a.getNume() + ": " +
										a.getSpecie() + " - vaccin: " +
										v.getTipVaccin() + " la data " +
										v.getNextDoze());
						find = true;
					}
				}
			}
		}

		if(find == false) {
			System.out.println("	* nu exista programari in urmatoarele " + intervalZile + " de zile");
		}

		System.out.println("-------------------------------------------------\n");
	}

	//venit total pe perioada, separat de servicii si produse
	@Override
	public void raportVenituri(LocalDate start, LocalDate end) {
		double totalServicii = 0.0;
		double totalProduse = 0.0;

		for(Factura f : listaFacturi) {
			if(!f.getData().isBefore(start) && !f.getData().isAfter(end)) {
				totalServicii = totalServicii + f.getTotalServicii();
				totalProduse = totalProduse + f.getTotalProduse();
			}
		}

		System.out.println("-------------------------------------------------");
		System.out.println("VENIT TOTAL pe perioada " + start + " - " + end);
		System.out.println("	* venit din servicii: " + totalServicii + " RON");
		System.out.println("	* venit din produse: " + totalProduse + " RON");
		System.out.println("	* venit total: " + totalProduse + " + " + totalServicii + " = " + (totalServicii + totalProduse) + " RON");
		System.out.println("-------------------------------------------------\n");
	}
}

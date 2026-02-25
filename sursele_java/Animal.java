import java.util.ArrayList;
import java.util.Comparator;

public class Animal implements AfisariAnimal{
	//inregistrare animal (nume, specie, rasa, varsta, numar microcip, stapan asociat
	private int id;
	private String nume;
	private String specie;
	private String rasa;
	private int varsta;
	private String microcip;
	private Stapan stapan;
	private ArrayList<IstoricMedical> istoricMedical;

	public Animal() {
		this.istoricMedical = new ArrayList<>();
	}

	public Animal(int id, String nume, String specie, String rasa, int varsta, String microcip, Stapan stapan) {
		this.id = id;
		this.nume = nume;
		this.specie = specie;
		this.rasa = rasa;
		this.varsta = varsta;
		this.microcip = microcip;
		this.stapan = stapan;
		this.istoricMedical = new ArrayList<>();
	}

	public int getId() { return id; }
	public void setId(int id) { this.id = id; }

	public String getNume() { return nume; }
	public void setNume(String nume) {
		if(nume != null && !nume.trim().isEmpty()) {
			this.nume = nume;
		}
	}

	public String getSpecie() { return specie; }
	public void setSpecie(String specie) {
		if(specie != null && !specie.trim().isEmpty()) {
			this.specie = specie;
		}
	}

	public String getRasa() { return rasa; }
	public void setRasa(String rasa) {
		if(rasa != null && !rasa.trim().isEmpty()) {
			this.rasa = rasa;
		}
	}

	public int getVarsta() { return varsta; }
	public void setVarsta(int varsta) {
		if(varsta >= 0) {
			this.varsta = varsta;
		}
	}

	public String getMicrocip() { return microcip; }
	public void setMicrocip(String microcip) {
		if(microcip != null && !microcip.trim().isEmpty()) {
			this.microcip = microcip;
		}
	}

	public Stapan getStapan() { return stapan; }
	public void setStapan(Stapan stapan) {
		this.stapan = stapan;
		if(stapan != null && !stapan.getListaAnimal().contains(this)) {
			stapan.getListaAnimal().add(this);
		}
	}

	public ArrayList<IstoricMedical> getIstoricMedical() { return istoricMedical; }
	public void setIstoricMedical(ArrayList<IstoricMedical> istoricMedical) {
		this.istoricMedical = istoricMedical;
	}

	//adaugarea unui tratament in istoricul medical
	public <T extends IstoricMedical> void addIstoricMedical(T tratament) {
		if(tratament != null) {
			istoricMedical.add(tratament);
		}
	}

	//eliminarea unui tratament
	public <T extends IstoricMedical> void removeIstoricMedical(T tratament) {
		if(tratament != null) {
			istoricMedical.remove(tratament);
		}
	}

	//afisarea inregistrarii animalului
	public void fisaAnimal() {
		System.out.println("--------------------------------------------------");
		System.out.println("INREGISTRARE animal [" + id + "]");
		System.out.println("	* nume: " + nume);
		System.out.println("	* specie: " + specie);
		System.out.println(" 	* rasa: " + rasa);
		System.out.println(" 	* varsta: " + varsta);
		System.out.println("	* microcip: " + microcip);

		if(stapan != null) {
			System.out.println("	* stapan asociat: " + stapan.getNume());
		}
		else {
			System.out.println("	* fara stapan asociat");
		}

		System.out.println("-------------------------------------------------\n");
	}

	//afisarea fisei complete a animalului
	public void fisaCompletaAnimal() {
		System.out.println("--------------------------------------------------");
		System.out.println("FISA COMPLETA animal [" + id + "]");
		System.out.println("	* nume: " + nume);
		System.out.println("	* specie: " + specie);
		System.out.println(" 	* rasa: " + rasa);
		System.out.println(" 	* varsta: " + varsta + " ani");
		System.out.println("	* microcip: " + microcip);

		if(stapan != null) {
			System.out.println("	* stapan asociat: " + stapan.getNume());
		}
		else {
			System.out.println("	* fara stapan asociat");
		}

		System.out.println("ISTORIC MEDICAL");
		if(istoricMedical.isEmpty()) {
			System.out.println("	* nu exista istoric medical pentru " + this.getNume());
		}
		else {
			for(IstoricMedical medical : istoricMedical) {
				System.out.println("	* " + medical);
			}
		}
		System.out.println("-------------------------------------------------\n");
	}

	//afisarea istoricului medical
	public void fisaIstoricmedical() {
		System.out.println("--------------------------------------------------");
		System.out.println("ISTORIC MEDICAL pentru " + this.getNume());

		if(istoricMedical.isEmpty()) {
			System.out.println("	* nu exista istoric medical");
		}
		else {
			for(IstoricMedical medical : istoricMedical) {
				System.out.print(medical + "\n");
			}
		}

		System.out.println("--------------------------------------------------\n");
	}

	@Override
	public String toString() {
		return "\n - Animal [" + id + "]" +
				"\n		|-> nume: " + nume +
				"\n		|-> specie: " + specie +
				"\n		|-> rasa: " + rasa +
				"\n		|-> varsta: " + varsta + " ani" +
				"\n		|-> microcip: " + microcip +
				"\n		|-> stapan: " + (stapan != null ? stapan.getNume() : "niciun stapan asociat") +
				"\n		|-> nr. programari: " + istoricMedical.size() + "\n";
	}

	//comparator pentru sortarea dupa nume
	public static Comparator<Animal> animalComparatorNume = new Comparator<Animal>() {
		@Override
		public int compare(Animal animal1, Animal animal2) {
			return animal1.getNume().compareTo(animal2.getNume());
		}
	};

	//comparator pentru sortarea dupa specie
	public static Comparator<Animal> animalComparatorSpecie = new Comparator<Animal>() {
		@Override
		public int compare(Animal animal1, Animal animal2) {
			return animal1.getSpecie().compareTo(animal2.getSpecie());
		}
	};
}

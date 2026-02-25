import java.util.ArrayList;
import java.util.Objects;

public class Stapan {
	//inregistrare stapan (nume, telefon, adresa, mail)
	private int id;
	private String nume;
	private String telefon;
	private String adresa;
	private String email;
	//un stapan poate avea mai multe animale
	private ArrayList<Animal> listaAnimal;

	public Stapan() {
		this.listaAnimal = new ArrayList<>();
	}

	public Stapan(int id, String nume, String telefon, String adresa, String email) {
		this.id = id;
		this.nume = nume;
		this.telefon = telefon;
		this.adresa = adresa;
		this.email = email;
		this.listaAnimal = new ArrayList<>();
	}

	public int getId() { return id; }
	public void setId(int id) { this.id = id; }

	public String getNume() { return nume; }
	public void setNume(String nume) {
		//asigurare ca numele nu este null
		if(nume != null && !nume.trim().isEmpty()) {
			this.nume = nume;
		}
	}

	public String getTelefon() { return telefon; }
	public void setTelefon(String telefon) {
		if(telefon != null && !telefon.trim().isEmpty()) {
			this.telefon = telefon;
		}
	}

	public String getAdresa() { return adresa; }
	public void setAdresa(String adresa) {
		if(adresa != null && !adresa.trim().isEmpty()) {
			this.adresa = adresa;
		}
	}

	public String getEmail() { return email; }
	public void setEmail(String email) {
		if(email != null && !email.trim().isEmpty()) {
			this.email = email;
		}
	}

	public ArrayList<Animal> getListaAnimal() {
		return listaAnimal;
	}
	public void setListaAnimal(ArrayList<Animal> listaAnimal) {
		this.listaAnimal = listaAnimal;
	}

	//adaugarea animalelor, intrucat un stapan poate avea mai multe animale
	public void addAnimal(Animal animal) {
		if(animal != null && !listaAnimal.contains(animal)) {
			listaAnimal.add(animal);
			animal.setStapan(this);
		}
	}

	//eliminarea unui animal din lista stapanului
	public void removeAnimal(Animal animal) {
		if(animal != null && listaAnimal.contains(animal)) {
			listaAnimal.remove(animal);
			animal.setStapan(null);
		}
	}

	@Override
	public boolean equals(Object o) {
		if(this == o) return true;
		if(o == null || getClass() != o.getClass()) return false;

		Stapan stapan = (Stapan) o;
		return Objects.equals(telefon, stapan.telefon);
	}

	@Override
	public int hashCode() {
		return Objects.hash(telefon);
	}

	//afisarea animalelor detinute de stapan
	public void printAnimal() {
		System.out.println("\n" + this.nume + " detine animalele: ");

		//verificare daca stapanul detine animale
		if(listaAnimal.isEmpty()) {
			System.out.println(" (niciun animal inregistrat in sistem)");
		}
		else {
			for(Animal animal : listaAnimal) {
				System.out.println("\n - " + animal.getNume() + ", " + animal.getSpecie());
			}
		}
	}

	//afisarea inregistrarii stapanului
	public void fisaStapan() {
		System.out.println("--------------------------------------------------");
		System.out.println("INREGISTRARE stapan [" + id + "]");
		System.out.println("	* nume: " + nume);
		System.out.println("	* telefon: " + telefon);
		System.out.println(" 	* adresa: " + adresa);
		System.out.println(" 	* email: " + email);
		System.out.println("	* animale: ");

		//verificare daca stapanul detine animale
		if(listaAnimal.isEmpty()) {
			System.out.println(" (niciun animal inregistrat in sistem)");
		}
		else {
			for(Animal animal : listaAnimal) {
				System.out.println("\n			- " + animal.getNume() + ", " + animal.getSpecie() + ", " + animal.getVarsta());
			}
		}
		System.out.println("-------------------------------------------------\n");
	}

	@Override
	public String toString() {
		return "\n - Stapan [" + id + "]" +
				"\n		|-> nume: " + nume +
				"\n		|-> telefon: " + telefon +
				"\n		|-> adresa: " + adresa +
				"\n		|-> email: " + email +
				"\n		|-> nr. animale: " + listaAnimal.size() + "\n";
	}
}

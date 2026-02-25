import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class MedicVeterinar {
	private int id;
	private String nume;
	private String specializare;
	//array pentru evitenta programarilor medicului
	private ArrayList<Programare> programare;

	public MedicVeterinar() {
		this.programare = new ArrayList<>();
	}

	public MedicVeterinar(int id, String nume, String specializare) {
		this.id = id;
		this.nume = nume;
		this.specializare = specializare;
		this.programare = new ArrayList<>();
	}

	public int getId() { return id; }
	public void setId(int id) { this.id = id; }

	public String getNume() { return nume; }
	public void setNume(String nume) {
		if(nume != null && !nume.trim().isEmpty()) {
			this.nume = nume;
		}
	}

	public String getSpecializare() { return specializare; }
	public void setSpecializare(String specializare) {
		if(specializare != null && !specializare.trim().isEmpty()) {
			this.specializare = specializare;
		}
	}

	public ArrayList<Programare> getProgramare() { return programare; }
	public void setProgramare(ArrayList<Programare> programare) { this.programare = programare; }

	//adaugarea unei programari
	public void addProgramare(Programare newProgramare) {
		if(newProgramare != null && !programare.contains(newProgramare)) {
			programare.add(newProgramare);
		}
	}

	//stergerea unei programari
	public void removeProgramare(Programare newProgramare) {
		if(newProgramare != null && programare.contains(newProgramare)) {
			programare.remove(newProgramare);
		}
	}

	//verificarea disponibilitatii unui doctor pe data si la ora ceruta
	public boolean isAvailable(LocalDate data, LocalTime ora) {
		for(Programare p : programare) {
			if(p.getData().equals(data) && p.getOra().equals(ora)) {
				return false;
			}
		}
		return true;
	}

	//afisarea programarilor unui medic
	public void porgramMedic() {
		System.out.println("--------------------------------------------------");
		System.out.println("PROGRAMARI dr. " + nume);
		for(Programare p : programare) {
			System.out.println("	* " + p);
		}
		System.out.println("--------------------------------------------------");
	}

	@Override
	public String toString() {
		return "\n - MedicVeterinar [" + id + "]" +
				"\n		|-> nume: " + nume +
				"\n		|-> specializare: " + specializare;
	}
}

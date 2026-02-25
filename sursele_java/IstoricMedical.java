import java.time.LocalDate;

public abstract class IstoricMedical {
	protected int id;
	protected LocalDate data;
	protected Animal animal;
	protected MedicVeterinar medic;
	protected String observatii;

	public IstoricMedical(int id, LocalDate data, Animal anima, MedicVeterinar medic, String observatii) {
		this.id = id;
		this.data = data;
		this.animal = anima;
		this.medic = medic;
		this.observatii = observatii;
	}

	public int getId() { return id; }
	public void setId(int id) { this.id = id; }

	public LocalDate getData() { return data; }
	public void setData(LocalDate data) { this.data = data; }

	public Animal getAnimal() { return animal; }
	public void setAnimal(Animal animal) { this.animal = animal; }

	public MedicVeterinar getMedic() { return medic; }
	public void setMedic(MedicVeterinar medic) { this.medic = medic; }

	public String getObservatii() { return observatii; }
	public void setObservatii(String observatii) { this.observatii = observatii; }

	public abstract String detaliiTratament();

	@Override
	public String toString() {
		return "\n - IstoricMedical [" + id + "]" +
				"\n		|-> data: " + data +
				"\n		|-> medic: " + medic +
				"\n		|-> observatii: " + observatii;
	}
}

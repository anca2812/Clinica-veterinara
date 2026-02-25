import java.time.LocalDate;
import java.time.LocalTime;

public class Programare implements Comparable<Programare>{
	//crearea programare (animal, medic veterinar, data, ora,
	//tip consultatie: control, vaccin, interventei)
	private int id;
	private Animal animal;
	private MedicVeterinar medic;
	private LocalDate data;
	private LocalTime ora;
	private TipProgramare tip;

	public Programare(int id, Animal animal, MedicVeterinar medic, LocalDate data, LocalTime ora,
					  TipProgramare tip) throws MedicIndisponibilException{
		if(!medic.isAvailable(data, ora)) {
			throw new MedicIndisponibilException("Medicul nu este disponibil!");
		}

		this.id = id;
		this.animal = animal;
		this.medic = medic;
		this.data = data;
		this.ora = ora;
		this.tip = tip;

		medic.addProgramare(this);
	}

	public int getId() { return id; }
	public void setId(int id) { this.id = id; }

	public Animal getAnimal() { return animal; }
	public void setAnimal(Animal animal) { this.animal = animal; }

	public MedicVeterinar getMedic() { return medic; }
	public void setMedic(MedicVeterinar medic) { this.medic = medic; }

	public LocalDate getData() { return data; }
	public void setData(LocalDate data) {
		if(data != null)
			this.data = data;
	}

	public LocalTime getOra() { return ora; }
	public void setOra(LocalTime ora) { this.ora = ora; }

	public TipProgramare getTip() { return tip; }
	public void setTip(TipProgramare tip) { this.tip = tip; }

	@Override
	public int compareTo(Programare o) {
		if(this.data.compareTo(o.getData()) != 0) {
			return this.data.compareTo(o.getData());
		}

		return this.ora.compareTo(o.getOra());
	}

	@Override
	public String toString() {
		return "\nProgramare [" + id + "]" +
				"\n		* animal: " + animal +
				"\n		* medic: " + medic +
				"\n\n		* data: " + data +
				"\n		* ora: " + ora +
				"\n		* tip: " + tip;
	}
}

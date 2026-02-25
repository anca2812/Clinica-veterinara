import java.time.LocalDate;

public class Vaccinare extends IstoricMedical{
	private String tipVaccin;
	private LocalDate nextDoza;

	public Vaccinare(int id, LocalDate data, Animal animal, MedicVeterinar medic, String tipVaccin, LocalDate nextDoza) {
		super(id, data, animal, medic, "Vaccinare");
		this.tipVaccin = tipVaccin;
		this.nextDoza = nextDoza;
	}

	public String getTipVaccin() { return tipVaccin; }
	public void setTipVaccin(String tipVaccin) { this.tipVaccin = tipVaccin; }

	public LocalDate getNextDoze() { return nextDoza; }
	public void setNextDoze(LocalDate nextDoza) { this.nextDoza = nextDoza; }

	@Override
	public String detaliiTratament() {
		return "\nVaccinare | tip: " + tipVaccin +
				", data administrarii: " + data +
				", data urmatoarei doze: " + nextDoza;
	}
}

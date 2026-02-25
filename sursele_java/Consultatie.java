import java.time.LocalDate;

public class Consultatie extends IstoricMedical {
	private String diagnostic;
	private String tratament;

	public Consultatie(int id, LocalDate data, Animal animal, MedicVeterinar medic, String diagnostic, String tratament) {
		super(id, data, animal, medic, "Consultatie generala");
		this.diagnostic = diagnostic;
		this.tratament = tratament;
	}

	public String getDiagnostic() { return diagnostic; }
	public void setDiagnostic(String diagnostic) { this.diagnostic = diagnostic; }

	public String getTratament() { return tratament; }
	public void setTratament(String tratament) { this.tratament = tratament; }

	@Override
	public String detaliiTratament() {
		return "\nConsultatie | diagnostic: " + diagnostic +
				", tratament: " + tratament;
	}
}

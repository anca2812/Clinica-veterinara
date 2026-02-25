import java.time.LocalDate;

public class Interventie extends IstoricMedical{
	//ex: operatie, procedura
	private String tipInterventie;
	private int durataMin;

	public Interventie(int id, LocalDate data, Animal animal, MedicVeterinar medic, String observatii, String tipInterventie, int durataMin) {
		super(id, data, animal, medic, observatii);
		this.tipInterventie = tipInterventie;
		this.durataMin = durataMin;
	}

	public String getTipInterventie() { return tipInterventie; }
	public void setTipInterventie(String tipInterventie) { this.tipInterventie = tipInterventie; }

	public int getDurataMin() { return durataMin; }
	public void setDurataMin(int durataMin) { this.durataMin = durataMin; }

	@Override
	public String detaliiTratament() {
		return "\nInterventie | tip: " + tipInterventie +
				", data: " + data +
				", durata: " + durataMin + " minute" +
				", medic responsabil: " + medic;
	}
}

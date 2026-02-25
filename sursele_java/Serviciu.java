public class Serviciu {
	//definire servicii (consultatie, vaccin, interventie) cu tarife
	private int id;
	private TipProgramare denumire;
	private double tarif;

	public Serviciu(int id, TipProgramare denumire, double tarif) {
		this.id = id;
		this.denumire = denumire;
		this.tarif = tarif;
	}

	public int getId() { return id; }
	public void setId(int id) { this.id = id; }

	public TipProgramare getDenumire() { return denumire; }
	public void setDenumire(TipProgramare denumire) { this.denumire = denumire; }

	public double getTarif() { return tarif; }
	public void setTarif(double tarif) {
		if(tarif >= 0)
			this.tarif = tarif;
	}

	@Override
	public String toString() {
		return "\n - Serviciu [" + id + "]" +
				"\n		|-> denumire: " + denumire +
				"\n		|-> tarif: " + tarif + " RON";
	}
}

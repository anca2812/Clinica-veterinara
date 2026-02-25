public class Produs {
	//definire produse (medicamente, hrana, accesorii) cu pret si stoc
	private int id;
	private String denumire;
	private double pret;
	private int stoc;

	public Produs(int id, String denumire, double pret, int stoc) {
		this.id = id;
		this.denumire = denumire;
		this.pret = pret;
		this.stoc = stoc;
	}

	public int getId() { return id; }
	public void setId(int id) { this.id = id; }

	public String getDenumire() { return denumire; }
	public void setDenumire(String denumire) { this.denumire = denumire; }

	public double getPret() { return pret; }
	public void setPret(double pret) { this.pret = pret; }

	public int getStoc() { return stoc; }
	public void setStoc(int stoc) { this.stoc = stoc;}

	//vinderea si actualizarea stocului unui produs
	public void sellProdus(int cantitate) throws StocInsuficientException {
		if(cantitate < 0) {
			return;
		}

		if(this.stoc < cantitate) {
			throw new StocInsuficientException("Stoc insuficient produs " + denumire + '!');
		}

		//actualizare stoc produse la vanzare
		this.stoc = this.stoc - cantitate;
	}

	@Override
	public String toString() {
		return "\n - Produs [" + id + "]" +
				"\n		|-> denumire: " + denumire +
				"\n		|-> pret: " + pret +
				"\n		|-> stoc: " + stoc;
	}
}

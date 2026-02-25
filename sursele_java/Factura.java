import java.time.LocalDate;
import java.util.ArrayList;

public class Factura {
	private int id;
	private Stapan stapan;
	private LocalDate data;
	private ArrayList<Serviciu> listaServicii;
	private ArrayList<Produs> listaProduse;
	private double totalServicii;
	private double totalProduse;
	private double total;

	public Factura(int id, Stapan stapan, LocalDate data) {
		this.id = id;
		this.stapan = stapan;
		this.data = data;
		this.listaServicii = new ArrayList<>();
		this.listaProduse = new ArrayList<>();
		this.totalServicii = 0.0;
		this.totalProduse = 0.0;
		this.total = 0.0;
	}

	public int getId() { return id; }
	public void setId(int id) { this.id = id; }

	public Stapan getStapan() { return stapan; }
	public void setStapan(Stapan stapan) { this.stapan = stapan; }

	public LocalDate getData() { return data; }
	public void setData(LocalDate data) { this.data = data; }

	public ArrayList<Serviciu> getListaServicii() { return listaServicii; }
	public void setListaServicii(ArrayList<Serviciu> listaServicii) {
		this.listaServicii = listaServicii;
	}

	public ArrayList<Produs> getListProduse() { return listaProduse; }
	public void setListProduse(ArrayList<Produs> listaProduse) {
		this.listaProduse = listaProduse;
	}

	public double getTotalServicii() { return totalServicii; }
	public void setTotalServicii(double totalServicii) { this.totalServicii = totalServicii; }

	public double getTotalProduse() { return totalProduse; }
	public void setTotalProduse(double totalProduse) { this.totalProduse = totalProduse; }

	public double getTotal() { return total; }
	public void setTotal(double total) { this.total = total; }

	//adaugarea unui serviciu
	public void addServiciu(Serviciu serviciu) {
		if(serviciu != null) {
			listaServicii.add(serviciu);
			totalServicii = totalServicii + serviciu.getTarif();
			sumTotal();
		}
	}

	//adaugarea unui produs
	public void addProdus(Produs produs, int cantitate) {
		try {
			produs.sellProdus(cantitate);
			listaProduse.add(produs);
			totalProduse = totalProduse + produs.getPret() * cantitate;
			sumTotal();
		}
		catch(StocInsuficientException e) {
			System.out.println(e.getMessage());
		}
	}

	//calcularea totalului
	public void sumTotal() {
		total = totalServicii + totalProduse;
	}

	//afisarea facturii
	public void afisareFactura() {
		System.out.println("-------------------------------------------------");
		System.out.println("FACTURA nr. " + id + " data " + data);
		System.out.println("Stapana: " + stapan.getNume());

		System.out.println("\nServicii: ");
		for(Serviciu serviciu : listaServicii) {
			System.out.println("	* " + serviciu.getDenumire() + ": " + serviciu.getTarif() + " RON");
		}

		System.out.println("\nProduse: ");
		for(Produs produs : listaProduse) {
			System.out.println("	* " + produs.getDenumire() + ": " + produs.getPret() + " RON");
		}

		System.out.println("\nTotal servicii: " + totalServicii + "RON");
		System.out.println("Total produse: " + totalProduse + "RON");
		System.out.println("Total total: " + total + "RON");
		System.out.println("-------------------------------------------------\n");
	}

	@Override
	public String toString() {
		return "\nFactura [" + id + "]" +
				"\n		* stapan: " + stapan +
				"\n		* data: " + data +
				"\n		* listaServicii: " + listaServicii +
				"\n\n		* listaProduse: " + listaProduse +
				"\n\n		* totalServicii: " + totalServicii + " RON" +
				"\n		* totalProduse:" + totalProduse + " RON" +
				"\n		* total: " + total + " RON\n";
	}
}

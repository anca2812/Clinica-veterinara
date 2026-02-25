import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

public class ClinicaVeterinara implements ListaRapoarte{
	//colectii
	private HashMap<String, Stapan> stapani;
	private ArrayList<Animal> animale;
	private ArrayList<MedicVeterinar> medici;
	private ArrayList<Programare> programari;
	private ArrayList<Serviciu> servicii;
	private ArrayList<Produs> produse;
	private ArrayList<Factura> facturi;
	private Rapoarte rapoarte;

	public ClinicaVeterinara() {
		this.stapani = new HashMap<>();
		this.animale = new ArrayList<>();
		this.medici = new ArrayList<>();
		this.programari = new ArrayList<>();
		this.servicii = new ArrayList<>();
		this.produse = new ArrayList<>();
		this.facturi = new ArrayList<>();
		this.rapoarte = new Rapoarte();
	}

	//inregistrare stapan
	public void inregistrareStapan(int id, String nume, String telefon, String adresa, String email) throws Exception {
		if(stapani.containsKey(telefon)) {
			throw new Exception("Stapanul " + nume + " cu numarul de telefon " + telefon + " exista deja!");
		}

		Stapan stapan = new Stapan(id, nume, telefon, adresa, email);
		stapani.put(telefon, stapan);
	}

	//inregistrare animal
	public void inregistrareAnimal(int id, String nume, String specie, String rasa, int varsta, String microcip, String telefonStapan) throws StapanInexistentException {
		Stapan stapan = stapani.get(telefonStapan);
		if(stapan == null) {
			throw new StapanInexistentException("Stapanul cu numarul de telefon " + telefonStapan + " nu exista!");
		}

		Animal animal = new Animal (id, nume, specie, rasa, varsta, microcip, stapan);
		animale.add(animal);
		stapan.addAnimal(animal);
	}

	//afisare fisa completa animal
	public void afisareFisaAnimal(int idAnimal) throws AnimalInexistentException {
		Animal animal = findAnimal(idAnimal);
		animal.fisaAnimal();
		animal.fisaIstoricmedical();
	}

	//creare programare
	public void createProgramare(int id, int idAnimal, int idMedic, LocalDate data, LocalTime ora, TipProgramare tip)
			throws AnimalInexistentException, MedicIndisponibilException {
		Animal animal = findAnimal(idAnimal);
		MedicVeterinar medic = findMedic(idMedic);

		if(!medic.isAvailable(data, ora)) {
			throw new MedicIndisponibilException("Medic indisponibil la data " + data + " si ora " + ora + "!");
		}

		Programare programare = new Programare(id, animal, medic, data, ora, tip);
		programari.add(programare);
		rapoarte.addProgramare(programare);
		medic.addProgramare(programare);
	}

	//afisare programari pentru un anumic medic
	public void afisareProgramMedic(int idMedic) {
		try {
			MedicVeterinar medic = findMedic(idMedic);
			medic.porgramMedic();
		}
		catch(MedicIndisponibilException e) {
			System.out.println("Medicul nu exista: " + e.getMessage());
		}
	}

	//afisare programari pentru un anumic animal
	public ArrayList<Programare> afisareProgramAnimal(int idAnimal) {
		ArrayList<Programare> programare = new ArrayList<>();

		for(Programare p : programari) {
			if(p.getAnimal().getId() == idAnimal) {
				programare.add(p);
			}
		}

		Collections.sort(programare);
		return programare;
	}

	public Animal findAnimal(int id) throws AnimalInexistentException {
		for(Animal a : animale) {
			if(a.getId() == id) {
				return a;
			}
		}
		throw new AnimalInexistentException("Animalul cu id-ul " + id + " nu exista!");
	}

	public MedicVeterinar findMedic(int id) throws MedicIndisponibilException {
		for(MedicVeterinar mv : medici) {
			if(mv.getId() == id) {
				return mv;
			}
		}
		throw new MedicIndisponibilException("Medicul cu id-ul " + id + " nu exista!");
	}

	//inregistrare consultatie efectuata
	public void inregistrareConsultatie(int id, LocalDate data, int idAnimal, int idMedic, String diagnostic, String tratament) throws AnimalInexistentException, MedicIndisponibilException {
		Animal animal = findAnimal(idAnimal);
		MedicVeterinar medic = findMedic(idMedic);

		Consultatie consultatie = new Consultatie(id, data, animal, medic, diagnostic, tratament);
		animal.addIstoricMedical(consultatie);
	}

	//inregistrare intervetie
	public void inregistrareInterventie(int id, LocalDate data, int idAnimal, int idMedic, String tip, int durataMin, String observatii) throws AnimalInexistentException, MedicIndisponibilException {
		Animal animal = findAnimal(idAnimal);
		MedicVeterinar medic = findMedic(idMedic);

		Interventie interventie = new Interventie(id, data, animal, medic, observatii, tip, durataMin);
		animal.addIstoricMedical(interventie);
	}

	//inregistrare vaccinare
	public void inregistrareVaccinare(int id, int idAnimal, int idMedic, String tip, LocalDate dataDozei, LocalDate dataNextDoza) throws AnimalInexistentException, MedicIndisponibilException {
		Animal animal = findAnimal(idAnimal);
		MedicVeterinar medic = findMedic(idMedic);

		Vaccinare vaccinare = new Vaccinare(id, dataDozei, animal, medic, tip, dataNextDoza);
		animal.addIstoricMedical(vaccinare);
	}

	//facturare servicii si produse
	public void addServiciu(int id, TipProgramare denumire, double tarif) {
		Serviciu serviciu = new Serviciu(id, denumire, tarif);
		servicii.add(serviciu);
	}

	public void addProdus(int id, String denumire, double pret, int stoc) {
		Produs produs = new Produs(id, denumire, pret, stoc);
		produse.add(produs);
	}

	public Factura createFactura(int id, String telefonStapan, ArrayList<Integer> idServicii, ArrayList<Integer> idProduse, ArrayList<Integer> cantitati)
		throws StapanInexistentException {
		Stapan stapan = stapani.get(telefonStapan);
		if(stapan == null) {
			throw new StapanInexistentException("Stapanul cu numarul de telefon " + telefonStapan + " nu exista!");
		}

		Factura factura = new Factura(id, stapan, LocalDate.now());
		for(Integer idS : idServicii) {
			Serviciu serviciu = findServiciu(idS);
			factura.addServiciu(serviciu);
		}

		for(int i=0; i<idProduse.size(); i++) {
			int idP = idProduse.get(i);
			int cantitate = cantitati.get(i);
			Produs produs = findProdus(idP);
			factura.addProdus(produs, cantitate);
		}

		facturi.add(factura);
		rapoarte.addFactura(factura);
		return factura;
	}

	public Serviciu findServiciu(int id) {
		for(Serviciu s : servicii) {
			if(s.getId() == id) {
				return s;
			}
		}
		return null;
	}

	public Produs findProdus(int id) {
		for(Produs p : produse) {
			if(p.getId() == id) {
				return p;
			}
		}
		return null;
	}

	//rapoarte
	//lista programarilor pe zi
	@Override
	public void raportZi(LocalDate data) {
		rapoarte.raportZi(data);
	}

	//lista programarilor pe saptamana
	@Override
	public void raportSaptamana(LocalDate start, LocalDate end) {
		rapoarte.raportSaptamana(start, end);
	}

	//animale cu vaccinari viitoare intr-un anumit interval
	public void raportVaccinariViitoare(int intervalZile) {
		rapoarte.raportVaccinariViitoare(animale, intervalZile);
	}

	//venit total pe perioada, separat pe servicii si produse
	@Override
	public void raportVenituri(LocalDate start, LocalDate end) {
		rapoarte.raportVenituri(start, end);
	}

	public static Comparator<Animal> animalComparatorNume = Comparator.comparing(Animal::getNume);
	public static Comparator<Animal> animalComparatorSpecie = Comparator.comparing(Animal::getSpecie);

	//adaugarea unui medic
	public void addMedic(int id, String nume, String specializare) {
		MedicVeterinar medic = new MedicVeterinar(id, nume, specializare);
		medici.add(medic);
	}
}

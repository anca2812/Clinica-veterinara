import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class DemoTest {
	private ClinicaVeterinara clinica;

	//executare inainte de fiecare test
	@BeforeEach
	void setUp() throws Exception {
		clinica = new ClinicaVeterinara();

		//adaugare medici
		clinica.addMedic(1, "Dr. Ion", "general");
		clinica.addMedic(2, "Dr. Maria", "chirurgie");

		//inregistrare stapani
		clinica.inregistrareStapan(1, "Ana Popescu", "0712345678", "Strada 1", "ana@email.com");
		clinica.inregistrareStapan(2, "Ion Ionescu", "0722222222", "Strada Florilor", "ion@email.com");

		//inregistrare animal
		clinica.inregistrareAnimal(1, "Rex", "caine", "labrador", 3, "123456", "0712345678");
		clinica.inregistrareAnimal(2, "Miti", "pisica", "siameza", 2, "MIC456", "0722222222");

		//adaugare servicii si produse
		clinica.addServiciu(1, TipProgramare.CONTROL, 120);
		clinica.addServiciu(2, TipProgramare.VACCIN, 80);
		clinica.addServiciu(3, TipProgramare.INTERVENTIE, 300);
		clinica.addProdus(1, "medicament X", 50, 10);
		clinica.addProdus(2, "hrana uscata", 150, 5);

		//creare programare
		clinica.createProgramare(1, 1, 1, LocalDate.of(2025, 3, 10), LocalTime.of(10, 0), TipProgramare.VACCIN);
		clinica.createProgramare(2, 2, 2, LocalDate.of(2025, 3, 12), LocalTime.of(12, 0), TipProgramare.CONTROL);

		//inregistrare vaccinare animal
		clinica.inregistrareVaccinare(1, 1, 1, "antirabic", LocalDate.of(2025, 3, 10), LocalDate.of(2026, 3, 10));
		clinica.inregistrareConsultatie(2, LocalDate.of(2025, 3, 10), 2, 2, "raceala", "tratament antibiotic");
	}

	//teste inregistrare stapan
	@Test
	void testInregistrareStapanSucces() {
		assertDoesNotThrow(() ->
				clinica.inregistrareStapan(3, "Maria Atomei", "0732456716", "Strada 5", "maria@gmail.com"));
	}

	@Test
	void testInregistrareStapanTelefonDublicat() {
		Exception e = assertThrows(Exception.class, () ->
				clinica.inregistrareStapan(4, "Matei Atome", "0712345678", "Strada 9", "matei@gmail.com"));

		assertTrue(e.getMessage().isEmpty(), "Telefonul stapanului deja exista!");
	}

	//teste inregistrare animal
	@Test
	void testInregistarareAnimalSucces() {
		assertDoesNotThrow(() ->
				clinica.inregistrareAnimal(3, "Cookie", "pisica", "british", 1, "3456", "0722222222"));
	}

	@Test
	void testInregistrareAnimalFaraStapan() {
		Exception e = assertThrows(Exception.class, () ->
				clinica.inregistrareAnimal(4, "Tom", "hamster", "hamster", 1, "12345", "07223333"));

		assertTrue(e.getMessage().isEmpty(), "Stapanul animalului lipseste!");
	}

	//teste crearea programare
	@Test
	void testCreareProgramareSucces() {
		assertDoesNotThrow(() ->
				clinica.createProgramare(1, 2, 1, LocalDate.of(2024, 12, 4), LocalTime.of(10, 0), TipProgramare.CONTROL));
	}

	@Test
	void testCreareProgramareMedicIndisponibil() {
		Exception e = assertThrows(Exception.class, () ->
				clinica.createProgramare(2, 1, 2, LocalDate.of(2025, 3, 12), LocalTime.of(12, 0), TipProgramare.INTERVENTIE));

		assertTrue(e.getMessage().isEmpty(), "Medicul este indisponibil!");
	}

	@Test
	void testCreareProgramareMedicInexistent() {
		Exception e = assertThrows(Exception.class, () ->
				clinica.createProgramare(2, 1, 3, LocalDate.of(2025, 8, 5), LocalTime.of(12, 0), TipProgramare.VACCIN));

		assertTrue(e.getMessage().isEmpty(), "Medicul nu exista!");
	}

	//test inregistrare consultatie
	@Test
	void testInregistrareConsultatie() {
		assertDoesNotThrow(() ->
				clinica.inregistrareConsultatie(1, LocalDate.of(2024, 5, 12), 2, 1, "diagnostic test", "tratement test"));
	}

	//test inregistrare vaccin
	@Test
	void testInregistrareVaccin() {
		assertDoesNotThrow(() ->
				clinica.inregistrareVaccinare(2, 2, 1, "vaccin test", LocalDate.of(2025, 1, 8), LocalDate.now().plusDays(30)));
	}

	//test inregistrare interventie
	@Test
	void testInregistrareInterventie() {
		assertDoesNotThrow(() ->
				clinica.inregistrareInterventie(1, LocalDate.now(), 1, 2, "tip test", 60, "obs test"));
	}

	//teste creare factura
	@Test
	void testEmitereFacturaSucces() {
		ArrayList<Integer> listaServicii = new ArrayList<>();
		listaServicii.add(3);
		listaServicii.add(4);

		ArrayList<Integer> listaProduse = new ArrayList<>();
		listaProduse.add(1);

		ArrayList<Integer> cantitatiProduse = new ArrayList<>();
		cantitatiProduse.add(4);

		assertDoesNotThrow(() ->
				clinica.createFactura(2, "0712345678", listaServicii, listaProduse, cantitatiProduse));
	}

	@Test
	void testEmitereFacturaStapanInexistent() {
		ArrayList<Integer> listaServicii = new ArrayList<>();
		listaServicii.add(3);
		listaServicii.add(4);

		ArrayList<Integer> listaProduse = new ArrayList<>();
		listaProduse.add(1);

		ArrayList<Integer> cantitatiProduse = new ArrayList<>();
		cantitatiProduse.add(4);

		assertDoesNotThrow(() ->
				clinica.createFactura(2, "07111111", listaServicii, listaProduse, cantitatiProduse));
	}

	//teste rapoarte
	@Test
	void testRaportZi() {
		assertDoesNotThrow(() ->
				clinica.raportZi(LocalDate.of(2025, 8, 5)));
	}

	void testRaportSaptamana() {
		assertDoesNotThrow(() ->
				clinica.raportSaptamana(LocalDate.of(2025, 8, 5), LocalDate.of(2025, 9, 5)));
	}

	void testRaportVaccinuriViitoare() {
		assertDoesNotThrow(() ->
				clinica.raportVaccinariViitoare(20));
	}

	void testRaportVenituri() {
		assertDoesNotThrow(() ->
				clinica.raportVenituri(LocalDate.of(2025, 8, 5), LocalDate.of(2025, 9, 5)));
	}

	//teste fise animale
	@Test
	void testAfisareFisaAnimal() {
		assertDoesNotThrow(() ->
				clinica.afisareFisaAnimal(1));
	}

	@Test
	void testAfisareFisaAnimalInexistent() {
		Exception e = assertThrows(Exception.class, () ->
				clinica.afisareFisaAnimal(999));

		assertTrue(e.getMessage().isEmpty(), "Fisa animalului nu exista!");
	}
}
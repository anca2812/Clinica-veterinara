import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {
	public static void main(String[] ags) {
	/*
	un sistem pentru o clinica veterinara care sa permita evidenta animalelor si
	a stapanilor lor, programarea consultatiilor si vaccinarilor, inregistrarea tratamentelor
	si interventiilor, precum si facturarea serviciilor si produselor vandute (medicamente, hrana)
	*/

		ClinicaVeterinara clinica = new ClinicaVeterinara();

		try {
			//adaugare medici
			clinica.addMedic(1, "Dr. Ion", "general");
			clinica.addMedic(2, "Dr. Maria", "chirurgie");

			//inregistrare stapani
			clinica.inregistrareStapan(1, "Ana Popescu", "0712345678", "Strada 1", "ana@email.com");
			clinica.inregistrareStapan(2, "Ion Ionescu", "0722222222", "Strada Florilor", "ion@email.com");


			//inregistrare animal
			clinica.inregistrareAnimal(1, "Rex", "caine", "labrador", 3, "123456", "0712345678");
			clinica.inregistrareAnimal(2, "Miti", "pisica", "siameza", 2, "MIC456", "0722222222");
			//testare stapan inexistent
			//clinica.inregistrareAnimal(3, "Test", "caine", "bichon", 1, "MIC999", "0000000000");

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

			//creare factura
			ArrayList<Integer> idServ = new ArrayList<>(Arrays.asList(1, 2));
			ArrayList<Integer> idProd = new ArrayList<>(Arrays.asList(1, 2));
			ArrayList<Integer> cantitate = new ArrayList<>(Arrays.asList(1, 1));

			Factura factura = clinica.createFactura(1, "0712345678", idServ, idProd, cantitate);
			System.out.println("--- FACTURA EMISA --- " + factura);

			//afisarea fisei animalului
			clinica.afisareFisaAnimal(1);
			clinica.afisareFisaAnimal(2);

			//rapoarte
			clinica.raportZi(LocalDate.of(2025, 3, 10));
			clinica.raportVaccinariViitoare(30);
			clinica.raportVenituri(LocalDate.now().minusDays(30), LocalDate.now());

		} catch (AnimalInexistentException | MedicIndisponibilException | StapanInexistentException | StocInsuficientException e) {
			System.out.println("Exceptie prinsa: " + e.getMessage());
		}
		catch (Exception e) {
			System.out.println("Eroare generala: " + e.getMessage());
		}
	}
}

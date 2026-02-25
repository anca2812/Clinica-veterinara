import java.time.LocalDate;

public interface ListaRapoarte {
	//lista programarilor pe zi
	void raportZi(LocalDate data);

	//lista programarilor pe saptamana
	void raportSaptamana(LocalDate start, LocalDate end);

	//venit total pe perioada, separat pe servicii si produse
	void raportVenituri(LocalDate start, LocalDate end);
}

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

/*
JFrame - clasa care permite crearea si gestionarea unui ferestre intr-o aplicatie Java
	   - fereastra principala pentru aplicatiile Java bazate pe interfata grafica cu utilizatorului (GUI)
*/
public class ClinicaVeterinaraGUI extends JFrame {
	//realizarea legaturii intre GUI si logica aplicatiei
	private ClinicaVeterinara clinica;

	public ClinicaVeterinaraGUI() {
		clinica = new ClinicaVeterinara();

		try {
			//initializarea cu cateva valori
			clinica.addMedic(1, "Dr. Ion", "general");
			clinica.addMedic(2, "Dr. Maria", "chirurgie");

			clinica.addServiciu(1, TipProgramare.VACCIN, 80);
			clinica.addServiciu(2, TipProgramare.CONTROL, 120);
			clinica.addServiciu(3, TipProgramare.VACCIN, 180);

			clinica.addProdus(1, "hrana uscata", 100, 5);
			clinica.addProdus(2, "medicament x", 30, 10);
			clinica.addProdus(3, "jucarie", 15, 30);
		}
		catch(Exception e) {
			//JOptionPane - creaza o caseta dialog (ex. caseta de dialog pt mesaje, de confirmatie, de intrare sau de optiuni)
			//.showMessageDialog - metoda afiseaza o caseta de dialog cu mesajul specific
			JOptionPane.showMessageDialog(null, "Eroare initializare ClinicaVeterinara " + e.getMessage());
			//folosit pentru testarea exceptiilor
		}

		//setTitlu - seteaze titlul JFrame-ului
		setTitle("Clinica Veterinara");
		//setDefaultCloseOperation - seteaza operatia implicita de inchidere pt JFrame
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//setSize - seteaza dimensiunea JFrame-ului
		setSize(800, 600);
		//setLayout - seteaza managerul de aspect pt JFrame
		setLayout(new BorderLayout());

		//JLabel - clasa pentru afisarea unei pictograme
		JPanel headerPanel = new JPanel(new BorderLayout());
		headerPanel.setBorder(BorderFactory.createEmptyBorder(17, 10, 17, 10));

		JLabel titlu = new JLabel("Clinica Veterinara ≽^•⩊•^≼");
		titlu.setHorizontalAlignment(SwingConstants.CENTER);
		titlu.setFont(new Font("Aptos", Font.BOLD, 30));
		titlu.setForeground(new Color(131, 166, 151));

		headerPanel.add(titlu, BorderLayout.CENTER);
		add(headerPanel, BorderLayout.NORTH);

		//crearea unui panel cu butoane
		JPanel panel = new JPanel(new GridLayout(4, 2, 5, 7));
		panel.setBorder(BorderFactory.createEmptyBorder(17, 17, 17, 17));
		this.add(panel);

		//crearea butoanelui
		JButton inregistrareStapanButton = new JButton("Inregistrare Stapan");

		JButton inregistrareAnimalButton = new JButton("Inregistrare Animal");
		JButton creareProgramareButton = new JButton("Creare Programare");
		JButton raportControlButton = new JButton("Raport Control");
		JButton raportVaccinButton = new JButton("Raport Vaccin");
		JButton raportInterventieButton = new JButton("Raport Interventie");
		JButton emitereFacturaButton = new JButton("Emitere Factura");
		JButton afisareRaportButton = new JButton("Afisare Raport");
		JButton fisaAnimalButton = new JButton("Fisa Completa Animal");

		panel.add(inregistrareStapanButton);
		panel.add(inregistrareAnimalButton);
		panel.add(creareProgramareButton);
		panel.add(raportControlButton);
		panel.add(raportVaccinButton);
		panel.add(raportInterventieButton);
		panel.add(emitereFacturaButton);
		panel.add(afisareRaportButton);
		panel.add(fisaAnimalButton);

		colorButtons(panel);

		//crearea actiunilor pentru butoane
		inregistrareStapanButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				inregistrareStapan();
			}
		});

		inregistrareAnimalButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				inregistrareAnimal();
			}
		});

		creareProgramareButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				creareProgramare();
			}
		});

		raportControlButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				raportControl();
			}
		});

		raportVaccinButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				raportVaccin();
			}
		});

		raportInterventieButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				raportInterventie();
			}
		});

		emitereFacturaButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				emitereFactura();
			}
		});

		afisareRaportButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				afisareRaport();
			}
		});

		fisaAnimalButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				fisaAnimal();
			}
		});
	}

	//metoda pentru colorarea butoanelor
	private void colorButtons(Container container) {
		Color normal = new Color(131, 166, 151);
		Color hover = new Color(76, 166, 107);

		for(Component c : container.getComponents()) {
			if(c instanceof JButton btn) {
				btn.setBackground(normal);
				btn.setForeground(Color.WHITE);
				btn.setFocusPainted(false);
				btn.setOpaque(true);
				btn.setContentAreaFilled(true);
				btn.setBorderPainted(false);
				btn.setFont(new Font("Aptos", Font.BOLD, 14));

				btn.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseEntered(MouseEvent e) {
						btn.setBackground(hover);
					}
					@Override
					public void mouseExited(MouseEvent e) {
						btn.setBackground(normal);
					}
				});
			}
		}
	}

	private void inregistrareStapan() {
		//JTextField - constructor care creaza un nou TextField
		JTextField idField = new JTextField();
		JTextField numeField = new JTextField();
		JTextField telefonField = new JTextField();
		JTextField adresaField = new JTextField();
		JTextField emailField = new JTextField();

		Object[] mesaj = {
				"ID: ", idField,
				"Nume: ", numeField,
				"Telefon: ", telefonField,
				"Adresa: ", adresaField,
				"Email: ", emailField
		};

		int confirmare = JOptionPane.showConfirmDialog(this, mesaj, "Inregistrare Stapan", JOptionPane.OK_CANCEL_OPTION);
		if(confirmare == JOptionPane.OK_OPTION) {
			try {
				int id = Integer.parseInt(idField.getText());
				String nume = numeField.getText();
				String telefon = telefonField.getText();
				String adresa = adresaField.getText();
				String email = emailField.getText();

				clinica.inregistrareStapan(id, nume, telefon, adresa, email);
				JOptionPane.showMessageDialog(null, "Stapan inregistrat cu succes!");
			} catch (Exception e) {
				JOptionPane.showMessageDialog(this, "Eroare inregistrare stapan:  " + e.getMessage());
			}
		}
	}

	private void inregistrareAnimal() {
		JTextField idField = new JTextField();
		JTextField numeField = new JTextField();
		JTextField specieField = new JTextField();
		JTextField rasaField = new JTextField();
		JTextField varstaField = new JTextField();
		JTextField microcipField = new JTextField();
		JTextField telefonStapanField = new JTextField();

		Object[] mesaj = {
				"ID: ", idField,
				"Nume: ", numeField,
				"Specie: ", specieField,
				"Rasa: ", rasaField,
				"Varsta: ", varstaField,
				"Microcip: ", microcipField,
				"Telefon stapan: ", telefonStapanField
		};

		int confirmare = JOptionPane.showConfirmDialog(this, mesaj, "Inregistrare Animal", JOptionPane.OK_CANCEL_OPTION);
		if(confirmare == JOptionPane.OK_OPTION) {
			try {
				int id = Integer.parseInt(idField.getText());
				String nume = numeField.getText();
				String specie = specieField.getText();
				String rasa = rasaField.getText();
				int varsta = Integer.parseInt(varstaField.getText());
				String microcip = microcipField.getText();
				String telefon = telefonStapanField.getText();

				clinica.inregistrareAnimal(id, nume, specie, rasa, varsta, microcip, telefon);
				JOptionPane.showMessageDialog(null, "Animal inregistrat cu succes!");
			} catch (Exception e) {
				JOptionPane.showMessageDialog(this, "Eroare inregistrare animal:  " + e.getMessage());
			}
		}
	}

	private void creareProgramare() {
		JTextField idField = new JTextField();
		JTextField animalField = new JTextField();
		JTextField medicField = new JTextField();
		JTextField dataField = new JTextField();
		JTextField oraField = new JTextField();
		JComboBox<TipProgramare> tipCombo = new JComboBox<>(TipProgramare.values());

		Object[] mesaj = {
				"ID: ", idField,
				"ID animal: ", animalField,
				"ID medic: ", medicField,
				"Data (YYYY-MM-DD): ", dataField,
				"Ora (HH:MM): ", oraField,
				"Tip: ", tipCombo
		};

		int confirmare = JOptionPane.showConfirmDialog(this, mesaj, "Creare Programare", JOptionPane.OK_CANCEL_OPTION);
		if(confirmare == JOptionPane.OK_OPTION) {
			try {
				int id = Integer.parseInt(idField.getText());
				int idAnimal = Integer.parseInt(animalField.getText());
				int idMedic = Integer.parseInt(medicField.getText());
				LocalDate data = LocalDate.parse(dataField.getText());
				LocalTime ora = LocalTime.parse(oraField.getText());
				TipProgramare tip = (TipProgramare) tipCombo.getSelectedItem();

				clinica.createProgramare(id, idAnimal, idMedic, data, ora, tip);
				JOptionPane.showMessageDialog(null, "Programare inregistrata cu succes!");

			} catch (Exception e) {
				JOptionPane.showMessageDialog(this, "Eroare creare programare: " + e.getMessage());
			}
		}
	}

	private void raportControl() {
		JTextField idField = new JTextField();
		JTextField dataField = new JTextField();
		JTextField animalField = new JTextField();
		JTextField medicField = new JTextField();
		JTextField diagnosticField = new JTextField();
		JTextField tratamentField = new JTextField();

		Object[] mesaj = {
				"ID: ", idField,
				"Data (YYYY-MM-DD): ", dataField,
				"ID animal: ", animalField,
				"ID medic: ", medicField,
				"Diagnostic: ", diagnosticField,
				"Tratament: ", tratamentField
		};

		int confirmare = JOptionPane.showConfirmDialog(this, mesaj, "Raport Consult", JOptionPane.OK_CANCEL_OPTION);
		if(confirmare == JOptionPane.OK_OPTION) {
			try {
				int id = Integer.parseInt(idField.getText());
				LocalDate data = LocalDate.parse(dataField.getText());
				int idAnimal = Integer.parseInt(animalField.getText());
				int idMedic = Integer.parseInt(medicField.getText());
				String diagnostic = diagnosticField.getText();
				String tratament = tratamentField.getText();

				clinica.inregistrareConsultatie(id, data,idAnimal,idMedic,diagnostic,tratament);
				JOptionPane.showMessageDialog(null, "Consult inregistrat cu succes!");
			} catch (Exception e) {
				JOptionPane.showMessageDialog(this, "Eroare raport consult: " + e.getMessage());
			}
		}
	}

	private void raportVaccin() {
		JTextField idField = new JTextField();
		JTextField animalField = new JTextField();
		JTextField medicField = new JTextField();
		JTextField tipField = new JTextField();
		JTextField dataDozaField = new JTextField();
		JTextField nextDozaField = new JTextField();

		Object[] mesaj = {
				"ID: ", idField,
				"ID animal: ", animalField,
				"ID medic: ", medicField,
				"Tip vaccin: ", tipField,
				"Data dozei (YYYY-MM-DD): ", dataDozaField,
				"Data urmatoarei doze (YYYY-MM-DD): ", nextDozaField
		};

		int confirmare = JOptionPane.showConfirmDialog(this, mesaj, "Raport Vaccin", JOptionPane.OK_CANCEL_OPTION);
		if(confirmare == JOptionPane.OK_OPTION) {
			try {
				int id = Integer.parseInt(idField.getText());
				int idAnimal = Integer.parseInt(animalField.getText());
				int idMedic = Integer.parseInt(medicField.getText());
				String tip = tipField.getText();
				LocalDate dataDoza = LocalDate.parse(dataDozaField.getText());
				LocalDate nextDoza = LocalDate.parse(nextDozaField.getText());

				clinica.inregistrareVaccinare(id, idAnimal, idMedic, tip, dataDoza, nextDoza);
				JOptionPane.showMessageDialog(null, "Vaccin inregistrat cu succes!");
			} catch (Exception e) {
				JOptionPane.showMessageDialog(this, "Eroare raport vaccin: " + e.getMessage());
			}
		}
	}

	private void raportInterventie() {
		JTextField idField = new JTextField();
		JTextField animalField = new JTextField();
		JTextField medicField = new JTextField();
		JTextField dataField = new JTextField();
		JTextField tipField = new JTextField();
		JTextField durataField = new JTextField();
		JTextField obsField = new JTextField();

		Object[] mesaj = {
				"ID: ", idField,
				"Data: (YYYY-MM-DD)", dataField,
				"ID animal: ", animalField,
				"ID medic: ", medicField,
				"Tip interventie: ", tipField,
				"Durata interventie: ", durataField,
				"Observatii: ", obsField
		};

		int confirmare = JOptionPane.showConfirmDialog(this, mesaj, "Raport Interventie", JOptionPane.OK_CANCEL_OPTION);
		if(confirmare == JOptionPane.OK_OPTION) {
			try {
				int id = Integer.parseInt(idField.getText());
				LocalDate data = LocalDate.parse(dataField.getText());
				int idAnimal = Integer.parseInt(animalField.getText());
				int idMedic = Integer.parseInt(medicField.getText());
				String tip = tipField.getText();
				int durataMin = Integer.parseInt(durataField.getText());
				String obs = obsField.getText();

				clinica.inregistrareInterventie(id, data, idAnimal, idMedic, tip, durataMin, obs);
				JOptionPane.showMessageDialog(null, "Interventie inregistrata cu succes!");
			} catch (Exception e) {
				JOptionPane.showMessageDialog(this, "Eroare raport interventie: " + e.getMessage());
			}
		}

	}

	private void emitereFactura() {
		JTextField idField = new JTextField();
		JTextField telefonStapanField = new JTextField();
		JTextField idServiciiField = new JTextField();
		JTextField idProduseField = new JTextField();
		JTextField cantitatiProduseField = new JTextField();

		Object[] mesaj = {
			"ID: ", idField,
			"Telefon stapan: ", telefonStapanField,
			"ID servicii: ", idServiciiField,
			"ID produse: ", idProduseField,
			"Cantitati produse: ", cantitatiProduseField
		};

		int confirmare = JOptionPane.showConfirmDialog(this, mesaj, "EDmitere Factura", JOptionPane.OK_CANCEL_OPTION);
		if(confirmare == JOptionPane.OK_OPTION) {
			try {
				int id = Integer.parseInt(idField.getText());
				String telefonStapan = telefonStapanField.getText();
				String[] idServiciiString = idServiciiField.getText().split(",");
				ArrayList<Integer> idServicii = new ArrayList<>();
				for(String s : idServiciiString) {
					idServicii.add(Integer.parseInt(s.trim()));
				}
				String[] idProduseString = idProduseField.getText().split(",");
				ArrayList<Integer> idProduse = new ArrayList<>();
				for(String s : idProduseString) {
					idProduse.add(Integer.parseInt(s.trim()));
				}
				String[] cantitatiProduseString = cantitatiProduseField.getText().split(",");
				ArrayList<Integer> cantitatiProduse = new ArrayList<>();
				for(String s : cantitatiProduseString) {
					cantitatiProduse.add(Integer.parseInt(s.trim()));
				}

				Factura factura = clinica.createFactura(id, telefonStapan, idServicii, idProduse, cantitatiProduse);
				JOptionPane.showMessageDialog(this, "Factura emisa cu succes!" + factura);
			} catch (Exception e) {
				JOptionPane.showMessageDialog(this, "Eroare emitere factura: " + e.getMessage());
			}
		}
	}

	private void afisareRaport() {
		String[] optiuniRapoarte = {"Lista programarilor pe zi", "Lista programarilor pe saptamana",
				"Lista vaccinarilor viitoare", "Venit total"};

		int optiune = JOptionPane.showOptionDialog(this, "Selectare Raport", "Rapoarte",
				JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, optiuniRapoarte, optiuniRapoarte[0]);

		if(optiune == 0) {
			String dataString = JOptionPane.showInputDialog(this, "Introduceti ziua (YYYY-MM-DD): ");
			if(dataString != null) {
				LocalDate data = LocalDate.parse(dataString);

				clinica.raportZi(data);
				JOptionPane.showMessageDialog(this, "Lista programarilor pe zi este afisata in consola!");
			}
		}
		else if(optiune == 1) {
			String startString = JOptionPane.showInputDialog(this, "Introduceti ziua de start (YYYY-MM-DD): ");
			String endString = JOptionPane.showInputDialog(this, "Introduceti ziua de sfarsit (YYYY-MM-DD): ");
			if(startString != null && endString != null) {
				LocalDate start = LocalDate.parse(startString);
				LocalDate end = LocalDate.parse(endString);

				clinica.raportSaptamana(start, end);
				JOptionPane.showMessageDialog(this, "Lista programarilor pe saptamana este afisata in consola!");
			}
		}
		else if(optiune == 2) {
			String ziString = JOptionPane.showInputDialog(this, "Introduceti intervalul de zile: ");
			if(ziString != null) {
				int intervalZile = Integer.parseInt(ziString);

				clinica.raportVaccinariViitoare(intervalZile);
				JOptionPane.showMessageDialog(this, "Lista vaccinurilor viitoare este afisata in consola!");
			}
		}
		else if(optiune == 3) {
			String startString = JOptionPane.showInputDialog(this, "Introduceti ziua de start (YYYY-MM-DD): ");
			String endString = JOptionPane.showInputDialog(this, "Introduceti ziua de sfarsit (YYYY-MM-DD): ");
			if(startString != null && endString != null) {
				LocalDate start = LocalDate.parse(startString);
				LocalDate end = LocalDate.parse(endString);

				clinica.raportVenituri(start, end);
				JOptionPane.showMessageDialog(this, "Venitul total este afisat in consola!");
			}
		}
	}

	private void fisaAnimal() {
		String idAnimalString = JOptionPane.showInputDialog(this, "Introduceti ID-ul animalului: ");
		if(idAnimalString != null) {
			try {
				int idAnimal = Integer.parseInt(idAnimalString);

				clinica.afisareFisaAnimal(idAnimal);
				JOptionPane.showMessageDialog(this, "Fisa animalului este afisata!");
			}
			catch(Exception e) {
				JOptionPane.showMessageDialog(this, "Eroare afisare fisa animalului: " + e.getMessage());
			}
		}
	}

	//main ClinicaVeterinaraGUI
	public static void main(String[] args) {
		//crearea si afisarea cadrului principal al aplicatiei
		SwingUtilities.invokeLater(() ->
				new ClinicaVeterinaraGUI().setVisible(true));
	}
}
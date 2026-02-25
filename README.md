# **Clinică Veterinară ≽^•⩊•^≼**


## Descriere generală 

Acest proiect implementează un sistem pentru **o clinică veterinară** care să permită evidența animalelor și a stăpânilor lor, programarea consultațiilor și vaccinărilor, înregistrarea tratamentelor și intervențiilor, precum și facturarea serviciilor și produselor vândute (medicamente, hrană).

## Cerințe funcționalități 

Gestionare stăpâni și animale:
- **înregistrare stăpân** - nume, telefon, adresă, e-mail
- **înregistrare animal** - nume, specie, rasă, vârstă, număr microcip, stăpân asociat - număr de telefon
- afișare fișă completă animal - date, stăpân, istoric medical
- un stăpân poate avea mai multe animale
  
Programări consultații și vaccinări
- **creare programare** - animal, medic veterinar, dată, oră, tip consultație: control, vaccin, intervenție
- verificare disponibilitate medic în intervalul respectiv 
- afișare programări pentru un anumit medic sau pentru un anumit animal
  
Evidență tratamente și intervenții
- **înregistrare consultații efectuate** - diagnostic, tratament recomandat
- **înregistrare intervenție** (ex: operație, procedură) cu dată, durată, medic responsabil)
- **înregistrare vaccinare** - tip vaccin, data administrării, data următoarei doze
- afișare istoric medical pentru un animal (listă consultații, intervenții, vaccinări)
  
Facturare servicii și produse 
- **definire servicii** (consultație, vaccin, intervenție) cu tarife 
- **definire produse** (medicamente, hrană, accesorii) cu preț și stoc
- creare factură
    - selectare servicii și produse pentru un anumit animal/stăpân
	- calcul total (suma serviciilor + produse)
- actualizare stoc produse la vânzare

Rapoarte
- lista programărilor pe zi/săptămână
- animale cu vaccinări viitoare într-un anumit interval (ex: următoarele 30 de zile)
- venit total pe perioadă, separat pe servicii și produse

## Rulare proiect 

În folderul sursele_java se află toate sursele necesare pentru rularea sistemului:
pentru testarea unui demo se va accesa clasa Main, unde se va implementa ClinicaVeterinara clinica, înregistrându-se stăpânii, animalele, medicii, informațiile necesare pentru istoricul medical și afișându-se în consolă factura, fișa animalului și rapoarte pentru testarea interfeței grafice (GUI) se va accesa clasa ClinicaVeterinaraGUI, se va deschide o fereastră cu butoane, cerând date cu dialoguri și redirecționând totul către metodele deja implementate în ClinicaVeterinara, apoi afișând mesaje despre introducerea cu succes a datelor sau eroare și, pentru rapoarte, se folosește consola pentru testarea completă a scenariilor se va accesa clasa DemoTest, unde, prin intermediul clasei JUnit, se verifică cam toate cerințele funcționale ale clinicii folosind un set comun de date de test

## Cerințe de sistem 

Necesită: JDK

Proiect realizat cu: IntelliJ IDEA versiunea 2025.3.1.1

## Structura proiectului

ClinicaVeterinara îi oferă unui stăpân posibilitatea de a își înregistra atât datele lui, cât și ale animalelor lui, folosindu-se doar de numărul său de telefon pentru a se realiza legătura dintre stăpân-animal. Acesta poate crea programări în funcție de tipul acesteia (control, vaccin, intervenție), după introducerea datelor primește un mesaj de succes sau de eroare, doctorul căutat nu există în clinică sau nu este disponibil în perioada selectată. Pe lângă toate acestea, se pot vizualiza și rapoartele din urma programărilor, având și un buton de emitere a facturii cu produsele și serviciile.
	
Acest sistem vine și în ajutorul medicilor veterinari, cuprinzând toate programările pe care le poate avea unul dintre aceștia într-o zi sau săptămână, vaccinările viitoare sau veniturile dintr-un interval dat. Pentru a evita folosirea unui carnet de sănătate a animalului, există și fișa completă cu toate informațiile privind istoricul medical.

## Concluzii

Proiectul realizează un sistem complet de management pentru stăpâni, animale, programări, tratamente, facturi și rapoarte, acoperind toate cerințele funcționale din enunț. Separarea între logica aplicației (ClinicaVeterinara), interfața grafică și testele automate face ca sistemul să fie ușor de folosit, testat și extins. Acest mod de organizare permite atât rularea unui demo în consolă, cât și utilizarea GUI-ului și verificarea funcționalităților prin teste JUnit, fără a amesteca cerințele. În ansamblu, proiectul prezintă aplicarea principiilor POO (clase, ierarhii, interfețe, colecții, excepții) într-un scenariu realist de clinică veterinară. 


##SZUT Schulprojekt

# NHPlus

## Informationen zur Lernsituation
Du bist Mitarbeiter der HiTec GmbH, die seit über 15 Jahren IT-Dienstleister und seit einigen Jahren ISO/IEC 27001 zertifiziert ist. Die HiTec GmbH ist ein mittelgroßes IT-Systemhaus und ist auf dem IT-Markt mit folgenden Dienstleistungen und Produkten vetreten: 

Entwicklung: Erstellung eigener Softwareprodukte

Consulting: Anwenderberatung und Schulungen zu neuen IT- und Kommunikationstechnologien , Applikationen und IT-Sicherheit

IT-Systembereich: Lieferung und Verkauf einzelner IT-Komponenten bis zur Planung und Installation komplexer Netzwerke und Dienste

Support und Wartung: Betreuung von einfachen und vernetzten IT-Systemen (Hard- und Software)

Für jede Dienstleistung gibt es Abteilungen mit spezialisierten Mitarbeitern. Jede Abteilung hat einen Abteilungs- bzw. Projektleiter, der wiederum eng mit den anderen Abteilungsleitern zusammenarbeitet.

 

## Projektumfeld und Projektdefinition

Du arbeitest als Softwareentwickler in der Entwicklungsabteilung. Aktuell bist du dem Team zugeordnet, das das Projekt "NHPlus" betreut. Dessen Auftraggeber - das Betreuungs- und Pflegeheim "Curanum Schwachhausen" - ist ein Pflegeheim im Bremer Stadteil Schwachhausen - bietet seinen in eigenen Zimmern untergebrachten Bewohnern umfangreiche Therapie- und Serviceleistungen an, damit diese so lange wie möglich selbstbestimmt und unabhängig im Pflegeheim wohnen können. Curanum Schwachhausen hat bei der HiTec GmbH eine Individualsoftware zur Verwaltung der Patienten und den an ihnen durchgeführten Behandlungen in Auftrag gegeben. Aktuell werden die Behandlungen direkt nach ihrer Durchführung durch die entsprechende Pflegekraft handschriftlich auf einem Vordruck erfasst und in einem Monatsordner abgelegt. Diese Vorgehensweise führt dazu, dass Auswertungen wie z.B. welche Behandlungen ein Patient erhalten oder welche Pflegkraft eine bestimmte Behandlung durchgeführt hat, einen hohen Arbeitsaufwand nach sich ziehen. Durch NHPlus soll die Verwaltung der Patienten und ihrer Behandlungen elektronisch abgebildet und auf diese Weise vereinfacht werden.

Bei den bisher stattgefundenen Meetings mit dem Kunden konnten folgende Anforderungen an NHPlus identifiziert werden:

- alle Patienten sollen mit ihrem vollen Namen, Geburtstag, Pflegegrad, dem Raum, in dem sie im Heim untergebracht sind, sowie ihrem Vermögensstand erfasst werden.

- Die Pflegekräfte werden mit ihrem vollen Namen und ihrer Telefonnumer erfasst, um sie auf Station schnell erreichen zu können.

- jede Pflegekraft erfasst eine Behandlung elektronisch, indem sie den Patienten, das Datum, den Beginn, das Ende, die Behandlungsart sowie einen längeren Text zur Behandlung erfasst.

- Die Software muss den Anforderungen des Datenschutzes entsprechen. 

- Die Software ist zunächst als Desktopanwendung zu entwickeln, da die Pflegekräfte ihre Behandlungen an einem stationären Rechner in ihrem Aufenthaltsraum erfassen sollen.

 

Da in der Entwicklungsabteilung der HiTech GmbH agile Vorgehensweisen vorgeschrieben sind, wurde für NHPlus Scum als Vorgehensweise gewählt.

 

## Stand des Projektes

In den bisherigen Sprints wurden die Module zur Erfassung der Patienten- und Behandlungsdaten fertiggestellt. Es fehlt das Modul zur Erfassung der Pflegekräfte. Deswegen kann bisher ebenfalls nicht erfasst werden, welche Pflegekraft eine bestimmte Behandlung durchgeführt hat. In der letzten Sprint Review sind von der Curanum Schwachhausen Zweifel angebracht worden, dass die bisher entwickelte Software den Anforderungen des Datenschutzes genügt.

## Technische Hinweise

Wird das Open JDK verwendet, werden JavaFX-Abhängigkeiten nicht importiert. Die Lösung besteht in der Installation der neuesten JDK-Version der Firma Oracle.

## Technische Hinweise zur Datenbank

- Benutzername: SA
- Passwort: SA
- Bitte nicht in die Datenbank schauen, während die Applikation läuft. Das sorgt leider für einen Lock, der erst wieder verschwindet, wenn IntelliJ neugestartet wird!



# User Stories und Testfälle

## Benutzeranmeldung
User Story:
Als Mitarbeiter möchte ich eine Benutzeranmeldung mit Passwortschutz haben, damit eine Rechteverteilung unter den Nutzern gegeben ist.
Akzeptanzkriterien:
-	A_1: Eine Login Maske sollte zu sehen sein
-	A_2: Die Login Daten werden in einer Datenbank gespeichert
-	A_3: Der Admin kann Accounts erstellen 

Tasks:
-	T_1: Neue SQL-Tabelle mit dem Namen Accounts
o	Felder:
o	Nutzername
o	Passwort
o	Rolle (Admin/Mitarbeiter)
-	T_2: Login View erstellen
-	T_3: Admin View erstellen
o	Darin können neue Accounts erstellt werden
-	T_3: Login Controller erstellen
o	Ruft die Methoden von AccountDAO auf
-	T_4: AccountDAO erstellen
o	Login()

Testfälle:
-	TF_1: 
o	Vorbedingung:
	Der Nutzer startet die Anwendung
o	Auszuführende Testschritte:
	Der Nutzer gibt ein richtiges Passwort und ein richtigen Usernamen ein
o	Erwartetes Ergebnis:
	Der Nutzer wird eingeloggt
-	TF_2:
o	Vorbedingung:
	Der Nutzer startet die Anwendung
o	Auszuführende Testschritte:
	Der Nutzer gibt ein falsches Passwort, aber einen richtigen Usernamen ein
o	Erwartetes Ergebnis:
	Der Nutzer wird nicht eingeloggt
-	TF_3:
o	Vorbedingung:
	Der Nutzer startet die Anwendung
o	Auszuführende Testschritte:
	Der Nutzer gibt ein richtiges Passwort, aber einen falschen Usernamen ein
o	Erwartetes Ergebnis:
	Der Nutzer wird nicht eingeloggt
-	TF_4:
o	Vorbedingung:
	Der Nutzer startet die Anwendung
o	Auszuführende Testschritte:
	Der Nutzer gibt ein falsches Passwort und einen falschen Usernamen ein
o	Erwartetes Ergebnis:
	Der Nutzer wird nicht eingeloggt

## Feld „Vermögensstand“ löschen:
User Story:
Als Datenschutzbeauftragter möchte ich, dass das Feld „Vermögensstand“ entfernet wird, damit ich dafür garantieren kann, dass due DSGVO eingehalten wird
Akzeptanzkriterien:
-	A_1: Die Möglichkeit, einen Vermögensstand zu speichern ist nicht mehr vorhanden
-	A_2: Die vorhandenen Daten zum Vermögensstand werden gelöscht
-	A_3: Die Spalte „Vermögensstand“ in der Tabelle im Frontend ist entfernt
Tasks:
-	T_1: Das Feld wird aus der SQL Tabelle entfernt
-	T_2: Das Feld Vermögensstand wird aus dem Frontend entfernt
o	Textfeld
o	Spalte in der Tabelle
-	T_3: Der Vermögensstand wird aus der Patientenklasse entfernt
o	PatientDAO
-	T_4: Die Methoden, die den Vermögensstand verwalten werden aus dem PatientController entfernt
o	onEditHandleAssets (komplett entfernt)
o	add (assets entfernen)
Testfälle:
-	TF_1: 
o	Vorbedingung:
	Der Nutzer ist eingeloggt
o	Auszuführende Testschritte:
	Der Nutzer ruft die Patiententabelle auf
o	Erwartetes Ergebnis:
	Die Spalte und das Textfeld „Vermögensstand“ existiert nicht

-	TF_2:
o	Vorbedingung:
	Der Nutzer ist ein Administrator und hat vollen Zugriff auf die SQL Datenbank
o	Auszuführende Testschritte:
	Der Nutzer macht ein Query und sucht nach dem Feld Vermögensstand in der Patiententabelle
o	Erwartetes Ergebnis:
	Die Query gibt nichts zurück

## Löschen von Patientendaten ist erst nach 10 Jahren erlaubt, davor sperren:
User Story:
Als Leiter der Einrichtung möchte ich, dass die Patientendaten zehn Jahren lang aufbewahrt werden, damit ich die Patientendaten einsehen kann, falls ich für Komplikationen verantwortlich gemacht werde, oder der Patient nach der Behandlung wieder eintritt. Außerdem möchte ich die Daten im Fall eines Einrichtungswechsels des Patienten, an eine andere Einrichtung weitergeben. Zusätzlich habe ich ein Interesse daran, dass sich unsere Praxis an die gesetzlich vorgeschriebene Aufbewahrungspflicht hält.
Akzeptanzkriterien:
-	A_1: Die Daten werden bei Austritt des Patienten gesperrt
-	A_2: Die Daten werden bei Überschreitung von 10 Jahren gelöscht
Tasks:
-	T_1: Im PatientView ist ein Button zum Sperren und Entsperren des zugehörigen Patienten
o	Wenn gesperrt dann „Behandlung wiederaufnehmen“
o	Wenn entsperrt das „Behandlung beenden“
-	T_2: In jede Tabelle kommt das Feld „Sperrkennzeichen“ (Boolean)
o	Wird bei Sperrung auf „true“ gesetzt
-	T_3: Im DAO interface wird eine lock() und unlock() Methode als Kopf hinzugefügt
-	T_4: Im DAOimpl werden diese Methoden durch setzen des Sperrungskennzeichens implementiert
-	T_5: Beim Drücken dieses Buttons wird eine Methode des AllPatientsControllers aufgerufen
o	Ruft die Methode vom Patienten auf, die für die Sperrung verantwortlich ist (lock, unlock)
-	T_6: In jede SQL-Tabelle kommt das Feld „Sperrkennzeichen“ (Boolean)
o	Wird bei Sperrung auf „true“ gesetzt
-	T_7: In jeder SQL-Tabelle kommt das Feld „SperrungsTimestamp“
o	Wird nur einmal bei Sperrung gesetzt
-	T_8: Der Sperrungs-Timestamp jedes Satzes wird regelmäßig geprüft
o	Ist der Sperrungs-Timestamp + 10 Jahre > Heute?
	Wenn ja, dann wird der Satz gelöscht
-	T_9: Der Administrator kann gesperrte Sätze einsehen und entsperren.
o	Checkbox: Gesperrte Sätze anzeigen
o	Nur für den Admin Sichtbar
-	T_10: Der normale Mitarbeiter kann sperren, aber nicht entsperren.

Testfälle:
-	TF_1: 
o	Vorbedingung:
	Der Nutzer ist eingeloggt und im Patientenview
o	Auszuführende Testschritte:
	Der Nutzer drückt auf den Button „Behandlung beenden“
o	Erwartetes Ergebnis:
	Der Satz verschwindet aus der Tabelle

-	TF_2:
o	Vorbedingung:
	Der Admin ist eingeloggt, im Patientenview und hat Checkbox „Gesperrte Anzeigen“ angehakt
o	Auszuführende Testschritte:
	Der Admin drückt wählt einen Satz aus und drückt auf den Button „Behandlung wiederaufnehmen“
	Der Admin loggt sich aus und ein normaler Nutzer loggt sich ein
	Der Nutzer navigiert ins Patientenview
o	Erwartetes Ergebnis:
	Der Nutzer kann den Satz, der vorher gesperrt war, wieder einsehen

-	TF_3:
o	Vorbedingung:
	Der Nutzer ist eingeloggt und im Patientenview
o	Auszuführende Testschritte:
	Der Nutzer wählt einen Patienten aus und drückt auf „Behandlung beenden“
	Der Nutzer setzt die Systemzeit auf jetzt +10 Jahre
	Der Nutzer starten NHPlus
	Der Admin startet eine Query auf die Patietentabelle, in der nach dem gesperrten Nutzer gesucht wird
o	Erwartetes Ergebnis:
	Die Query gibt keine Ergebnisse zurück

-	TF_4:
o	Vorbedingung:
	Der Nutzer ist eingeloggt und im Patientenview
o	Auszuführende Testschritte:
	Der Nutzer wählt einen Patienten aus und drückt auf „Behandlung beenden“
	Der Admin macht eine Query, in der nach dem gesperrten Nutzer gesucht wird.
o	Erwartetes Ergebnis:
	In dem Ergebnis der SQL Query ist im Feld „SperrungsTimestamp“ das heutige Datum zu sehen
	In dem Ergebnis der SQL Query ist das Feld „Gesperrt“ auf „true“ gesetzt

## Pfleger erfassen:
User Story:
Als Geschäftsführer möchte ich an der Navigationsleiste am linken Rand einen neuen Button bekommen, mit dem ich auf eine Pflegeransicht wechseln kann, die ihre ID, Nach- und Vornamen, sowie ihre Telefonnummer anzeigt. Dazu möchte ich, die Daten der Pfleger an die Behandlung anbinden, damit ich nachvollziehen kann, wer welche Behandlung durchgeführt hat.
Akzeptanzkriterien:
-	A_1: Menüpunkt in der Navigationsbar, der die AllCaregiverView aufruft
-	A_2: Pfleger wird zu den Behandlungen abgespeichert
-	A_3: In der Treatment/NewTreatmentView wird die Option hinterlegt, über eine Combobox den zuständigen Pfleger zur Behandlung zu speichern
-	A_4: In der AllTreatmentView werden der Name, Vorname und die Telefonnummer der Pfleger pro Behandlung angezeigt
Tasks:
-	T_1: Pfleger SQL-Tabelle erstellen
o	ID
o	Vorname
o	Nachname
o	Telefonnummer
-	T_2: Behandlungs SQL-Tabelle um das Feld Pfleger-ID erweitern
-	T_3: Pfleger Model erstellen
-	T_4: Pfleger DAO erstellen
o	getNameSurnameAndTelNoFromId(ID)
o	getAllCaregivers() -> <Id, Name>
-	T_5: TreatmentDAO erweitern auf das Feld Pfleger ID
o	Methoden müssen geändert werden
-	T_6: Treatment Model erweitern um die Variale Pfleger ID
-	T_7: AllCaregiverController erstellen
-	T_8: In der NewTreatment/TreatmentView die die Combobox mit den Pflegern hinzufügen
o	Zeigt die Namen an
o	Schickt die ID an den Controller
-	T_9: All Treatment Controller wird erweitert:
o	onEditTreatment()
	PflegerDAO aufrufen
•	getNameSurnameAndTelNoFromId(ID)
-	T_10: NewTreatment- und Treatment Controller werden erweitert:
	PflegerDAO aufrufen
•	getAllCaregivers()
-	T_11: In der AllTreatmentView Vor- Nachname und Telefonnummer des Pflegers anzeigen
-	T_12: In der Navigationsleiste den Button einfügen, der auf das AllCaregiverView zeigt
o	Der WindowController bekommt die Logik, auf das AllCaregiverView zu verweisen
Testfälle:
-	TF_1:
o	Vorbedingung:
	User ist eingeloggt
o	Auszuführende Testschritte:
	Der Nutzer klickt auf den „Pfleger/innen“ Button
o	Erwartetes Ergebnis:
	Der Nutzer landet auf der AllCaregiverView
-	TF_2:
o	Vorbedingung:
	User ist eingeloggt und ist im AllTreatmentView
o	Auszuführende Testschritte:
	Der Nutzer fügt eine neue Behandlung hinzu
	Der Nutzer wählt einen Pfleger aus der Combobox aus
	Der Nutzer drückt auf „Anlegen“
o	Erwartetes Ergebnis:	
	In der AllTreatmentView ist jetzt der Vorname, Nachname und die Telefonnummer des Pflegers neben der Behandlung zu sehen
-	TF_3:
o	Vorbedingung:
	User ist eingeloggt und ist im AllTreatmentView
o	Auszuführende Testschritte:
	Der Nutzer fügt eine neue Behandlung hinzu
o	Erwartetes Ergebnis:	
	Der Nutzer sieht eine ComboBox mit allen Pflegern
-	TF_4:
o	Vorbedingung:
	User ist eingeloggt
o	Auszuführende Testschritte:
	Der Nutzer navigiert in die AllTreatmentView
o	Erwartetes Ergebnis:	
	In der AllTreatmentView ist jetzt der Vorname, Nachname und die Telefonnummer des Pflegers neben der Behandlung zu sehen
-	TF_5:
o	Vorbedingung:
	User ist eingeloggt und ist im AllTreatmentView
o	Auszuführende Testschritte:
	Der Nutzer ändert eine Behandlung
o	Erwartetes Ergebnis:	
	Der Nutzer sieht eine ComboBox mit allen Pflegern

## CSV Export:
User Story:
Als Datenschutzbeauftragter möchte ich die Möglichkeit eines CSV-Exports haben, damit ich den Nutzern die Daten, die über sie gespeichert werden, auf Anfrage zur Verfügung stellen kann.
Akzeptanzkriterien:
-	A_1: In der Patientenview ist der Button „Datenexport“
-	A_2: Drückt man auf den Button, öffnet sich ein „Speichern unter Dialog“, in dem der User den Pfad angeben kann
-	A_3: Ist der Pfad ausgewählt, wird ein CSV Dokument gespeichert, in dem alle Daten des Patienten aufgelistet sind

Tasks:
-	T_1: Button „Datenexport“ in der Patientenview anlegen
-	T_2: Neue Klasse CSVManager in Utils
o	Hat alle Models
o	Methoden:
	exportPatient(id)
	exportCaregiver(id)
-	T_3: AllPatientController erweitern um Speicher unter Dialog 
Testfälle:
-	TF_1:
o	Vorbedingung:
	User ist eingeloggt und ist im AllPatientView
o	Auszuführende Testschritte:
	Der Nutzer wählt einen Satz aus
	Der Nutzer drückt auf Datenexport
	Der Nutzer wählt einen Pfad über den Speichern-unter Dialog
o	Erwartetes Ergebnis:	
	Der Nutzer sieht im gewählten Pfad ein CSV Dokument mit den Daten des gewählten Satzes
-	TF_2:
o	Vorbedingung:
	User ist eingeloggt und ist im AllCaregiverView
o	Auszuführende Testschritte:
	Der Nutzer wählt einen Satz aus
	Der Nutzer drückt auf Datenexport
	Der Nutzer wählt einen Pfad über den Speichern-unter Dialog
o	Erwartetes Ergebnis:	
	Der Nutzer sieht im gewählten Pfad ein CSV Dokument mit den Daten des gewählten Satzes

## Passwörter als Hash-Werte speichern
User Story:

Als Mitarbeiter, möchte ich die über meine Passwörter verschlüsselt haben, da ich immer und überall dasselbe benutze.

Akzeptanzkriterien:

-	A_1: Das Passwort wird nie in normalen Text gespeichert
Tasks:
-	T_1: AccountModel bekommt eine createHash(String) -> String Methode
-	T_2: Beim Abfragen auf das Passwort, wird auch ein Hash vom Input erstellt und mit der Prüfsumme in der Accounts SQL-Tabelle abgeglichen.
Testfälle:
-	TF_1: 
o	Vorbedingung:
	Admin macht ein SQL Query auf die Accounts Tabelle
o	Auszuführende Testschritte:
	Der Admin sucht nach dem Passwort Feld
o	Erwartetes Ergebnis:	
	Der Admin sieht nicht die wirklich eingegebenen Passwörter


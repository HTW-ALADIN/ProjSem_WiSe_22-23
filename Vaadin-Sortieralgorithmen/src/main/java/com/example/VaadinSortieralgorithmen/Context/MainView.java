package com.example.VaadinSortieralgorithmen.Context;

import com.example.VaadinSortieralgorithmen.Interaktion.Konfigurator_Sortierung;
import com.example.VaadinSortieralgorithmen.Interaktion.Printer;
import com.example.VaadinSortieralgorithmen.Listen.Sortierschritt;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.accordion.Accordion;
import com.vaadin.flow.component.accordion.AccordionPanel;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.confirmdialog.ConfirmDialog;
import com.vaadin.flow.component.details.DetailsVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.provider.ListDataProvider;
import com.vaadin.flow.data.provider.Query;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Route("")
@RouteAlias("start")
public class MainView extends VerticalLayout {

    // ----------------------
    // Globale Variablen ----
    // ----------------------

    // UI Components
    // -------------
    // Forms
    public Component konfigurationForm;
    public Component feldAnzeigenForm;
    public Component uebungsmodusForm;
    public Component pruefungsmodusForm;

    // Layouts
    public VerticalLayout layoutKonfiguration;
    public VerticalLayout layoutUebungsmodus;
    public VerticalLayout layoutPruefungsmodus;

    // Buttons - Konfiguration
    private final Button feldGenerieren = new Button("Feld generieren");
    private final Button sortierungGenerieren = new Button("Sortieren");
    private final Button feldAnzeigen = new Button("Feld anzeigen");
    private final Button uebungsmodus = new Button("Übungsmodus");
    private final Button pruefungsmodus = new Button("Prüfungsmodus");
    // Buttons - Feld anzeigen
    private final Button naechsterSchritt = new Button("Nächster Schritt");
    private final Button alleSchritte = new Button("Alle Schritte anzeigen");
    // Buttons - Übungsmodus
    private final Button tauschenUebungsmodus = new Button("Tauschen");
    // Buttons - Prüfungsmodus
    private final Button tauschenPruefungsmodus = new Button("Tauschen");
    private final Button auswertungPruefungsmodus = new Button("Auswerten");
    // Buttons - Zurück
    private final Button zurKonfigurationFeldAnzeigen = new Button("Zurück zur Konfiguration");
    private final Button zurKonfigurationUebungsmodus = new Button("Zurück zur Konfiguration");
    private final Button zurKonfigurationPruefungsmodus = new Button("Zurück zur Konfiguration");

    // Konfiguration Components
    private final Accordion accordion = new Accordion();
    private final Select<String> zeichenart = new Select<>();
    private final IntegerField anzahl = new IntegerField();
    private final Select<String> reihenfolge = new Select<>();
    private final Select<String> algorithmus = new Select<>();
    // Konfiguration - Zusammenfassung
    private final TextField generiertesFeld = new TextField();
    private final TextField generiertesFeld_ro = new TextField();
    private final TextField algorithmus_ro = new TextField();
    private final TextField reihenfolge_ro = new TextField();
    private final TextField zeichenart_ro = new TextField();

    // Feld anzeigen Components
    private final TextField generiertesFeld_feldAnzeigen = new TextField();
    public Label auswertungstextFeldAnzeigen = new Label("");

    // Übungsmodus Components
    private final TextField generiertesFeld_uebungsmodus = new TextField();
    private final TextField index1Uebungsmodus = new TextField("Index 1");
    private final TextField index2Uebungsmodus = new TextField("Index 2");
    private final TextField statusUebungsmodus = new TextField();
    public Label auswertungstextUebungsmodus = new Label("");

    // Prüfungsmodus Components
    private final TextField generiertesFeld_pruefungsmodus = new TextField();
    private final TextField index1Pruefungsmodus = new TextField("Index 1");
    private final TextField index2Pruefungsmodus = new TextField("Index 2");
    public TextArea auswertungstextPruefungsmodus = new TextArea("");
    public TextArea auswertungstextPunktevergabe = new TextArea("");
    private Label auswertungUeberschrift;
    private Label auswertungUeberschriftListe;
    private Label auswertungUeberschriftVergleich;
    private Label auswertungUeberschriftPunkte;

    // Grids
    private final Grid<Sortierschritt> listGridFeldAnzeigen = new Grid<>();
    private final Grid<Sortierschritt> listGridUebungsmodus = new Grid<>();
    private final Grid<Sortierschritt> listGridPruefungsmodusNutzer = new Grid<>();
    private final Grid<Sortierschritt> listGridPruefungsmodusSystem = new Grid<>();

    // Data Provider
    // -------------
    private ListDataProvider<Sortierschritt> listDataProviderFeldAnzeigen;
    private ListDataProvider<Sortierschritt> listDataProviderUebungsmodus;
    private ListDataProvider<Sortierschritt> listDataProviderPruefungsmodusNutzer;

    // Variablen
    // ---------
    private List<Sortierschritt> liste_system;
    private List<Sortierschritt> liste_nutzer = new ArrayList<>();
    private List<Sortierschritt> emptyData = new ArrayList<>();
    private String[] sortierfeld;
    private String[] sortierfeld_unsortiert;
    private String[] sortierfeld_nutzer;
    private String[] pseudofeld = {"0", "1"};
    private int schritte_zuViel = 0;
    private int schritte_zuWenig = 0;
    private int schritte_richtig = 0;
    private int schritte_falsch = 0;

    // Counter
    // -------
    private final AtomicInteger buttonCounterNaechsterSchritt = new AtomicInteger();
    private final AtomicInteger buttonCounterTauschenSchritt = new AtomicInteger();
    private final AtomicInteger buttonCounterAlleSchritte = new AtomicInteger();
    private final AtomicInteger fehler_in_schritt = new AtomicInteger();
    private final AtomicInteger fehler_insgesamt = new AtomicInteger();

    // Interaction
    // -----------
    private final Printer printer = new Printer();

    // MainView:
    //      - Forms, welche ein- und ausgeblendet werden
    //      - Funktionsaufruf für alle verfügbaren Methoden
    public MainView() {

        // GeneralLayout: permanent eingeblendetes Layout
        AppLayout generalLayout = new AppLayout();
        var title = new H2("Generator für Sortieralgorithmen");
        title.getStyle().set("font-size", "var(--lumo-font-size-xl)")
                .set("margin", "var(--lumo-space-l)");
        generalLayout.getStyle().set("text-align", "center");
        generalLayout.addToNavbar(title);

        // Forms
        this.konfigurationForm = getKonfigurationForm();
        this.feldAnzeigenForm = getFeldAnzeigenForm();
        this.uebungsmodusForm = getUebungsmodusForm();
        this.pruefungsmodusForm = getPruefungsmodusForm();
        konfigurationForm.setVisible(true);
        feldAnzeigenForm.setVisible(false);
        uebungsmodusForm.setVisible(false);
        pruefungsmodusForm.setVisible(false);

        // Components zur MainView hinzufügen
        add(generalLayout);
        add(konfigurationForm);
        add(feldAnzeigenForm);
        add(uebungsmodusForm);
        add(pruefungsmodusForm);

        // Funktionen, die die Auswertung steuern
        feldAnzeigen();
        uebungsmodus();
        pruefungsmodus();

        // Steuerung, welche Form ein- und ausgeblendet werden soll
        feldAnzeigen.addClickListener(f -> {
            konfigurationForm.setVisible(false);
            feldAnzeigenForm.setVisible(true);
            uebungsmodusForm.setVisible(false);
            pruefungsmodusForm.setVisible(false);
        });

        uebungsmodus.addClickListener(u -> {
            konfigurationForm.setVisible(false);
            feldAnzeigenForm.setVisible(false);
            uebungsmodusForm.setVisible(true);
            pruefungsmodusForm.setVisible(false);
        });

        pruefungsmodus.addClickListener(k -> {
            konfigurationForm.setVisible(false);
            feldAnzeigenForm.setVisible(false);
            uebungsmodusForm.setVisible(false);
            pruefungsmodusForm.setVisible(true);
        });

        zurKonfigurationFeldAnzeigen.addClickListener(a -> {
            konfigurationForm.setVisible(true);
            feldAnzeigenForm.setVisible(false);
            uebungsmodusForm.setVisible(false);
            pruefungsmodusForm.setVisible(false);
        });

        zurKonfigurationUebungsmodus.addClickListener(a -> {
            konfigurationForm.setVisible(true);
            feldAnzeigenForm.setVisible(false);
            uebungsmodusForm.setVisible(false);
            pruefungsmodusForm.setVisible(false);
        });

        zurKonfigurationPruefungsmodus.addClickListener(a -> {
            konfigurationForm.setVisible(true);
            feldAnzeigenForm.setVisible(false);
            uebungsmodusForm.setVisible(false);
            pruefungsmodusForm.setVisible(false);
        });
    }

    // getKonfigurationForm:
    //      - erstellt und designt die Konfigurationsform
    //      - fügt der Form Components hinzu
    //      - return: KonfigurationForm
    public Component getKonfigurationForm() {

        // Generelles Layout der Konfigurationsform
        var konfigurationLayout = new VerticalLayout();
        Label konfiguration = new Label("Konfiguration");
        konfiguration.getStyle().set("font-size", "var(--lumo-font-size-xl)")
                .set("margin", "var(--lumo-space-s)");
        konfigurationLayout.add(konfiguration);

        // Feld konfigurieren
        // ------------------
        // Zeichenart
        zeichenart.setLabel("Zeichenart");
        zeichenart.setItems("numerisch", "alphanumerisch");
        zeichenart.setValue("numerisch");
        // Anzahl
        anzahl.setLabel("Anzahl");
        anzahl.setValue(5);
        anzahl.setHasControls(true);
        anzahl.setMin(2);
        // Buttons
        feldGenerieren.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

        // Sortierung konfigurieren
        // ------------------------
        // Generiertes Feld
        generiertesFeld.setVisible(false);
        generiertesFeld.setLabel("Generiertes Feld");
        // Reihenfolge
        reihenfolge.setLabel("Sortierung");
        reihenfolge.setItems("aufsteigend", "absteigend");
        reihenfolge.setValue("aufsteigend");
        // Algorithmus
        algorithmus.setLabel("Algorithmus");
        algorithmus.setItems("Bubblesort", "Insertsort", "Quicksort");
        algorithmus.setValue("Bubblesort");
        // Buttons
        sortierungGenerieren.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

        // Zusammenfassung/Auswertung
        // --------------------------
        generiertesFeld_ro.setVisible(false);
        generiertesFeld_ro.setReadOnly(true);
        generiertesFeld_ro.setLabel("Generiertes Feld");
        algorithmus_ro.setVisible(false);
        algorithmus_ro.setReadOnly(true);
        algorithmus_ro.setLabel("Gewählter Algorithmus");
        reihenfolge_ro.setVisible(false);
        reihenfolge_ro.setReadOnly(true);
        reihenfolge_ro.setLabel("Gewählte Sortierung");
        pruefungsmodus.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

        // Layouts
        // -------
        FormLayout feldKonfigurierenLayout = new FormLayout(zeichenart, anzahl, feldGenerieren);
        feldKonfigurierenLayout.setColspan(feldGenerieren, 3);
        FormLayout sortierungKonfigurierenLayout = new FormLayout(generiertesFeld, reihenfolge, algorithmus, sortierungGenerieren);
        sortierungKonfigurierenLayout.setColspan(generiertesFeld, 3);
        sortierungKonfigurierenLayout.setColspan(sortierungGenerieren, 3);

        FormLayout auswertungKonfigurierenLayout = new FormLayout(generiertesFeld_ro, algorithmus_ro, reihenfolge_ro, feldAnzeigen, uebungsmodus, pruefungsmodus);
        auswertungKonfigurierenLayout.setColspan(generiertesFeld_ro, 3);
        auswertungKonfigurierenLayout.setColspan(algorithmus_ro, 1);
        auswertungKonfigurierenLayout.setColspan(zeichenart_ro, 1);
        auswertungKonfigurierenLayout.setColspan(feldAnzeigen, 1);
        auswertungKonfigurierenLayout.setColspan(uebungsmodus, 1);
        auswertungKonfigurierenLayout.setColspan(pruefungsmodus, 3);

        // Accordion
        // ---------
        AccordionPanel feldKonfigurierenPanel = accordion.add("Feld konfigurieren", feldKonfigurierenLayout);
        feldKonfigurierenPanel.addThemeVariants(DetailsVariant.FILLED);
        AccordionPanel sortierungKonfigurierenPanel = accordion.add("Sortierung konfigurieren", sortierungKonfigurierenLayout);
        sortierungKonfigurierenPanel.addThemeVariants(DetailsVariant.FILLED);
        sortierungKonfigurierenPanel.setEnabled(false);
        AccordionPanel auswertungKonfigurierenPanel = accordion.add("Auswertung wählen", auswertungKonfigurierenLayout);
        auswertungKonfigurierenPanel.addThemeVariants(DetailsVariant.FILLED);
        auswertungKonfigurierenPanel.setEnabled(false);
        add(accordion);
        accordion.setSizeFull();
        accordion.open(feldKonfigurierenPanel);


        // Events
        // ------
        feldGenerieren.addClickListener(e -> {
            // Wenn der Button 'Feld generieren' gedrückt wird, wird ein Feld erzeugt und automatisch im nächsten Panel angezeigt
            Konfigurator_Sortierung konfigurator_sortierung = new Konfigurator_Sortierung();
            this.sortierfeld = konfigurator_sortierung.feld_generieren(this.anzahl.getValue(), this.zeichenart.getValue());

            generiertesFeld.setValue(Arrays.toString(this.sortierfeld));
            generiertesFeld.setVisible(true);

            sortierungKonfigurierenPanel.setEnabled(true);
            accordion.open(sortierungKonfigurierenPanel);
        });

        sortierungGenerieren.addClickListener(f -> {
            // Es sollte möglich sein, das generierte Sortierfeld manuell zu verändern
            // Es wird hier der Wert aus dem Textfeld 'Generiertes Feld' ausgelesen und zum Sortieren verwendet
            // Somit ist es egal, ob das Feld verändert wird oder nicht - es wird immmer der Wert aus dem Textfeld verwendet
            // z.B. [0, 34, 112, 44, 5]
            String sortierfeld = generiertesFeld.getValue();
            // [] abschneiden
            sortierfeld = sortierfeld.substring(1, sortierfeld.length() - 1);
            // Bei ',' splitten und in Sortierfeld speichern
            String[] sortierfeld_ausgelesen = sortierfeld.split(",");
            this.sortierfeld = new String[sortierfeld_ausgelesen.length];
            for (int i = 0; i < sortierfeld_ausgelesen.length; i++) {
                this.sortierfeld[i] = sortierfeld_ausgelesen[i].trim();
            }
            System.out.println(Arrays.toString(this.sortierfeld));

            // Um das unsortierte, generierte Feld im späteren Verlauf ausgeben zu können,
            // muss es kopiert werden, da sich this.sortierfeld mit dem Aufruf sortieren() verändert
            this.sortierfeld_unsortiert = new String[this.sortierfeld.length];
            System.arraycopy(this.sortierfeld, 0, this.sortierfeld_unsortiert, 0, this.sortierfeld.length);
            // Feld anlegen, welches der Nutzer sortieren kann
            this.sortierfeld_nutzer = new String[this.sortierfeld.length];
            System.arraycopy(this.sortierfeld, 0, this.sortierfeld_nutzer, 0, this.sortierfeld.length);

            // Felder für Auswertung mit generiertem Feld befüllen
            generiertesFeld_feldAnzeigen.setValue(Arrays.toString(this.sortierfeld_unsortiert));
            generiertesFeld_uebungsmodus.setValue(Arrays.toString(this.sortierfeld_unsortiert));
            generiertesFeld_pruefungsmodus.setValue(Arrays.toString(this.sortierfeld_unsortiert));

            // Ab hier beginnt die eigentliche Sortierung
            AppContext appContext = new AppContext();
            this.liste_system = appContext.startKonfiguration(this.algorithmus.getValue(), this.reihenfolge.getValue(), this.zeichenart.getValue(), this.sortierfeld);
            generiertesFeld_ro.setValue(Arrays.toString(this.sortierfeld_unsortiert));
            generiertesFeld_ro.setVisible(true);
            algorithmus_ro.setValue(this.algorithmus.getValue());
            algorithmus_ro.setVisible(true);
            reihenfolge_ro.setValue(this.reihenfolge.getValue());
            reihenfolge_ro.setVisible(true);

            auswertungKonfigurierenPanel.setEnabled(true);
            accordion.open(auswertungKonfigurierenPanel);
        });

        konfigurationLayout.add(accordion);
        return konfigurationLayout;
    }

    // getFeldAnzeigenForm:
    //      - erstellt und designt die Konfigurationsform
    //      - fügt der Form Components hinzu
    //      - return: FeldAnzeigenForm
    private Component getFeldAnzeigenForm() {
        layoutKonfiguration = new VerticalLayout();
        var menu = new HorizontalLayout();
        var menuButton = new HorizontalLayout();
        Label menuUeberschrift = new Label("Feld anzeigen");
        menuUeberschrift.getStyle().set("font-size", "var(--lumo-font-size-xl)")
                .set("margin", "var(--lumo-space-l)");

        generiertesFeld_feldAnzeigen.setVisible(true);
        generiertesFeld_feldAnzeigen.setReadOnly(true);
        generiertesFeld_feldAnzeigen.setLabel("Generiertes Feld");
        auswertungstextFeldAnzeigen.setVisible(false);
        zurKonfigurationFeldAnzeigen.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

        // ListGrid erstellen
        listGridFeldAnzeigen.setAllRowsVisible(true);
        listGridFeldAnzeigen.removeAllColumns();

        // Definieren Sie die Spalten, die in der Liste angezeigt werden sollen
        Grid.Column<Sortierschritt> feldColumn = listGridFeldAnzeigen.addColumn(Sortierschritt::getSortierfeld_String).setHeader("Feld");
        listGridFeldAnzeigen.addColumn(Sortierschritt::getElement1).setHeader("Getauschtes Element 1");
        listGridFeldAnzeigen.addColumn(Sortierschritt::getElement2).setHeader("Getauschtes Element 2");
        listGridFeldAnzeigen.addColumn(Sortierschritt::getIndex_element1).setHeader("Getauschter Index 1");
        listGridFeldAnzeigen.addColumn(Sortierschritt::getIndex_element2).setHeader("Getauschter Index 2");
        feldColumn.setAutoWidth(true);

        menuButton.add(generiertesFeld_feldAnzeigen);
        menuButton.add(naechsterSchritt);
        menuButton.add(alleSchritte);
        menuButton.add(zurKonfigurationFeldAnzeigen);
        menuButton.setAlignItems(Alignment.END);
        menu.add(menuUeberschrift, menuButton);
        menu.setAlignItems(Alignment.BASELINE);

        layoutKonfiguration.add(menu);
        layoutKonfiguration.add(listGridFeldAnzeigen);
        layoutKonfiguration.add(auswertungstextFeldAnzeigen);
        return layoutKonfiguration;
    }

    // feldAnzeigen:
    //      - stellt die Funktionalität für die feldAnzeigenFoorm bereit
    //      - nächster Schritt: zeigt jeden Schritt einzeln an
    //      - alle Schritte: zeigt alle zur Sortierung nötigen Schritte auf einmal an
    private void feldAnzeigen() {
        buttonCounterNaechsterSchritt.getAndSet(0);
        buttonCounterAlleSchritte.getAndSet(0);

        // Man braucht eine leere Liste, um den Dataprovider mit einer Liste aus Sortierschritten zu initialisieren, ohne direkt alle Schritte anzuzeigen
        listDataProviderFeldAnzeigen = new ListDataProvider<>(emptyData);
        listGridFeldAnzeigen.setDataProvider(listDataProviderFeldAnzeigen);

        naechsterSchritt.addClickListener(buttonClickEvent -> {
            System.out.println("Nächster Schritt: " + buttonCounterNaechsterSchritt.intValue());
            if (buttonCounterNaechsterSchritt.get() == this.liste_system.size()) {
                Notification notification = Notification.show("Bereits alle Schritte angezeigt");
                notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
                return;
            }
            Sortierschritt schritt = this.liste_system.get(buttonCounterNaechsterSchritt.intValue());
            listDataProviderFeldAnzeigen.getItems().add(schritt);
            listDataProviderFeldAnzeigen.refreshAll();
            buttonCounterNaechsterSchritt.getAndIncrement();

            if (listGridFeldAnzeigen.getDataProvider().size(new Query<>()) == this.liste_system.size()) {
                auswertungstextFeldAnzeigen.setText("Das Feld wurde erfolgreich nach " + this.liste_system.size() + " Schritten sortiert.");
                auswertungstextFeldAnzeigen.getStyle().set("font-size", "var(--lumo-font-size-l)")
                        .set("margin", "var(--lumo-space-l)");

                auswertungstextFeldAnzeigen.setVisible(true);
            }
        });

        alleSchritte.addClickListener(buttonClickEvent -> {
            System.out.println("Alle Schritt: " + buttonCounterAlleSchritte.get());
            if (buttonCounterAlleSchritte.get() >= 1) {
                Notification notification = Notification.show("Bereits alle Schritte angezeigt");
                notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
                return;
            }
            listGridFeldAnzeigen.setItems(this.liste_system);
            buttonCounterAlleSchritte.getAndIncrement();

            if (listGridFeldAnzeigen.getDataProvider().size(new Query<>()) == this.liste_system.size()) {
                auswertungstextFeldAnzeigen.setText("Das Feld wurde erfolgreich nach " + this.liste_system.size() + " Schritten sortiert.");
                auswertungstextFeldAnzeigen.getStyle().set("font-size", "var(--lumo-font-size-l)")
                        .set("margin", "var(--lumo-space-l)");

                auswertungstextFeldAnzeigen.setVisible(true);
            }
        });

        zurKonfigurationFeldAnzeigen.addClickListener(a -> {
            // Alles clearen und für neuen Aufruf vorbereiten
            buttonCounterAlleSchritte.getAndSet(0);
            buttonCounterNaechsterSchritt.getAndSet(0);
            auswertungstextFeldAnzeigen.setVisible(false);
            emptyData.clear();
            listDataProviderFeldAnzeigen = new ListDataProvider<>(emptyData);
            listGridFeldAnzeigen.setDataProvider(listDataProviderFeldAnzeigen);
            listDataProviderFeldAnzeigen.refreshAll();
        });
    }

    // getUebungsmodusForm:
    //      - erstellt und designt die UeubungsmodusForm
    //      - fügt der Form Components hinzu
    //      - return: UeubungsmodusForm
    private Component getUebungsmodusForm() {
        layoutUebungsmodus = new VerticalLayout();
        var menu = new HorizontalLayout();
        var menuText = new VerticalLayout();
        var menuButton = new HorizontalLayout();

        Label menuUeberschrift = new Label("Übungsmodus");
        menuUeberschrift.getStyle().set("font-size", "var(--lumo-font-size-xl)")
                .set("margin", "var(--lumo-space-m)");

        generiertesFeld_uebungsmodus.setVisible(true);
        generiertesFeld_uebungsmodus.setReadOnly(true);
        generiertesFeld_uebungsmodus.setLabel("Generiertes Feld");
        menuButton.setAlignItems(Alignment.END);
        auswertungstextUebungsmodus.setVisible(false);
        zurKonfigurationUebungsmodus.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

        // ListGrid erstellen
        listGridUebungsmodus.setAllRowsVisible(true);
        listGridUebungsmodus.removeAllColumns();

        // Definieren Sie die Spalten, die in der Liste angezeigt werden sollen
        Grid.Column<Sortierschritt> feldColumn = listGridUebungsmodus.addColumn(Sortierschritt::getSortierfeld_String).setHeader("Feld");
        listGridUebungsmodus.addColumn(Sortierschritt::getElement1).setHeader("Getauschtes Element 1");
        listGridUebungsmodus.addColumn(Sortierschritt::getElement2).setHeader("Getauschtes Element 2");
        listGridUebungsmodus.addColumn(Sortierschritt::getIndex_element1).setHeader("Getauschter Index 1");
        listGridUebungsmodus.addColumn(Sortierschritt::getIndex_element2).setHeader("Getauschter Index 2");
        feldColumn.setAutoWidth(true);

        menuButton.add(generiertesFeld_uebungsmodus);
        menuButton.add(index1Uebungsmodus);
        menuButton.add(index2Uebungsmodus);
        menuButton.add(tauschenUebungsmodus);
        menuButton.add(zurKonfigurationUebungsmodus);

        menu.add(menuUeberschrift, menuText, menuButton);
        menu.setAlignItems(Alignment.BASELINE);

        layoutUebungsmodus.add(menu, menuButton);
        layoutUebungsmodus.add(listGridUebungsmodus);
        layoutUebungsmodus.add(auswertungstextUebungsmodus);
        return layoutUebungsmodus;
    }

    // uebungsmodus:
    //      - stellt die Funktionalität für die uebungsmodusForm bereit
    //      - tauschen: Nutzer gibt zu tauschende Indexe ein und tauscht diese mit diesem Button
    //      - Fehler: nach drei falschen Eingaben pro Schritt erhält der Nutzer die Möglichkeit, sich diesen anzeigen zu lassen
    private void uebungsmodus() {
        fehler_in_schritt.getAndSet(0);
        fehler_insgesamt.getAndSet(0);
        buttonCounterTauschenSchritt.getAndSet(0);

        // Man braucht eine leere Liste, um den Dataprovider mit einer Liste aus Sortierschritten zu initialisieren, ohne direkt alle Schritte anzuzeigen
        listDataProviderUebungsmodus = new ListDataProvider<>(emptyData);
        listGridUebungsmodus.setDataProvider(listDataProviderUebungsmodus);

        tauschenUebungsmodus.addClickListener(buttonClickEvent -> {
            ConfirmDialog dialogFehlerUebungsmodus = new ConfirmDialog();

            // 1. Nutzereingabe aus den Inputfeldern index1 und index 2 holen
            String index1_string = this.index1Uebungsmodus.getValue();
            String index2_string = this.index2Uebungsmodus.getValue();

            int index1;
            int index2;

            try {
                index1 = Integer.parseInt(index1_string);
            } catch (NumberFormatException e) {
                Notification notification = Notification.show("Index 1 muss eine Zahl sein");
                notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
                return;
            }

            try {
                index2 = Integer.parseInt(index2_string);
            } catch (NumberFormatException e) {
                Notification notification = Notification.show("Index 2 muss eine Zahl sein");
                notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
                return;
            }

            // Falls Indexe gleich sind wird nicht getauscht
            if (index1_string.equals(index2_string)) {
                Notification notification = Notification.show("Die selben Indexe können nicht getauscht werden");
                notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
                return;
            }

            // Falls Index größer als Feld ist wird nicht getauscht
            if ((index1 > this.sortierfeld.length - 1) || (index1 < 0)) {
                Notification notification = Notification.show("Index 1 existiert nicht");
                notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
                return;
            }

            if ((index2 > this.sortierfeld.length - 1) || (index2 < 0)) {
                Notification notification = Notification.show("Index 2 existiert nicht");
                notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
                return;
            }

            // 2. Überprüfen der Nutzereingabe
            Sortierschritt schritt = this.liste_system.get(buttonCounterTauschenSchritt.get());
            if (((index1 == schritt.getIndex_element1()) && (index2 == schritt.getIndex_element2()))
                    || ((index2 == schritt.getIndex_element1()) && (index1 == schritt.getIndex_element2()))) {
                // 2.1 Eingabe erfolgreich
                listDataProviderUebungsmodus.getItems().add(schritt);
                listDataProviderUebungsmodus.refreshAll();
                buttonCounterTauschenSchritt.getAndIncrement();

                fehler_in_schritt.getAndSet(0);
            } else {
                // 2.2 Eingabe fehlgeschlagen
                Notification notification = Notification.show("Schritt ist falsch, versuchen sie es erneut");
                notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
                fehler_insgesamt.getAndIncrement();
                fehler_in_schritt.getAndIncrement();
            }

            // 3. Hilfe anbieten, wenn 3 Fehler in diesem Schritt
            if (fehler_in_schritt.get() >= 3) {
                String fehlerText = "Sie haben schon " + fehler_in_schritt.get() + " Fehler in diesem Schritt gemacht.\n" +
                        "Möchten Sie den Schritt anzeigen lassen?";

                dialogFehlerUebungsmodus.setHeader("Wiederholter Fehler");
                dialogFehlerUebungsmodus.setText(fehlerText);

                dialogFehlerUebungsmodus.setRejectable(true);
                dialogFehlerUebungsmodus.setRejectText("Nein");
                dialogFehlerUebungsmodus.addRejectListener(e -> {
                    System.out.println("Rejected");
                    this.statusUebungsmodus.setValue("Nein");
                });

                dialogFehlerUebungsmodus.setConfirmText("Ja");
                dialogFehlerUebungsmodus.addConfirmListener(event -> {
                    System.out.println("Confirmed");
                    this.statusUebungsmodus.setValue("Ja");
                    // 3.1 Nächsten Schritt zeigen, wenn der Nutzer Hilfe will
                    System.out.println("Status: " + statusUebungsmodus.getValue());
                    listDataProviderUebungsmodus.getItems().add(schritt);
                    listDataProviderUebungsmodus.refreshAll();
                    buttonCounterTauschenSchritt.getAndIncrement();

                    // 4. Auswertung anzeigen
                    if (listGridUebungsmodus.getDataProvider().size(new Query<>()) == this.liste_system.size()) {
                        auswertungstextUebungsmodus.setText("Das Feld wurde erfolgreich nach " + this.liste_system.size() + " Schritten sortiert.\n"
                                + "Beim Sortieren des Feldes haben Sie insgesamt " + fehler_insgesamt.get() + " Fehler gemacht.");
                        auswertungstextUebungsmodus.getStyle().set("font-size", "var(--lumo-font-size-l)")
                                .set("margin", "var(--lumo-space-l)");

                        auswertungstextUebungsmodus.setVisible(true);
                    }

                    fehler_in_schritt.getAndSet(0);
                });

                dialogFehlerUebungsmodus.open();
            }

            // 4. Auswertung anzeigen
            if (listGridUebungsmodus.getDataProvider().size(new Query<>()) == this.liste_system.size()) {
                auswertungstextUebungsmodus.setText("Das Feld wurde erfolgreich nach " + this.liste_system.size() + " Schritten sortiert.\n"
                        + "Beim Sortieren des Feldes haben Sie insgesamt " + fehler_insgesamt.get() + " Fehler gemacht.");
                auswertungstextUebungsmodus.getStyle().set("font-size", "var(--lumo-font-size-l)")
                        .set("margin", "var(--lumo-space-l)");

                auswertungstextUebungsmodus.setVisible(true);
            }

            // 5. Input Felder clearen
            index1Uebungsmodus.clear();
            index2Uebungsmodus.clear();
        });

        zurKonfigurationUebungsmodus.addClickListener(a -> {
            // Alles clearen und für neuen Aufruf vorbereiten
            buttonCounterTauschenSchritt.getAndSet(0);
            auswertungstextUebungsmodus.setVisible(false);
            index1Uebungsmodus.clear();
            index2Uebungsmodus.clear();
            emptyData.clear();
            listDataProviderUebungsmodus = new ListDataProvider<>(emptyData);
            listGridUebungsmodus.setDataProvider(listDataProviderUebungsmodus);
            listDataProviderUebungsmodus.refreshAll();
        });
    }

    // getPruefungsmodusForm:
    //      - erstellt und designt die pruefungsmodusForm
    //      - fügt der Form Components hinzu
    //      - return: pruefungsmodusForm
    private Component getPruefungsmodusForm() {
        layoutPruefungsmodus = new VerticalLayout();
        var menu = new HorizontalLayout();
        var menuButton = new HorizontalLayout();

        Label menuUeberschrift = new Label("Prüfungsmodus");
        menuUeberschrift.getStyle().set("font-size", "var(--lumo-font-size-xl)")
                .set("margin", "var(--lumo-space-s)");

        generiertesFeld_pruefungsmodus.setVisible(true);
        generiertesFeld_pruefungsmodus.setReadOnly(true);
        generiertesFeld_pruefungsmodus.setLabel("Generiertes Feld");
        menuButton.setAlignItems(Alignment.END);
        zurKonfigurationPruefungsmodus.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

        // ListGrid für Nutzer erstellen
        listGridPruefungsmodusNutzer.setAllRowsVisible(true);
        listGridPruefungsmodusNutzer.removeAllColumns();

        // Definieren Sie die Spalten, die in der Liste angezeigt werden sollen
        Grid.Column<Sortierschritt> feldColumn = listGridPruefungsmodusNutzer.addColumn(Sortierschritt::getSortierfeld_String).setHeader("Feld");
        listGridPruefungsmodusNutzer.addColumn(Sortierschritt::getElement1).setHeader("Getauschtes Element 1");
        listGridPruefungsmodusNutzer.addColumn(Sortierschritt::getElement2).setHeader("Getauschtes Element 2");
        listGridPruefungsmodusNutzer.addColumn(Sortierschritt::getIndex_element1).setHeader("Getauschter Index 1");
        listGridPruefungsmodusNutzer.addColumn(Sortierschritt::getIndex_element2).setHeader("Getauschter Index 2");
        feldColumn.setAutoWidth(true);

        // Überschrift für Auswertung
        auswertungUeberschrift = new Label("Auswertung");
        auswertungUeberschrift.getStyle().set("font-size", "var(--lumo-font-size-l)")
                .set("margin", "var(--lumo-space-s)");
        auswertungUeberschriftListe = new Label("Sortierschritte des Systems:");
        auswertungUeberschriftListe.getStyle().set("font-size", "var(--lumo-font-size-m)")
                .set("margin", "var(--lumo-space-s)");
        auswertungUeberschriftVergleich = new Label("Auswertung einzelner Schritte:");
        auswertungUeberschriftVergleich.getStyle().set("font-size", "var(--lumo-font-size-m)")
                .set("margin", "var(--lumo-space-s)");
        auswertungUeberschriftPunkte = new Label("Punktevergabe:");
        auswertungUeberschriftPunkte.getStyle().set("font-size", "var(--lumo-font-size-m)")
                .set("margin", "var(--lumo-space-s)");

        // ListGrid für System erstellen
        listGridPruefungsmodusSystem.setAllRowsVisible(true);
        listGridPruefungsmodusSystem.removeAllColumns();

        // Definieren Sie die Spalten, die in der Liste angezeigt werden sollen
        Grid.Column<Sortierschritt> feldColumnSystem = listGridPruefungsmodusSystem.addColumn(Sortierschritt::getSortierfeld_String).setHeader("Feld");
        listGridPruefungsmodusSystem.addColumn(Sortierschritt::getElement1).setHeader("Getauschtes Element 1");
        listGridPruefungsmodusSystem.addColumn(Sortierschritt::getElement2).setHeader("Getauschtes Element 2");
        listGridPruefungsmodusSystem.addColumn(Sortierschritt::getIndex_element1).setHeader("Getauschter Index 1");
        listGridPruefungsmodusSystem.addColumn(Sortierschritt::getIndex_element2).setHeader("Getauschter Index 2");
        feldColumnSystem.setAutoWidth(true);

        menuButton.add(generiertesFeld_pruefungsmodus);
        menuButton.add(index1Pruefungsmodus);
        menuButton.add(index2Pruefungsmodus);
        menuButton.add(tauschenPruefungsmodus);
        menuButton.add(auswertungPruefungsmodus);
        menuButton.add(zurKonfigurationPruefungsmodus);
        menu.add(menuUeberschrift, menuButton);
        menu.setAlignItems(Alignment.BASELINE);
        layoutPruefungsmodus.add(menu, menuButton);
        layoutPruefungsmodus.add(listGridPruefungsmodusNutzer);
        layoutPruefungsmodus.add(auswertungUeberschrift);
        layoutPruefungsmodus.add(auswertungUeberschriftListe);
        layoutPruefungsmodus.add(listGridPruefungsmodusSystem);
        layoutPruefungsmodus.add(auswertungUeberschriftVergleich);
        layoutPruefungsmodus.add(auswertungstextPruefungsmodus);
        layoutPruefungsmodus.add(auswertungUeberschriftPunkte);
        layoutPruefungsmodus.add(auswertungstextPunktevergabe);

        listGridPruefungsmodusSystem.setVisible(false);
        auswertungstextPruefungsmodus.setVisible(false);
        auswertungstextPruefungsmodus.setWidth("100%");
        auswertungstextPruefungsmodus.setReadOnly(true);
        auswertungstextPunktevergabe.setVisible(false);
        auswertungstextPunktevergabe.setWidth("100%");
        auswertungstextPunktevergabe.setReadOnly(true);
        auswertungUeberschrift.setVisible(false);
        auswertungUeberschriftListe.setVisible(false);
        auswertungUeberschriftVergleich.setVisible(false);
        auswertungUeberschriftPunkte.setVisible(false);
        return layoutPruefungsmodus;
    }

    // pruefungsmodus:
    //      - stellt die Funktionalität für die pruefungsmodusForm bereit
    //      - tauschen: Nutzer gibt zu tauschende Indexe ein und tauscht diese mit diesem Button
    //      - auswerten: Nutzer gibt sein sortiertes Feld ab, kann es nicht mehr verändern und die Auswertung beginnt
    private void pruefungsmodus() {
        fehler_in_schritt.getAndSet(0);
        fehler_insgesamt.getAndSet(0);
        buttonCounterTauschenSchritt.getAndSet(0);

        // Man braucht eine leere Liste, um den Dataprovider mit einer Liste aus Sortierschritten zu initialisieren, ohne direkt alle Schritte anzuzeigen
        listDataProviderPruefungsmodusNutzer = new ListDataProvider<>(emptyData);
        listGridPruefungsmodusNutzer.setDataProvider(listDataProviderPruefungsmodusNutzer);

        tauschenPruefungsmodus.addClickListener(buttonClickEvent -> {
            // 1. Indexe aus der Nutzereingabe tauschen
            String index1_string = this.index1Pruefungsmodus.getValue();
            String index2_string = this.index2Pruefungsmodus.getValue();

            int index1;
            int index2;

            try {
                index1 = Integer.parseInt(index1_string);
            } catch (NumberFormatException e) {
                Notification notification = Notification.show("Index 1 muss eine Zahl sein");
                notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
                return;
            }

            try {
                index2 = Integer.parseInt(index2_string);
            } catch (NumberFormatException e) {
                Notification notification = Notification.show("Index 2 muss eine Zahl sein");
                notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
                return;
            }

            // Falls Indexe gleich sind wird nicht getauscht
            if (index1_string.equals(index2_string)) {
                Notification notification = Notification.show("Die selben Indexe können nicht getauscht werden");
                notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
                return;
            }

            // Falls Index größer als Feld ist wird nicht getauscht
            if ((index1 > this.sortierfeld_nutzer.length - 1) || (index1 < 0)) {
                Notification notification = Notification.show("Index 1 existiert nicht");
                notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
                return;
            }

            if ((index2 > this.sortierfeld_nutzer.length - 1) || (index2 < 0)) {
                Notification notification = Notification.show("Index 2 existiert nicht");
                notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
                return;
            }

            // 1.1 Tauschen
            String tmp = sortierfeld_nutzer[index1];
            sortierfeld_nutzer[index1] = sortierfeld_nutzer[index2];
            sortierfeld_nutzer[index2] = tmp;

            // 1.2 Feld überschreiben
            String[] sortierfeld_nutzer_neu = new String[sortierfeld_nutzer.length];
            System.arraycopy(sortierfeld_nutzer, 0, sortierfeld_nutzer_neu, 0, sortierfeld_nutzer.length);

            // 1.3 Speichern
            Sortierschritt schritt = new Sortierschritt(sortierfeld_nutzer_neu,
                    sortierfeld_nutzer[index2],
                    sortierfeld_nutzer[index1],
                    index1,
                    index2);
            liste_nutzer.add(schritt);

            // 2. Getauschtes Feld ausgeben
            listDataProviderPruefungsmodusNutzer.getItems().add(schritt);
            listDataProviderPruefungsmodusNutzer.refreshAll();
            buttonCounterTauschenSchritt.getAndIncrement();

            index1Pruefungsmodus.clear();
            index2Pruefungsmodus.clear();
        });

        auswertungPruefungsmodus.addClickListener(i -> {
            // Form vorbereiten, so dass der Nutzer keine weitere Änderung am Feld vornehmen kann
            index1Pruefungsmodus.clear();
            index2Pruefungsmodus.clear();
            auswertungUeberschrift.setVisible(true);
            auswertungUeberschriftListe.setVisible(true);
            auswertungUeberschriftVergleich.setVisible(true);
            auswertungUeberschriftPunkte.setVisible(true);
            auswertungstextPruefungsmodus.setVisible(true);
            auswertungstextPunktevergabe.setVisible(true);
            listGridPruefungsmodusSystem.setVisible(true);
            index1Pruefungsmodus.setVisible(false);
            index2Pruefungsmodus.setVisible(false);
            tauschenPruefungsmodus.setVisible(false);
            auswertungPruefungsmodus.setVisible(false);

            // 1. Grid mit der Liste des Systems zeigen
            listGridPruefungsmodusSystem.setItems(this.liste_system);

            // 2. Einzelne Schritte auswerten
            int laenge = 0;
            int index = 0;
            int index_neu = 0;
            int verglichen = 1;

            // Es wird durch die kleinere Liste iteriert
            // Die Schritte in den Listen werden am jeweiligen Index verglichen
            laenge = Math.min(liste_system.size(), liste_nutzer.size());

            // Beide Schritte ausgeben
            for (index = 0; index < laenge; index++) {
                auswertungstextPruefungsmodus.setValue(auswertungstextPruefungsmodus.getValue() + verglichen + ". Vergleich: \n");
                auswertungstextPruefungsmodus.setValue(auswertungstextPruefungsmodus.getValue() + "Nutzer - ");
                String string = printer.print_schritt(liste_nutzer.get(index));
                auswertungstextPruefungsmodus.setValue(auswertungstextPruefungsmodus.getValue() + string);
                auswertungstextPruefungsmodus.setValue(auswertungstextPruefungsmodus.getValue() + "System - ");
                string = printer.print_schritt(liste_system.get(index));
                auswertungstextPruefungsmodus.setValue(auswertungstextPruefungsmodus.getValue() + string);
                vergleichen(liste_nutzer.get(index).getSortierfeld(), liste_system.get(index).getSortierfeld());
                auswertungstextPruefungsmodus.setValue(auswertungstextPruefungsmodus.getValue() + "--------------------------------------------------------------\n");
                verglichen++;
            }

            // Restliche Schritte ausgeben
            if (liste_system.size() != liste_nutzer.size()) {
                if (liste_system.size() > liste_nutzer.size()) {
                    index_neu = liste_nutzer.size();
                    for (index = index_neu; index < liste_system.size(); index++) {
                        auswertungstextPruefungsmodus.setValue(auswertungstextPruefungsmodus.getValue() + verglichen + ". Vergleich: \n");
                        auswertungstextPruefungsmodus.setValue(auswertungstextPruefungsmodus.getValue() + "Nutzer - Nicht vorhanden\n");
                        auswertungstextPruefungsmodus.setValue(auswertungstextPruefungsmodus.getValue() + "System - ");
                        String string = printer.print_schritt(liste_system.get(index));
                        auswertungstextPruefungsmodus.setValue(auswertungstextPruefungsmodus.getValue() + string);
                        vergleichen(pseudofeld, liste_system.get(index).getSortierfeld());
                        auswertungstextPruefungsmodus.setValue(auswertungstextPruefungsmodus.getValue() + "--------------------------------------------------------------\n");
                        verglichen++;
                    }
                } else {
                    index_neu = liste_system.size();
                    for (index = index_neu; index < liste_nutzer.size(); index++) {
                        auswertungstextPruefungsmodus.setValue(auswertungstextPruefungsmodus.getValue() + verglichen + ". Vergleich: \n");
                        auswertungstextPruefungsmodus.setValue(auswertungstextPruefungsmodus.getValue() + "Nutzer - ");
                        String string = printer.print_schritt(liste_nutzer.get(index));
                        auswertungstextPruefungsmodus.setValue(auswertungstextPruefungsmodus.getValue() + string);
                        auswertungstextPruefungsmodus.setValue(auswertungstextPruefungsmodus.getValue() + "System - Nicht vorhanden\n");
                        vergleichen(liste_nutzer.get(index).getSortierfeld(), pseudofeld);
                        auswertungstextPruefungsmodus.setValue(auswertungstextPruefungsmodus.getValue() + "--------------------------------------------------------------\n");
                        verglichen++;
                    }
                }
            }
            punkte_auswerten();
        });

        zurKonfigurationPruefungsmodus.addClickListener(a -> {
            // Alles clearen und für neuen Aufruf vorbereiten
            System.arraycopy(this.sortierfeld_unsortiert, 0, this.sortierfeld_nutzer, 0, this.sortierfeld_unsortiert.length);
            this.liste_nutzer.clear();
            auswertungstextPruefungsmodus.setValue("");
            auswertungstextPunktevergabe.setValue("");
            buttonCounterTauschenSchritt.getAndSet(0);
            this.schritte_falsch = 0;
            this.schritte_richtig = 0;
            this.schritte_zuViel = 0;
            this.schritte_zuWenig = 0;
            index1Pruefungsmodus.clear();
            index2Pruefungsmodus.clear();
            auswertungUeberschrift.setVisible(false);
            auswertungUeberschriftListe.setVisible(false);
            auswertungUeberschriftVergleich.setVisible(false);
            auswertungUeberschriftPunkte.setVisible(false);
            auswertungstextPruefungsmodus.setVisible(false);
            auswertungstextPunktevergabe.setVisible(false);
            listGridPruefungsmodusSystem.setVisible(false);
            index1Pruefungsmodus.setVisible(true);
            index2Pruefungsmodus.setVisible(true);
            tauschenPruefungsmodus.setVisible(true);
            auswertungPruefungsmodus.setVisible(true);
            emptyData.clear();
            listDataProviderPruefungsmodusNutzer = new ListDataProvider<>(emptyData);
            listGridPruefungsmodusNutzer.setDataProvider(listDataProviderPruefungsmodusNutzer);
            listDataProviderPruefungsmodusNutzer.refreshAll();
        });
    }

    // vergleichen:
    //      - Funktion für den Prüfungsmodus
    //      - ein einzelner Sortierschritt des Nutzers und Systems werden hier verglichen
    public void vergleichen(String[] feld_nutzer, String[] feld_system) {
        // 1. feld_nutzer hat zu wenig Schritte
        if (Arrays.equals(pseudofeld, feld_nutzer)) {
            this.schritte_zuWenig++;
            auswertungstextPruefungsmodus.setValue(auswertungstextPruefungsmodus.getValue() + "\nSie haben diesen Schritt nicht gemacht.\n");
            return;
        }

        // 2. feld_nutzer hat zu viel Schritte
        if (Arrays.equals(pseudofeld, feld_system)) {
            this.schritte_zuViel++;
            auswertungstextPruefungsmodus.setValue(auswertungstextPruefungsmodus.getValue() + "\nSie haben diesen Schritt zu viel gemacht.\n");
            return;
        }

        // 3. Richtige und falsche Sortierung
        if (Arrays.equals(feld_nutzer, feld_system)) {
            this.schritte_richtig++;
            auswertungstextPruefungsmodus.setValue(auswertungstextPruefungsmodus.getValue() + "\nSie haben diesen Schritt richtig sortiert.\n");
        } else {
            this.schritte_falsch++;
            auswertungstextPruefungsmodus.setValue(auswertungstextPruefungsmodus.getValue() + "\nSie haben diesen Schritt falsch sortiert.\n");
        }
    }

    // punkte_auswerten:
    //      - Funktion für den Prüfdungsmodus
    //      - mittels gemachten Fehlern wird der Score berechnet, den der Nutzer erreicht hat
    public void punkte_auswerten() {
        auswertungstextPunktevergabe.setValue(auswertungstextPunktevergabe.getValue() + "Die Gesamtpunktzahl wird folgendermaßen berechnet: " + "\n");
        auswertungstextPunktevergabe.setValue(auswertungstextPunktevergabe.getValue() + "Es gibt insgesamt maximal 10 Punkte. Die Wertigkeit eines Punktes wird mittels 10/(Zu benötigende Schrittzahl) berechnet." + "\n");
        auswertungstextPunktevergabe.setValue(auswertungstextPunktevergabe.getValue() + "Punktabzüge gibt es bei falschen und zusätzlichen Sortierschritten." + "\n");
        auswertungstextPunktevergabe.setValue(auswertungstextPunktevergabe.getValue() + "Die Gesamtpunktzahl ergibt sich also aus: (Richtige Schritte * Wertigkeit) - (Falsche Schritte * Wertigkeit) - (Zusätzliche Schritte * Wertigkeit)\n" + "\n");

        // Insgesamt soll es immer 10 P geben
        // Beispiel: Es gibt 6 Schritte
        // -> ein richtiger Schritt gibt 10/6 Punkte
        // -> volle Punktzahl bei 6 richtigen Schritten : 6*10/6 = 10
        float maximalePunktzahl = 10;
        float einPunkt = maximalePunktzahl / this.liste_system.size();

        // Abzüge gibt es bei: falschen Schritten, zusätzlichen Schritten
        //int gesamtPunktzahl = this.schritte_richtig - this.schritte_falsch - this.schritte_zuViel;
        float gesamtPunktzahl = (this.schritte_richtig * einPunkt) - (this.schritte_falsch * einPunkt) - (this.schritte_zuViel * einPunkt);
        if (gesamtPunktzahl < 0) {
            gesamtPunktzahl = 0;
        }
        float gesamtPunktzahlProzent = gesamtPunktzahl / maximalePunktzahl * 100;

        auswertungstextPunktevergabe.setValue(auswertungstextPunktevergabe.getValue() + "\tWertigkeit ein Punkt:\t\t\t " + einPunkt + "\n");
        auswertungstextPunktevergabe.setValue(auswertungstextPunktevergabe.getValue() + "--------------------------------------------------------------" + "\n");
        auswertungstextPunktevergabe.setValue(auswertungstextPunktevergabe.getValue() + "\t1. Richtig sortierte Schritte:\t+  " + this.schritte_richtig + "\n");
        auswertungstextPunktevergabe.setValue(auswertungstextPunktevergabe.getValue() + "\t2. Falsch sortierte Schritte:\t+  " + this.schritte_falsch + "\n");
        auswertungstextPunktevergabe.setValue(auswertungstextPunktevergabe.getValue() + "\t3. Zusätzliche Sortierschritte:\t-  " + this.schritte_zuViel + "\n");
        auswertungstextPunktevergabe.setValue(auswertungstextPunktevergabe.getValue() + "\t4. Fehlende Sortierschritte:\t+- " + this.schritte_zuWenig + "\n");
        auswertungstextPunktevergabe.setValue(auswertungstextPunktevergabe.getValue() + "--------------------------------------------------------------" + "\n");
        auswertungstextPunktevergabe.setValue(auswertungstextPunktevergabe.getValue() + "\tGesamtpunktzahl:\t\t\t\t " + gesamtPunktzahl + "\n");
        auswertungstextPunktevergabe.setValue(auswertungstextPunktevergabe.getValue() + "\n\tInsgesamt haben Sie " + gesamtPunktzahl + " von " + maximalePunktzahl + " Punkten erreicht." + "\n");
        auswertungstextPunktevergabe.setValue(auswertungstextPunktevergabe.getValue() + "\tSie haben somit " + gesamtPunktzahlProzent + "% richtig beantwortet." + "\n");
    }
}

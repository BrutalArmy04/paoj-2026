package com.pao.laboratory09.exercise1;

import java.io.*;
import java.util.*;

public class Main {
    private static final String OUTPUT_FILE = "output/lab09_ex1.ser";

    public static void main(String[] args) throws Exception {
        // TODO: Implementează conform Readme.md
        //
        // 1. Citește N din stdin, apoi cele N tranzacții (id suma data contSursa contDestinatie tip)
        // 2. Setează câmpul note = "procesat" pe fiecare tranzacție înainte de serializare
        // 3. Serializează lista de tranzacții în OUTPUT_FILE cu ObjectOutputStream (try-with-resources)
        // 4. Deserializează lista din OUTPUT_FILE cu ObjectInputStream (try-with-resources)
        // 5. Procesează comenzile din stdin până la EOF:
        //    - LIST          → afișează toate tranzacțiile, câte una pe linie
        //    - FILTER yyyy-MM → afișează tranzacțiile cu data care începe cu yyyy-MM
        //                       sau "Niciun rezultat." dacă nu există
        //    - NOTE id        → afișează "NOTE[id]: <valoarea câmpului note>"
        //                       sau "NOTE[id]: not found" dacă id-ul nu există
        //
        // Format linie tranzacție:
        //   [id] data tip: suma RON | contSursa -> contDestinatie
        //   Ex: [1] 2024-01-15 CREDIT: 1500.00 RON | RO01SRC1 -> RO01DST1

        Scanner scanner = new Scanner(System.in);
        scanner.useLocale(Locale.US);
        
        if (!scanner.hasNextInt()) {
            return;
        }

        int n = scanner.nextInt();
        List<Tranzactie> tranzactiiInitiale = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            int id = scanner.nextInt();
            double suma = scanner.nextDouble();
            String data = scanner.next();
            String contSursa = scanner.next();
            String contDestinatie = scanner.next();
            String tipStr = scanner.next();

            Tranzactie tranzactie = new Tranzactie(id, suma, data, contSursa, contDestinatie, TipTranzactie.valueOf(tipStr));
            tranzactie.setNote("procesat");
            tranzactiiInitiale.add(tranzactie);
        }

        File file = new File(OUTPUT_FILE);
        file.getParentFile().mkdirs();

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
            oos.writeObject(tranzactiiInitiale);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        List<Tranzactie> tranzactiiRestaurate;
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            tranzactiiRestaurate = (List<Tranzactie>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return;
        }

        while (scanner.hasNext()) {
            String command = scanner.next();

            switch (command) {
                case "LIST":
                    for (Tranzactie t : tranzactiiRestaurate) {
                        // Folosim formatul din enunț (Locale.US) pentru afisarea punctului
                        System.out.printf(Locale.US, "[%d] %s %s: %.2f RON | %s -> %s\n",
                                t.getId(), t.getData(), t.getTip(), t.getSuma(), t.getContSursa(), t.getContDestinatie());
                    }
                    break;

                case "FILTER":
                    String filtruData = scanner.next();
                    boolean gasit = false;
                    for (Tranzactie t : tranzactiiRestaurate) {
                        if (t.getData().startsWith(filtruData)) {
                             System.out.printf(Locale.US, "[%d] %s %s: %.2f RON | %s -> %s\n",
                                t.getId(), t.getData(), t.getTip(), t.getSuma(), t.getContSursa(), t.getContDestinatie());
                            gasit = true;
                        }
                    }
                    if (!gasit) {
                        System.out.println("Niciun rezultat.");
                    }
                    break;

                case "NOTE":
                    int idCautat = scanner.nextInt();
                    Tranzactie tranzactieGasita = null;
                    for (Tranzactie t : tranzactiiRestaurate) {
                        if (t.getId() == idCautat) {
                            tranzactieGasita = t;
                            break;
                        }
                    }
                    if (tranzactieGasita != null) {
                        System.out.println("NOTE[" + idCautat + "]: " + tranzactieGasita.getNote());
                    } else {
                        System.out.println("NOTE[" + idCautat + "]: not found");
                    }
                    break;
            }
        }
        scanner.close();
    }
}
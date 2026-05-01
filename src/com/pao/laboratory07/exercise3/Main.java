package com.pao.laboratory07.exercise3;

import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        // vezi readme.md pentru cerinte
        Scanner sc = new Scanner(System.in);
        if (!sc.hasNextLine()) return;
        
        int n = Integer.parseInt(sc.nextLine().trim());
        List<Comanda> comenzi = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            String[] tokens = sc.nextLine().trim().split(" ");
            if (tokens[0].equals("STANDARD")) {
                comenzi.add(new ComandaStandard(tokens[1], Double.parseDouble(tokens[2]), tokens[3]));
            } else if (tokens[0].equals("DISCOUNTED")) {
                comenzi.add(new ComandaRedusa(tokens[1], Double.parseDouble(tokens[2]), Integer.parseInt(tokens[3]), tokens[4]));
            } else if (tokens[0].equals("GIFT")) {
                comenzi.add(new ComandaGratuita(tokens[1], tokens[2]));
            }
        }

        for (Comanda c : comenzi) {
            System.out.println(c.descriere());
        }
        System.out.println();

        while (sc.hasNext()) {
            String cmd = sc.next();
            switch (cmd) {
                case "STATS" -> {
                    System.out.println("--- STATS ---");
                    Map<String, Double> medii = comenzi.stream()
                            .collect(Collectors.groupingBy(Comanda::tipComanda, Collectors.averagingDouble(Comanda::pretFinal)));
                    
                    String[] ordineTipuri = {"STANDARD", "DISCOUNTED", "GIFT"};
                    for (String tip : ordineTipuri) {
                        if (medii.containsKey(tip)) {
                            System.out.printf(Locale.US, "%s: medie = %.2f lei\n", tip, medii.get(tip));
                        }
                    }
                    System.out.println();
                }
                case "FILTER" -> {
                    double threshold = sc.nextDouble();
                    System.out.printf(Locale.US, "--- FILTER (>= %.2f) ---\n", threshold);
                    comenzi.stream()
                            .filter(c -> c.pretFinal() >= threshold)
                            .forEach(c -> System.out.println(c.descriere()));
                    System.out.println();
                }
                case "SORT" -> {
                    System.out.println("--- SORT (by client, then by pret) ---");
                    comenzi.stream()
                            .sorted(Comparator.comparing(Comanda::getClient).thenComparingDouble(Comanda::pretFinal))
                            .forEach(c -> System.out.println(c.descriere()));
                    System.out.println();
                }
                case "SPECIAL" -> {
                    System.out.println("--- SPECIAL (discount > 15%) ---");
                    comenzi.stream()
                            .filter(c -> c instanceof ComandaRedusa cr && cr.getDiscountProcent() > 15)
                            .forEach(c -> System.out.println(c.descriere()));
                    System.out.println();
                }
                case "QUIT" -> {
                    sc.close();
                    return;
                }
            }
        }
        sc.close();
    }
}
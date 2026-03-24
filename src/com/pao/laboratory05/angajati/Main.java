package com.pao.laboratory05.angajati;

import java.util.Scanner;

/**
 * Exercise 3 — Angajați
 *
 * Cerințele complete se află în:
 *   src/com/pao/laboratory05/Readme.md  →  secțiunea "Exercise 3 — Angajați"
 *
 * Creează fișierele de la zero în acest pachet, apoi rulează Main.java
 * pentru a verifica output-ul așteptat din Readme.
 */

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        AngajatService service = AngajatService.getInstance();

        while (true) {
            System.out.println("\n===== Gestionare Angajați =====");
            System.out.println("1. Adaugă angajat");
            System.out.println("2. Listare după salariu");
            System.out.println("3. Caută după departament");
            System.out.println("0. Ieșire");
            System.out.print("Opțiune: ");
            
            String optiune = scanner.nextLine().trim();

            if (optiune.equals("0")) {
                System.out.println("La revedere!");
                break;
            }

            switch (optiune) {
                case "1":
                    System.out.print("Nume: ");
                    String nume = scanner.nextLine().trim();
                    System.out.print("Departament (nume): ");
                    String numeDept = scanner.nextLine().trim();
                    System.out.print("Departament (locatie): ");
                    String locatieDept = scanner.nextLine().trim();
                    System.out.print("Salariu: ");
                    double salariu = Double.parseDouble(scanner.nextLine().trim());

                    Departament dept = new Departament(numeDept, locatieDept);
                    Angajat angajat = new Angajat(nume, salariu, dept);
                    service.addAngajat(angajat);
                    break;
                    
                case "2":
                    service.listBySalary();
                    break;
                    
                case "3":
                    System.out.print("Departament: ");
                    String searchDept = scanner.nextLine().trim();
                    service.findByDepartament(searchDept);
                    break;
                    
                default:
                    System.out.println("Opțiune invalidă! Mai încearcă.");
            }
        }
        
        scanner.close();
    }
}
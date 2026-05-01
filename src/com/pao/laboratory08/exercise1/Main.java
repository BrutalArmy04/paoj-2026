package com.pao.laboratory08.exercise1;

import java.io.*;
import java.util.*;

public class Main {
    // Calea către fișierul cu date — relativă la rădăcina proiectului
    private static final String FILE_PATH = "src/com/pao/laboratory08/tests/studenti.txt";

    public static void main(String[] args) throws Exception {

        List<Student> studenti = new ArrayList<>();
        // TODO: Implementează conform Readme.md
        //
        // 1. Citește studenții din FILE_PATH cu BufferedReader
        // 2. Citește comanda din stdin: PRINT, SHALLOW <nume> sau DEEP <nume>
        // 3. Execută comanda:
        //    - PRINT → afișează toți studenții
        //    - SHALLOW <nume> → shallow clone + modifică orașul clonei la "MODIFICAT" + afișează
        //    - DEEP <nume> → deep clone + modifică orașul clonei la "MODIFICAT" + afișează

        try (BufferedReader br = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 4) {
                    String nume = parts[0].trim();
                    int varsta = Integer.parseInt(parts[1].trim());
                    String oras = parts[2].trim();
                    String strada = parts[3].trim();
                    
                    Adresa adresa = new Adresa(oras, strada);
                    Student student = new Student(nume, varsta, adresa);
                    studenti.add(student);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Fisierul nu a fost gasit: " + FILE_PATH);
            return;
        }

        Scanner sc = new Scanner(System.in);
        if (!sc.hasNextLine()) return;
        
        String commandLine = sc.nextLine().trim();
        String[] cmdParts = commandLine.split(" ", 2);
        String comanda = cmdParts[0];

        if (comanda.equals("PRINT")) {
            for (Student s : studenti) {
                System.out.println(s);
            }
        } else if (comanda.equals("SHALLOW") || comanda.equals("DEEP")) {
            if (cmdParts.length < 2) return;
            String numeCautat = cmdParts[1].trim();
            
            Student studentOriginal = null;
            for (Student s : studenti) {
                if (s.getNume().equals(numeCautat)) {
                    studentOriginal = s;
                    break;
                }
            }
            
            if (studentOriginal != null) {
                Student clona;
                if (comanda.equals("SHALLOW")) {
                    clona = studentOriginal.shallowClone();
                } else {
                    clona = studentOriginal.deepClone();
                }
                
                clona.getAdresa().setOras("MODIFICAT");
                
                System.out.println("Original: " + studentOriginal);
                System.out.println("Clona: " + clona);
            }
        }
        
        sc.close();
    }
}

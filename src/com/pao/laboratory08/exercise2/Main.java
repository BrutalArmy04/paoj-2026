package com.pao.laboratory08.exercise2;

import com.pao.laboratory08.exercise1.Adresa;
import com.pao.laboratory08.exercise1.Student;
import java.io.*;
import java.util.*;

public class Main {
    private static final String FILE_PATH = "src/com/pao/laboratory08/tests/studenti.txt";
    private static final String OUTPUT_PATH = "rezultate.txt";

    public static void main(String[] args) throws Exception {
        List<Student> studenti = new ArrayList<>();
        // TODO: Implementează conform Readme.md
        //
        // 1. Citește studenții din FILE_PATH cu BufferedReader
        // 2. Citește pragul de vârstă din stdin cu Scanner
        // 3. Filtrează studenții cu varsta >= prag
        // 4. Scrie filtrații în "rezultate.txt" cu BufferedWriter
        // 5. Afișează sumarul la consolă

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
        } catch (IOException e) {
            System.out.println("Eroare la citirea fisierului: " + e.getMessage());
            return;
        }

        Scanner sc = new Scanner(System.in);
        if (!sc.hasNextInt()) 
        {
            sc.close();
            return;
        }
        int prag = sc.nextInt();
        sc.close();
        List<Student> filtrati = new ArrayList<>();
        for (Student s : studenti) 
        {
            if (s.getVarsta() >= prag) {
                filtrati.add(s);
            }
        }
        System.out.println("Filtru: varsta >= " + prag);
        System.out.println("Rezultate: " + filtrati.size() + " studenti\n");

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(OUTPUT_PATH))) {
            for (Student s : filtrati) 
            {
                String studentStr = s.toString();
                System.out.println(studentStr);
                bw.write(studentStr);
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Eroare la scrierea in fisier: " + e.getMessage());
        }

        System.out.println("\nScris in: " + OUTPUT_PATH);

        
    }
}


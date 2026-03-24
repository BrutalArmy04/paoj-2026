package com.pao.laboratory04.collections;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
/**
 * Exercițiul 1 — Colecții: HashMap și TreeMap
 *
 * Creează în acest main:
 *
 * PARTEA A — HashMap (frecvența cuvintelor)
 * 1. Declară un array de String-uri:
 *    String[] words = {"java", "python", "java", "c++", "python", "java", "rust", "c++", "go"};
 * 2. Creează un HashMap<String, Integer> care contorizează de câte ori apare fiecare cuvânt.
 *    - Parcurge array-ul și folosește put() + getOrDefault() pentru a incrementa contorul.
 * 3. Afișează map-ul.
 * 4. Verifică dacă există cheia "rust" cu containsKey().
 * 5. Afișează DOAR cheile (keySet()), apoi DOAR valorile (values()).
 * 6. Parcurge map-ul cu entrySet() și afișează "cheia -> valoarea" pentru fiecare intrare.
 *
 * PARTEA B — TreeMap (sortare automată)
 * 7. Creează un TreeMap<String, Integer> din același HashMap (constructor cu argument).
 * 8. Afișează TreeMap-ul — observă ordinea alfabetică a cheilor.
 * 9. Folosește firstKey() și lastKey() pentru a afișa prima și ultima cheie.
 *
 * PARTEA C — Map cu obiecte
 * 10. Creează un HashMap<String, List<String>> care asociază materii cu liste de studenți.
 *     Exemplu: "PAOJ" -> ["Ana", "Mihai", "Ion"], "BD" -> ["Ana", "Elena"]
 * 11. Afișează toți studenții de la materia "PAOJ".
 * 12. Adaugă un student nou la "BD" și afișează lista actualizată.
 *
 * Output așteptat (orientativ — ordinea HashMap poate varia):
 *
 * === PARTEA A: HashMap — frecvența cuvintelor ===
 * Frecvență: {python=2, c++=2, java=3, rust=1, go=1}
 * Conține 'rust'? true
 * Chei: [python, c++, java, rust, go]
 * Valori: [2, 2, 3, 1, 1]
 * python -> 2
 * c++ -> 2
 * java -> 3
 * rust -> 1
 * go -> 1
 *
 * === PARTEA B: TreeMap — sortare automată ===
 * Sortat: {c++=2, go=1, java=3, python=2, rust=1}
 * Prima cheie: c++
 * Ultima cheie: rust
 *
 * === PARTEA C: Map cu obiecte ===
 * Studenți la PAOJ: [Ana, Mihai, Ion]
 * Studenți la BD (actualizat): [Ana, Elena, George]
 */
public class Main {
    public static void main(String[] args) {
        String[] words = {"java", "python", "java", "c++", "python", "java", "rust", "c++", "go"};
        HashMap<String, Integer>wordcount = new HashMap<>(); 
        for (var word : words)
            wordcount.put(word, wordcount.getOrDefault(word, 0) + 1);
        System.out.println(wordcount);
        System.out.println("Contine 'rust'? " + wordcount.containsKey("rust"));
        System.out.println("Chei: " + wordcount.keySet());
        System.out.println("Valori : " + wordcount.values());
        for (Map.Entry<String, Integer> en : wordcount.entrySet()) {
            String cuv = en.getKey();
            Integer frec = en.getValue();
            System.out.println(cuv + " -> " + frec);
        }

        TreeMap<String, Integer> sorted_wordcount = new TreeMap<>(wordcount);
        System.out.println(sorted_wordcount);
        System.out.println(sorted_wordcount.firstKey());
        System.out.println(sorted_wordcount.lastKey());

        HashMap<String, List<String>> studmat = new HashMap<>();
        studmat.put("PAOJ", new ArrayList<>(Arrays.asList("Ana", "Mihai", "Ion")));
        studmat.put("BD", new ArrayList<>(Arrays.asList("Ana", "Elena")));
        System.out.println("Studenti la PAOJ: " + studmat.get("PAOJ"));
        List<String> studentiBD = studmat.get("BD");
        studentiBD.add("George");
        System.out.println("Studenti la BD (actualizat): " + studmat.get("BD"));
    }
    
}


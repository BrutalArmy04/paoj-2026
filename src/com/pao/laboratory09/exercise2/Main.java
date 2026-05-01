package com.pao.laboratory09.exercise2;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.*;

public class Main {
    private static final String OUTPUT_FILE = "output/lab09_ex2.bin";
    private static final int RECORD_SIZE = 32;

    public static void main(String[] args) throws Exception {
        // TODO: Implementează conform Readme.md
        //
        // 1. Citește N din stdin, apoi cele N tranzacții (id suma data tip)
        // 2. Scrie toate înregistrările în OUTPUT_FILE cu DataOutputStream (format binar, RECORD_SIZE=32 bytes/înreg.)
        //    - bytes 0-3:   id (int, little-endian via ByteBuffer)
        //    - bytes 4-11:  suma (double, little-endian via ByteBuffer)
        //    - bytes 12-21: data (String, 10 chars ASCII, paddat cu spații la dreapta)
        //    - byte 22:     tip (0=CREDIT, 1=DEBIT)
        //    - byte 23:     status (0=PENDING, 1=PROCESSED, 2=REJECTED)
        //    - bytes 24-31: padding (zerouri)
        // 3. Procesează comenzile din stdin până la EOF cu RandomAccessFile:
        //    - READ idx       → seek(idx * RECORD_SIZE), citește și afișează înregistrarea
        //    - UPDATE idx ST  → seek(idx * RECORD_SIZE + 23), scrie noul status (0/1/2)
        //                       afișează "Updated [idx]: STATUS"
        //    - PRINT_ALL      → citește și afișează toate înregistrările
        //
        // Format linie output:
        //   [idx] id=<id> data=<data> tip=<CREDIT|DEBIT> suma=<suma:.2f> RON status=<STATUS>

        Scanner scanner = new Scanner(System.in);
        scanner.useLocale(Locale.US); 

        if (!scanner.hasNextInt()) return;
        int n = scanner.nextInt();

        File file = new File(OUTPUT_FILE);
        file.getParentFile().mkdirs();

        try (DataOutputStream dos = new DataOutputStream(new FileOutputStream(file))) {
            for (int i = 0; i < n; i++) {
                int id = scanner.nextInt();
                double suma = scanner.nextDouble();
                String data = scanner.next();
                String tipStr = scanner.next();

                byte[] idBytes = ByteBuffer.allocate(4).order(ByteOrder.LITTLE_ENDIAN).putInt(id).array();
                dos.write(idBytes);

                byte[] sumaBytes = ByteBuffer.allocate(8).order(ByteOrder.LITTLE_ENDIAN).putDouble(suma).array();
                dos.write(sumaBytes);

                byte[] dataBytes = data.getBytes();
                dos.write(dataBytes);
                for (int j = dataBytes.length; j < 10; j++) {
                    dos.writeByte(' ');
                }

                dos.writeByte(tipStr.equals("CREDIT") ? 0 : 1);

                dos.writeByte(0);

                for (int j = 0; j < 8; j++) {
                    dos.writeByte(0);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        try (RandomAccessFile raf = new RandomAccessFile(file, "rw")) {
            while (scanner.hasNext()) {
                String command = scanner.next();

                switch (command) {
                    case "READ": {
                        int idx = scanner.nextInt();
                        printRecord(raf, idx); 
                        break;
                    }
                    case "UPDATE": {
                        int idx = scanner.nextInt();
                        String newStatus = scanner.next();
                        byte statusByte = 0;
                        if (newStatus.equals("PROCESSED")) statusByte = 1;
                        else if (newStatus.equals("REJECTED")) statusByte = 2;

                        raf.seek(idx * RECORD_SIZE + 23);
                        raf.writeByte(statusByte);
                        System.out.println("Updated [" + idx + "]: " + newStatus);
                        break;
                    }
                    case "PRINT_ALL": {
                        long totalRecords = raf.length() / RECORD_SIZE;
                        for (int i = 0; i < totalRecords; i++) {
                            printRecord(raf, i); 
                        }
                        break;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        scanner.close();
    }

    private static void printRecord(RandomAccessFile raf, int idx) throws IOException {
        raf.seek(idx * RECORD_SIZE); 
        byte[] recordBytes = new byte[RECORD_SIZE];
        raf.readFully(recordBytes); 

        ByteBuffer buffer = ByteBuffer.wrap(recordBytes).order(ByteOrder.LITTLE_ENDIAN); 

        int id = buffer.getInt(); 
        double suma = buffer.getDouble(); 

        byte[] dataArr = new byte[10];
        buffer.get(dataArr);
        String data = new String(dataArr).trim();

        byte tipByte = buffer.get();
        String tip = (tipByte == 0) ? "CREDIT" : "DEBIT";

        byte statusByte = buffer.get();
        String status = "PENDING";
        if (statusByte == 1) status = "PROCESSED";
        else if (statusByte == 2) status = "REJECTED";

        System.out.printf(Locale.US, "[%d] id=%d data=%s tip=%s suma=%.2f RON status=%s\n",
                idx, id, data, tip, suma, status);
    }
}

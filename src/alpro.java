import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

class JadwalKuliah {
    String mataKuliah;
    int sks;
    String kelas;
    String namaDosen;
    String ruangan;
    String hari;  // Contoh: "Senin", "Selasa", "Rabu", ...
    String waktu; // Format: HH:MM

    // Constructor
    public JadwalKuliah(String mataKuliah, int sks, String kelas, String namaDosen, String ruangan, String hari, String waktu) {
        this.mataKuliah = mataKuliah;
        this.sks = sks;
        this.kelas = kelas;
        this.namaDosen = namaDosen;
        this.ruangan = ruangan;
        this.hari = hari;
        this.waktu = waktu;
    }

    @Override
    public String toString() {
        return mataKuliah + " (SKS: " + sks + ", Kelas: " + kelas + ", Dosen: " + namaDosen + ")\n" +
                "Hari: " + hari + ", Waktu: " + waktu + " (Ruangan: " + ruangan + ")";
    }
}

public class BubbleSortJadwal {

    // Fungsi untuk mengurutkan hari berdasarkan urutan dalam seminggu
    private static int getDayOrder(String hari) {
        switch (hari.toLowerCase()) {
            case "senin": return 1;
            case "selasa": return 2;
            case "rabu": return 3;
            case "kamis": return 4;
            case "jumat": return 5;
            case "sabtu": return 6;
            case "minggu": return 7;
            default: return 8; // Hari tidak valid
        }
    }

    // Fungsi Bubble Sort untuk mengurutkan jadwal
    public static void bubbleSort(ArrayList<JadwalKuliah> jadwalList) {
        int n = jadwalList.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                JadwalKuliah current = jadwalList.get(j);
                JadwalKuliah next = jadwalList.get(j + 1);

                // Bandingkan hari terlebih dahulu, jika sama bandingkan waktu
                if (getDayOrder(current.hari) > getDayOrder(next.hari) ||
                        (getDayOrder(current.hari) == getDayOrder(next.hari) && current.waktu.compareTo(next.waktu) > 0)) {
                    // Tukar elemen
                    Collections.swap(jadwalList, j, j + 1);
                }
            }
        }
    }

    // Fungsi untuk menulis data ke dalam file Excel
    public static void writeToExcel(ArrayList<JadwalKuliah> jadwalList) throws IOException {
        // Membuat workbook dan worksheet baru
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Jadwal Kuliah");

        // Membuat header untuk tabel Excel
        Row header = sheet.createRow(0);
        header.createCell(0).setCellValue("No");
        header.createCell(1).setCellValue("Mata Kuliah");
        header.createCell(2).setCellValue("SKS");
        header.createCell(3).setCellValue("Kelas");
        header.createCell(4).setCellValue("Nama Dosen");
        header.createCell(5).setCellValue("Hari");
        header.createCell(6).setCellValue("Waktu");
        header.createCell(7).setCellValue("Ruangan");

        // Menambahkan data jadwal kuliah ke dalam worksheet
        int rowNum = 1;
        for (JadwalKuliah jadwal : jadwalList) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(rowNum - 1);  // Menambahkan nomor urut
            row.createCell(1).setCellValue(jadwal.mataKuliah);
            row.createCell(2).setCellValue(jadwal.sks);
            row.createCell(3).setCellValue(jadwal.kelas);
            row.createCell(4).setCellValue(jadwal.namaDosen);
            row.createCell(5).setCellValue(jadwal.hari);
            row.createCell(6).setCellValue(jadwal.waktu);
            row.createCell(7).setCellValue(jadwal.ruangan);
        }

        // Menyimpan file Excel
        FileOutputStream fileOut = new FileOutputStream("jadwal_kuliah.xlsx");
        workbook.write(fileOut);
        fileOut.close();
        workbook.close();
    }

    public static void main(String[] args) {
        // Contoh data jadwal kuliah
        ArrayList<JadwalKuliah> jadwalList = new ArrayList<>();
        jadwalList.add(new JadwalKuliah("Algoritma", 3, "A", "Dr. Andi", "Lab 1", "Selasa", "08:00"));
        jadwalList.add(new JadwalKuliah("Basis Data", 3, "B", "Dr. Budi", "Lab 2", "Senin", "10:00"));
        jadwalList.add(new JadwalKuliah("Pemrograman", 4, "C", "Dr. Citra", "Lab 3", "Senin", "07:00"));
        jadwalList.add(new JadwalKuliah("Jaringan Komputer", 2, "A", "Dr. Dian", "Lab 4", "Rabu", "09:00"));

        // Urutkan jadwal menggunakan Bubble Sort
        bubbleSort(jadwalList);

        try {
            // Menulis data yang terurut ke dalam file Excel
            writeToExcel(jadwalList);
            System.out.println("Jadwal kuliah telah berhasil disimpan ke dalam file Excel.");
        } catch (IOException e) {
            System.err.println("Terjadi kesalahan saat menulis ke file Excel: " + e.getMessage());
        }
    }
}

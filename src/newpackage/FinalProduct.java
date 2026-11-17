package newpackage;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import javax.swing.JOptionPane;

public class FinalProduct {
    
    // 1. PINDAHKAN METHOD tanya() KE SINI (LUAR MAIN)
    static boolean tanya(String pertanyaan) {
        int jawab = JOptionPane.showConfirmDialog(
            null,
            pertanyaan,
            "Cek Kesiapan Perjalanan",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.QUESTION_MESSAGE);
        return jawab == JOptionPane.YES_OPTION;
    }

    // 2. PINDAHKAN METHOD status() KE SINI (LUAR MAIN)
    static String status(boolean siap) {
        return siap ? " Lengkap" : "\u2717 Belum";
    }

    // METHOD UTAMA
    public static void main(String[] args) {
        // Variable
        boolean programjalan = true;
        int jumlahOrang = 0;
        int lamaPerjalanan = 0;
        
        // Seluruh program dibungkus dalam while
        while (programjalan) {
            try {
                // Merangkai menu menjadi satu string utuh menggunakan \n (baris baru)
                String menu = "===========================\n"
                            + "             TRAVELPLANNER           \n"
                            + "===========================\n"
                            + "1. Pilih Destinasi                 \n"
                            + "2. Hitung Budget Perjalanan         \n"
                            + "3. Buat Itinerary                  \n"
                            + "4. Cek Kesiapan Perjalanan          \n"
                            + "5. Keluar                          \n"
                            + "===========================\n"
                            + "Masukkan angka pilihan Anda (1-5):";                                                                                                                                 

                // Menampilkan dialog input teks biasa
                String input = JOptionPane.showInputDialog(
                    null, 
                    menu, 
                    "Menu Utama", 
                    JOptionPane.INFORMATION_MESSAGE
                );

                // Mengubah input string menjadi integer
                if (input != null && !input.trim().isEmpty()) {
                    int angkaPilih = Integer.parseInt(input);
                    switch (angkaPilih) {
                        case 1:
                            String nama = JOptionPane.showInputDialog(null, "Masukkan nama :", "Pilih Destinasi", JOptionPane.QUESTION_MESSAGE);
                            if (nama == null) break; 

                            String menuDestinasi = "Pilihan Destinasi :\n"
                                                 + "1. Bali\n"
                                                 + "2. Yogyakarta\n"
                                                 + "3. Bandung\n"
                                                 + "4. Lombok\n\n"
                                                 + "Masukkan pilihan :";
                            String inputDestinasi = JOptionPane.showInputDialog(null, menuDestinasi, "Pilih Destinasi", JOptionPane.QUESTION_MESSAGE);
                            if (inputDestinasi == null) break;
                            
                            int pilihDestinasi = Integer.parseInt(inputDestinasi);
                            String namaDestinasi = "";
                            switch (pilihDestinasi) {
                                case 1: namaDestinasi = "Bali"; break;
                                case 2: namaDestinasi = "Yogyakarta"; break;
                                case 3: namaDestinasi = "Bandung"; break;
                                case 4: namaDestinasi = "Lombok"; break;
                                default: namaDestinasi = "Destinasi Tidak Valid"; break;
                            }

                            jumlahOrang = Integer.parseInt(JOptionPane.showInputDialog("Masukkan Jumlah orang : "));
                            lamaPerjalanan = Integer.parseInt(JOptionPane.showInputDialog("Masukkan Lama perjalanan (hari) : "));

                            String kategori = (lamaPerjalanan > 3) ? "Long Trip" : "Short Trip";

                            String hasilAkhir = "=========================================\n"
                                              + "Destinasi\t\t\t: " + namaDestinasi + "\n"
                                              + "Jumlah orang\t\t: " + jumlahOrang + " orang\n"
                                              + "Lama perjalanan\t: " + lamaPerjalanan + " hari\n"
                                              + "Kategori\t\t\t: " + kategori + "\n"
                                              + "=========================================";

                            JOptionPane.showMessageDialog(null, hasilAkhir, "Detail Perjalanan - " + nama, JOptionPane.INFORMATION_MESSAGE);
                            break;

                        case 2:
                            int budget, transport, hotel = 500000, makan, biaya;
                            
                            if (jumlahOrang == 0 || lamaPerjalanan == 0) {
                            JOptionPane.showMessageDialog(null, "Silakan isi data destinasi di Menu 1 terlebih dahulu!", "Peringatan", JOptionPane.WARNING_MESSAGE);
                            break; // Menghentikan eksekusi case 2 dan kembali ke menu utama
                            }
                            
                            // Gunakan try-catch bawaan jika input cancel agar tidak langsung error ke loop utama
                            String strBudget = JOptionPane.showInputDialog("Masukkan Budget Awal                     : ");
                            if(strBudget == null) break;
                            budget = Integer.parseInt(strBudget);
                            
                            String strTransport = JOptionPane.showInputDialog("Masukkan Biaya Transportasi              : ");
                            if(strTransport == null) break;
                            transport = Integer.parseInt(strTransport);
                            
                            String strMakan = JOptionPane.showInputDialog("Masukkan Biaya Makan Per Orang Per Hari  : ");
                            if(strMakan == null) break;
                            makan = Integer.parseInt(strMakan);
                            
                            
                            int TotalHotel = (jumlahOrang-1)*hotel;
                            int TotalMakan = makan*jumlahOrang*lamaPerjalanan;
                            int AllTotal   = transport+TotalHotel+TotalMakan;
                            int sisa       = budget-AllTotal;
                            
                            DecimalFormatSymbols symbols = new DecimalFormatSymbols();
                            symbols.setGroupingSeparator('.');
                            symbols.setDecimalSeparator(',');

                            // 2. Menerapkan pola format Rupiah
                            DecimalFormat formatRupiah = new DecimalFormat("Rp#,##0.00", symbols);
                            
                            String statusBudget;

                            if (sisa > 0) {
                                statusBudget = "BUDGET AMAN";
                            } else if (sisa == 0) {
                                statusBudget = "BUDGET PAS";
                            } else {
                                statusBudget = "BUDGET KURANG";
                            }

                            hasilAkhir = "HASIL PERHITUNGAN BUDGET\n\n" +
                    "Total Transportasi : " + formatRupiah.format(transport) + "\n" +
                    "Total Hotel : " + formatRupiah.format(TotalHotel) + "\n" +
                    "Total Makan : " + formatRupiah.format(TotalMakan) + "\n" +
                    "-----------------------------\n" +
                    "Total Pengeluaran : " + formatRupiah.format(AllTotal) + "\n" +
                    "Budget Awal : " + formatRupiah.format(budget) + "\n" +
                    "Sisa Budget : " + formatRupiah.format(sisa) + "\n\n" +
                    "STATUS : " + statusBudget;

JOptionPane.showMessageDialog(null, hasilAkhir, "Message", JOptionPane.INFORMATION_MESSAGE);
                            break;

                        case 3:
                            // Validasi: Cek apakah user sudah mengisi case 1
    if (lamaPerjalanan == 0) {
        JOptionPane.showMessageDialog(null, "Silakan isi data destinasi di Menu 1 terlebih dahulu!", "Peringatan", JOptionPane.WARNING_MESSAGE);
        break; 
    }

    try {
        // AMBIL VALUE DARI CASE 1
        int hariItinerary = lamaPerjalanan; 
        
        int total_aktivitas = 0;
        String outputItinerary = "==========================\n" +
                                 " ITINERARY PERJALANAN\n" +
                                 "==========================\n";
        
        // Loop otomatis menyesuaikan lamaPerjalanan dari case 1
        // Looping menggunakan hariItinerary
for (int i = 1; i <= hariItinerary; i++) {
    
    // 1. Meminta input dari user
    int pilihanAktivitas = Integer.parseInt(JOptionPane.showInputDialog(
            "========== HARI KE-" + i + " ==========\n" +
            " Pilih Aktivitas :\n" +
            " 1. Wisata Pantai\n" +
            " 2. Wisata Alam\n" +
            " 3. Wisata Kuliner\n" +
            " 4. Wisata Belanja\n" +
            " 5. Wisata Budaya dan Sejarah\n" +
            " Masukkan Pilihan :"));
  
    // 2. Deklarasi nilai awal (kosong)
    String nama_wisata = "";
    String saran = "";
    
    // 3. BLOK INI HARUS ADA UNTUK MENGISI NAMA WISATA & SARAN
    switch (pilihanAktivitas) {
        case 1:
            nama_wisata = "Wisata Pantai";
            saran = " Gunakan sunscreen, bawa pakaian ganti, dan datang pada pagi hari.";                                                            
            break;
        case 2:
            nama_wisata = "Wisata Alam";
            saran = " Gunakan sepatu yang nyaman, dan bawa air minum.";                        
            break;
        case 3:
            nama_wisata = "Wisata Kuliner";
            saran = " Cari makanan khas daerah dan perhatikan kebersihan tempat.";
            break;
        case 4:
            nama_wisata = "Wisata Belanja";
            saran = " Siapkan daftar belanja dan anggaran.";
            break;
        case 5:
            nama_wisata = "Wisata Budaya dan Sejarah";
            saran = " Patuhi aturan lokal, gunakan pakaian sopan dan ikuti pemandu.";
            break;
        default:
            nama_wisata = " Wisata Umum";
            saran = " Tetap Jaga Keselamatan dan Kesehatan.";
            break; 
    } 
    
    // 4. Menggabungkan hasil yang sudah diisi di atas ke teks utama
    outputItinerary += "\n Hari Ke-" + i + " : " + nama_wisata;
    outputItinerary += "\n Saran :" + saran + "\n";
    
    total_aktivitas++;
}
        
        outputItinerary += "\n Kategori Perjalanan :" + total_aktivitas;
        outputItinerary += "\n Pesan: Selamat Menikmati Perjalananmu!";
        
        JOptionPane.showMessageDialog(null, outputItinerary);
        
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Data yang diinput salah!", "ERROR!!", JOptionPane.ERROR_MESSAGE);
    }
    break;
                        case 4:
                            // 3. CASE 4 MENJADI LEBIH BERSIH KARENA METHODNYA SUDAH DI LUAR
                            boolean ktp        = tanya("Sudah membawa KTP?");
                            boolean uang       = tanya("Sudah menyiapkan uang?");
                            boolean hotelCek   = tanya("Sudah booking hotel?");
                            boolean transportCek = tanya("Sudah booking transportasi?");
                     
                            String hasil = "CHECKLIST PERJALANAN\n"
                                    + "------------------------------------\n"
                                    + "KTP                : " + status(ktp) + "\n"
                                    + "Uang               : " + status(uang) + "\n"
                                    + "Hotel              : " + status(hotelCek) + "\n"
                                    + "Transportasi    : " + status(transportCek) + "\n"
                                    + "------------------------------------\n\n"
                                    + "STATUS :\n";
                     
                            if (ktp && uang && hotelCek && transportCek) {
                                hasil += "Perjalanan sudah siap. Selamat berangkat!";
                            } else {
                                hasil += "Perjalanan belum sepenuhnya siap.\n";
                                if (!ktp)       hasil += "Silakan bawa KTP terlebih dahulu.\n";
                                if (!uang)      hasil += "Silakan siapkan uang terlebih dahulu.\n";
                                if (!hotelCek)     hasil += "Silakan booking hotel terlebih dahulu.\n";
                                if (!transportCek) hasil += "Silakan booking transportasi terlebih dahulu.\n";
                            }
                     
                            JOptionPane.showMessageDialog(
                                    null,
                                    hasil,
                                    "Hasil Cek Kesiapan Perjalanan",
                                    (ktp && uang && hotelCek && transportCek)
                                            ? JOptionPane.INFORMATION_MESSAGE
                                            : JOptionPane.WARNING_MESSAGE);
                            break;

                        case 5:
                            JOptionPane.showMessageDialog(null, "Terima kasih telah menggunakan aplikasi ini!");
                            programjalan = false; // Mematikan loop
                            break;

                        default:
                            JOptionPane.showMessageDialog(null, "Pilihan tidak valid! Masukkan angka 1-5.");
                            break;
                    }
                } else if (input == null) {
                     programjalan = false; // Jika user menekan cancel di menu utama
                }

            } catch (NumberFormatException ne) {
                JOptionPane.showMessageDialog(null, "Harap masukkan HANYA ANGKA!", "Input Salah", JOptionPane.WARNING_MESSAGE);
            } catch (Exception ie) {
                JOptionPane.showMessageDialog(null, "Terjadi kesalahan sistem!", "ERROR!!", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
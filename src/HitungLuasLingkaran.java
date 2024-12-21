import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HitungLuasLingkaran {

    public static final double PHI = 3.14;

    public static void main(String[] args) {
        // Membuat frame
        JFrame frame = new JFrame("Hitung Luas Lingkaran");
        frame.setSize(600, 800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // Panel input
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(4, 2));
        inputPanel.setBackground(new Color(167, 205, 255, 255)); // Menambahkan warna latar belakang panel

        JLabel radiusLabel = new JLabel("Radius:");
        JTextField radiusField = new JTextField();
        radiusField.setBackground(new Color(255, 255, 255)); // Memberikan warna latar belakang pada text field
        JButton calculateButton = new JButton("Hitung");
        calculateButton.setBackground(new Color(0, 148, 255)); // Menambahkan warna latar belakang tombol
        calculateButton.setForeground(Color.WHITE); // Mengubah warna teks pada tombol

        inputPanel.add(radiusLabel);
        inputPanel.add(radiusField);
        inputPanel.add(new JLabel());
        inputPanel.add(calculateButton);

        frame.add(inputPanel, BorderLayout.NORTH);

        // Model dan tabel untuk hasil
        DefaultTableModel tableModel = new DefaultTableModel(new String[]{"Radius", "Luas"}, 0);
        JTable resultTable = new JTable(tableModel);
        resultTable.setBackground(new Color(252, 186, 0)); // Menambahkan warna latar belakang pada tabel
        resultTable.setForeground(Color.BLACK); // Mengubah warna teks tabel
        JScrollPane scrollPane = new JScrollPane(resultTable);
        frame.add(scrollPane, BorderLayout.CENTER);

        // Action listener untuk tombol hitung
        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String radiusText = radiusField.getText();

                if (radiusText.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "Radius tidak boleh kosong!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                try {
                    double radius = Double.parseDouble(radiusText);
                    double area = PHI * radius * radius;

                    // Menambahkan hasil ke tabel
                    tableModel.addRow(new Object[]{radius, area});

                    // Membersihkan input
                    radiusField.setText("");
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Masukkan angka yang valid!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Menampilkan frame
        frame.setVisible(true);
    }
}

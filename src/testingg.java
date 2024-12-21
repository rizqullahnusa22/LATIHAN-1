import javax.swing.*;
import java.awt.*;

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

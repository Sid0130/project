package com.itwill.project.view;

import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import com.itwill.project.controller.PetDao;
import javax.swing.JButton;

public class DoctorCreateFrame extends JFrame {
	
//	private static final String[] IMAGES = {
//			
//	};

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblDoctor;
	private JLabel lblDoctorName;
	private JLabel lblDoctorSpecialty;
	private JLabel lblDoctorNumber;
	private JLabel lblDoctorMemo;
	private JTextField textNameField;
	private JTextField textSpecialtyField;
	private JTextField textNumberField;
	private JScrollPane scrollPane;
	private JLabel labelImage;
	private PetDao petDao;
	private Component parentComponent;
	private JPanel buttonPanel;
	private JButton btnSave;
	private JButton btnCancel;
	
	
	/**
	 * Launch the application.
	 */
	public static void showDocotrCreate(Component parentComponent) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DoctorCreateFrame frame = new DoctorCreateFrame(parentComponent);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public DoctorCreateFrame(Component parentComponent) {
		petDao = PetDao.INSTANCE;
		this.parentComponent = parentComponent;
		initialize();
	}
	/**
	 * Create the frame.
	 */
	public void initialize() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 382, 352);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setLocationRelativeTo(parentComponent);

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblDoctor = new JLabel("주치의 등록");
		lblDoctor.setFont(new Font("D2Coding", Font.PLAIN, 25));
		lblDoctor.setBounds(12, 10, 138, 30);
		contentPane.add(lblDoctor);
		
		lblDoctorName = new JLabel("이름");
		lblDoctorName.setFont(new Font("D2Coding", Font.PLAIN, 15));
		lblDoctorName.setBounds(43, 63, 30, 18);
		contentPane.add(lblDoctorName);
		
		lblDoctorSpecialty = new JLabel("전문분야");
		lblDoctorSpecialty.setFont(new Font("D2Coding", Font.PLAIN, 15));
		lblDoctorSpecialty.setBounds(13, 91, 60, 18);
		contentPane.add(lblDoctorSpecialty);
		
		lblDoctorNumber = new JLabel("연락처");
		lblDoctorNumber.setFont(new Font("D2Coding", Font.PLAIN, 15));
		lblDoctorNumber.setBounds(28, 121, 45, 18);
		contentPane.add(lblDoctorNumber);
		
		lblDoctorMemo = new JLabel("비고");
		lblDoctorMemo.setFont(new Font("D2Coding", Font.PLAIN, 15));
		lblDoctorMemo.setBounds(12, 154, 45, 18);
		contentPane.add(lblDoctorMemo);
		
		textNameField = new JTextField();
		textNameField.setBounds(87, 59, 116, 21);
		contentPane.add(textNameField);
		textNameField.setColumns(10);
		
		textSpecialtyField = new JTextField();
		textSpecialtyField.setColumns(10);
		textSpecialtyField.setBounds(87, 90, 116, 21);
		contentPane.add(textSpecialtyField);
		
		textNumberField = new JTextField();
		textNumberField.setColumns(10);
		textNumberField.setBounds(87, 120, 116, 21);
		contentPane.add(textNumberField);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 178, 344, 80);
		contentPane.add(scrollPane);
		
		labelImage = new JLabel("");
		Border boder = BorderFactory.createLineBorder(Color.black, 1);
		labelImage.setBorder(boder);
		labelImage.setBounds(234, 23, 120, 130);
		contentPane.add(labelImage);
		
		buttonPanel = new JPanel();
		buttonPanel.setBounds(12, 268, 344, 37);
		contentPane.add(buttonPanel);
		
		btnSave = new JButton("등록");
		btnSave.setFont(new Font("D2Coding", Font.PLAIN, 12));
		buttonPanel.add(btnSave);
		
		btnCancel = new JButton("취소");
		btnCancel.setFont(new Font("D2Coding", Font.PLAIN, 12));
		btnCancel.addActionListener(e -> dispose());
		buttonPanel.add(btnCancel);
	}
}

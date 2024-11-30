package com.itwill.project.view;

import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import com.itwill.project.controller.DoctorDao;
import com.itwill.project.controller.PetDao;
import com.itwill.project.model.Doctor;
import javax.swing.JTextArea;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class DoctorCreateFrame extends JFrame {
	
//	private static final String[] IMAGES = {
//			
//	};
	public interface CreateDoctorNotify {
		void notifyDoctorCreateSuccess();
	}
	
	private static final String[] SPECIALTY = {
			"수의내과", "수의외과"
	};
	
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblDoctor;
	private JLabel lblDoctorName;
	private JLabel lblDoctorSpecialty;
	private JLabel lblDoctorNumber;
	private JLabel lblDoctorMemo;
	private JTextField textNameField;
	private JTextField textNumberField;
	private JScrollPane scrollPane;
	private JLabel labelImage;
	private DoctorDao doctorDao;
	private Component parentComponent;
	private JPanel buttonPanel;
	private JButton btnSave;
	private JButton btnCancel;
	private JComboBox<String> textSpecialtyField;
	private DefaultComboBoxModel<String> model;
	private CreateDoctorNotify app;
	private JTextArea textArea;
	
	
	/**
	 * Launch the application.
	 */
	public static void showDocotrCreate(Component parentComponent, CreateDoctorNotify app) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DoctorCreateFrame frame = new DoctorCreateFrame(parentComponent, app);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public DoctorCreateFrame(Component parentComponent, CreateDoctorNotify app) {
		this.doctorDao = DoctorDao.INSTANCE;
		this.parentComponent = parentComponent;
		this.app = app;
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
		lblDoctor.setFont(new Font("학교안심 바른돋움 B", Font.PLAIN, 20));
		lblDoctor.setBounds(12, 10, 138, 30);
		contentPane.add(lblDoctor);
		
		lblDoctorName = new JLabel("이름");
		lblDoctorName.setFont(new Font("학교안심 바른돋움 R", Font.PLAIN, 15));
		lblDoctorName.setBounds(43, 63, 30, 18);
		contentPane.add(lblDoctorName);
		
		lblDoctorSpecialty = new JLabel("전문분야");
		lblDoctorSpecialty.setFont(new Font("학교안심 바른돋움 R", Font.PLAIN, 15));
		lblDoctorSpecialty.setBounds(13, 91, 60, 18);
		contentPane.add(lblDoctorSpecialty);
		
		lblDoctorNumber = new JLabel("연락처");
		lblDoctorNumber.setFont(new Font("학교안심 바른돋움 R", Font.PLAIN, 15));
		lblDoctorNumber.setBounds(28, 121, 45, 18);
		contentPane.add(lblDoctorNumber);
		
		lblDoctorMemo = new JLabel("비고");
		lblDoctorMemo.setFont(new Font("학교안심 바른돋움 R", Font.PLAIN, 15));
		lblDoctorMemo.setBounds(12, 171, 45, 18);
		contentPane.add(lblDoctorMemo);
		
		textNameField = new JTextField();
		textNameField.setFont(new Font("학교안심 바른돋움 R", Font.PLAIN, 14));
		textNameField.setBounds(87, 59, 116, 21);
		contentPane.add(textNameField);
		textNameField.setColumns(10);
		
		textSpecialtyField = new JComboBox<String>();
		textSpecialtyField.setFont(new Font("학교안심 바른돋움 R", Font.PLAIN, 14));
		textSpecialtyField.setEditable(true);
		model = new DefaultComboBoxModel<>(SPECIALTY);
		textSpecialtyField.setModel(model);
		textSpecialtyField.setBounds(87, 90, 116, 21);
		contentPane.add(textSpecialtyField);
		
		textNumberField = new JTextField();
		textNumberField.setFont(new Font("학교안심 바른돋움 R", Font.PLAIN, 14));
		textNumberField.setColumns(10);
		textNumberField.setBounds(87, 120, 116, 21);
		contentPane.add(textNumberField);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 194, 344, 70);
		contentPane.add(scrollPane);
		
		textArea = new JTextArea();
		textArea.setFont(new Font("학교안심 바른돋움 R", Font.PLAIN, 15));
		scrollPane.setViewportView(textArea);
		
		labelImage = new JLabel("");
		labelImage.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JFileChooser chooser = new JFileChooser();				
				chooser.showOpenDialog(null);
				
			}
		});
		Border boder = BorderFactory.createLineBorder(Color.black, 1);
		labelImage.setBorder(boder);
		labelImage.setBounds(234, 23, 120, 130);
		contentPane.add(labelImage);
		
		buttonPanel = new JPanel();
		buttonPanel.setBounds(12, 268, 344, 37);
		contentPane.add(buttonPanel);
		
		btnSave = new JButton("등록");
		btnSave.addActionListener(e -> createNewDoctor());
		btnSave.setFont(new Font("학교안심 바른돋움 R", Font.PLAIN, 15));
		buttonPanel.add(btnSave);
		
		btnCancel = new JButton("취소");
		btnCancel.setFont(new Font("학교안심 바른돋움 R", Font.PLAIN, 15));
		btnCancel.addActionListener(e -> dispose());
		buttonPanel.add(btnCancel);
	}

	private void createNewDoctor() {
		String doctorName = textNameField.getText();
		String doctorSpeSpecialty = (String) textSpecialtyField.getSelectedItem();
		String doctorPhoneNumber = textNumberField.getText();
		String doctorMemo = textArea.getText();
		if(doctorName.equals("") || doctorPhoneNumber.equals("")) {
			JOptionPane.showMessageDialog(
					DoctorCreateFrame.this,
					"이름과 연락처를 입력하세요.",
					"경고",
					JOptionPane.WARNING_MESSAGE);
			return;
		}
		
		Doctor doctor = Doctor.builder()
				.name(doctorName)
				.specialty(doctorSpeSpecialty)
				.phoneNumber(doctorPhoneNumber)
				.memo(doctorMemo)
				.build();
		
		int result = doctorDao.createDoctor(doctor);
		if (result == 1) {
			JOptionPane.showMessageDialog(DoctorCreateFrame.this, "등록 성공");
			
			app.notifyDoctorCreateSuccess();
			
			dispose();
		} else {
			JOptionPane.showMessageDialog(DoctorCreateFrame.this, "등록 실패");
		}
				
			
		
	}
}

package com.itwill.project.view;

import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.time.format.DateTimeFormatter;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import com.itwill.project.controller.DoctorDao;
import com.itwill.project.controller.PetDao;
import com.itwill.project.model.Doctor;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.SwingConstants;
import javax.swing.JTextArea;
import javax.swing.UIManager;

public class DoctorUpdateFrame extends JFrame {
	
	public interface UpdateDoctorNotify{
		void notifyDoctorUpdateSuccess();
	}
	
	private static final String[] SPECIALTY = {
			"수의내과", "수의외과"
	};
	
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblDoctorUpdate;
	private JLabel lblDoctorName;
	private JLabel lblDoctorSpecialty;
	private JLabel lblDoctorNumber;
	private JTextField textNameField;
	private JComboBox<String> textSpecialtyField;
	private JTextField textNumberField;
	private JLabel labelImage;
	private DoctorDao doctorDao;
	private JPanel buttonPanel;
	private JButton btnSave;
	private JButton btnCancel;
	private JTextField textCreatedField;
	private JTextField textModifiedField;
	private Component parentComponent;
	private UpdateDoctorNotify app;
	private JLabel lblDoctorCreated;
	private JLabel lblDoctorModified;
	private final Integer id;
	private DefaultComboBoxModel<String> model;
	private JTextArea textDoctorMemo;
	private JLabel lblDoctorMemo;
	private JScrollPane scrollPane;
	
	
	/**
	 * Launch the application.
	 */
	public static void showDocotrUpdate(Component parentComponent, UpdateDoctorNotify app, Integer id) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DoctorUpdateFrame frame = new DoctorUpdateFrame(parentComponent, app, id);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public DoctorUpdateFrame(Component parentComponent, UpdateDoctorNotify app, Integer id) {
		doctorDao = DoctorDao.INSTANCE;
		this.parentComponent = parentComponent;
		this.app = app;
		this.id = id;
		
		initialize();
		initializeDoctor();
		
	}
	/**
	 * Create the frame.
	 */
	public void initialize() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 383, 386);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setLocationRelativeTo(parentComponent);

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblDoctorUpdate = new JLabel("주치의 수정");
		lblDoctorUpdate.setFont(new Font("학교안심 바른돋움 B", Font.PLAIN, 20));
		lblDoctorUpdate.setBounds(12, 10, 138, 30);
		contentPane.add(lblDoctorUpdate);
		
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
		
		textNameField = new JTextField();
		textNameField.setFont(new Font("학교안심 바른돋움 R", Font.PLAIN, 14));
		textNameField.setBounds(87, 59, 116, 21);
		contentPane.add(textNameField);
		textNameField.setColumns(10);
		
		textSpecialtyField = new JComboBox<String>();
		textSpecialtyField.setFont(new Font("학교안심 바른돋움 R", Font.PLAIN, 14));
		textSpecialtyField.setEnabled(false);
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
		
		labelImage = new JLabel("");
		Border boder = BorderFactory.createLineBorder(Color.black, 1);
		labelImage.setBorder(boder);
		labelImage.setBounds(234, 23, 120, 130);
		contentPane.add(labelImage);
		
		buttonPanel = new JPanel();
		buttonPanel.setBounds(0, 307, 367, 40);
		contentPane.add(buttonPanel);
		
		btnSave = new JButton("확인");
		btnSave.addActionListener(e -> updateDoctor());
		btnSave.setFont(new Font("학교안심 바른돋움 R", Font.PLAIN, 15));
		buttonPanel.add(btnSave);
		
		btnCancel = new JButton("취소");
		btnCancel.setFont(new Font("학교안심 바른돋움 R", Font.PLAIN, 15));
		btnCancel.addActionListener(e -> dispose());
		buttonPanel.add(btnCancel);
		
		textCreatedField = new JTextField();
		textCreatedField.setEditable(false);
		textCreatedField.setFont(new Font("학교안심 바른돋움 R", Font.PLAIN, 12));
		textCreatedField.setHorizontalAlignment(SwingConstants.CENTER);
		textCreatedField.setColumns(10);
		textCreatedField.setBounds(52, 280, 116, 21);
		contentPane.add(textCreatedField);
		
		textModifiedField = new JTextField();
		textModifiedField.setEditable(false);
		textModifiedField.setFont(new Font("학교안심 바른돋움 R", Font.PLAIN, 12));
		textModifiedField.setHorizontalAlignment(SwingConstants.CENTER);
		textModifiedField.setColumns(10);
		textModifiedField.setBounds(196, 280, 116, 21);
		contentPane.add(textModifiedField);
		
		lblDoctorCreated = new JLabel("등록일");
		lblDoctorCreated.setFont(new Font("학교안심 바른돋움 R", Font.PLAIN, 15));
		lblDoctorCreated.setBounds(85, 261, 45, 18);
		contentPane.add(lblDoctorCreated);
		
		lblDoctorModified = new JLabel("수정일");
		lblDoctorModified.setFont(new Font("학교안심 바른돋움 R", Font.PLAIN, 15));
		lblDoctorModified.setBounds(230, 261, 45, 18);
		contentPane.add(lblDoctorModified);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 186, 344, 70);
		contentPane.add(scrollPane);
		
		textDoctorMemo = new JTextArea();
		textDoctorMemo.setFont(new Font("학교안심 바른돋움 R", Font.PLAIN, 15));
		scrollPane.setViewportView(textDoctorMemo);
		
		lblDoctorMemo = new JLabel("비고");
		lblDoctorMemo.setFont(new Font("학교안심 바른돋움 R", Font.PLAIN, 15));
		lblDoctorMemo.setBounds(12, 163, 45, 18);
		contentPane.add(lblDoctorMemo);
	}
	
	private void updateDoctor() {
		String doctorName = textNameField.getText();
		String doctorPhone = textNumberField.getText();
		String doctorSpecialty = (String) textSpecialtyField.getSelectedItem();
		String doctorMemo = textDoctorMemo.getText();
		
		if(doctorName.equals("") || doctorPhone.equals("")) {
			JOptionPane.showMessageDialog(
					DoctorUpdateFrame.this,
					"이름과 연락처는 반드시 입력해야 합니다.",
					"경고",
					JOptionPane.WARNING_MESSAGE);
			return;
		}
		
		Doctor doctor = Doctor.builder()
				.id(id)
				.name(doctorName)
				.phoneNumber(doctorPhone)
				.specialty(doctorSpecialty)
				.memo(doctorMemo)
				.build();
		
		
		int result = doctorDao.updateDoctor(doctor);
		if(result == 1) {
			JOptionPane.showMessageDialog(DoctorUpdateFrame.this, "수정 성공");
			app.notifyDoctorUpdateSuccess();
			dispose();
		} else {
			JOptionPane.showMessageDialog(DoctorUpdateFrame.this, "수정 실패");
		}
		
		
	}
	
	private void initializeDoctor() {
		Doctor doctor = doctorDao.readDoctor(id);
		
		textNameField.setText(doctor.getName());
		textNumberField.setText(doctor.getPhoneNumber());
		textSpecialtyField.setSelectedItem(doctor.getSpecialty().toString());
		textDoctorMemo.setText(doctor.getMemo());
		textCreatedField.setText(doctor.getCreatedTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH")));
		textModifiedField.setText(doctor.getModifiedTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH")));	
	}
}

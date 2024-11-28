package com.itwill.project.view;

import java.awt.Component;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import com.itwill.project.controller.PetDao;
import com.itwill.project.view.PetCreateFrame.CreateNotify;

public class PetReceptionFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textOwderField;
	private JTextField textWeightField;
	private JTextField textAgeField;
	private JTextField textGenderField;
	private JTextField textNameField;
	private JTextField textNumberField;
	private JTextField textTimeField;
	private JSeparator separator_1;
	private JSeparator separator;
	private JPanel buttonPanel;
	private JButton btnSave_1_1;
	private JButton btnSave;
	private JPanel titlePanel;
	private JLabel titleLabel;
	private JLabel timeLabel;
	private JLabel numberLabel;
	private JLabel nameLabel;
	private JLabel typeLabel;
	private JLabel genderLabel;
	private JLabel ageLabel;
	private JLabel weightLabel;
	private JLabel owderLabel;
	private JLabel doctorLabel;
	private JLabel statusLabel;
	private JComboBox doctorComboBox;
	private JComboBox statusComboBox;
	private JComboBox typeComboBox;
	private Component parentComponent;
	private PetDao petDao;
	private CreateNotify app;
	
	public static void showReceptionFrame(Component parentComponent, CreateNotify app) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PetReceptionFrame frame = new PetReceptionFrame(parentComponent, app);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public PetReceptionFrame(Component parentComponent, CreateNotify app) {
		petDao = PetDao.INSTANCE;
		this.parentComponent = parentComponent;
		this.app = app;
		initialize();
	}
	
	/**
	 * Create the frame.
	 */
	public void initialize() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 340, 520);
		setLocationRelativeTo(parentComponent);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		separator_1 = new JSeparator();
		separator_1.setBounds(2, 435, 319, 6);
		contentPane.add(separator_1);
		
		separator = new JSeparator();
		separator.setBounds(2, 41, 319, 6);
		contentPane.add(separator);
		
		buttonPanel = new JPanel();
		buttonPanel.setBounds(0, 440, 324, 34);
		contentPane.add(buttonPanel);
		
		btnSave = new JButton("취소");
		btnSave.addActionListener(e -> dispose());
		buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		btnSave_1_1 = new JButton("접수");
		btnSave_1_1.setFont(new Font("D2Coding", Font.PLAIN, 12));
		buttonPanel.add(btnSave_1_1);
		btnSave.setFont(new Font("D2Coding", Font.PLAIN, 12));
		buttonPanel.add(btnSave);
		
		titlePanel = new JPanel();
		titlePanel.setBounds(0, 5, 324, 34);
		contentPane.add(titlePanel);
		titlePanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		titleLabel = new JLabel("접수 정보");
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel.setFont(new Font("D2Coding", Font.PLAIN, 20));
		titlePanel.add(titleLabel);
		
		timeLabel = new JLabel("예약시간");
		timeLabel.setFont(new Font("D2Coding", Font.PLAIN, 15));
		timeLabel.setBounds(67, 59, 60, 24);
		contentPane.add(timeLabel);
		
		numberLabel = new JLabel("번호");
		numberLabel.setFont(new Font("D2Coding", Font.PLAIN, 15));
		numberLabel.setBounds(67, 93, 60, 24);
		contentPane.add(numberLabel);
		
		nameLabel = new JLabel("이름");
		nameLabel.setFont(new Font("D2Coding", Font.PLAIN, 15));
		nameLabel.setBounds(67, 127, 60, 24);
		contentPane.add(nameLabel);
		
		typeLabel = new JLabel("분류");
		typeLabel.setFont(new Font("D2Coding", Font.PLAIN, 15));
		typeLabel.setBounds(67, 161, 60, 24);
		contentPane.add(typeLabel);
		
		genderLabel = new JLabel("성별");
		genderLabel.setFont(new Font("D2Coding", Font.PLAIN, 15));
		genderLabel.setBounds(67, 195, 60, 24);
		contentPane.add(genderLabel);
		
		ageLabel = new JLabel("나이");
		ageLabel.setFont(new Font("D2Coding", Font.PLAIN, 15));
		ageLabel.setBounds(67, 229, 60, 24);
		contentPane.add(ageLabel);
		
		weightLabel = new JLabel("체중");
		weightLabel.setFont(new Font("D2Coding", Font.PLAIN, 15));
		weightLabel.setBounds(67, 263, 60, 24);
		contentPane.add(weightLabel);
		
		owderLabel = new JLabel("보호자");
		owderLabel.setFont(new Font("D2Coding", Font.PLAIN, 15));
		owderLabel.setBounds(67, 297, 60, 24);
		contentPane.add(owderLabel);
		
		doctorLabel = new JLabel("주치의");
		doctorLabel.setFont(new Font("D2Coding", Font.PLAIN, 15));
		doctorLabel.setBounds(67, 359, 60, 24);
		contentPane.add(doctorLabel);
		
		statusLabel = new JLabel("상태");
		statusLabel.setFont(new Font("D2Coding", Font.PLAIN, 15));
		statusLabel.setBounds(67, 393, 60, 24);
		contentPane.add(statusLabel);
		
		doctorComboBox = new JComboBox();
		doctorComboBox.setBounds(139, 359, 110, 22);
		contentPane.add(doctorComboBox);
		
		statusComboBox = new JComboBox();
		statusComboBox.setBounds(139, 394, 110, 22);
		contentPane.add(statusComboBox);
		
		textOwderField = new JTextField();
		textOwderField.setBounds(139, 297, 110, 22);
		contentPane.add(textOwderField);
		textOwderField.setColumns(10);
		
		textWeightField = new JTextField();
		textWeightField.setColumns(10);
		textWeightField.setBounds(139, 265, 110, 22);
		contentPane.add(textWeightField);
		
		textAgeField = new JTextField();
		textAgeField.setColumns(10);
		textAgeField.setBounds(139, 231, 110, 22);
		contentPane.add(textAgeField);
		
		textGenderField = new JTextField();
		textGenderField.setColumns(10);
		textGenderField.setBounds(139, 197, 110, 22);
		contentPane.add(textGenderField);
		
		textNameField = new JTextField();
		textNameField.setColumns(10);
		textNameField.setBounds(139, 129, 110, 22);
		contentPane.add(textNameField);
		
		textNumberField = new JTextField();
		textNumberField.setColumns(10);
		textNumberField.setBounds(139, 95, 110, 22);
		contentPane.add(textNumberField);
		
		textTimeField = new JTextField();
		textTimeField.setColumns(10);
		textTimeField.setBounds(139, 61, 110, 22);
		contentPane.add(textTimeField);
		
		typeComboBox = new JComboBox();
		typeComboBox.setBounds(139, 163, 110, 22);
		contentPane.add(typeComboBox);
	}
}

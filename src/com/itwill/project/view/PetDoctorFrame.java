package com.itwill.project.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.itwill.project.controller.PetDao;

import java.awt.BorderLayout;
import java.awt.Component;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JLabel;

public class PetDoctorFrame extends JFrame {

	
	private static final String[] COULMN_NAME = {
			"번호", "이름", "전문분야","연락처"
	};
	private static final long serialVersionUID = 1L;
	private JPanel doctorPane;
	private JPanel titlePanel;
	private JTable table;
	private JScrollPane scrollPane;
	private JPanel buttonPanel;
	private JButton btnCreateDoctor;
	private JButton btnEditDoctor;
	private JButton btnDeleteDoctor;
	private JLabel lblNewLabel;
	private DefaultTableModel model;
	private Component parentComponent;
	private PetDao petDao;
	private JButton btnCancelDoctor;

	/**
	 * Launch the application.
	 */
	public static void showDoctorCreateFrame(Component parentComponent) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PetDoctorFrame frame = new PetDoctorFrame(parentComponent);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public PetDoctorFrame(Component parentComponent) {
		petDao = PetDao.INSTANCE;
		this.parentComponent = parentComponent;
		initialize();
	}
	/**
	 * Create the frame.
	 */
	public void initialize() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 624, 507);
		setLocationRelativeTo(parentComponent);
		doctorPane = new JPanel();
		doctorPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(doctorPane);
		doctorPane.setLayout(new BorderLayout(0, 0));
		
		buttonPanel = new JPanel();
		doctorPane.add(buttonPanel, BorderLayout.SOUTH);
		
		btnCreateDoctor = new JButton("등록");
		btnCreateDoctor.addActionListener(e -> DoctorCreateFrame.showDocotrCreate(doctorPane));
		btnCreateDoctor.addActionListener(null);
		
		btnCreateDoctor.setFont(new Font("D2Coding", Font.PLAIN, 12));
		buttonPanel.add(btnCreateDoctor);
		
		btnEditDoctor = new JButton("수정");
		btnEditDoctor.addActionListener(e -> DoctorUpdateFrame.showDocotrUpdate(doctorPane));
		btnEditDoctor.setFont(new Font("D2Coding", Font.PLAIN, 12));
		buttonPanel.add(btnEditDoctor);
		
		btnDeleteDoctor = new JButton("삭제");
		btnDeleteDoctor.setFont(new Font("D2Coding", Font.PLAIN, 12));
		buttonPanel.add(btnDeleteDoctor);
		
		btnCancelDoctor = new JButton("취소");
		btnCancelDoctor.setFont(new Font("D2Coding", Font.PLAIN, 12));
		btnCancelDoctor.addActionListener(e -> dispose());
		buttonPanel.add(btnCancelDoctor);
		
		titlePanel = new JPanel();
		doctorPane.add(titlePanel, BorderLayout.NORTH);
		
		lblNewLabel = new JLabel("의사 리스트");
		lblNewLabel.setFont(new Font("D2Coding", Font.PLAIN, 20));
		titlePanel.add(lblNewLabel);
		
		scrollPane = new JScrollPane();
		doctorPane.add(scrollPane, BorderLayout.CENTER);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		DefaultTableModel model = new DefaultTableModel(null, COULMN_NAME);
		table.setModel(model);
				
		
	}

}

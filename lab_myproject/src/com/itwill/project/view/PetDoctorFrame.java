package com.itwill.project.view;

import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.itwill.project.controller.DoctorDao;
import com.itwill.project.model.Doctor;
import com.itwill.project.view.DoctorCreateFrame.CreateDoctorNotify;
import com.itwill.project.view.DoctorUpdateFrame.UpdateDoctorNotify;
import java.awt.BorderLayout;
import java.awt.FlowLayout;

public class PetDoctorFrame extends JFrame implements CreateDoctorNotify, UpdateDoctorNotify {

	
	private static final String[] COULMN_NAMES = {
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
	private DoctorDao doctorDao;
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
		doctorDao = DoctorDao.INSTANCE;
		this.parentComponent = parentComponent;
		initialize();
		initializeTable();
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
		doctorPane.setLayout(null);
		
		buttonPanel = new JPanel();
		buttonPanel.setBounds(5, 427, 598, 39);
		doctorPane.add(buttonPanel);
		
		btnCreateDoctor = new JButton("등록");
		btnCreateDoctor.addActionListener(e -> DoctorCreateFrame.showDocotrCreate(doctorPane,PetDoctorFrame.this));
		btnCreateDoctor.addActionListener(null);
		
		btnCreateDoctor.setFont(new Font("학교안심 바른돋움 R", Font.PLAIN, 15));
		buttonPanel.add(btnCreateDoctor);
		
		btnEditDoctor = new JButton("수정");
		btnEditDoctor.addActionListener(e -> showDoctorDetails());
		btnEditDoctor.setFont(new Font("학교안심 바른돋움 R", Font.PLAIN, 15));
		buttonPanel.add(btnEditDoctor);
		
		btnDeleteDoctor = new JButton("삭제");
		btnDeleteDoctor.addActionListener(e -> deleteDoctor());
		btnDeleteDoctor.setFont(new Font("학교안심 바른돋움 R", Font.PLAIN, 15));
		buttonPanel.add(btnDeleteDoctor);
		
		btnCancelDoctor = new JButton("취소");
		btnCancelDoctor.setFont(new Font("학교안심 바른돋움 R", Font.PLAIN, 15));
		btnCancelDoctor.addActionListener(e -> dispose());
		buttonPanel.add(btnCancelDoctor);
		
		titlePanel = new JPanel();
		titlePanel.setBounds(5, 5, 598, 37);
		doctorPane.add(titlePanel);
		titlePanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		lblNewLabel = new JLabel("의사 리스트");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("학교안심 바른돋움 B", Font.PLAIN, 24));
		titlePanel.add(lblNewLabel);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(5, 49, 598, 375);
		doctorPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		DefaultTableModel model = new DefaultTableModel(null, COULMN_NAMES);
		table.setModel(model);
				
		
	}
	
	// 의사 테이블 업데이트
	private void showDoctorDetails() {
		int index = table.getSelectedRow();
		if(index == -1) {
			JOptionPane.showMessageDialog(
					PetDoctorFrame.this, 
					"수정할 행을 선택하세요",
					"경고",
					JOptionPane.WARNING_MESSAGE);
			return;			
		}
		Integer id = (Integer) model.getValueAt(index, 0);
		
		DoctorUpdateFrame.showDocotrUpdate(doctorPane,PetDoctorFrame.this, id);
		
	}
	
	private void deleteDoctor() { // 삭제 이벤트
		int index = table.getSelectedRow();
		if(index == -1) {
			JOptionPane.showMessageDialog(
					doctorPane,
					"삭제할 행을 먼저 선택하세요",
					"경고",
					JOptionPane.WARNING_MESSAGE);
			return;
		}
		
		int confirm = JOptionPane.showConfirmDialog(
				doctorPane,
				"삭제할까요?",
				"삭제 확인",
				JOptionPane.YES_NO_OPTION);
		if(confirm == JOptionPane.YES_NO_OPTION) {
			Integer id = (Integer) model.getValueAt(index, 0);
			
			int result = doctorDao.deleteDoctor(id);
			if(result == 1) {
				initializeTable();
				JOptionPane.showMessageDialog(doctorPane, "삭제 성공");
			} else {
				JOptionPane.showMessageDialog(doctorPane, "삭제 실패");
			}
		}
	}
	

	private void initializeTable() {
		List<Doctor> list = doctorDao.read();
		resetTableModel(list);
	}
	
	private void resetTableModel(List<Doctor> list) {
        model = new DefaultTableModel(null, COULMN_NAMES);
        
        for (Doctor b : list) {
            Object[] rowData = {
                    b.getId(),  b.getName(), b.getSpecialty(), b.getPhoneNumber(), b.getMemo()
            };
            model.addRow(rowData);
        }
        table.setModel(model);
    }

	@Override
	public void notifyDoctorCreateSuccess() {
		initializeTable();
		
	}

	@Override
	public void notifyDoctorUpdateSuccess() {
		initializeTable();
	}
}

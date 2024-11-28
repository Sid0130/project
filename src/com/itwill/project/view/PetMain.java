package com.itwill.project.view;

import java.awt.EventQueue;
import java.awt.Font;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;

import com.itwill.project.controller.PetDao;
import com.itwill.project.model.AnimalOwner;
import com.itwill.project.model.AnimalOwnerUpdate;
import com.itwill.project.view.PetCreateFrame.CreateNotify;
import com.itwill.project.view.PetDetailsFrame.UpdateNotify;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PetMain implements CreateNotify, UpdateNotify {

	
	private static final String[] SEARCH_TYPE = { 
			"전체", "이름", "보호자" , "분류", "성별" 
	};
	
	private static final String[] COULMN_CUSTOMER = { // 고객 목록. 
			"번호", "이름", "분류", "성별", "나이", "체중", "보호자"
	};
	private static final String[] COULMN_RECEPTION = { // 접수 목록. 
			"예약시간", "주치의", "번호", "이름", "분류", "상태"
	};
	private JFrame frame;
	private JTable tableCustomerView;
	private JPanel buttonPanel;
	private JPanel searchPanel;
	private JScrollPane scrollPaneReception;
	private JScrollPane scrollPaneCustomer;
	private JPanel customerPanel;
	private JButton btnCreateNew;
	private JButton btnCreateDoctor;
	private JButton btnSearch;
	private DefaultTableModel model;
	
	private JComboBox<String> searchComboBox;
	private DefaultComboBoxModel<String> modelCombo;
	private JButton lblDeleteCustmoer;
	private JButton lblEditCustmoer;
	private JTextField textSearch;
	private JTable tableReceptionView;
	private JButton btnReception;
	private JSeparator separator;
	private JSeparator separator_1;
	private JPanel receptionPanel;
	private JButton lblDeleteReception;
	private JButton lblEditReception;
	private PetDao petDao;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PetMain window = new PetMain();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public PetMain() {
		petDao = PetDao.INSTANCE; 
		initialize();
		initializeTable();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("동물병원 접수 프로그램 v1.0");
		frame.setBounds(100, 100, 800, 641);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		separator = new JSeparator();
		separator.setBounds(0, 43, 784, 12);
		frame.getContentPane().add(separator);
		
		separator_1 = new JSeparator();
		separator_1.setBounds(0, 555, 784, 12);
		frame.getContentPane().add(separator_1);
		
		buttonPanel = new JPanel();
		buttonPanel.setBounds(0, 555, 784, 47);
		frame.getContentPane().add(buttonPanel);
		buttonPanel.setLayout(null);
		
		btnCreateNew = new JButton("신규등록");
		btnCreateNew.setFont(new Font("학교안심 바른돋움 B", Font.PLAIN, 12));
		btnCreateNew.addActionListener(e -> PetCreateFrame.showPetCreateFrame(frame, PetMain.this));
		btnCreateNew.setBounds(691, 10, 81, 30);
		buttonPanel.add(btnCreateNew);
		
		btnCreateDoctor = new JButton("주치의");
		btnCreateDoctor.setFont(new Font("학교안심 바른돋움 B", Font.PLAIN, 12));
		btnCreateDoctor.addActionListener(e -> PetDoctorFrame.showDoctorCreateFrame(frame));
		btnCreateDoctor.setBounds(607, 10, 81, 30);
		buttonPanel.add(btnCreateDoctor);
		
		searchPanel = new JPanel();
		searchPanel.setBounds(0, 0, 784, 43);
		frame.getContentPane().add(searchPanel);
		searchPanel.setLayout(null);
		
		btnSearch = new JButton("검색");
		btnSearch.addActionListener(e -> searchList());
		btnSearch.setBounds(707, 8, 65, 25);
		searchPanel.add(btnSearch);
		
		btnReception = new JButton("접수");
		btnReception.setFont(new Font("학교안심 바른돋움 B", Font.PLAIN, 12));
		btnReception.addActionListener(e -> PetReceptionFrame.showReceptionFrame(frame,PetMain.this));
		btnReception.setBounds(12, 5, 69, 30);
		searchPanel.add(btnReception);
		
		textSearch = new JTextField();
		textSearch.setBounds(510, 10, 192, 22);
		searchPanel.add(textSearch);
		textSearch.setColumns(10);
		
		searchComboBox = new JComboBox<>();
		modelCombo = new  DefaultComboBoxModel<String>(SEARCH_TYPE);
		searchComboBox.setModel(modelCombo);
		searchComboBox.setBounds(393, 10, 105, 22);
		searchPanel.add(searchComboBox);
		
		scrollPaneReception = new JScrollPane();
		scrollPaneReception.setBounds(12, 75, 366, 470);
		frame.getContentPane().add(scrollPaneReception);
		
		tableReceptionView = new JTable();
		scrollPaneReception.setViewportView(tableReceptionView);
		model = new DefaultTableModel(null, COULMN_RECEPTION);
		tableReceptionView.setModel(model);
		
		
		scrollPaneCustomer = new JScrollPane();
		scrollPaneCustomer.setBounds(390, 75, 382, 470);
		frame.getContentPane().add(scrollPaneCustomer);
		
		tableCustomerView = new JTable();
		scrollPaneCustomer.setViewportView(tableCustomerView);
		
		model = new DefaultTableModel(null, COULMN_CUSTOMER);
		tableCustomerView.setModel(model);
		
		customerPanel = new JPanel();
		customerPanel.setLayout(null);
		customerPanel.setBounds(390, 43, 382, 33);
		frame.getContentPane().add(customerPanel);
		
		lblDeleteCustmoer = new JButton("삭제");
		lblDeleteCustmoer.setFont(new Font("학교안심 바른돋움 B", Font.PLAIN, 11));
		lblDeleteCustmoer.addActionListener(e -> deleteAnimal());
		lblDeleteCustmoer.setHorizontalAlignment(SwingConstants.CENTER);
		lblDeleteCustmoer.setBackground(UIManager.getColor("Button.background"));
		lblDeleteCustmoer.setBounds(320, 5, 57, 25);
		customerPanel.add(lblDeleteCustmoer);
		
		lblEditCustmoer = new JButton("수정");
		lblEditCustmoer.addActionListener(e -> showPetDetails());
		lblEditCustmoer.setFont(new Font("학교안심 바른돋움 B", Font.PLAIN, 11));
		lblEditCustmoer.setHorizontalAlignment(SwingConstants.CENTER);
		lblEditCustmoer.setBounds(260, 5, 57, 25);
		customerPanel.add(lblEditCustmoer);
		
		lblNewLabel = new JLabel("고객 목록");
		lblNewLabel.setFont(new Font("학교안심 바른돋움 B", Font.PLAIN, 20));
		lblNewLabel.setBounds(0, 5, 87, 25);
		customerPanel.add(lblNewLabel);
		
		JLabel lblNewLabel_2 = new JLabel("새로고침");
		lblNewLabel_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				initializeTable();
			}
		});
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setFont(new Font("학교안심 바른돋움 B", Font.PLAIN, 10));
		lblNewLabel_2.setBounds(214, 5, 46, 25);
		customerPanel.add(lblNewLabel_2);
		
		receptionPanel = new JPanel();
		receptionPanel.setLayout(null);
		receptionPanel.setBounds(0, 43, 378, 33);
		frame.getContentPane().add(receptionPanel);
		
		lblDeleteReception = new JButton("삭제");
		lblDeleteReception.setFont(new Font("학교안심 바른돋움 B", Font.PLAIN, 11));
		lblDeleteReception.setHorizontalAlignment(SwingConstants.CENTER);
		lblDeleteReception.setBackground(UIManager.getColor("Button.background"));
		lblDeleteReception.setBounds(309, 5, 57, 25);
		receptionPanel.add(lblDeleteReception);
		
		lblEditReception = new JButton("수정");
		lblEditReception.setFont(new Font("학교안심 바른돋움 B", Font.PLAIN, 11));
		lblEditReception.setHorizontalAlignment(SwingConstants.CENTER);
		lblEditReception.setBounds(249, 5, 57, 25);
		receptionPanel.add(lblEditReception);
		
		lblNewLabel_1 = new JLabel("접수 목록");
		lblNewLabel_1.setFont(new Font("학교안심 바른돋움 B", Font.PLAIN, 20));
		lblNewLabel_1.setBounds(12, 4, 104, 25);
		receptionPanel.add(lblNewLabel_1);
		
		
	}




	private void initializeTable() {
		List<AnimalOwner> list = petDao.readAnimalOwner();
		resetTabel(list);
		
	}	
	
	private void resetTabel(List<AnimalOwner> list) {
		model = new DefaultTableModel(null, COULMN_CUSTOMER);
		
		
		for(AnimalOwner aao : list) {
			Object[] rowDate = {
					aao.getAnimalId(),
					aao.getAnimalName(),
					aao.getAnimalType(),
					aao.getAnimalGender(),
					aao.getAnimalAge(),					
					aao.getAnimalWeight(),
					aao.getOwnerName()
			};
			
			model.addRow(rowDate);
		}
		
		tableCustomerView.setModel(model);
	}
	

	
	
	//삭제 기능
	private void deleteAnimal() {
		
		int index = tableCustomerView.getSelectedRow();
		if(index == -1) {
			JOptionPane.showMessageDialog(
					frame,
					"테이블에서 삭제할 행을 먼저 선택하세요",
					"경고",
					JOptionPane.WARNING_MESSAGE);
			return;
		}
		
		int confirm = JOptionPane.showConfirmDialog(
				frame,
				"정말 삭제할까요?",
				"삭제 확인",
				JOptionPane.YES_NO_OPTION);
		if(confirm == JOptionPane.YES_OPTION) {
			
			Integer id = (Integer) model.getValueAt(index, 0);
			
			int result = petDao.delete(id);
			
			id = (Integer)model.getValueAt(index, 0);
			petDao.deleteOwner(id);
			
			if(result == 1) {
				JOptionPane.showMessageDialog(frame, "삭제 성공");
				initializeTable();
			} else {
				JOptionPane.showMessageDialog(frame, "삭제 실패");
			}
		}
	}
	
	//업데이트 기능
	private void showPetDetails() {
		int index = tableCustomerView.getSelectedRow();
		if(index == -1) {
			JOptionPane.showMessageDialog(
					frame,
					"테이블에서 수정할 행을 먼저 선택하세요",
					"경고",
					JOptionPane.WARNING_MESSAGE);
			return;
		}
		
		Integer id = (Integer) model.getValueAt(index, 0);
		
		PetDetailsFrame.showPetDetailsFrame(frame, PetMain.this, id);
	}
	
	// 검색 기능
	private void searchList() {
		int type = searchComboBox.getSelectedIndex(); 
		
		
		String keyword = textSearch.getText();
		if(keyword.equals("")) {
			JOptionPane.showMessageDialog(
					frame,
					"검색어를 입력하세요",
					"경고",
					JOptionPane.WARNING_MESSAGE);
			return;
		}
		
		List<AnimalOwner> ao = petDao.read(type, keyword);
		resetTabel(ao);
	}
	
	
	

	@Override
	public void notifyCreateSuccess() { //성공했음을 알리고 초기화.
		initializeTable();
	}
	@Override
	public void notifyUpdateSuccess() { //업데이트 했음을 알리고 초기화.
		initializeTable();		
	}
}

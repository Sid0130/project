package com.itwill.project.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
import com.itwill.project.controller.ReceptionDao;
import com.itwill.project.model.Animal;
import com.itwill.project.model.AnimalOwner;
import com.itwill.project.model.ReceptionData;
import com.itwill.project.view.PetCreateFrame.CreateNotify;
import com.itwill.project.view.PetDetailsFrame.UpdateNotify;

public class PetMain implements CreateNotify, UpdateNotify {

	
	private static final String[] SEARCH_TYPE = { 
			"전체", "이름", "보호자" , "분류", "성별" 
	};
	
	private static final String[] COULMN_CUSTOMER = { // 고객 목록. 
			"번호", "이름", "분류", "성별", "나이", "체중", "보호자"
	};
	private static final String[] COULMN_RECEPTION = { // 접수 목록.
			"날짜","예약시간", "주치의", "번호", "보호자 ", "이름", "분류", "상태" };
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
	private JPanel receptionPanel;
	private JButton lblDeleteReception;
	private JButton lblEditReception;
	private PetDao petDao;
	private ReceptionDao receptionDao;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel;
	private Integer doctorId;
	private JSeparator separator_1;

	private Animal animalId;
	


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
		receptionDao = ReceptionDao.INSTANCE;
		initialize();
		initializeTable();
		initializeTables();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		UIManagerDesignComponent();
		
		frame = new JFrame();
		frame.setTitle("동물병원 접수 프로그램 v1.0");
		frame.setBounds(100, 100, 1001, 715);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout());
		
		separator = new JSeparator();
		separator.setBounds(0, 43, 1200, 12);
		frame.getContentPane().add(separator);
		modelCombo = new  DefaultComboBoxModel<String>(SEARCH_TYPE);
		model = new DefaultTableModel(null, COULMN_RECEPTION);
		
		model = new DefaultTableModel(null, COULMN_CUSTOMER);
		
		receptionPanel = new JPanel();
		receptionPanel.setBackground(SystemColor.inactiveCaptionBorder);
		receptionPanel.setLayout(null);
		receptionPanel.setBounds(0, 43, 378, 33);
		frame.getContentPane().add(receptionPanel);
		
		
		scrollPaneCustomer = new JScrollPane();
		receptionPanel.add(scrollPaneCustomer);
		scrollPaneCustomer.setBounds(494, 90, 479, 522);
		
		tableCustomerView = new JTable();
		scrollPaneCustomer.setViewportView(tableCustomerView);
		
		tableCustomerView.setSelectionBackground(Color.LIGHT_GRAY); 
		tableCustomerView.setSelectionForeground(Color.BLACK); 
		tableCustomerView.setGridColor(Color.LIGHT_GRAY);
		tableCustomerView.setModel(model);
		
		customerPanel = new JPanel();
		receptionPanel.add(customerPanel);
		customerPanel.setBackground(SystemColor.inactiveCaptionBorder);
		customerPanel.setLayout(null);
		customerPanel.setBounds(494, 48, 479, 43);
		
		lblDeleteCustmoer = new JButton("삭제");
		lblDeleteCustmoer.setFont(new Font("학교안심 바른돋움 B", Font.PLAIN, 12));
		lblDeleteCustmoer.addActionListener(e -> deleteAnimal());
		lblDeleteCustmoer.setHorizontalAlignment(SwingConstants.CENTER);
		lblDeleteCustmoer.setBackground(UIManager.getColor("Button.background"));
		lblDeleteCustmoer.setBounds(414, 9, 57, 25);
		customerPanel.add(lblDeleteCustmoer);
		
		lblEditCustmoer = new JButton("수정");
		lblEditCustmoer.addActionListener(e -> showPetDetails());
		lblEditCustmoer.setFont(new Font("학교안심 바른돋움 B", Font.PLAIN, 12));
		lblEditCustmoer.setHorizontalAlignment(SwingConstants.CENTER);
		lblEditCustmoer.setBounds(355, 9, 57, 25);
		customerPanel.add(lblEditCustmoer);
		
		
		lblNewLabel = new JLabel("고객 목록");
		lblNewLabel.setFont(new Font("학교안심 바른돋움 B", Font.PLAIN, 25));
		lblNewLabel.setBounds(12, 10, 111, 25);
		customerPanel.add(lblNewLabel);
		
		searchPanel = new JPanel();
		receptionPanel.add(searchPanel);
		searchPanel.setBackground(SystemColor.inactiveCaptionBorder);
		searchPanel.setBounds(0, 0, 985, 43);
		searchPanel.setLayout(null);
		
		btnSearch = new JButton("검색");
		btnSearch.setFont(new Font("학교안심 바른돋움 R", Font.PLAIN, 14));
		btnSearch.addActionListener(e -> searchList());
		btnSearch.setBounds(847, 8, 65, 29);
		searchPanel.add(btnSearch);
		
		btnReception = new JButton("접수");
		btnReception.setFont(new Font("학교안심 바른돋움 B", Font.PLAIN, 16));
		btnReception.addActionListener(e -> showReceptionDetails());
		btnReception.setBounds(12, 5, 70, 32);
		searchPanel.add(btnReception);
		
		textSearch = new JTextField();
		textSearch.setBounds(605, 10, 237, 26);
		searchPanel.add(textSearch);
		textSearch.setColumns(10);
		
		searchComboBox = new JComboBox<>();
		searchComboBox.setFont(new Font("학교안심 바른돋움 R", Font.PLAIN, 14));
		searchComboBox.setModel(modelCombo);
		searchComboBox.setBounds(495, 12, 105, 22);
		searchPanel.add(searchComboBox);
		
		JLabel lblNewLabel_2 = new JLabel("새로고침");
		lblNewLabel_2.setBounds(916, 11, 57, 25);
		searchPanel.add(lblNewLabel_2);
		lblNewLabel_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				initializeTable();
			}
		});
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setFont(new Font("학교안심 바른돋움 B", Font.PLAIN, 12));
		
		buttonPanel = new JPanel();
		receptionPanel.add(buttonPanel);
		buttonPanel.setBackground(SystemColor.inactiveCaptionBorder);
		buttonPanel.setBounds(0, 624, 985, 52);
		buttonPanel.setLayout(null);
		
		btnCreateNew = new JButton("신규등록");
		btnCreateNew.setFont(new Font("학교안심 바른돋움 B", Font.PLAIN, 14));
		btnCreateNew.addActionListener(e -> PetCreateFrame.showPetCreateFrame(frame, PetMain.this));
		btnCreateNew.setBounds(885, 5, 88, 40);
		buttonPanel.add(btnCreateNew);
		
		btnCreateDoctor = new JButton("의사등록");
		btnCreateDoctor.setFont(new Font("학교안심 바른돋움 B", Font.PLAIN, 14));
		btnCreateDoctor.addActionListener(e -> PetDoctorFrame.showDoctorCreateFrame(frame));
		btnCreateDoctor.setBounds(791, 5, 88, 40);
		buttonPanel.add(btnCreateDoctor);
		
		separator_1 = new JSeparator();
		separator_1.setBounds(0, 622, 985, 13);
		receptionPanel.add(separator_1);
		
		
		scrollPaneReception = new JScrollPane();
		receptionPanel.add(scrollPaneReception);
		scrollPaneReception.setBounds(10, 90, 476, 522);
		
		tableReceptionView = new JTable();
		tableReceptionView.setModel(model);
		
		scrollPaneReception.setViewportView(tableReceptionView);
		
		
		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.inactiveCaptionBorder);
		panel.setBounds(10, 48, 476, 43);
		receptionPanel.add(panel);
		panel.setLayout(null);
		
		lblDeleteReception = new JButton("삭제");
		lblDeleteReception.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		lblDeleteReception.setBounds(353, 9, 57, 25);
		panel.add(lblDeleteReception);
		lblDeleteReception.setFont(new Font("학교안심 바른돋움 B", Font.PLAIN, 12));
		lblDeleteReception.setHorizontalAlignment(SwingConstants.CENTER);
		lblDeleteReception.setBackground(UIManager.getColor("Button.background"));
		
		lblEditReception = new JButton("수정");
		lblEditReception.setBounds(412, 9, 57, 25);
		panel.add(lblEditReception);
		lblEditReception.setFont(new Font("학교안심 바른돋움 B", Font.PLAIN, 12));
		lblEditReception.setHorizontalAlignment(SwingConstants.CENTER);
		
		lblNewLabel_1 = new JLabel("접수 목록");
		lblNewLabel_1.setBounds(12, 10, 111, 25);
		panel.add(lblNewLabel_1);
		lblNewLabel_1.setFont(new Font("학교안심 바른돋움 B", Font.PLAIN, 25));
		
		
		tableReceptionView.setFont(new Font("학교안심 바른돋움 R", Font.PLAIN, 14));
		tableCustomerView.setFont(new Font("학교안심 바른돋움 R", Font.PLAIN, 14));
		
	}
	

	
	// 접수 테이블 표시
	private void showReceptionDetails() {
		int index = tableCustomerView.getSelectedRow();
		if(index == -1) {
			JOptionPane.showMessageDialog(
					frame,
					"접수할 인원을 먼저 목록에서 선택하세요.",
					"경고",
					JOptionPane.WARNING_MESSAGE);
			return;
		}
			Integer anmalId = (Integer) model.getValueAt(index, 0);
			ReceptionFrame.showReceptionFrame(frame, PetMain.this, anmalId);
		
	}	
		
	
	private void resetReceptionTable(List<ReceptionData> list) {
		model = new DefaultTableModel(null, COULMN_RECEPTION);
		for(ReceptionData rd : list) {
			Object[] rowDate = {
				rd.getReceptionAppointmentTime(),
				rd.getReceptionAppointmentDate(),
				rd.getDoctorName(),
				rd.getReceptionId(),
				rd.getOwnerName(),
				rd.getAnimalName(),
				rd.getAnimalType(),
				rd.getReceptionStatus()
			};
			model.addRow(rowDate);
		}
		tableReceptionView.setModel(model);
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
	
	//TODO: 삭제 기능
	private void deleteAnimal() {
		
		int index = tableCustomerView.getSelectedRow();
		if(index == -1) {
			JOptionPane.showMessageDialog(
					frame,
					"고객목록에서 삭제할 인원을 먼저 선택하세요",
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
	
	//TODO:업데이트 기능
	
	private void showPetDetails() {
		int index = tableCustomerView.getSelectedRow();
		if(index == -1) {
			JOptionPane.showMessageDialog(
					frame,
					"고객목록에서 수정할 인원을 먼저 선택하세요",
					"경고",
					JOptionPane.WARNING_MESSAGE);
			return;
		}
		
		Integer anmalId = (Integer) model.getValueAt(index, 0);
		
		PetDetailsFrame.showPetDetailsFrame(frame, PetMain.this, anmalId);
	}
	

	
	//TODO: 검색 기능
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

	private void initializeTable() {
		List<AnimalOwner> list = petDao.readAnimalOwner();
		resetTabel(list);
		
	}
	private void initializeTables() {
		List<ReceptionData> lists = receptionDao.read(); 
		resetReceptionTable(lists);
	}
	
	public void UIManagerDesignComponent() {
		UIManager.put("Button.background", Color.WHITE);
		UIManager.put("Button.foreground", Color.BLACK);
		UIManager.put("ComboBox.background", Color.WHITE);
		UIManager.put("ComboBox.foreground", Color.BLACK); 
		UIManager.put("ComboBox.selectionBackground", Color.LIGHT_GRAY);
		UIManager.put("ComboBox.selectionForeground", Color.black);
		UIManager.put("ScrollPane.background", Color.WHITE); 
		UIManager.put("ScrollPane.foreground", Color.black);
	}
}

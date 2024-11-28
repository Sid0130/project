package com.itwill.project.view;

import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import com.itwill.project.controller.PetDao;
import com.itwill.project.model.Animal;
import com.itwill.project.model.AnimalOwner;
import com.itwill.project.model.AnimalOwnerUpdate;
import com.itwill.project.model.Owner;

public class PetDetailsFrame extends JFrame {
	
	public interface UpdateNotify {
		void notifyUpdateSuccess();
	}

	private static final String[] SELECT_TYPE = {
		"강아지", "고양이", "기타"
	};
	
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel animalPanel;
	private JPanel titlePanel;
	private JPanel buttonPanel;
	private JLabel lblAnimalLabel;
	private JLabel lblAnimalName;
	private JLabel lblAnimalAge;
	private JLabel lblAnimalWeight;
	private JLabel lblAnimalTpye;
	private JLabel lblAnimalGender;
	private JTextField textFieldAnimalName;
	private JTextField textFieldAnimalAge;
	private JTextField textFieldAnimalWeight;
	private JLabel lblAnimalMemo;
	private JScrollPane scrollAnimalPane;
	private JTextField textFieldOwnerName;
	private JTextField textFieldOwnerContact;
	private JTextField textFieldOwnerAdress;
	private JLabel lblOwnerName;
	private JLabel lblOwnerContact;
	private JLabel lblOwnerLabel;
	private JLabel lblOwnerAderss;
	private JPanel OwnerPanel;
	private JTextArea textOwnerArea;
	private JLabel lblOwnerMemo;
	private JScrollPane scrollOwnerPane;
	private JTextArea textAnimalArea;
	private JComboBox<String> animalTypeBox;
	private JRadioButton radioBtnfmale;
	private JRadioButton radioBtnMale;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private PetDao petDao;
	private JButton btnUpdate;
	private JButton btnCancel;
	private Component parentComponent;
	private JLabel lblNewLabel;
	private JSeparator separator;
	private UpdateNotify app;
	private final Integer id;
	private JTextField textFieldUpdated;
	private JTextField textFieldCreated;
	private JLabel lblCreated;
	private JLabel lblUpdated;
	
	
	

	/**
	 * Launch the application.
	 */
	public static void showPetDetailsFrame(Component parentComponent, UpdateNotify app, Integer id) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PetDetailsFrame frame = new PetDetailsFrame(parentComponent, app, id);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public PetDetailsFrame(Component parentComponent, UpdateNotify app, Integer id) {
		petDao = PetDao.INSTANCE;
		this.parentComponent = parentComponent;
		this.app = app;
		this.id = id;
		
		initialize();
		initializePet();
	}
	
	/**
	 * Create the frame.
	 */
	public void initialize() {
		setTitle("정보 수정");
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 718);
	
		setLocationRelativeTo(parentComponent); // 프레임 위치를 부모 프레임 중간에
		
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		separator = new JSeparator();
		separator.setBounds(0, 48, 434, 9);
		contentPane.add(separator);
		
		buttonPanel = new JPanel();
		buttonPanel.setBounds(0, 636, 434, 33);
		contentPane.add(buttonPanel);
		
		btnUpdate = new JButton("수정");
		btnUpdate.addActionListener(e -> updateAnimals());
		btnUpdate.setFont(new Font("학교안심 바른돋움 R", Font.PLAIN, 12));
		buttonPanel.add(btnUpdate);
		
		btnCancel = new JButton("취소");
		btnCancel.setFont(new Font("학교안심 바른돋움 R", Font.PLAIN, 12));
		btnCancel.addActionListener(e -> dispose());
		buttonPanel.add(btnCancel);
		
		animalPanel = new JPanel();
		animalPanel.setBounds(5, 56, 424, 259);
		contentPane.add(animalPanel);
		animalPanel.setLayout(null);
		
		lblAnimalLabel = new JLabel("동물 정보 수정");
		lblAnimalLabel.setFont(new Font("학교안심 바른돋움 R", Font.PLAIN, 18));
		lblAnimalLabel.setBounds(12, 10, 143, 25);
		animalPanel.add(lblAnimalLabel);
		
		lblAnimalName = new JLabel("이름");
		lblAnimalName.setFont(new Font("학교안심 바른돋움 R", Font.PLAIN, 15));
		lblAnimalName.setBounds(22, 45, 38, 25);
		animalPanel.add(lblAnimalName);
		
		lblAnimalAge = new JLabel("나이");
		lblAnimalAge.setFont(new Font("학교안심 바른돋움 R", Font.PLAIN, 15));
		lblAnimalAge.setBounds(22, 80, 38, 25);
		animalPanel.add(lblAnimalAge);
		
		lblAnimalWeight = new JLabel("체중");
		lblAnimalWeight.setFont(new Font("학교안심 바른돋움 R", Font.PLAIN, 15));
		lblAnimalWeight.setBounds(22, 115, 38, 25);
		animalPanel.add(lblAnimalWeight);
		
		lblAnimalTpye = new JLabel("분류");
		lblAnimalTpye.setFont(new Font("학교안심 바른돋움 R", Font.PLAIN, 15));
		lblAnimalTpye.setBounds(237, 45, 38, 25);
		animalPanel.add(lblAnimalTpye);
		
		lblAnimalGender = new JLabel("성별");
		lblAnimalGender.setFont(new Font("학교안심 바른돋움 R", Font.PLAIN, 15));
		lblAnimalGender.setBounds(237, 80, 38, 25);
		animalPanel.add(lblAnimalGender);
		
		radioBtnMale = new JRadioButton("남자");
		radioBtnMale.setEnabled(false);
		radioBtnMale.setSelected(true);
		radioBtnMale.setFont(new Font("학교안심 바른돋움 R", Font.PLAIN, 12));
		buttonGroup.add(radioBtnMale);
		radioBtnMale.setBounds(285, 80, 49, 23);
		animalPanel.add(radioBtnMale);
		
		radioBtnfmale = new JRadioButton("여자");
		radioBtnfmale.setEnabled(false);
		radioBtnfmale.setFont(new Font("학교안심 바른돋움 R", Font.PLAIN, 12));
		buttonGroup.add(radioBtnfmale);
		radioBtnfmale.setBounds(349, 80, 49, 23);
		animalPanel.add(radioBtnfmale);
		
		animalTypeBox = new JComboBox<>(); // 분류
		animalTypeBox.setEnabled(false);
		animalTypeBox.setFont(new Font("학교안심 바른돋움 R", Font.PLAIN, 12));
		animalTypeBox.setBounds(281, 45, 121, 23);
		DefaultComboBoxModel<String> comboBoxModel = new DefaultComboBoxModel<>(SELECT_TYPE);
		animalTypeBox.setModel(comboBoxModel);
		
		animalPanel.add(animalTypeBox);
		
		textFieldAnimalName = new JTextField();
		textFieldAnimalName.setFont(new Font("학교안심 바른돋움 R", Font.PLAIN, 12));
		textFieldAnimalName.setBounds(62, 45, 128, 21);
		animalPanel.add(textFieldAnimalName);
		textFieldAnimalName.setColumns(10);
		
		textFieldAnimalAge = new JTextField();
		textFieldAnimalAge.setFont(new Font("학교안심 바른돋움 R", Font.PLAIN, 12));
		textFieldAnimalAge.setColumns(10);
		textFieldAnimalAge.setBounds(62, 80, 128, 21);
		animalPanel.add(textFieldAnimalAge);
		
		textFieldAnimalWeight = new JTextField();
		textFieldAnimalWeight.setFont(new Font("학교안심 바른돋움 R", Font.PLAIN, 12));
		textFieldAnimalWeight.setColumns(10);
		textFieldAnimalWeight.setBounds(62, 115, 128, 21);
		animalPanel.add(textFieldAnimalWeight);
		
		lblAnimalMemo = new JLabel("비고");
		lblAnimalMemo.setFont(new Font("학교안심 바른돋움 R", Font.PLAIN, 15));
		lblAnimalMemo.setBounds(22, 150, 38, 25);
		animalPanel.add(lblAnimalMemo);
		
		scrollAnimalPane = new JScrollPane();
		scrollAnimalPane.setBounds(22, 174, 380, 75);
		animalPanel.add(scrollAnimalPane);
		
		textAnimalArea = new JTextArea();
		textAnimalArea.setFont(new Font("학교안심 바른돋움 R", Font.PLAIN, 13));
		scrollAnimalPane.setViewportView(textAnimalArea);
		
		titlePanel = new JPanel();
		titlePanel.setBounds(5, 10, 424, 36);
		contentPane.add(titlePanel);
		
		lblNewLabel = new JLabel("고객 정보 수정");
		lblNewLabel.setFont(new Font("학교안심 바른돋움 R", Font.PLAIN, 25));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titlePanel.add(lblNewLabel);
		
		OwnerPanel = new JPanel();
		OwnerPanel.setLayout(null);
		OwnerPanel.setBounds(5, 325, 424, 259);
		contentPane.add(OwnerPanel);
		
		lblOwnerLabel = new JLabel("보호자 정보 수정");
		lblOwnerLabel.setFont(new Font("학교안심 바른돋움 R", Font.PLAIN, 18));
		lblOwnerLabel.setBounds(12, 10, 152, 25);
		OwnerPanel.add(lblOwnerLabel);
		
		lblOwnerName = new JLabel("보호자 이름");
		lblOwnerName.setFont(new Font("학교안심 바른돋움 R", Font.PLAIN, 15));
		lblOwnerName.setBounds(22, 45, 83, 25);
		OwnerPanel.add(lblOwnerName);
		
		lblOwnerContact = new JLabel("연락처");
		lblOwnerContact.setFont(new Font("학교안심 바른돋움 R", Font.PLAIN, 15));
		lblOwnerContact.setBounds(22, 80, 49, 25);
		OwnerPanel.add(lblOwnerContact);
		
		lblOwnerAderss = new JLabel("주소");
		lblOwnerAderss.setFont(new Font("학교안심 바른돋움 R", Font.PLAIN, 15));
		lblOwnerAderss.setBounds(22, 115, 49, 25);
		OwnerPanel.add(lblOwnerAderss);
		
		textFieldOwnerName = new JTextField();
		textFieldOwnerName.setFont(new Font("학교안심 바른돋움 R", Font.PLAIN, 12));
		textFieldOwnerName.setColumns(10);
		textFieldOwnerName.setBounds(116, 45, 128, 21);
		OwnerPanel.add(textFieldOwnerName);
		
		textFieldOwnerContact = new JTextField();
		textFieldOwnerContact.setFont(new Font("학교안심 바른돋움 R", Font.PLAIN, 12));
		textFieldOwnerContact.setColumns(10);
		textFieldOwnerContact.setBounds(116, 80, 128, 21);
		OwnerPanel.add(textFieldOwnerContact);
		
		textFieldOwnerAdress = new JTextField();
		textFieldOwnerAdress.setFont(new Font("학교안심 바른돋움 R", Font.PLAIN, 12));
		textFieldOwnerAdress.setColumns(10);
		textFieldOwnerAdress.setBounds(116, 117, 286, 21);
		OwnerPanel.add(textFieldOwnerAdress);
		
		lblOwnerMemo = new JLabel("비고");
		lblOwnerMemo.setFont(new Font("학교안심 바른돋움 R", Font.PLAIN, 15));
		lblOwnerMemo.setBounds(22, 150, 49, 25);
		OwnerPanel.add(lblOwnerMemo);
		
		scrollOwnerPane = new JScrollPane();
		scrollOwnerPane.setBounds(22, 174, 380, 75);
		OwnerPanel.add(scrollOwnerPane);
		
		textOwnerArea = new JTextArea();
		textOwnerArea.setFont(new Font("학교안심 바른돋움 R", Font.PLAIN, 13));
		scrollOwnerPane.setViewportView(textOwnerArea);
		
		textFieldUpdated = new JTextField();
		textFieldUpdated.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldUpdated.setFont(new Font("학교안심 바른돋움 R", Font.PLAIN, 12));
		textFieldUpdated.setEditable(false);
		textFieldUpdated.setColumns(10);
		textFieldUpdated.setBounds(230, 611, 155, 21);
		contentPane.add(textFieldUpdated);
		
		textFieldCreated = new JTextField();
		textFieldCreated.setFont(new Font("학교안심 바른돋움 R", Font.PLAIN, 12));
		textFieldCreated.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldCreated.setEditable(false);
		textFieldCreated.setColumns(10);
		textFieldCreated.setBounds(46, 611, 155, 21);
		contentPane.add(textFieldCreated);
		
		lblCreated = new JLabel("등록일");
		lblCreated.setFont(new Font("학교안심 바른돋움 R", Font.PLAIN, 15));
		lblCreated.setBounds(99, 584, 49, 25);
		contentPane.add(lblCreated);
		
		lblUpdated = new JLabel("수정일");
		lblUpdated.setFont(new Font("학교안심 바른돋움 R", Font.PLAIN, 15));
		lblUpdated.setBounds(279, 584, 49, 25);
		contentPane.add(lblUpdated);
	}

	private void updateAnimals() {
		String animalName = textFieldAnimalName.getText();
		String animalAgeStr = textFieldAnimalAge.getText();
		String animalWeightStr = textFieldAnimalWeight.getText();
		String animaltype = (String) animalTypeBox.getSelectedItem();
		String animalMemo = textAnimalArea.getText();
		String animalGender = null;
		if(radioBtnMale.isSelected()) {
			animalGender = "Male";
		} else if (radioBtnfmale.isSelected()) {
			animalGender = "Female";
		}
		
		String ownerName = textFieldOwnerName.getText();
		String ownerContact = textFieldOwnerContact.getText();
		String ownerAddress = textFieldOwnerAdress.getText();
		String ownerMemo = textOwnerArea.getText();
		
		if(animalName.equals("") || animalAgeStr.equals("") || animalWeightStr.equals("") || animaltype.equals("") || animalGender.equals("")
				|| ownerName.equals("") || ownerContact.equals("") || ownerAddress.equals("")) {
			JOptionPane.showMessageDialog(
					PetDetailsFrame.this,
					"필수 정보를 입력하지 않았습니다.",
					"경고",
					JOptionPane.WARNING_MESSAGE);
			return;						
		
		}
		Integer animalAge;
		Double animalWeight;
		try {	
			animalAge = Integer.parseInt(animalAgeStr);
			animalWeight = Double.parseDouble(animalWeightStr);
		} catch(Exception e) {	
			JOptionPane.showMessageDialog(
					this, "나이와 체중은 숫자만 입력 가능합니다.", "입력 오류", JOptionPane.ERROR_MESSAGE);
			return;
		} 
		
		
		Animal UpdateAnimal = Animal.builder().animalId(id).animalName(animalName).age(animalAge)
				.animalType(animaltype).weight(animalWeight).memo(animalMemo)
				.gender(animalGender).build();
		
		Owner UpdateOwner = Owner.builder().ownerId(id).ownerName(ownerName).phoneNumber(ownerContact)
				.address(ownerAddress).ownerMemo(ownerMemo).build();
		
		boolean ua = petDao.animalUpdate(UpdateAnimal);
		boolean uo = petDao.ownerUpdate(UpdateOwner);
		
		if(ua && uo) {
			JOptionPane.showMessageDialog(
					PetDetailsFrame.this,
					"수정 성공", 
					"성공",
					JOptionPane.INFORMATION_MESSAGE);
			app.notifyUpdateSuccess();
			dispose();
		} else {
			JOptionPane.showMessageDialog(
					PetDetailsFrame.this,
					"수정 실패", 
					"실패",
					JOptionPane.ERROR_MESSAGE);
		}
		
	}
	
	private void initializePet() {
		AnimalOwnerUpdate aou = petDao.read(id);
		
		 if (aou == null) {
		        JOptionPane.showMessageDialog(PetDetailsFrame.this, "해당 동물 정보가 존재하지 않습니다.", "오류", JOptionPane.ERROR_MESSAGE);
		        dispose(); // 프레임을 닫음
		        return;
		    }
		textFieldAnimalName.setText(aou.getAnimalName().toString());
		textFieldAnimalAge.setText(String.valueOf(aou.getAnimalAge()).toString());
		textFieldAnimalWeight.setText(String.valueOf(aou.getAnimalWeight()).toString());
		animalTypeBox.setSelectedItem(aou.getAnimalType().toString());
		textFieldOwnerAdress.setText(aou.getOwnerAdress());
		textFieldOwnerContact.setText(aou.getOwnerPhone());
		textFieldOwnerName.setText(aou.getOwnerName());
		
		if ("Male".equals(aou.getAnimalGender())) {
	        radioBtnMale.setSelected(true); // maleRadioButton은 male 라디오 버튼의 변수명
	    } else if ("Female".equals(aou.getAnimalGender())) {
	        radioBtnfmale.setSelected(true); // femaleRadioButton은 female 라디오 버튼의 변수명
	    }
		
		textAnimalArea.setText(aou.getAnimalMemo());
		textOwnerArea.setText(aou.getOwnerMemo());
		
		textFieldCreated.setText(aou.getAnimalCreatedTime().format(DateTimeFormatter.ofPattern("YYYY-MM-DD HH:mm")));
		textFieldUpdated.setText(aou.getAnimalModifiedTime().format(DateTimeFormatter.ofPattern("YYYY-MM-DD HH:mm")));
	}
}	

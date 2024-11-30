package com.itwill.project.view;

import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.SystemColor;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import com.itwill.project.controller.DoctorDao;
import com.itwill.project.controller.PetDao;
import com.itwill.project.controller.ReceptionDao;
import com.itwill.project.model.AnimalOwner;
import com.itwill.project.model.Doctor;
import com.itwill.project.model.ReceptionData;
import com.itwill.project.view.PetCreateFrame.CreateNotify;

public class ReceptionFrame extends JFrame {
	
	private static final String[] AVAILABLE_TIMES = {
			"09:00", "09:30", "10:00", "10:30", 
		    "11:00", "11:30", "12:00", "12:30", 
		    "13:00", "13:30", "14:00", "14:30", 
		    "15:00", "15:30", "16:00", "16:30", 
		    "17:00", "17:30", "18:00"	
	};
	
	
	private static final String[] APPOINTMENT = {
		"예약", "진료중", "대기중"	
	};
	
	private static final String[] SELECT_TYPE = {
			"강아지", "고양이", "기타"
		};
		
	private static final String[] SPECIALTY = {
			"수의내과", "수의외과"
	};

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textOwderField;
	private JTextField textWeightField;
	private JTextField textAgeField;
	private JTextField textGenderField;
	private JTextField textAnimalNameField;
	private JSeparator separator_1;
	private JSeparator separator;
	private JPanel buttonPanel;
	private JButton btnSave;
	private JButton btnCancle;
	private JPanel titlePanel;
	private JLabel titleLabel;
	private JLabel dateLabel;
	private JLabel nameLabel;
	private JLabel typeLabel;
	private JLabel genderLabel;
	private JLabel ageLabel;
	private JLabel weightLabel;
	private JLabel owderLabel;
	private JLabel doctorLabel;
	private JLabel statusLabel;
	private JComboBox<String> textDoctorBox;
	private JComboBox<String> statusComboBox;
	private JComboBox<String> typeComboBox;
	private DefaultComboBoxModel<String> model;
	
	private Component parentComponent;
	private ReceptionDao receptionDao;
	private PetDao petDao;
	private CreateNotify app;
	private DoctorDao doctorDao;
	private JComboBox<String> textTimeField;
	private JTextField textDateField;
	private final Integer animalId;
	private JButton btnNewButton;
	private JLabel TimeLabel;
	
	
	
	
	public static void showReceptionFrame(Component parentComponent, CreateNotify app, Integer animalId) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ReceptionFrame frame = new ReceptionFrame(parentComponent, app, animalId);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public ReceptionFrame(Component parentComponent, CreateNotify app, Integer animalId) {
		petDao = PetDao.INSTANCE;
		doctorDao = DoctorDao.INSTANCE;
		receptionDao = ReceptionDao.INSTANCE;
		this.parentComponent = parentComponent;
		this.app = app;
		this.animalId = animalId;
		initialize();
		loadAoData();
		loadDoctorComboBox();
	}
	
	/**
	 * Create the frame.
	 */
	public void initialize() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 400, 551);
		setLocationRelativeTo(parentComponent);
		
		
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.inactiveCaptionBorder);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		separator_1 = new JSeparator();
		separator_1.setBounds(2, 453, 382, 5);
		contentPane.add(separator_1);
		
		separator = new JSeparator();
		separator.setBounds(0, 45, 384, 5);
		contentPane.add(separator);
		
		buttonPanel = new JPanel();
		buttonPanel.setBackground(SystemColor.inactiveCaptionBorder);
		buttonPanel.setBounds(0, 461, 384, 51);
		contentPane.add(buttonPanel);
		
		btnCancle = new JButton("취소");
		btnCancle.addActionListener(e -> dispose());
		btnCancle.setFont(new Font("학교안심 바른돋움 B", Font.PLAIN, 17));
		
		//TODO: 여기를 데이터베이스에 넘기기.
		btnSave = new JButton("저장");
		btnSave.addActionListener(e -> createReception());
		buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		btnSave.setFont(new Font("학교안심 바른돋움 B", Font.PLAIN, 17));
		buttonPanel.add(btnSave);
		buttonPanel.add(btnCancle);
		
		titlePanel = new JPanel();
		titlePanel.setBackground(SystemColor.inactiveCaptionBorder);
		titlePanel.setBounds(0, 10, 384, 36);
		contentPane.add(titlePanel);
		
		titleLabel = new JLabel("접수 정보");
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel.setFont(new Font("학교안심 바른돋움 B", Font.PLAIN, 23));
		titlePanel.add(titleLabel);
		
		dateLabel = new JLabel("예약날짜");
		dateLabel.setFont(new Font("학교안심 바른돋움 R", Font.PLAIN, 16));
		dateLabel.setBounds(46, 58, 60, 24);
		contentPane.add(dateLabel);
		
		nameLabel = new JLabel("이름");
		nameLabel.setFont(new Font("학교안심 바른돋움 R", Font.PLAIN, 16));
		nameLabel.setBounds(46, 136, 60, 24);
		contentPane.add(nameLabel);
		
		typeLabel = new JLabel("분류");
		typeLabel.setFont(new Font("학교안심 바른돋움 R", Font.PLAIN, 16));
		typeLabel.setBounds(46, 175, 60, 24);
		contentPane.add(typeLabel);
		
		genderLabel = new JLabel("성별");
		genderLabel.setFont(new Font("학교안심 바른돋움 R", Font.PLAIN, 16));
		genderLabel.setBounds(46, 213, 60, 24);
		contentPane.add(genderLabel);
		
		ageLabel = new JLabel("나이");
		ageLabel.setFont(new Font("학교안심 바른돋움 R", Font.PLAIN, 16));
		ageLabel.setBounds(46, 254, 60, 24);
		contentPane.add(ageLabel);
		
		weightLabel = new JLabel("체중");
		weightLabel.setFont(new Font("학교안심 바른돋움 R", Font.PLAIN, 16));
		weightLabel.setBounds(46, 293, 60, 24);
		contentPane.add(weightLabel);
		
		owderLabel = new JLabel("보호자");
		owderLabel.setFont(new Font("학교안심 바른돋움 R", Font.PLAIN, 16));
		owderLabel.setBounds(46, 330, 60, 24);
		contentPane.add(owderLabel);
		
		doctorLabel = new JLabel("주치의");
		doctorLabel.setFont(new Font("학교안심 바른돋움 R", Font.PLAIN, 16));
		doctorLabel.setBounds(46, 375, 60, 24);
		contentPane.add(doctorLabel);
		
		statusLabel = new JLabel("상태");
		statusLabel.setFont(new Font("학교안심 바른돋움 R", Font.PLAIN, 16));
		statusLabel.setBounds(46, 409, 60, 24);
		contentPane.add(statusLabel);
		
		textDoctorBox = new JComboBox<String>();
		textDoctorBox.setFont(new Font("학교안심 바른돋움 R", Font.PLAIN, 15));
		textDoctorBox.setEditable(true);
		textDoctorBox.setBounds(116, 372, 160, 25);
		contentPane.add(textDoctorBox);
		
		statusComboBox = new JComboBox<String>();
		statusComboBox.setFont(new Font("학교안심 바른돋움 R", Font.PLAIN, 15));
		statusComboBox.setEditable(true);
		model = new DefaultComboBoxModel<>(APPOINTMENT);
		statusComboBox.setModel(model);
		 
		statusComboBox.setBounds(116, 407, 160, 25);
		contentPane.add(statusComboBox);
		
		textOwderField = new JTextField();
		textOwderField.setFont(new Font("학교안심 바른돋움 R", Font.PLAIN, 15));
		textOwderField.setEditable(false);
		textOwderField.setBounds(116, 329, 160, 25);
		contentPane.add(textOwderField);
		textOwderField.setColumns(10);
		
		textWeightField = new JTextField();
		textWeightField.setFont(new Font("학교안심 바른돋움 R", Font.PLAIN, 15));
		textWeightField.setEditable(false);
		textWeightField.setColumns(10);
		textWeightField.setBounds(116, 291, 160, 25);
		contentPane.add(textWeightField);
		
		textAgeField = new JTextField();
		textAgeField.setFont(new Font("학교안심 바른돋움 R", Font.PLAIN, 15));
		textAgeField.setEditable(false);
		textAgeField.setColumns(10);
		textAgeField.setBounds(116, 252, 160, 25);
		contentPane.add(textAgeField);
		
		textGenderField = new JTextField();
		textGenderField.setFont(new Font("학교안심 바른돋움 R", Font.PLAIN, 15));
		textGenderField.setEditable(false);
		textGenderField.setColumns(10);
		textGenderField.setBounds(116, 212, 160, 25);
		contentPane.add(textGenderField);
		
		textAnimalNameField = new JTextField();
		textAnimalNameField.setFont(new Font("학교안심 바른돋움 R", Font.PLAIN, 15));
		textAnimalNameField.setEditable(false);
		textAnimalNameField.setColumns(10);
		textAnimalNameField.setBounds(116, 135, 160, 25);
		contentPane.add(textAnimalNameField);
		
		textDateField = new JTextField();
		textDateField.setEditable(false);
		textDateField.setFont(new Font("학교안심 바른돋움 R", Font.PLAIN, 15));
		textDateField.setColumns(10);
		textDateField.setBounds(116, 57, 160, 25);
		contentPane.add(textDateField);
		
		typeComboBox = new JComboBox<>();
		typeComboBox.setFont(new Font("학교안심 바른돋움 R", Font.PLAIN, 15));
		typeComboBox.setEnabled(false);
		typeComboBox.setEditable(true);
		model = new DefaultComboBoxModel<>(SELECT_TYPE);
		typeComboBox.setModel(model);
		typeComboBox.setBounds(116, 173, 160, 25);
		contentPane.add(typeComboBox);
		
		btnNewButton = new JButton("달력");
		btnNewButton.setFont(new Font("학교안심 바른돋움 B", Font.PLAIN, 14));
		btnNewButton.addActionListener(e -> showCalendar());
		btnNewButton.setBounds(291, 56, 81, 27);
		contentPane.add(btnNewButton);
		
		textTimeField = new JComboBox<String>();
		textTimeField.setEditable(true);
		textTimeField.setFont(new Font("학교안심 바른돋움 R", Font.PLAIN, 15));
		textTimeField.setBounds(116, 95, 160, 25);
		model = new DefaultComboBoxModel<String>(AVAILABLE_TIMES);
		textTimeField.setModel(model);
		contentPane.add(textTimeField);
		
		TimeLabel = new JLabel("예약시간");
		TimeLabel.setFont(new Font("학교안심 바른돋움 R", Font.PLAIN, 16));
		TimeLabel.setBounds(46, 97, 60, 24);
		contentPane.add(TimeLabel);
		
		UIManager.put("Button.background", Color.WHITE);
		UIManager.put("Button.foreground", Color.BLACK);
		UIManager.put("ComboBox.background", Color.WHITE);
		UIManager.put("ComboBox.foreground", Color.BLACK); 
		UIManager.put("ComboBox.selectionBackground", Color.LIGHT_GRAY);
		UIManager.put("ComboBox.selectionForeground", Color.black);

	}

	
	private void createReception() {
	    String appointmentDate = textDateField.getText(); // 날짜
	    String appointmentTime = (String) textTimeField.getSelectedItem(); // 시간
	    String receptionStatus = (String) statusComboBox.getSelectedItem();
	    String doctorName = (String) textDoctorBox.getSelectedItem();
	    String animalName = textAnimalNameField.getText();
	    String animalType = (String) typeComboBox.getSelectedItem();
	    String ownerName = textOwderField.getText();
	    
	    if (appointmentDate.equals("")) {
	        JOptionPane.showMessageDialog(ReceptionFrame.this, 
	                "예약 날짜는 반드시 입력해야 합니다.", 
	                "경고", 
	                JOptionPane.WARNING_MESSAGE);
	        return;        
	    }
	    
	    if (doctorName == null || doctorName.equals("")) {
	    	JOptionPane.showMessageDialog(ReceptionFrame.this, 
	                "의사 이름이 선택되지 않았습니다.", 
	                "경고", 
	                JOptionPane.WARNING_MESSAGE);
	        return;        
	    	
	    }
	        
	    
	    
	    
	    // 입력값 검증
	    DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	    LocalDate parsedDate = LocalDate.parse(appointmentDate, dateFormatter);  // String을 LocalDate로 변환

	    // 시간 포맷 처리
	    DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
	    LocalTime parsedTime = LocalTime.parse(appointmentTime, timeFormatter);  // String을 LocalTime으로 변환

	    // 포맷팅된 날짜와 시간
	    String formattedDate = parsedDate.format(dateFormatter);  // yyyy-MM-dd 형식으로 변환
	    String formattedTime = parsedTime.format(timeFormatter);  // HH:mm 형식으로 변환

	    
	    
	    
	    	
	    // ID 조회 (이름으로 조회)
	    int animalId = receptionDao.getAnimalIdByName(animalName); // 동물 아이디
	    int ownerId = receptionDao.getOwnerIdByName(ownerName); // 보호자 아이디
	    int doctorId = receptionDao.getDoctorIdByName(doctorName); // 의사 아이디

	    if (doctorId == -1) {
	        JOptionPane.showMessageDialog(
	            ReceptionFrame.this,
	            "등록되지 않은 의사입니다. 의사를 먼저 등록해주세요.",
	            "경고",
	            JOptionPane.WARNING_MESSAGE
	        );
	        return;  // 접수를 진행하지 않고 종료
	    }
	    
	    // ID 유효성 확인
	    if (animalId == -1 || ownerId == -1 || doctorId == -1) {
	        JOptionPane.showMessageDialog(
	                ReceptionFrame.this,
	                "동물, 보호자 또는 의사의 정보가 올바르지 않습니다. 다시 확인해주세요.",
	                "경고",
	                JOptionPane.WARNING_MESSAGE);
	        return;
	    }
	    
	    
	    // ReceptionData 생성
	    ReceptionData reception = ReceptionData.builder()
	            .doctorId(doctorId)
	            .ownerId(ownerId)
	            .animalId(animalId)
	            .receptionAppointmentDate(formattedDate)
	            .receptionAppointmentTime(formattedTime)
	            .receptionStatus(receptionStatus)
	            .animalType(animalType)	            
	            .build();

	    // 데이터베이스 저장
	    int createReceptionTable = receptionDao.createReception(reception);

	    // 결과 처리
	    if (createReceptionTable != 1) {
	        JOptionPane.showMessageDialog(ReceptionFrame.this, "접수 등록 실패");
	        return;
	    } else {
	        JOptionPane.showMessageDialog(ReceptionFrame.this, "접수 등록 성공");
	        app.notifyCreateSuccess(); // 성공 알림
	        dispose(); // 창 닫기
	    }
	}
	
	
	
	
	//예약날짜 텍스트필드에 입력
	public void setDateToTextField(String date) {
		textDateField.setText(date);
	}
	private void showCalendar() {
		
		ReceptionCalendar.showReceptionCalendar(contentPane, ReceptionFrame.this);
	}
	
// 동물과 보호자 인덱스 선택후 가져오기 
	private void loadAoData() {
		AnimalOwner ao = petDao.readAo(animalId); 
		if(ao == null) {
			JOptionPane.showMessageDialog(
					ReceptionFrame.this,
					"해당 동물 정보가 존재하지 않습니다.",
					"오류",
					JOptionPane.ERROR_MESSAGE);
	        dispose(); // 프레임을 닫음
	        return;
		}
				
		textAnimalNameField.setText(ao.getAnimalName());
		typeComboBox.setSelectedItem(ao.getAnimalType());
		textOwderField.setText(ao.getOwnerName());
		textGenderField.setText(ao.getAnimalGender());
		textWeightField.setText(String.valueOf(ao.getAnimalWeight()).toString());
		textAgeField.setText(String.valueOf(ao.getAnimalAge()).toString());
		
	}
	
	// 의사 목록 들고오기
	private void loadDoctorComboBox() {
		List<Doctor> doctorList = doctorDao.readDoctorInfo();
		
		if(doctorList == null) {
			JOptionPane.showMessageDialog(
					ReceptionFrame.this,
					"해당 정보가 존재하지 않습니다.",
					"오류",
					JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		DefaultComboBoxModel<String> model1 = new DefaultComboBoxModel<>();
		
		for(Doctor doctor : doctorList) {
			String doctorInfo = doctor.getName();
			model1.addElement(doctorInfo);			
		}
		
		textDoctorBox.setModel(model1);
	}
}

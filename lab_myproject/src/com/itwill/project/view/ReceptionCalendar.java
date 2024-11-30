package com.itwill.project.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.SystemColor;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import com.toedter.calendar.JCalendar;



public class ReceptionCalendar extends JFrame {

	
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JCalendar calendar;
	private JButton btnSelectButton;
	private JButton btnCencleButton;
	private JPanel buttonPanel;
	private JCalendar calendars;
	private Component parentComponent;
	private ReceptionFrame receptionFrame;
	/**
	 * Launch the application.
	 */
	
	
	
	public static void showReceptionCalendar(Component parentComponent, ReceptionFrame receptionFrame) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ReceptionCalendar frame = new ReceptionCalendar(parentComponent, receptionFrame);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	

	
	public ReceptionCalendar(Component parentComponent, ReceptionFrame rf) {
		this.parentComponent = parentComponent;
		this.receptionFrame = rf;
		initialize();
	};
	/**
	 * Create the frame.
	 */
	public void initialize() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 300, 350);
		setLocationRelativeTo(parentComponent);
		setIconImage(null);
		contentPane = new JPanel(new BorderLayout());
		contentPane.setBackground(SystemColor.window);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		UIManager.put("Button.background", Color.WHITE);
		UIManager.put("Button.foreground", Color.BLACK);
		UIManager.put("ComboBox.background", Color.WHITE);
		UIManager.put("ComboBox.foreground", Color.BLACK); 
		UIManager.put("ComboBox.selectionBackground", Color.LIGHT_GRAY);
		UIManager.put("ComboBox.selectionForeground", Color.black);

		calendars = new JCalendar();
		calendars.getDayChooser().getDayPanel().setBackground(SystemColor.window);
		calendars.getDayChooser().setBackground(SystemColor.window);
		calendars.setBorder(new EmptyBorder(5, 5, 5, 5));
		calendars.setBackground(Color.white);
		contentPane.add(calendars, BorderLayout.CENTER);
		
		
		
		buttonPanel = new JPanel();
		buttonPanel.setBackground(SystemColor.window);
		contentPane.add(buttonPanel, BorderLayout.SOUTH);
		
		btnSelectButton = new JButton("선택");
		btnSelectButton.addActionListener(e -> dateSelect());
		buttonPanel.add(btnSelectButton);
		
		btnCencleButton = new JButton("취소");
		btnCencleButton.addActionListener(e -> dispose());
		buttonPanel.add(btnCencleButton);
		
		
	}



	
	private void dateSelect() {
		Date selectedDate = calendars.getDate();
		
		Date currnetDate = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		
		String fsd = sdf.format(selectedDate);
		String fcd = sdf.format(currnetDate);
		
		try {
			Date csd = sdf.parse(fsd);
			Date ccd = sdf.parse(fcd);
			
			if(selectedDate.before(ccd)){
				JOptionPane.showMessageDialog(this, 
						"예약일은 오늘 이후로 선택해야 합니다.",
						"경고",
						JOptionPane.WARNING_MESSAGE				
						);
				return;			
			} 
			receptionFrame.setDateToTextField(fsd);
			
			dispose();		
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		
	}
}

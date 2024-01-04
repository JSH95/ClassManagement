package Save;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import Detail.Repo.DetailRepo;
import List.ListFrame;
import Save.Repo.SaveRepo;
import sun.net.www.content.image.jpeg;

public class SaveFrame  extends JFrame{
	
	
	JButton b1 = new JButton("일람으로");
	JButton b2 = new JButton("초기화");
	JButton b3 = new JButton("등록");
	JTextField t1 = new JTextField(10);
	JTextField t2 = new JTextField(10);
	JTextField t3 = new JTextField(10);
	JTextArea t4 = new JTextArea(50, 50);
	JTextField t5 = new JTextField(10);
	JTextField t6 =  new JTextField(10);
	
	public SaveFrame(String id) {
		setTitle("수업관리 시스템 - 등록화면");
		setSize(700, 600);
		setLayout(new BorderLayout(10, 10));

		setNorthPanel();
		setCenterPanel();
		setEastPanel(id);
		setSouthPanel();
			
		setLocationRelativeTo(null);
		setVisible(true);
		
}
	private void setNorthPanel() {
		JPanel p = new JPanel();
		JLabel l1 = new JLabel("날짜");
		JLabel l2 = new JLabel("(년-월-일)");
		JLabel l3 = new JLabel("일정제목");
		
		p.add(l1);
		p.add(t1);
		p.add(l2);
		p.add(l3);
		p.add(t3);
		add(p, "North");
		
	}
	
	private void setCenterPanel() {
		JPanel p = new JPanel();
		JLabel l4 = new JLabel("일정내용");
		p.add(l4);
		p.add(t4);
		add(p, "Center");

	}
	
	private void setSouthPanel() {
		JPanel p = new JPanel(new GridLayout(0,6));
		JLabel l2 = new JLabel("출석여부");
		JLabel l5 = new JLabel("수업시간(H)");
		JLabel l6 = new JLabel("자습시간(H)");
		
		p.add(l2);
		p.add(t2);
		p.add(l5);
		p.add(t5);
		p.add(l6);
		p.add(t6);
		add(p, "South");

	}
	
	private void setEastPanel(String id) {
		JPanel p = new JPanel(new GridLayout(10, 0));
		
		p.add(b1);
		p.add(b2);
		p.add(b3);
		add(p, "East");
		
		b1.addActionListener(e -> {
			int result = JOptionPane.showConfirmDialog(null,"정말로 돌아가시겠습니까?", "확인", JOptionPane.YES_NO_OPTION);
			if(result == 0) {
			setVisible(false);
			new ListFrame(id);
			}
		});
		
		b2.addActionListener(e ->{
			t1.setText("");
			t2.setText("");
			t3.setText("");
			t4.setText("");
			t5.setText("");
			t6.setText("");
		});
		
		b3.addActionListener(e -> {
			
				
			int result1 = JOptionPane.showConfirmDialog(null,"등록 하시겠습니까?", "확인", JOptionPane.YES_NO_OPTION);
				if(result1 == 0) {
					
					SaveRepo repo = new SaveRepo();
					String study_day = t1.getText();
					String status = t2.getText();
					String title = t3.getText();
					String detail = t4.getText();
					String class_time = t5.getText();
					String self_time = t6.getText();
					boolean result = repo.save(study_day, status, title, detail, class_time, self_time, id);
					JOptionPane.showMessageDialog(null,"정상 등록 되었습니다.");
					setVisible(false);
					new ListFrame(id);
				} else {
					JOptionPane.showMessageDialog(null,"취소되었습니다.");
				}
			
		});		
	
	}
	
	
	
	private void SaveRepo() {
		
	}
	
}

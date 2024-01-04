
package Modify;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.sql.PreparedStatement;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import Code.CategoryUtil;
import Detail.DetailFrame;
import Detail.Repo.DetailRepo;
import List.ListFrame;
import Modify.Repo.ModifyRepo;
import sun.net.www.content.image.jpeg;
import Save.Repo.SaveRepo;

public class ModifyFrame  extends JFrame{
	
	JButton b1 = new JButton("수정");
	JButton b2 = new JButton("취소");
	DetailRepo repo1 = new DetailRepo();
	String[] detailInfo;
	JTextField t1;
	JTextField t2;
	JTextField t3;
	JTextArea t4;
	JTextField t5;
	JTextField t6;
	
	public ModifyFrame(String day, String id) {
		setTitle("수업관리 시스템 - 수정화면");
		setSize(700, 600);
	
		setLayout(new BorderLayout(10, 10));
		
		setNorthPanel(day, id);
		setCenterPanel(day, id);
		setEastPanel(day, id);
		setSouthPanel(day, id);
		
		setLocationRelativeTo(null);
		setVisible(true);
		
}
	
	private void setNorthPanel(String day, String id) {
		String[] detailInfo = repo1.detail(day, id);

		JPanel p = new JPanel();
		JLabel l1 = new JLabel("날짜");
		JLabel l2 = new JLabel("(년-월-일)");
		JLabel l3 = new JLabel("일정제목");
		t1 = new JTextField(detailInfo[0]);
		t3 = new JTextField(detailInfo[2]);

		p.add(l1);
		p.add(t1);
		p.add(l2);
		p.add(l3);
		p.add(t3);
		add(p, "North");
		t1.setEnabled(false);

	}
	
	private void setCenterPanel(String day, String id) {
		String[] detailInfo = repo1.detail(day, id);
		
		JPanel p = new JPanel();
		JLabel l4 = new JLabel("일정내용");
		t4 = new JTextArea(detailInfo[3]);
		p.setLayout(new BorderLayout());
		p.setSize(60, 60);
		p.add(l4, BorderLayout.NORTH);
		p.add(new JScrollPane(t4), BorderLayout.CENTER);
		
		add(p, "Center");
	}
	
	private void setSouthPanel(String day, String id) {
		String[] detailInfo = repo1.detail(day, id);
		JPanel p = new JPanel(new GridLayout(0,6));
		JLabel l2 = new JLabel("출석여부");
		JLabel l5 = new JLabel("수업시간(H)");
		JLabel l6 = new JLabel("자습시간(H)");
		t2 = new JTextField(detailInfo[1]);
		t5 = new JTextField(detailInfo[4]);
		t6 = new JTextField(detailInfo[5]);

		p.add(l2);
		p.add(t2);
		p.add(l5);
		p.add(t5);
		p.add(l6);
		p.add(t6);
		add(p, "South");
	}
	
	private void setEastPanel(String day, String id) {
		
		String[] detailInfo = repo1.detail(day, id);
		JPanel p = new JPanel(new GridLayout(10, 0));

		p.add(b1);
		p.add(b2);
		add(p, "East");
	
		b1.addActionListener(e -> {
			int result1 = JOptionPane.showConfirmDialog(null,"수정 하시겠습니까?", "확인", JOptionPane.YES_NO_OPTION);
			if(result1 == 0) {
			
				ModifyRepo repo = new ModifyRepo();
				String study_day = t1.getText();
				String status = t2.getText();
				String title = t3.getText();
				String detail = t4.getText();
				String class_time = t5.getText();
				String self_time = t6.getText();
							
				boolean result = repo.modify(study_day, status, title, detail, class_time, self_time, id);
				JOptionPane.showMessageDialog(null,"정상 수정 되었습니다.");
				setVisible(false);
				new ListFrame(id);
			} else {
				JOptionPane.showMessageDialog(null,"취소되었습니다.");
			}
		});
	//옵션 후 종료
		b2.addActionListener(e -> {
			int result = JOptionPane.showConfirmDialog(null,"정말 수정을 취소하시겠습니까?", "확인", JOptionPane.YES_NO_OPTION);  
			if(result == 0) {
				setVisible(false);
				new ListFrame(id);
			}
		});	
			
	}
		
}

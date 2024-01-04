package Detail;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.JTextComponent;

import com.mysql.cj.x.protobuf.MysqlxCrud.Delete;

import Code.CategoryUtil;
import Delete.Repo.DeleteRepo;
import Detail.Repo.DetailRepo;
import List.ListFrame;
import List.Repo.ListRepo;
import Modify.ModifyFrame;
import Modify.Repo.ModifyRepo;
import sun.net.www.content.image.jpeg;
import Save.Repo.SaveRepo;

public class DetailFrame extends JFrame{
	
	JButton b1 = new JButton("목록으로");
	JButton b2 = new JButton("수정");
	JButton b3 = new JButton("삭제");	
//	
	public DetailFrame(String day, String id) {
			setTitle("수업관리 시스템 - 상세화면");
			setSize(700, 600);
			setLayout(new BorderLayout(10, 10));
					
			
			setNorthPanel(day, id);
			setCenterPanel(day, id);
			setEastPanel(day, id);
			setSouthPanel(day, id);
			
			DetailRepo repo1 = new DetailRepo();
			String[] detailInfo = repo1.detail(day, id);
			
			setLocationRelativeTo(null);
			setVisible(true);
	}

	private void setNorthPanel(String day, String id) {
		
		DetailRepo repo1 = new DetailRepo();
		String[] detailInfo = repo1.detail(day, id);
		
		JPanel p = new JPanel();
		JLabel l1 = new JLabel("날짜");
		JLabel l2 = new JLabel("(년-월-일)");
		JLabel l3 = new JLabel("일정제목");
		JTextField t1 = new JTextField(detailInfo[0]);
		JTextField t3 = new JTextField(detailInfo[2]);
		
		
		p.add(l1);
		p.add(t1);
		p.add(l2);
		p.add(l3);
		p.add(t3);
		add(p, "North");
		
		t1.setEnabled(false);
		t3.setEnabled(false);
	}
	
	private void setCenterPanel(String day, String id) {
		
		DetailRepo repo1 = new DetailRepo();
		String[] detailInfo = repo1.detail(day, id);
		
		JPanel p = new JPanel();
		JLabel l4 = new JLabel("일정내용");
		JTextArea t4 = new JTextArea(detailInfo[3]);
		
		p.setLayout(new BorderLayout());
		p.setSize(60, 60);
		p.add(l4, BorderLayout.NORTH);
		p.add(new JScrollPane(t4), BorderLayout.CENTER);
		
		add(p, "Center");
		t4.setEnabled(false);

	}

	private void setEastPanel(String day, String id) {
		
		DetailRepo repo1 = new DetailRepo();
		String[] detailInfo = repo1.detail(day, id);
		
		JPanel p = new JPanel(new GridLayout(10, 0));
		
		p.add(b1);
		p.add(b2);
		p.add(b3);
		add(p, "East");
		
		b1.addActionListener(e -> {
			setVisible(false);
			new ListFrame(id);
		});
		
		b2.addActionListener(e -> {
			
			setVisible(false);
			new ModifyFrame(day, id);
		});
		
		b3.addActionListener(e -> {
			int result1 = JOptionPane.showConfirmDialog(null,"정말로 삭제하시겠습니까?", "확인", JOptionPane.YES_NO_OPTION);
			if(result1 == 0) {
			DeleteRepo repo = new DeleteRepo(); 
			boolean result = repo.delete(day, id);
				
				JOptionPane.showMessageDialog(null,"정상삭제 되었습니다.");
				setVisible(false);
				new ListFrame(id);
			} else {
				JOptionPane.showMessageDialog(null,"취소되었습니다.");
			}
		});
	}
	
	private void setSouthPanel(String day, String id) {
		
		DetailRepo repo1 = new DetailRepo();
		String[] detailInfo = repo1.detail(day, id);
		
		JPanel p = new JPanel(new GridLayout(0,6));
		JLabel l2 = new JLabel("출석여부");
		JLabel l5 = new JLabel("수업시간(H)");
		JLabel l6 = new JLabel("자습시간(H)");
		JTextField t5 = new JTextField(detailInfo[4]);
		JTextField t6 = new JTextField(detailInfo[5]);
		
		JLabel t2 = null;
		try {
			if(Integer.parseInt(detailInfo[1]) == 0) {
		
				t2 = new JLabel("참석");
			} else if(Integer.parseInt(detailInfo[1]) == 1) {
				t2 = new JLabel("불참석");
				} else {
					t2 = new JLabel("병가");
				}	
		} catch (NumberFormatException e) {
				e.printStackTrace();
				t2 = new JLabel("출석여부불명");
			}
		
		p.add(l2);
		p.add(t2);
		p.add(l5);
		p.add(t5);
		p.add(l6);
		p.add(t6);
		add(p, "South");
		t2.setEnabled(false);
		t5.setEnabled(false);
		t6.setEnabled(false);
	}
	
}


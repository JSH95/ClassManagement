package List;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.List;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import Code.Category;
import Code.CategoryUtil;
import Detail.DetailFrame;
import List.Repo.ListRepo;
import Login.LoginFrame;
import Save.SaveFrame;
import sun.tools.jar.resources.jar;

public class ListFrame extends JFrame{
	
	String selectedDay = "0";
	String[][] datalist;
	JButton b2 = new JButton("일정상세보기");		
	JTable t;
	DefaultTableModel tm;

	public ListFrame(String id) {
		setTitle("수업관리 시스템 - 일람화면");
		setSize(700, 600);
		setLayout(new BorderLayout(10, 10));
		
		setCenterPanel(id);
		setEastPanel(id);
		setSouthPanel(id);
		
		
		setLocationRelativeTo(null);
		setVisible(true);
	}

	private void setSouthPanel(String id) {
		JPanel p = new JPanel();
		add(p, "South");
	}

	private void setEastPanel(String id) {
		JPanel p = new JPanel(new GridLayout(10, 0));
		JButton b1 = new JButton("일정 등록 ");
		b2.setEnabled(false);
		JButton b3 = new JButton("로그아웃");
		p.add(b1);
		p.add(b2);
		p.add(b3);
		add(p, "East");
		
		b1.addActionListener(e ->{
			setVisible(false);
			new SaveFrame(id);
		});
		
		b2.addActionListener(e -> {
			setVisible(false);
			new DetailFrame(selectedDay, id); //세부정보이동
		});
		b3.addActionListener(e -> {
			setVisible(false);
			new LoginFrame(); //로그아웃이동
		});
	}

	private void setCenterPanel(String id) {
		JPanel p = new JPanel();
		
		String[] title = {"날짜(년_월_일)", "출석여부", "일정제목", "수업시간(H)", "자습시간(H)"};
		
		tm = new DefaultTableModel(new String[0][0], title);
		
		t = new JTable(tm);
		JScrollPane s = new JScrollPane(t);
	
		p.add(s);
		add(p, "Center");
		
			ListRepo repo = new ListRepo();
			datalist = repo. list(id);
			tm.setRowCount(0);
			
			for(String[] data : datalist) {
				
					//출석여부 변환 처리
				CategoryUtil cUtil = new CategoryUtil();
						data[1] = cUtil.getDispValue(data[1]);
						
					tm.addRow(data);
				}
			
		t.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				b2.setEnabled(true);
				int selectedRow = t.getSelectedRow();
				selectedDay = datalist[selectedRow][0];
			}
		});
	}
	
}

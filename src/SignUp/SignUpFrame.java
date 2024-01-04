package SignUp;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import List.ListFrame;
import Login.LoginFrame;
import Login.Repo.LoginRepo;
import SignUpRepo.SingUpRepo;

public class SignUpFrame extends JFrame{
	
	
	public SignUpFrame() {
		
		setTitle("수업관리프로그램-회원가입화면");
		setSize(300, 200);
		
		 JPanel p = new JPanel(new GridLayout(4, 0));

		
		 JPanel p1 = new JPanel();
		 JLabel j1 = new JLabel("ID");
		 JTextField t1 = new JTextField(10);
		 p1.add(j1);
		 p1.add(t1);
		 
		 JPanel p2 = new JPanel();
		 JLabel j2 = new JLabel("PW");
		 JTextField t2 = new JTextField(10);

		 
		 p2.add(j2);
		 p2.add(t2);
		 
		 JPanel p3 = new JPanel();
		 JLabel j3 = new JLabel("NAME");
		 JTextField t3 = new JTextField(10);

		 p3.add(j3);
		 p3.add(t3);
		 
		 JPanel p4 = new JPanel();
		 JButton b1 = new JButton("회원가입");
		 JButton b2 = new JButton("돌아가기");
		 
		 
		 //회원가입처리
		 b1.addActionListener(e ->{
				String id = t1.getText();
				String password = t2.getText();
				String name = t3.getText();
				if(id.equals("") || password.equals("") || name.equals("")) {
					JOptionPane.showMessageDialog(null, "아이디와 패스워드와 이름을 입력해주세요");	
				} else {
					int result1 = JOptionPane.showConfirmDialog(null, "이 Id와 Password로 가입하시겠습니까?");
					if(result1 == 0) {					
						String id2 = t1.getText();
						SingUpRepo repo = new SingUpRepo();
						boolean result2 = repo.idCheck(id2);
						if(result2) {	 //아이디 중복 검사
							JOptionPane.showMessageDialog(null, "아이디가 중복됩니다.");	
						} else {
							boolean result = repo.singup(id, password, name);
							boolean result3 = repo.dbCreate(id);
							
							JOptionPane.showMessageDialog(null, "정상가입 되었습니다.");
							setVisible(false);
							new LoginFrame();
						}
					} else {
						JOptionPane.showMessageDialog(null, "취소되었습니다.");
						}
				}
		 	});
		 b2.addActionListener(e -> {
			 setVisible(false);
			 new LoginFrame();
		 });
		 
		 p4.add(b1);
		 p4.add(b2);
			p.add(p1);
			p.add(p2);
			p.add(p3);
			p.add(p4);

			 add(p);
			 
			 setLocationRelativeTo(null);
			 setDefaultCloseOperation(3);
			 setVisible(true);
	}
	
	private void SingUpRepo() {
		
	}
}

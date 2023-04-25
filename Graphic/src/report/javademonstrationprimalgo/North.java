package report.javademonstrationprimalgo;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class North extends JPanel implements ActionListener {
	/**
	 *
	 */
//class north có các biến như saise để lấy vào sổ đỉnh, envoyer là nút bấm để người dùng ấn.
	private JTextField saisie = new JTextField();
	private JButton envoyer = new JButton("send");
	private JLabel node = new JLabel(" number of Node");
//	combobox để lựa chọn thuật toán tuy nhiên krusal chưa hoàn thiện nên chỉ có prim
	private JComboBox<String> box = new JComboBox<String>();
	Dessin d;
	private String ExpressionReguliere = "([0-9]*)";

//hàm tạo class north sẽ thêm các component vào nó và đăng ký các send và envoyer để khi ấn ta có thể xử lý nó bằng actionperform
	public North(Dessin d) {
		this.d = d;
		setBackground(Color.DARK_GRAY);
		box.addItem(("Prim"));
		box.setBackground(Color.WHITE);

		box.addActionListener(this);
		envoyer.addActionListener(this);

		this.add(box);
		node.setForeground(Color.white);
		this.add(node);
		saisie.setColumns(3);
		this.add(saisie);
		this.add(envoyer);
	}

//hàm xử lí các event
	public void actionPerformed(ActionEvent e) {
//		khi bấm nút send và số đỉnh người dùng nhập vào hợp lệ ta sẽ khởi tạo ma trận các cạnh kề. tuy nhiên các cạnh này đều sẽ bằng không vì chưa nhập số liệu
		String s = saisie.getText();
		if (s.matches(ExpressionReguliere) && s.length() > 0) {
			d.setNodes(Integer.parseInt(s));
			d.tree = new Edge[2500];
			d.nEdges = 0;
			d.setCpt(0);
			d.finish = false;
			d.recommence = true;
//			cập nhật lại hình vẽ với dữ liệu mới
			d.repaint();
		}
	}
}

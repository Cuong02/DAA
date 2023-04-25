package report.javademonstrationprimalgo;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class South extends JPanel implements ActionListener {
	/**
	 *
	 */
//	box1 để lấy vào đỉnh thứ nhất
	private JTextField box1 = new JTextField("");
//	box2 để lấy vào đỉnh thứ hai
	private JTextField box2 = new JTextField("");
//	box3 để đánh trọng số cho 2 cạnh muốn nối
	private JTextField setWeight = new JTextField("");

	private JButton envoyer = new JButton("send");
	private JButton step = new JButton("step");

	private JLabel node1 = new JLabel("1st node");
	private JLabel node2 = new JLabel("2nd node");
	private JLabel weight = new JLabel("weight");

	private String ExpressionReguliere = "([1-9][0-9]*)";
	Dessin d;
	public int nNodes = 50;
	Edge[] tree = new Edge[nNodes * nNodes];;

	Prim p;

	public South(Dessin d) {
		this.d = d;
		initTree(tree);
		setBackground(Color.black);
//		dky các envoyer và step để ta có thể xử lý bằng actionperform
		envoyer.addActionListener(this);
		step.addActionListener(this);

		node1.setForeground(Color.white);
		this.add(node1);
		box1.setColumns(3);
		this.add(box1);

		node2.setForeground(Color.white);
		this.add(node2);
		box2.setColumns(3);
		this.add(box2);

		weight.setForeground(Color.white);
		this.add(weight);
		setWeight.setColumns(3);

		this.add(setWeight);
		this.add(envoyer);
		this.add(step);
	}

	public void initTree(Edge[] tree) {
		for (int i = 0; i < tree.length; i++) {
			tree[i] = null;
		}
	}

//	class actionperformed để xử lý khi ấn các button được dky
	public void actionPerformed(ActionEvent event) {
		if (d.recommence) {
			initTree(tree);
			d.recommence = false;
		}
		if (event.getSource() == envoyer) {
			String c1 = box1.getText();
			String c2 = box2.getText();
			String c3 = setWeight.getText();
//			nếu người dùng nhập đúng input số thì thực hiện
			if (c1.matches(ExpressionReguliere) && c2.matches(ExpressionReguliere) && c3.matches(ExpressionReguliere)) {

				int s1 = Integer.parseInt(c1);
				int s2 = Integer.parseInt(c2);
				int weight = Integer.parseInt(c3);
//				nếu người dùng nhập đúng số đỉnh nhỏ hơn số nốt đã khởi tạo thì thực hiện
				if (s1 <= d.getNode() && s2 <= d.getNode() && s1 != s2) {
					Edge a = new Edge(s1, s2, weight);

					boolean isReady = false;
					int emplacement = 0;
					int i = 0;

					while (tree[i] != null && i < tree.length) {
						if ((tree[i].getNode1() == a.getNode1() || tree[i].getNode1() == a.getNode2())
								&& (tree[i].getNode2() == a.getNode1() || tree[i].getNode2() == a.getNode2())) {
							emplacement = i;
							isReady = true;
						}
						i++;
					}
					if (!isReady) {
						tree[d.getNEdge()] = a;
						d.addEdge(a, d.getNEdge());

					} else {
						tree[emplacement].setWeight(weight);
						d.modifyEdge(emplacement, weight);
					}
//					cập nhật lại các các số liệu
					d.repaint();
				}
			}
		} else if (event.getSource() == step) {
			if (tree[0] != null) {
				if (d.getCpt() == 0) {
//					khởi tạo thuật toán prim với các tham số truyền vào là số đỉnh và số cạnh
					p = new Prim(tree, d.getNode(), d.getNEdge());
//					kết quả sẽ được lưu vào treeFinal
					d.treeFinal = p.tree;

					d.finish = true;
				}

				if (d.treeFinal[d.getCpt()] != null) {
					d.setCpt(d.getCpt() + 1);
				}
//				cập nhật lại và vẽ lại kết quả dựa trên treeFinal
				d.repaint();
			}
		}
	}
}

package report.javademonstrationprimalgo;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;

import javax.swing.JPanel;

public class Dessin extends JPanel {
	/**
	 *
	 */

	private int nNodes = 0;

	Edge[] tree;
	public int nEdges = 0;

	Edge[] treeFinal = new Edge[2500];
	public boolean finish = false;
	public boolean recommence = false;
	private int cpt = 0;

	public Dessin() {
		tree = new Edge[2500];
		setBackground(Color.white);
	}
//overide lại hàm paint của Jpanel
	public void paint(Graphics g) {

		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;

		int length = getSize().width;
		int height = getSize().height;
		double angle = 0;
		Stroke s = g2.getStroke();
//vòng lặp for đầu để vẽ các cạnh với trọng số của đồ thị
		for (int i = 0; i < nEdges; i++) {
			int node1 = tree[i].getNode1();
			int node2 = tree[i].getNode2();

			angle = (node1 - 1) * (2 * Math.PI / nNodes);
			double angle1 = (node2 - 1) * (2 * Math.PI / nNodes);

			g2.setColor(Color.black);

			int x = (int) (length / 2 - (length / 4) * Math.cos(angle) + 15);

			int y = (int) (height / 2 - (height / 4) * Math.sin(angle) + 15);

			int x2 = (int) ((length / 2) - (length / 4) * Math.cos(angle1) + 15);

			int y2 = (int) (height / 2 - (height / 4) * Math.sin(angle1) + 15);

			g2.drawLine(x, y, x2, y2);

			g.setColor(Color.black);

			g.drawString("" + (tree[i].getWeight()), (int) ((x + x2) / 2 + 20), (int) ((y + y2) / 2 + 20));
		}
//vòng lặp for thứ hai để vẽ các bước thực hiện thuật toán. Các đường kẻ sẽ được tô màu đỏ
		for (int i = 0; i < getCpt(); i++) {

			if (treeFinal[i] != null) {
				int node1 = treeFinal[i].getNode1();
				int node2 = treeFinal[i].getNode2();

				angle = (node1 - 1) * (2 * Math.PI / nNodes);
				double angle1 = (node2 - 1) * (2 * Math.PI / nNodes);

				g2.setColor(Color.red);
				g2.setStroke(new BasicStroke(2));
				int x = (int) ((length / 2) - ((length / 4) * Math.cos(angle)) + 15);

				int y = (int) (height / 2 - (height / 4) * Math.sin(angle) + 15);

				int x2 = (int) ((length / 2) - ((length / 4) * Math.cos(angle1)) + 15);
				int y2 = (int) (height / 2 - (height / 4) * Math.sin(angle1) + 15);

				g2.drawLine(x, y, x2, y2);
				g2.setStroke(s);

				g.setColor(Color.red);

				g.drawString("" + (treeFinal[i].getWeight()), (int) ((x + x2) / 2 + 20), (int) ((y + y2) / 2 + 20));
			}
		}
//vẽ các đỉnh của đồ thị
		angle = 0;
		for (int i = 0; i < nNodes; i++) {
			g2.setColor(Color.BLACK);
			g2.fillOval((int) ((length / 2) - ((length / 4) * Math.cos(angle))),
					(int) (height / 2 - (height / 4) * Math.sin(angle)), 30, 30);

			g.setColor(Color.white);

			g.drawString("" + (i + 1), (int) ((length / 2) - ((length / 4) * Math.cos(angle)) + 10),
					(int) (height / 2 - (height / 4) * Math.sin(angle) + 20));

			angle += 2 * Math.PI / nNodes;
		}

	}

	public int getCpt() {
		return cpt;
	}

	public void setCpt(int i) {
		this.cpt = i;
	}

	public int getNode() {
		return nNodes;
	}

	public void setNodes(int i) {
		this.nNodes = i;
	}

	public int getNEdge() {
		return nEdges;
	}

	public void addEdge(Edge a, int nEdge) {
		tree[nEdge] = a;
		nEdges++;
	}

	public void modifyEdge(int emplacement, int weight) {
		tree[emplacement].setWeight(weight);
	}
}

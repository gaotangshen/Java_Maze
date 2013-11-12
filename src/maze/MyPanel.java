package maze;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;
import java.util.Stack;
//import java.util.Map;

import javax.swing.JPanel;

public class MyPanel extends JPanel {

	public void init(int cellSize) {
		myDispatcher = new MyKeyEventDispatcher(this);
		KeyboardFocusManager.getCurrentKeyboardFocusManager()
				.addKeyEventDispatcher(myDispatcher);
		
		
		// cellSize = 8;
		this.cellSize = cellSize;
		this.myHeight = panelHeight / cellSize;
		this.myWidth = panelWidth / cellSize;
		this.matrix = new int[myWidth][myHeight];
		initFill();
		// System.out.print(cellSize+" "+width+" "+height);
		// this.addKeyListener(l);
		this.repaint();

	}
	
	private void initFill() {
		curX = 0;
		curY = 4;
		int count = 0;
		int count1 = 0;
		ArrayList<Integer> number = new ArrayList<Integer>();
		Stack<Integer> st = new Stack<Integer>();
		st.push(curY);
		st.push(curX);
		Random rand = new Random();
		int flag = 0;
		int randNumber = rand.nextInt(4);
		// number.add(randNumber);
		int temcurX = 0;
		int temcurY = 0;
		for (int i = 0; i < myWidth; i++) {
			for (int j = 0; j < myHeight; j++) {
				matrix[i][j] = 1;
			}
		}
		matrix[curX][curY] = 0;

		while (true) {

			switch (randNumber) {
			case 0:
				if (curX - 2 >= 0 && curY >= 0) {
					
					if (matrix[curX - 2][curY] == 1) {

						matrix[curX - 1][curY] = 0;
						matrix[curX - 2][curY] = 0;

						temcurX = curX - 2;
						temcurY = curY;
						flag = 1;
					}
				}
				break;
			case 1:
				if (curX + 2 < myWidth && curY >= 0) {
					if (matrix[curX + 2][curY] == 1) {
						matrix[curX + 1][curY] = 0;
						matrix[curX + 2][curY] = 0;

						temcurX = curX + 2;
						temcurY = curY;
						flag = 1;
					}
				}
				break;
			case 2:
				if (curX >= 0 && curY - 2 >= 0) {
					if (matrix[curX][curY - 2] == 1) {
						matrix[curX][curY - 1] = 0;
						matrix[curX][curY - 2] = 0;

						temcurX = curX;
						temcurY = curY - 2;
						flag = 1;
					}
				}
				break;
			case 3:
				if (curX >= 0 && curY + 2 < myHeight) {
					if (matrix[curX][curY + 2] == 1) {
						matrix[curX][curY + 1] = 0;
						matrix[curX][curY + 2] = 0;

						temcurX = curX;
						temcurY = curY + 2;
						flag = 1;
					}
				}
				break;
			}
			if (flag == 1) {
				st.push(curY);
				st.push(curX);
				// System.out.println(curX + "  " + curY);
				curX = temcurX;
				curY = temcurY;
//				System.out.println(temcurX + " " + temcurY);

				randNumber = rand.nextInt(4);
				number.clear();

			} else {
				for (int i = 0; i < number.size(); i++) {
					if (number.get(i).intValue() != randNumber) {
						count1++;
//						System.out.println(randNumber);

					}
					if (count1 == number.size()) {
						number.add(randNumber);
						count1 = 0;
					}

				}

			}
			count1 = 0;
			if (number.size() == 0)
				number.add(5);
			// System.out.println(number.get(0));
			for (int i = 0; i < number.size(); i++) {
				if (number.get(i).intValue() != randNumber
						&& number.get(i) != 5) {

					count++;
				}

			}
			if (count == 3) {
				number.clear();
				curX = st.pop();
				curY = st.pop();
				count = 0;

			} else
				count = 0;
			if (st.empty()) {
				break;
			}
			randNumber = rand.nextInt(4);
			flag = 0;

		}
		matrix[myWidth-1][0] = 0;
	}

	

	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		super.paint(g);
		paintMyStuff(g);
		// System.out.print("test");

	}

	private void paintMyStuff(Graphics g) {
		paintLines(g);
		paintBlocks(g);
		paintOtherStuff(g);
		paintMouse(g, curX, curY);
	}

	private void paintLines(Graphics g) {
		g.setColor(Color.black);

		for (int i = 1; i <= myHeight + 1; i++) {
			g.drawLine(cellSize, cellSize * i, panelWidth + cellSize, cellSize
					* i);
		}
		for (int i = 1; i <= myWidth + 1; i++) {
			g.drawLine(cellSize * i, cellSize, cellSize * i, panelHeight
					+ cellSize);
		}
	}

	private void paintBlocks(Graphics g) {
		for (int i = 0; i < myWidth; i++)
			for (int j = 0; j < myHeight; j++) {
				if (matrix[i][j] == 1) {
					paintBlock(g, i, j);
				}
				if (matrix[i][j] == 2) {
					paintPath(g, i, j);
				}

			}

	}

	private void paintPath(Graphics g, int x, int y) {
		g.setColor(Color.blue);
		g.fillRect((x + 1) * (cellSize) + 1, (y + 1) * cellSize + 1,
				cellSize - 1, cellSize - 1);
	}

	private void paintBlock(Graphics g, int x, int y) {
		g.setColor(Color.black);
		g.fillRect((x + 1) * (cellSize) + 1, (y + 1) * cellSize + 1,
				cellSize - 1, cellSize - 1);
	}

	private void paintOtherStuff(Graphics g) {

		g.setColor(Color.green);
		g.fillRect(cellSize + 1, 5 * cellSize + 1, cellSize - 1, cellSize - 1);

		g.setColor(Color.red);
		g.fillRect((myWidth) * cellSize + 1, cellSize + 1, cellSize - 1,
				cellSize - 1);

	}

	private void paintMouse(Graphics g, int curX, int curY) {
		g.setColor(Color.black);
		// System.out.println(curX + " " + curY);
		g.drawRoundRect((curX + 1) * cellSize + 1, (curY + 1) * cellSize + 1,
				cellSize - 1, cellSize - 1, 20, 20);

	}

	public boolean isMovable(int x, int y) {
		// System.out.println("\n"+"current:"+x + " " + y+" ;");
		if (y < 0 || x < 0 || x >= myWidth || y >= myHeight)
			return false;

		if (matrix[x][y] == 0||matrix[x][y] == 2) {

			return true;
		} else {

			return false;
		}
	}

	public void paintPath(int front) {
		for(int i  = 0;i<myWidth;i++){
			for(int j = 0;j<myHeight;j++){
				if(matrix[i][j] == -1){
					System.out.println("test");
					matrix[i][j] = 0;
				}
			}
		}

		System.out.print("find path!");
		int X = 0;
		int Y = 0;
		int pre = node[front][3];
		while (pre != -1) {
			X = node[pre][0];
			Y = node[pre][1];
			matrix[X][Y] = 2;
			System.out.println("pathnode : " + X + " " + Y);
			pre = node[pre][3];
		}
		// for(int i = 0;i<myHeight;i++){
		// for(int j = 0;j<myWidth;j++){
		// System.out.print(matrix[j][i]);
		// }
		// System.out.println("\n");
		// }
		repaint();
	}
	public void clearHint(){
		for(int i  = 0;i<myWidth;i++){
			for(int j = 0;j<myHeight;j++){
				if(matrix[i][j] == 2||matrix[i][j] == -1){
					
					matrix[i][j] = 0;
				}
				
			}
		}
		repaint();
	}
	public void path() {
		for(int i  = 0;i<myWidth;i++){
			for(int j = 0;j<myHeight;j++){
				if(matrix[i][j] == 2){
					System.out.println("test");
					matrix[i][j] = 0;
				}
			}
		}
		// int curX = mypanel.getCurX();
		// int curY = mypanel.getCurY();
		this.node = new int[myWidth * myHeight][4];
		int myWidth = getMyWidth();
		int front = -1;
		int rear = 0;
		int temY = 0;
		int temX = 0;
		int findpath = 0;
		node[rear][0] = curX;
		node[rear][1] = curY;
		node[rear][2] = -1;
		node[rear][3] = -1;
		while (front <= rear && findpath == 0) {
			
			front++;
			int find = 0;

			if (node[front][0] == myWidth - 1 && node[front][1] == 0) {
				System.out.print(front + "find path!");
				findpath = 1;
				paintPath(front);
			}
			while (findpath == 0 && node[front][2] < 4 && find == 0) {
				node[front][2]++;
				switch (node[front][2]) {
				case 0:
					temX = node[front][0] - 1;
					temY = node[front][1];
					if (isMovable(temX, temY))
						find = 1;
					break;
				case 1:
					temX = node[front][0] + 1;
					temY = node[front][1];
					if (isMovable(temX, temY))
						find = 1;
					break;
				case 2:
					temX = node[front][0];
					temY = node[front][1] - 1;
					if (isMovable(temX, temY))
						find = 1;
					break;
				case 3:
					temX = node[front][0];
					temY = node[front][1] + 1;
					if (isMovable(temX, temY))
						find = 1;
					break;
				}
				if (find == 1) {
					rear++;

					matrix[temX][temY] = -1;
					node[rear][0] = temX;
					node[rear][1] = temY;
					node[rear][2] = -1;
					node[rear][3] = front;
					// for(int i=0;i<4;i++){
					// System.out.print(node[rear][i]+" ");
					//
					// }
				}
				find = 0;
			}

		}
	}

	// 83
	// 83
	// 94
	// 94
	// 92
	// 92
	// 103
	// 103

	private int node[][];
	private int curX, curY;

	private int myWidth = -1, myHeight = -1;

	private int panelWidth = 360, panelHeight = 240;

	private int cellSize;
	private KeyEventDispatcher myDispatcher;

	private int[][] matrix;

	public int getCurX() {
		return curX;
	}

	public void setCurX(int curX) {
		this.curX = curX;
	}

	public int getCurY() {
		return curY;
	}

	public void setCurY(int curY) {
		this.curY = curY;
	}

	public int getMyWidth() {
		return myWidth;
	}

	public void setMyWidth(int myWidth) {
		this.myWidth = myWidth;
	}

	public int getMyHeight() {
		return myHeight;
	}

	public void setMyHeight(int myHeight) {
		this.myHeight = myHeight;
	}

	public int getPanelWidth() {
		return panelWidth;
	}

	public void setPanelWidth(int panelWidth) {
		this.panelWidth = panelWidth;
	}

	public int getPanelHeight() {
		return panelHeight;
	}

	public void setPanelHeight(int panelHeight) {
		this.panelHeight = panelHeight;
	}

	public int getCellSize() {
		return cellSize;
	}

	public void setCellSize(int cellSize) {
		this.cellSize = cellSize;
	}

	public int[][] getMatrix() {
		return matrix;
	}

	public void setMatrix(int[][] matrix) {
		this.matrix = matrix;
	}

}

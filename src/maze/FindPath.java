package maze;

import java.util.*;

public class FindPath {

	public FindPath(MyPanel mypanel) {
		this.mypanel = mypanel;
	}

//	public void autoPath(int curX, int curY) {
//		int myWidth = mypanel.getMyWidth();
//		int myHeight = mypanel.getMyHeight();
//		Boolean[][] visit = new Boolean[myWidth][myHeight];
//		matrix = mypanel.getMatrix();
//		// LinkedList<Object> queue = new LinkedList<Object>();
//		// Stack<Object> stackout = new Stack<Object>();
//		// int startX = curX;
//		// int startY = curY;
//		int x = curX;
//		int y = curY;
//		count = 4;
//		// queue.offer(matrix[startX][startY]);
//		// while(queue.poll() !=null){
//		// matrix= queue.poll();
//		// }
//		// LinkedList<int[][]> queue = new LinkedList<int[][]>();
//		// if (curX >= 0 && curY >= 0 && curX < mypanel.getMyWidth()
//		// && curY < mypanel.getMyHeight() && matrix[curX][curY] != 1) {
//		// if (curX == mypanel.getMyWidth() - 1 && curY == 5) {
//		//
//		// } else {
//		// if (mypanel.isMovable(curX - 1, curY)) {
//		// matrix[curX - 1][curY] = 1;
//		// }
//		// if (mypanel.isMovable(curX + 1, curY)) {
//		// matrix[curX + 1][curY] = 1;
//		// }
//		// if (mypanel.isMovable(curX, curY + 1)) {
//		// matrix[curX][curY + 1] = 1;
//		// }
//		// if (mypanel.isMovable(curX, curY - 1)) {
//		// matrix[curX][curY - 1] = 1;
//		// }
//		// }
//		// }
//		while (curX != myWidth - 1 && curY != 5) {
//
//			for (int i = 0; i < myWidth; i++) {
//				for (int j = 0; j < 4; j++) {
//					if (!visit[i][j]) {
//						BFS(i, j, visit);
//					}
//				}
//			}
//		}
//		// while(count != 1){
//		// if(mypanel.isMovable(curX - 1, curY)){
//		//
//		// }
//		// count--;
//		// }
//
//	}
//
//	public void BFS(int i, int j, Boolean visit[][]) {
//		LinkedList<int[][]> queue = new LinkedList<int[][]>();
//
//	}
	public void printPath(int front){
		int X;
		int Y;
		int pre = node[front][3];
		while(pre != -1){
			X = node[front][0];
			Y = node[front][1];
			matrix[X][Y] = 2;
		}
		mypanel.repaint();
	}

	public void path(int curX,int curY) {
		//int curX = mypanel.getCurX();
		//int curY = mypanel.getCurY();
		
		int myWidth = mypanel.getMyWidth();
		int front = -1;
		int rear = 0;
		node[rear][3] = -1;
		node[rear][2] = -1;
		node[rear][0] = curX;
		node[rear][1] = curY;
		while (front<=rear) {
			front ++;
			int find = 0;
			
			if(node[front][0] == myWidth&&node[front][1]==5){
				printPath(front);
			}
			while (node[front][2] < 4 && find == 0) {
				node[front][2]++;
				switch (node[front][2]) {
				case 0:
					curX = curX - 1;
					curY = curY;
					if (mypanel.isMovable(curX, curY))
						find = 1;
					break;
				case 1:
					curX = curX + 1;
					curY = curY;
					if (mypanel.isMovable(curX, curY))
						find = 1;
					break;
				case 2:
					curX = curX;
					curY = curY - 1;
					if (mypanel.isMovable(curX, curY))
						find = 1;
					break;
				case 3:
					curX = curX;
					curY = curY + 1;
					if (mypanel.isMovable(curX, curY))
						find = 1;
					break;
				}
				if (find == 1) {
					rear++;
					
					//matrix[curX][curY] = 2;
					node[rear][0] = curX;
					node[rear][1] = curY;
					node[rear][2] = -1;
					node[rear][3] = front;
				}
				find = 0;
			}

		}
	}

	private MyPanel mypanel;
	private LinkedList queue;
	// private int matrix1[][][];
	private int matrix[][];
	private int count;
	private int node[][];
}

class node {
	int i;
	int j;
	int pre;
	int di;

	node() {

	}
}
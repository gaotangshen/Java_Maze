package maze;

import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.event.KeyEvent;

import javax.swing.JOptionPane;

public class MyKeyEventDispatcher implements KeyEventDispatcher {
	public MyKeyEventDispatcher(MyPanel myPanel) {
		this.myPanel = myPanel;
	}
//	private MyKeyEventDispatcher dispatcher;
	private MyPanel myPanel;

	@Override
	public boolean dispatchKeyEvent(KeyEvent e) {

		// TODO Auto-generated method stub
		if (e.getID() == KeyEvent.KEY_PRESSED) {
			if (e.getKeyCode() == KeyEvent.VK_DOWN) {
				if (myPanel.isMovable(myPanel.getCurX(), myPanel.getCurY() + 1)) {
					myPanel.setCurY(myPanel.getCurY() + 1);
					System.out.println(myPanel.getCurY());
					 checkSuccess();
				}
			}
			if (e.getKeyCode() == KeyEvent.VK_UP) {
				if (myPanel.isMovable(myPanel.getCurX(), myPanel.getCurY() - 1)) {
					myPanel.setCurY(myPanel.getCurY() - 1);
					 checkSuccess();
				}
			}
			if (e.getKeyCode() == KeyEvent.VK_LEFT) {
				if (myPanel.isMovable(myPanel.getCurX() - 1, myPanel.getCurY())) {
					myPanel.setCurX(myPanel.getCurX() - 1);
					 checkSuccess();
				}
			}
			if (e.getKeyCode() == KeyEvent.VK_RIGHT) {// System.out.println(myPanel.getCurX());
				// System.out.println(myPanel.isMovable(myPanel.getCurX(),
				// myPanel.getCurY()));
				if (myPanel.isMovable(myPanel.getCurX() + 1, myPanel.getCurY())) {
					System.out.println(myPanel.getCurX());
					myPanel.setCurX(myPanel.getCurX() + 1);
					 checkSuccess();
				}
			}
			myPanel.repaint();
		}
		return true;
	}

	private void checkSuccess() {
		if (myPanel.getCurX() == (myPanel.getMyWidth() - 1)
				&& myPanel.getCurY() == 0) {
			JOptionPane.showMessageDialog(myPanel, "success!");
			KeyboardFocusManager.getCurrentKeyboardFocusManager()
			.removeKeyEventDispatcher(this);
		}
		
	}

}

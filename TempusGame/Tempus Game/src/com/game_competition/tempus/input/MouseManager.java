package com.game_competition.tempus.input;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import com.game_competition.tempus.ui.UIManager;

public class MouseManager implements MouseListener, MouseMotionListener, MouseWheelListener {
	
	private boolean leftPressed, rightPressed;
	private int mouseX, mouseY;
	private int wheelPosition;
	private UIManager uiManager;
	
	public MouseManager() {
		
	}
	
	public void setUIManager(UIManager uiManager) {
		this.uiManager = uiManager;
		
	}
	//GETTERS
	public boolean isLeftPressed() {
		return leftPressed;
	}
	
	public boolean isRightPressed() {
		return rightPressed;
	}
	
	public int getMouseX() {
		return mouseX;
	}
	public int getMouseY() {
		return mouseY;
	}
	
	public int getWheelPosition() {
		return wheelPosition;
	}
	
	
	//IMPLEMENTED

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		mouseX = e.getX();
		mouseY = e.getY();
		
		if(uiManager != null) {
			uiManager.onMouseMove(e);
		}
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if(e.getButton()==MouseEvent.BUTTON1) {
			leftPressed = true;
		}else if(e.getButton() == MouseEvent.BUTTON3);{
			rightPressed = true;
		}
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if(e.getButton()==MouseEvent.BUTTON1) {
			leftPressed = false;
		}else if(e.getButton() == MouseEvent.BUTTON3);{
			rightPressed = false;
		}
		if(uiManager != null) {
			uiManager.onMouseRelease(e);
		}
	}

	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		wheelPosition = e.getWheelRotation();
		
	}

}

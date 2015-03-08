package controllers;

import java.awt.*;

import javax.swing.*;

import views.*;

public class ApplicationController {
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) throws Exception  {
		
		// take the menu bar off the jframe
		System.setProperty("apple.laf.useScreenMenuBar", "true");

		// set the name of the application menu item
		System.setProperty("com.apple.mrj.application.apple.menu.about.name", "PBM");
		
		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				System.out.println("Program starts...");
				runApp();
			}
		});
		
		
	}// end of main method
	
	public static void runApp(){

		System.out.println("Log in:");
		
		UsersFrame usersFrame = new UsersFrame();
		new UsersController(usersFrame);
		
		usersFrame.setVisible(true);
	}
}

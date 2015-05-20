package com.example.mockdemo.app;


import org.junit.Test;

import com.example.mockdemo.messenger.MessageServiceImpl;

import static org.junit.Assert.*;

public class TestWithOut {

	public MessageServiceImpl ms = new MessageServiceImpl() ;

	Messenger nowy = new Messenger(ms);

	@Test
	public void testConnectioon() {

		String serwer = "wp.pl";
		
		int actual = nowy.testConnection(serwer);
		
		int target = 0;
		
		assertEquals(target, actual);

	}
	
	@Test
	public void testConnectioon2() {

		String serwer = "inf.ug.edu.pl";
		
		int actual = nowy.testConnection(serwer);
		
		int target = 0;
		
		assertEquals(target, actual);

	}
	
	@Test
	public void testConnectioon3() {

		String serwer = "wp.pl.edu";
		
		int actual = nowy.testConnection(serwer);
		
		int target = 1;
		
		assertEquals(target, actual);

	}
	
	@Test
	public void testConnectioon4() {

		String serwer = "bash.org";
		
		int actual = nowy.testConnection(serwer);
		
		int target = 1;
		
		assertEquals(target, actual);

	}
	
	@Test
	public void testConnectioon5() {

		String serwer = "wiocha.pl";
		
		int actual = nowy.testConnection(serwer);
		
		
		assertNotNull(actual);

	}
	
	@Test
	public void testSending(){
	
		assertEquals(0,nowy.sendMessage("onet.pl", "abcd"));
		
	}
	
	
	@Test
	public void testSending2(){
	
		assertEquals(1,nowy.sendMessage("wpi.pl.com", "abrada"));
		
	}
	
	@Test
	public void testSending3brzeg(){
		

		assertEquals(2, nowy.sendMessage("", "ropper"));
		
	}
	
	@Test
	public void testSending4brzeg(){
		

		assertEquals(2, nowy.sendMessage("www.wp.pl", "r"));
		
	}
	
	@Test
	public void testSending5brzeg(){
		

		assertEquals(2, nowy.sendMessage(null, "oppklr"));
		
	}
	
	

}

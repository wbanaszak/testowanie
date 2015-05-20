package com.example.mockdemo.app;

import static org.junit.Assert.*;
import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;

import org.junit.Before;
import org.junit.Test;

import com.example.mockdemo.messenger.ConnectionStatus;
import com.example.mockdemo.messenger.MalformedRecipientException;
import com.example.mockdemo.messenger.MessageService;
import com.example.mockdemo.messenger.SendingStatus;

public class easyMockTest {
	
	
	private Messenger myMessenger;
	private MessageService mock;
	
	@Before
	public void setUp(){
		mock = createMock(MessageService.class);
		myMessenger = new Messenger(mock);
	}

	@Test
	public void testingTheConnectionSuccessfull(){
		expect(mock.checkConnection("wp.pl")).andReturn(ConnectionStatus.SUCCESS);
		replay(mock);
		assertEquals(0, myMessenger.testConnection("wp.pl"));
		verify(mock);
	}
	
	@Test
	public void testingTheConnectionSuccessfull3(){
		expect(mock.checkConnection(".pl")).andReturn(ConnectionStatus.SUCCESS);
		replay(mock);
		assertEquals(0, myMessenger.testConnection(".pl"));
		verify(mock);
	}
	
	@Test
	public void testingTheConnectionSuccessfull2(){
		expect(mock.checkConnection("inf.ug.edu.pl")).andReturn(ConnectionStatus.SUCCESS);
		replay(mock);
		assertEquals(0, myMessenger.testConnection("inf.ug.edu.pl"));
		verify(mock);
	}
	
	@Test
	public void testingTheConnectionNotSuccessfull(){
		expect(mock.checkConnection("eska.rock")).andReturn(ConnectionStatus.FAILURE);
		replay(mock);
		assertEquals(1, myMessenger.testConnection("eska.rock"));
		verify(mock);
	}
	
	@Test
	public void testingTheConnectionNotSuccessfull2(){
		expect(mock.checkConnection("omegle")).andReturn(ConnectionStatus.FAILURE);
		replay(mock);
		assertEquals(1, myMessenger.testConnection("omegle"));
		verify(mock);
	}
	
	@Test
	public void testingTheConnectionNotSuccessfull3(){
		expect(mock.checkConnection("pl")).andReturn(ConnectionStatus.FAILURE);
		replay(mock);
		assertEquals(1, myMessenger.testConnection("pl"));
		verify(mock);
	}
	
	@Test
	public void goodSend() throws MalformedRecipientException{
		expect(mock.send("onet.pl", "malpa")).andReturn(SendingStatus.SENT);
		replay(mock);
		assertEquals(0, myMessenger.sendMessage("onet.pl", "malpa"));
		verify(mock);
	}
	
	@Test
	public void badSend2() throws MalformedRecipientException{
		expect(mock.send("eska.cos", "kicham")).andReturn(SendingStatus.SENDING_ERROR);
		replay(mock);
		assertEquals(1, myMessenger.sendMessage("eska.cos", "kicham"));
		verify(mock);
	}
	
	@Test
	public void testingSendingWithException() throws MalformedRecipientException{
		expect(mock.send("wp.pl", "r")).andThrow(new MalformedRecipientException());
		replay(mock);
		assertEquals(2, myMessenger.sendMessage("wp.pl", "r"));
		verify(mock);
	}
	
	@Test
	public void testingSendingWithException2() throws MalformedRecipientException{
		expect(mock.send(null, "aaar")).andThrow(new MalformedRecipientException());
		replay(mock);
		assertEquals(2, myMessenger.sendMessage(null, "aaar"));
		verify(mock);
	}
	
	@Test
	public void testingSendingWithException3() throws MalformedRecipientException{
		expect(mock.send(null, null)).andThrow(new MalformedRecipientException());
		replay(mock);
		assertEquals(2, myMessenger.sendMessage(null, null));
		verify(mock);
	}
	
	@Test
	public void testingSendingWithException4() throws MalformedRecipientException{
		expect(mock.send("www.orange.pl", null)).andThrow(new MalformedRecipientException());
		replay(mock);
		assertEquals(2, myMessenger.sendMessage("www.orange.pl", null));
		verify(mock);
	}
	
}

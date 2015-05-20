package com.example.mockdemo.app;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

import com.example.mockdemo.messenger.ConnectionStatus;
import com.example.mockdemo.messenger.MalformedRecipientException;
import com.example.mockdemo.messenger.MessageService;
import com.example.mockdemo.messenger.SendingStatus;

public class mockito {
	
	private Messenger myMessenger;
	private MessageService mock;
	
	@Before
	public void setUp(){
		mock = mock(MessageService.class);
		myMessenger = new Messenger(mock);
	}

	@Test
	public void testingTheConnectionSuccessfull(){
		when(mock.checkConnection("wp.pl")).thenReturn(ConnectionStatus.SUCCESS);
		assertEquals(0, myMessenger.testConnection("wp.pl"));
	}
	
	@Test
	public void testingTheConnectionSuccessfull2(){
		when(mock.checkConnection(".pl")).thenReturn(ConnectionStatus.SUCCESS);
		assertEquals(0, myMessenger.testConnection(".pl"));
	}
	
	@Test
	public void testingTheConnectionSuccessfull3(){
		when(mock.checkConnection("inf.ug.edu.pl")).thenReturn(ConnectionStatus.SUCCESS);
		assertEquals(0, myMessenger.testConnection("inf.ug.edu.pl"));
	}
	@Test
	public void testingTheConnectionNotSuccessfull(){
		when(mock.checkConnection("wp.pl.com.edu")).thenReturn(ConnectionStatus.FAILURE);
		assertEquals(1, myMessenger.testConnection("wp.pl.com.edu"));
	}
	
	@Test
	public void testingTheConnectionNotSuccessfull2(){
		when(mock.checkConnection("pl")).thenReturn(ConnectionStatus.FAILURE);
		assertEquals(1, myMessenger.testConnection("pl"));
	}
	
	@Test
	public void testingTheConnectionNotSuccessfull3(){
		when(mock.checkConnection(null)).thenReturn(ConnectionStatus.FAILURE);
		assertEquals(1, myMessenger.testConnection(null));
	}
	
	@Test
	public void testingSendingSuccessfully() throws MalformedRecipientException{
		when(mock.send("wp.pl", "ramzes")).thenReturn(SendingStatus.SENT);
		assertEquals(0, myMessenger.sendMessage("wp.pl", "ramzes"));
	}
	
	@Test
	public void testingSendingNotSuccessfully() throws MalformedRecipientException{
		when(mock.send("inf.com", "reksio")).thenReturn(SendingStatus.SENDING_ERROR);
		assertEquals(1, myMessenger.sendMessage("inf.com", "reksio"));
	}
	
	@Test
	public void testingSendingWithException() throws MalformedRecipientException{
		when(mock.send("wp.pl", "r")).thenThrow(new MalformedRecipientException());
		assertEquals(2, myMessenger.sendMessage("wp.pl", "r"));
	}
	
	@Test
	public void testingSendingWithException2() throws MalformedRecipientException{
		when(mock.send(null, "rrroo")).thenThrow(new MalformedRecipientException());
		assertEquals(2, myMessenger.sendMessage(null, "rrroo"));
	}
	
	@Test
	public void testingSendingWithException3() throws MalformedRecipientException{
		when(mock.send(null, null)).thenThrow(new MalformedRecipientException());
		assertEquals(2, myMessenger.sendMessage(null, null));
	}
	
	@Test
	public void testingSendingWithException4() throws MalformedRecipientException{
		when(mock.send("www.orange.pl", null)).thenThrow(new MalformedRecipientException());
		assertEquals(2, myMessenger.sendMessage("www.orange.pl", null));
	}
	
	
}

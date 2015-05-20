package com.example.mockdemo.app;

import static org.junit.Assert.assertEquals;

import java.lang.reflect.Method;

import net.sf.cglib.proxy.InvocationHandler;
import net.sf.cglib.proxy.Proxy;

import org.junit.Test;

import com.example.mockdemo.messenger.ConnectionStatus;
import com.example.mockdemo.messenger.MalformedRecipientException;
import com.example.mockdemo.messenger.MessageService;
import com.example.mockdemo.messenger.SendingStatus;

public class testProxy {
	
	
	
		InvocationHandler ih = new MessageServiceHandler();
		MessageService msMock = (MessageService) Proxy.newProxyInstance(
				MessageService.class.getClassLoader(),
				new Class[] { MessageService.class }, ih);
		Messenger myMessage = new Messenger(msMock);
	

	@Test
	public void checkTestingConnectionSuccesfully() {

		assertEquals(0, myMessage.testConnection("wp.pl"));
	}

	
	@Test
	public void checkTestingConnectionSuccesfully3() {

		assertEquals(0, myMessage.testConnection("inf.ug.edu.pl"));
	}

	@Test
	public void checkTestingConnectionNotSuccesfully() {
		
		assertEquals(1, myMessage.testConnection("wp.pl.com"));
	}

	@Test
	public void checkTestingConnectionNotSuccesfully2() {

		assertEquals(1, myMessage.testConnection(".pl"));
	}
	
	@Test
	public void checkTestingConnectionNotSuccesfully3() {

		assertEquals(1, myMessage.testConnection("abecde"));
	}
	
	@Test
	public void checkSendingMessageSuccessfully() {
		
		assertEquals(0, myMessage.sendMessage("wp.pl", "dobrze"));
	}

	@Test
	public void checkSendingMessageNotSuccessfully() {
		
		assertEquals(1, myMessage.sendMessage("wp.pl.com", "wiadomosc panie"));
	}

	@Test
	public void checkSendingMessageReturnsUndefined() {

		assertEquals(2, myMessage.sendMessage("wp.pl.com", "wyjatek"));
	}

	@Test
	public void checkSendingMessageException() {

		assertEquals(2, myMessage.sendMessage("", "radek"));
	}
	
	
	

	class MessageServiceHandler implements InvocationHandler {
		@Override
		public Object invoke(Object proxy, Method method, Object[] args)
				throws Throwable {
			if ("checkConnection".equals(method.getName())) {
				if (args[0].toString().matches("[a-z].*.pl$")) {
					return ConnectionStatus.SUCCESS;
				} else {
					return ConnectionStatus.FAILURE;
				}
			}
			if ("send".equals(method.getName())) {
				if (args[0].toString().equals("wp.pl.com")
						&& args[1].toString().equals("wyjatek")) {
					throw new MalformedRecipientException();
				}
			}
			if ("send".equals(method.getName())) {
				if (args[0].toString().length() > 3) {														
					if (args[0].toString().matches("[a-z].*.pl$")) {
						return SendingStatus.SENT;
					} else if (!args[0].toString().matches("[a-z].*.pl$")) {
						return SendingStatus.SENDING_ERROR;
					}
				} else {
					throw new MalformedRecipientException();
				}
			
			}
			return 5;
		}
	}

}

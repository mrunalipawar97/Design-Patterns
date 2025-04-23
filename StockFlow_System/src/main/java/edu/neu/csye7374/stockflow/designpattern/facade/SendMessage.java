package edu.neu.csye7374.stockflow.designpattern.facade;

import edu.neu.csye7374.stockflow.designpattern.factory.CommunicationInstanceFactory;
import edu.neu.csye7374.stockflow.repository.BillingSlipRepository;
import edu.neu.csye7374.stockflow.utils.AppLogger;

public class SendMessage extends Facade{

	@Override
	protected void udpTrigger(String msg) {
		AppLogger.info("[Facade Pattern] SendMessage - udpTrigger(): " + msg);
		new CommunicationInstanceFactory().getObject().initiateInteraction(msg);
	}
	
	public static void message(String msg) {
		AppLogger.info("[Facade Pattern] SendMessage - message(): " + msg);
		SendMessage send = new SendMessage();
		send.udpTrigger(msg);
	
	}

	@Override
	protected void pdfGen(int id, BillingSlipRepository invoiceRepo) {
	}

}

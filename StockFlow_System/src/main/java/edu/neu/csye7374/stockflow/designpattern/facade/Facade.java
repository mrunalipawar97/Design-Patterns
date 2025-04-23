package edu.neu.csye7374.stockflow.designpattern.facade;

import edu.neu.csye7374.stockflow.repository.BillingSlipRepository;

public abstract class Facade {

	protected abstract void udpTrigger(String msg);
	
	protected abstract void pdfGen(int id, BillingSlipRepository invoiceRepo);
	
}

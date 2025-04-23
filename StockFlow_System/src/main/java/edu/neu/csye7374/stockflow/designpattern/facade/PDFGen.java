package edu.neu.csye7374.stockflow.designpattern.facade;

import edu.neu.csye7374.stockflow.model.BillingSlip;
import edu.neu.csye7374.stockflow.repository.BillingSlipRepository;
import edu.neu.csye7374.stockflow.utils.AppLogger;

import java.util.Optional;

public class PDFGen extends Facade{

	@Override
	protected void udpTrigger(String msg) {
		// TODO: Implement UDP trigger logic
	}

	@Override
	protected void pdfGen(int invoiceID, BillingSlipRepository invoiceRepo) {
		AppLogger.info("[Facade Pattern] PDFGen - pdfGen() Generating PDF for Invoice ID: " + invoiceID);
		Optional<BillingSlip> insertedInvoice = invoiceRepo.findById(invoiceID);
		PDFGenerator pdf = new PDFGenerator();
		pdf.generatePDF(insertedInvoice.get());
	}

	public static void pdfGenerator(int invoiceID, BillingSlipRepository invoiceRepo) {
		new PDFGen().pdfGen(invoiceID, invoiceRepo);
	}
}

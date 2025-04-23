package edu.neu.csye7374.stockflow.designpattern.facade;

import edu.neu.csye7374.stockflow.repository.BillingSlipRepository;
import edu.neu.csye7374.stockflow.utils.AppLogger;

public class FacadeClient {
    private final Facade facade;
    public FacadeClient(Facade facadeAPI) {
        this.facade = facadeAPI;
    }
    public void sendMessage(String msg) {
        AppLogger.info("[Facade Pattern] FacadeClient - sendMessage(): " + msg);
    	facade.udpTrigger(msg);
    }
    public void generatePDF(int invoiceID, BillingSlipRepository invoiceRepo) {
        AppLogger.info("[Facade Pattern] FacadeClient - generatePDF(): " + "Invoice ID: " + invoiceID);
        facade.pdfGen(invoiceID, invoiceRepo);
    }
}

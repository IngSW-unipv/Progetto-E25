package it.unipv.ingsw.model.transazioni;

import java.lang.reflect.Constructor;
import java.util.Properties;
import java.io.FileInputStream;

public class PagamentoStrategyFactory {
	
	private static PagamentoEsternoAdapter pEsternoAdapter; 
	private static PagamentoSaldoAdapter pSaldoAdapter;
	private static PagamentoPuntiAppAdapter pPuntiAdapter;
	private static final String CP_PROPERTYNAME="carta.adapter.class.name"; //Chiave del file di properties-carta
	private static final String SP_PROPERTYNAME="saldo.adapter.class.name"; //Chiave del file di properties-saldo
	private static final String PP_PROPERTYNAME="punti.adapter.class.name"; //Chiave del file di properties-puntiApp

	public static PagamentoEsternoAdapter getPagamentoEsternoAdapter(PagamentoCarta pc) {
		if(pEsternoAdapter==null) {
			String pagamentoEsternoAdaptClassName;

			try {
				Properties p = new Properties(System.getProperties());
				p.load(new FileInputStream("properties/FactoryFile"));
				pagamentoEsternoAdaptClassName=p.getProperty(CP_PROPERTYNAME); 

				Constructor c = Class.forName(pagamentoEsternoAdaptClassName).getConstructor(PagamentoCarta.class);
				pEsternoAdapter=(PagamentoEsternoAdapter)c.newInstance(pc);

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return pEsternoAdapter;
	}

	public static PagamentoSaldoAdapter getPagamentoSaldoAdapter(PagamentoSaldo ps) {
		if(pSaldoAdapter==null) {
			String pagamentoSaldoAdaptClassName;

			try {
				Properties p = new Properties(System.getProperties());
				p.load(new FileInputStream("properties/FactoryFile"));
				pagamentoSaldoAdaptClassName=p.getProperty(SP_PROPERTYNAME); 

				Constructor c = Class.forName(pagamentoSaldoAdaptClassName).getConstructor(PagamentoSaldo.class);
				pSaldoAdapter=(PagamentoSaldoAdapter)c.newInstance(ps);

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return pSaldoAdapter;
	}
	
	public static PagamentoPuntiAppAdapter getPagamentoPuntiAppAdapter(PagamentoPuntiApp ps) {
		if(pPuntiAdapter==null) {
			String pagamentoPuntiAdaptClassName;

			try {
				Properties p = new Properties(System.getProperties());
				p.load(new FileInputStream("properties/FactoryFile"));
				pagamentoPuntiAdaptClassName=p.getProperty(PP_PROPERTYNAME); 

				Constructor c = Class.forName(pagamentoPuntiAdaptClassName).getConstructor(PagamentoPuntiApp.class);
				pPuntiAdapter=(PagamentoPuntiAppAdapter)c.newInstance(ps);

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return pPuntiAdapter;
	}
}

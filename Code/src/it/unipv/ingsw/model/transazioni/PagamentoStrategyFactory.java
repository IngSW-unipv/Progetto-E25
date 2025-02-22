package it.unipv.ingsw.model.transazioni;

import java.lang.reflect.Constructor;
import java.util.Properties;
import java.io.FileInputStream;

public class PagamentoStrategyFactory {
	
	private static PagamentoEsternoAdapter pEsternoAdapter; 
	private static PagamentoSaldoAdapter pSaldoAdapter;
	private static PagamentoPuntiAppAdapter pPuntiAdapter;
	private static CompositePuntiCartaAdapter pPuntiCartaAdapter;
	private static CompositePuntiSaldoAdapter pPuntiSaldoAdapter;
	private static CompositeSaldoCartaAdapter pSaldoCartaAdapter;
	private static final String CP_PROPERTYNAME="carta.adapter.class.name"; //Chiave del file di properties-carta
	private static final String SP_PROPERTYNAME="saldo.adapter.class.name"; //Chiave del file di properties-saldo
	private static final String PP_PROPERTYNAME="punti.adapter.class.name"; //Chiave del file di properties-puntiApp
	private static final String PCP_PROPERTYNAME="punti_carta.adapter.class.name"; //Chiave del file di properties puntiApp+carta
	private static final String PSP_PROPERTYNAME="punti_saldo.adapter.class.name"; //Chiave del file di properties puntiApp+saldo
	private static final String SCP_PROPERTYNAME="saldo_carta.adapter.class.name"; //Chiave del file di properties saldo+carta
	
	public static PagamentoEsternoAdapter getPagamentoEsternoAdapter(PagamentoCarta cp) {
		if(pEsternoAdapter==null) {
			String pagamentoEsternoAdaptClassName;

			try {
				Properties p = new Properties(System.getProperties());
				p.load(new FileInputStream("properties/FactoryFile"));
				pagamentoEsternoAdaptClassName=p.getProperty(CP_PROPERTYNAME); 

				Constructor c = Class.forName(pagamentoEsternoAdaptClassName).getConstructor(PagamentoCarta.class);
				pEsternoAdapter=(PagamentoEsternoAdapter)c.newInstance(cp);

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return pEsternoAdapter;
	}

	public static PagamentoSaldoAdapter getPagamentoSaldoAdapter(PagamentoSaldo sp) {
		if(pSaldoAdapter==null) {
			String pagamentoSaldoAdaptClassName;

			try {
				Properties p = new Properties(System.getProperties());
				p.load(new FileInputStream("properties/FactoryFile"));
				pagamentoSaldoAdaptClassName=p.getProperty(SP_PROPERTYNAME); 

				Constructor c = Class.forName(pagamentoSaldoAdaptClassName).getConstructor(PagamentoSaldo.class);
				pSaldoAdapter=(PagamentoSaldoAdapter)c.newInstance(sp);

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return pSaldoAdapter;
	}
	
	public static PagamentoPuntiAppAdapter getPagamentoPuntiAppAdapter(PagamentoPuntiApp pp) {
		if(pPuntiAdapter==null) {
			String pagamentoPuntiAdaptClassName;

			try {
				Properties p = new Properties(System.getProperties());
				p.load(new FileInputStream("properties/FactoryFile"));
				pagamentoPuntiAdaptClassName=p.getProperty(PP_PROPERTYNAME); 

				Constructor c = Class.forName(pagamentoPuntiAdaptClassName).getConstructor(PagamentoPuntiApp.class);
				pPuntiAdapter=(PagamentoPuntiAppAdapter)c.newInstance(pp);

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return pPuntiAdapter;
	}
	
	public static CompositePuntiCartaAdapter getPagamentoPuntiCartaAdapter(CompositePuntiCarta pcp) {
		if(pPuntiCartaAdapter==null) {
			String pagamentoPuntiCartaAdaptClassName;

			try {
				Properties p = new Properties(System.getProperties());
				p.load(new FileInputStream("properties/FactoryFile"));
				pagamentoPuntiCartaAdaptClassName=p.getProperty(PCP_PROPERTYNAME); 

				Constructor c = Class.forName(pagamentoPuntiCartaAdaptClassName).getConstructor(CompositePuntiCarta.class);
				pPuntiCartaAdapter=(CompositePuntiCartaAdapter)c.newInstance(pcp);

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return pPuntiCartaAdapter;
	}
	
	public static CompositePuntiSaldoAdapter getPagamentoPuntiSaldoAdapter(CompositePuntiSaldo psp) {
		if(pPuntiSaldoAdapter==null) {
			String pagamentoPuntiSaldoAdaptClassName;

			try {
				Properties p = new Properties(System.getProperties());
				p.load(new FileInputStream("properties/FactoryFile"));
				pagamentoPuntiSaldoAdaptClassName=p.getProperty(PSP_PROPERTYNAME); 

				Constructor c = Class.forName(pagamentoPuntiSaldoAdaptClassName).getConstructor(CompositePuntiSaldo.class);
				pPuntiSaldoAdapter=(CompositePuntiSaldoAdapter)c.newInstance(psp);

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return pPuntiSaldoAdapter;
	}
	
	public static CompositeSaldoCartaAdapter getPagamentoSaldoCartaAdapter(CompositeSaldoCarta scp) {
		if(pSaldoCartaAdapter==null) {
			String pagamentoSaldoCartaAdaptClassName;

			try {
				Properties p = new Properties(System.getProperties());
				p.load(new FileInputStream("properties/FactoryFile"));
				pagamentoSaldoCartaAdaptClassName=p.getProperty(SCP_PROPERTYNAME); 

				Constructor c = Class.forName(pagamentoSaldoCartaAdaptClassName).getConstructor(CompositeSaldoCarta.class);
				pSaldoCartaAdapter=(CompositeSaldoCartaAdapter)c.newInstance(scp);

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return pSaldoCartaAdapter;
	}
}

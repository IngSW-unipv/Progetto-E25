package it.unipv.ingsw.test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import it.unipv.ingsw.exceptions.PaymentException;
import it.unipv.ingsw.model.transazioni.Pagamento;
import it.unipv.ingsw.model.transazioni.PagamentoCarta;
import it.unipv.ingsw.model.transazioni.PagamentoStrategyFactory;

public class TestPagamenti {
	
	private Pagamento pagamento;

	@Before
	public void initTest() {
	}

	@Test
	public void testPagamentoSuccess() throws PaymentException {
		double amount=1;
		int punti=1;
		pagamento=new Pagamento(PagamentoStrategyFactory.getPagamentoEsternoAdapter(new PagamentoCarta()));
		assertTrue(pagamento.provaPagamento(amount,punti, PagamentoStrategyFactory.getPagamentoEsternoAdapter(new PagamentoCarta()), null));
	}
	@Test
	public void testPagamentoFailDenaro() throws PaymentException { //importo negativo
		double amount=-1;
		int punti=1;
		pagamento=new Pagamento(PagamentoStrategyFactory.getPagamentoEsternoAdapter(new PagamentoCarta()));
		assertThrows(PaymentException.class, () -> {
			pagamento.provaPagamento(amount,punti, PagamentoStrategyFactory.getPagamentoEsternoAdapter(new PagamentoCarta()), null);
		});
	}
	@Test
	public void testPagamentoFailPunti() throws PaymentException { //punti negativi
		double amount=1;
		int punti=-1;
		pagamento=new Pagamento(PagamentoStrategyFactory.getPagamentoEsternoAdapter(new PagamentoCarta()));
		assertThrows(PaymentException.class, () -> {
			pagamento.provaPagamento(amount,punti, PagamentoStrategyFactory.getPagamentoEsternoAdapter(new PagamentoCarta()), null);
			});
		}
}

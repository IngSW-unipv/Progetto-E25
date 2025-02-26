package it.unipv.ingsw.dao;

import java.util.List;

import it.unipv.ingsw.model.spedizione.Coordinate;
import it.unipv.ingsw.model.spedizione.puntoDeposito.IPuntoDeposito;

public interface IPuntoDepositoDAO {

	List<IPuntoDeposito> selectAll();
	IPuntoDeposito selectPuntoDeposito(Coordinate c);

}

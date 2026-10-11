package gymApp.dao;

import java.util.List;

import gymApp.model.Rutina;

public interface IRutinaDAO {
    
	boolean guardar(Rutina rutina);
    Rutina obtenerPorDia(String dia);
    List<Rutina> obtenerTodas();
    boolean eliminar(String dia);
}
package gymApp.dao;

import gymApp.model.Usuario;

public interface IUsuarioDAO {
   
	Usuario obtener();
    boolean guardar(Usuario usuario);
}

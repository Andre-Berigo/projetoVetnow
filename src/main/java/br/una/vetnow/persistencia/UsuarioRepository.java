
   
package br.una.vetnow.persistencia;



import java.util.List;

import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.una.vetnow.entidades.Usuario;



/**
 * Esta classe estende o padrão CrudRepository 
 * @author jhcru
 *
 */
@EnableScan()
@Repository
public interface UsuarioRepository extends CrudRepository<Usuario, String> {
	List<Usuario> findByNome(String nome);
 }

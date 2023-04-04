
   
package br.una.vetnow.persistencia;



import java.util.List;

import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.una.vetnow.entidades.Veterinario;



/**
 * Esta classe estende o padrão CrudRepository 
 * @author jhcru
 *
 */
@EnableScan()
@Repository
public interface VeterinarioRepository extends CrudRepository<Veterinario, String> {
	List<Veterinario> findByNome(String nome);
 }
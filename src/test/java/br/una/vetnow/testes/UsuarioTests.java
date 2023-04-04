package br.una.vetnow.testes;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.text.ParseException;
import java.util.List;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.context.PropertyPlaceholderAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.una.vetnow.entidades.Usuario;
import br.una.vetnow.persistencia.DynamoDBConfig;
import br.una.vetnow.persistencia.UsuarioRepository;




/**
 * Classe de testes para a entidade Usuario.
 *  <br>
 * Para rodar, antes sete a seguinte variável de ambiente: -Dspring.config.location=C:/Users/jhcru/sdm/
 *  <br>
 * Neste diretório, criar um arquivo application.properties contendo as seguitnes variáveis:
 * <br>
 * amazon.aws.accesskey=<br>
 * amazon.aws.secretkey=<br>
 * @author jhcru
 *
 */

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {PropertyPlaceholderAutoConfiguration.class, DynamoDBConfig.class})
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UsuarioTests {

    private static Logger LOGGER = LoggerFactory.getLogger(UsuarioTests.class);
    
	@Autowired
	private UsuarioRepository repository;

	@Test
	public void teste1Criacao() throws ParseException {
		LOGGER.info("Criando objetos...");
		Usuario c1 = new Usuario("Carlos", "888.888.888-30", "rua 66 casa 66", "carlos@gmail.com", "31 88888-8888");
		Usuario c2 = new Usuario("Joana", "888.888.888-30", "rua 77 casa 77", "joana@gmail.com", "31 88888-8888");
        Usuario c3 = new Usuario("Fernanda", "888.888.888-30", "rua 88 casa 88", "Fernanda@gmail.com", "31 88888-8888");
        repository.save(c1);
		repository.save(c2);
		repository.save(c3);
		
		Iterable<Usuario> lista = repository.findAll();
		assertNotNull(lista.iterator());
		for (Usuario Usuario : lista) {
			LOGGER.info(Usuario.toString());
		}
		LOGGER.info("Pesquisado um objeto");
		List<Usuario> result = repository.findByNome("Carlos");
		assertEquals(result.size(), 1);
		LOGGER.info("Encontrado: {}", result.size());
	}
	
	@Test
	public void teste2Exclusao() throws ParseException {
		LOGGER.info("Excluindo objetos...");
		Iterable<Usuario> result = repository.findAll();
		for (Usuario Usuario : result) {
			LOGGER.info("Excluindo Usuario id = "+Usuario.getId());
			repository.delete(Usuario);
		}
		result = repository.findAll();
		assertEquals(result.iterator().hasNext(), false);
		LOGGER.info("Exclusão feita com sucesso");
	}
	
	
}

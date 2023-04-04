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

import br.una.vetnow.entidades.Veterinario;
import br.una.vetnow.persistencia.DynamoDBConfig;
import br.una.vetnow.persistencia.VeterinarioRepository;



/**
 * Classe de testes para a entidade Veterinario.
 * <br>
 * Para rodar, antes sete a seguinte variável de ambiente:
 * -Dspring.config.location=C:/Users/jhcru/sdm/
 * <br>
 * Neste diretório, criar um arquivo application.properties contendo as
 * seguitnes variáveis:
 * <br>
 * amazon.aws.accesskey=<br>
 * amazon.aws.secretkey=<br>
 * 
 * @author jhcru
 *
 */

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { PropertyPlaceholderAutoConfiguration.class, DynamoDBConfig.class })
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class VeterinarioTests {

    private static Logger LOGGER = LoggerFactory.getLogger(VeterinarioTests.class);

    @Autowired
    private VeterinarioRepository repository;

    @Test
    public void teste1Criacao() throws ParseException {
        LOGGER.info("Criando objetos...");
        Veterinario c1 = new Veterinario("Joao", "999.999.999-01", "31 99999-9999", "joao@gmail.com", "99999", "cao");
        Veterinario c2 = new Veterinario("Jose", "999.999.999-02", "31 99999-9999", "jose@gmail.com", "99999", "gato");
        Veterinario c3 = new Veterinario("Maria", "999.999.999-03", "31 99999-9999", "maria@gmail.com", "99999", "ave");
        repository.save(c1);
        repository.save(c2);
        repository.save(c3);

        Iterable<Veterinario> lista = repository.findAll();
        assertNotNull(lista.iterator());
        for (Veterinario Veterinario : lista) {
            LOGGER.info(Veterinario.toString());
        }
        LOGGER.info("Pesquisado um objeto");
        List<Veterinario> result = repository.findByNome("Jose");
        assertEquals(result.size(), 1);
        assertEquals(result.get(0).getCpf(), "999.999.999-02");
        LOGGER.info("Encontrado: {}", result.size());
    }

    @Test
    public void teste2Exclusao() throws ParseException {
        LOGGER.info("Excluindo objetos...");
        Iterable<Veterinario> result = repository.findAll();
        for (Veterinario Veterinario : result) {
            LOGGER.info("Excluindo Veterinario id = " + Veterinario.getId());
            repository.delete(Veterinario);
        }
        result = repository.findAll();
        assertEquals(result.iterator().hasNext(), false);
        LOGGER.info("Exclusão feita com sucesso");
    }

}

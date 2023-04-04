package br.una.vetnow.negocio;

import java.lang.invoke.MethodHandles;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.commons.collections4.IteratorUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import br.una.vetnow.entidades.Veterinario;
import br.una.vetnow.persistencia.VeterinarioRepository;



/**
 * Classe contendo a lógica de negócio para Veterinario
 * @author jhcru
 *
 */
@Service
public class VeterinarioService {

    private static final Logger logger= LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    
    private final VeterinarioRepository veterinarioRepo;

    public VeterinarioService(VeterinarioRepository veterinarioRepository){
        this.veterinarioRepo=veterinarioRepository;
    }
    
    public List<Veterinario> getVeterinarios(){
        if(logger.isInfoEnabled()){
            logger.info("Buscando todos os objetos");
        }
        Iterable<Veterinario> lista = this.veterinarioRepo.findAll();
        if (lista == null) {
        	return new ArrayList<Veterinario>();
        }
        return IteratorUtils.toList(lista.iterator());
    }

    public Veterinario getVeterinarioById(String id){
        if(logger.isInfoEnabled()){
            logger.info("Buscando Veterinario com o codigo {}",id);
        }
        Optional<Veterinario> retorno = this.veterinarioRepo.findById(id);
        if(!retorno.isPresent()){
            throw new RuntimeException("Veterinario com o codigo "+id+" nao encontrada");
        }
        return retorno.get();
    }
    
    public Veterinario getVeterinarioByNome(String nome){
        if(logger.isInfoEnabled()){
            logger.info("Buscando Veterinario com o nome {}",nome);
        }
        List<Veterinario> lista = this.veterinarioRepo.findByNome(nome);
        if(lista == null || lista.isEmpty()){
            throw new RuntimeException("Veterinario com o nome "+nome+" nao encontrada");
        }
        return lista.get(0);
    }

    public Veterinario saveVeterinario(Veterinario veterinario){
        if(logger.isInfoEnabled()){
            logger.info("Salvando Veterinario com os detalhes {}",veterinario.toString());
        }
        return this.veterinarioRepo.save(veterinario);
    }
    
    public void deleteVeterinario(String id){
        if(logger.isInfoEnabled()){
            logger.info("Excluindo Veterinario com id {}",id);
        }
        this.veterinarioRepo.deleteById(id);
    }

    public boolean isVeterinarioExists(Veterinario veterinario){
    	Optional<Veterinario> retorno = this.veterinarioRepo.findById(veterinario.getId());
        return retorno.isPresent() ? true:  false;
    }

    public boolean isVeterinarioExists(String id){
    	Optional<Veterinario> retorno = this.veterinarioRepo.findById(id);
        return retorno.isPresent() ? true:  false;
    }
}

package br.una.vetnow.negocio;

import java.lang.invoke.MethodHandles;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.commons.collections4.IteratorUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import br.una.vetnow.entidades.Usuario;
import br.una.vetnow.persistencia.UsuarioRepository;



/**
 * Classe contendo a lógica de negócio para Cotação
 * @author jhcru
 *
 */
@Service
public class UsuarioService {

    private static final Logger logger= LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    
    private final UsuarioRepository usuarioRepo;

    public UsuarioService(UsuarioRepository usuarioRepository){
        this.usuarioRepo=usuarioRepository;
    }
    
    public List<Usuario> getUsuarios(){
        if(logger.isInfoEnabled()){
            logger.info("Buscando todos os objetos");
        }
        Iterable<Usuario> lista = this.usuarioRepo.findAll();
        if (lista == null) {
        	return new ArrayList<Usuario>();
        }
        return IteratorUtils.toList(lista.iterator());
    }    

    public Usuario getUsuarioById(String id){
        if(logger.isInfoEnabled()){
            logger.info("Buscando Usuario com o codigo {}",id);
        }
        Optional<Usuario> retorno = this.usuarioRepo.findById(id);
        if(!retorno.isPresent()){
            throw new RuntimeException("Usuario com o id "+id+" nao encontrada");
        }
        return retorno.get();
    }
    
    public List<Usuario> getUsuarioByNome(String nome){
    	if(logger.isInfoEnabled()){
            logger.info("Buscando todos os objetos");
        }
        Iterable<Usuario> lista = this.usuarioRepo.findByNome("nome");
        if (lista == null) {
        	return new ArrayList<Usuario>();
        }
        return IteratorUtils.toList(lista.iterator());
    }
    
    public Usuario saveUsuario(Usuario usuario){
        if(logger.isInfoEnabled()){
            logger.info("Salvando Usuario com os detalhes {}",usuario.toString());
        }
        return this.usuarioRepo.save(usuario);
    }
    
    public void deleteUsuario(String id){
        if(logger.isInfoEnabled()){
            logger.info("Excluindo Usuario com id {}",id);
        }
        this.usuarioRepo.deleteById(id);
    }

    public boolean isUsuarioExists(Usuario usuario){
    	Optional<Usuario> retorno = this.usuarioRepo.findById(usuario.getId());
        return retorno.isPresent() ? true:  false;
    }

}

package br.una.vetnow.rest;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.una.vetnow.entidades.Veterinario;
import br.una.vetnow.negocio.VeterinarioService;


/**
 * Classe contendo as definições de serviços REST/JSON para Veterinario
 * @author jhcru
 *
 */
@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE, path = "Veterinario")
public class VeterinarioController {
   
    private final VeterinarioService veterinarioService;

    public VeterinarioController(VeterinarioService veterinarioService){
        this.veterinarioService=veterinarioService;
    }

    @GetMapping(value = "")
    public List<Veterinario> getVeterinarios(){
        return veterinarioService.getVeterinarios();
    }
    
    @GetMapping(value="{id}")
    public Veterinario getVeterinarioById(@PathVariable String id) throws Exception{
        if(!ObjectUtils.isEmpty(id)){
           return veterinarioService.getVeterinarioById(id);
        }
        throw new Exception("Veterinario com codigo "+id+" nao encontrada");
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Veterinario createVeterinario(@RequestBody @NotNull Veterinario veterinario) throws Exception {
         return veterinarioService.saveVeterinario(veterinario);
    }
    
    @PutMapping(value = "{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Veterinario updateVeterinario(@PathVariable String id, 
    		@RequestBody @NotNull Veterinario veterinario) throws Exception {
         return veterinarioService.saveVeterinario(veterinario);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping(value = "{id}")
    public boolean updateVeterinario(@PathVariable String id) throws Exception {
         veterinarioService.deleteVeterinario(id);
         return true;
    }
    
}

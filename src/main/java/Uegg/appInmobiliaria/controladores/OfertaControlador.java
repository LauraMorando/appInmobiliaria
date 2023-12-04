package Uegg.appInmobiliaria.controladores;

import Uegg.appInmobiliaria.entidades.Oferta;
import Uegg.appInmobiliaria.excepciones.MyException;
import Uegg.appInmobiliaria.repositorios.OfertaRepositorio;
import Uegg.appInmobiliaria.servicios.OfertaServicio;
import Uegg.appInmobiliaria.servicios.inmuebleServicio;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author Gimenez Victor
 */
@Controller
@RequestMapping("/oferta")
public class OfertaControlador {

    @Autowired
    private OfertaServicio ofertaServicio;

    @PostMapping("/hacer")
    public String hacerOferta() {
        return "oferta_form";
    }

    @GetMapping("/enviar")
    public String enviarOferta(
            @RequestParam Double montoOferta,
            @RequestParam String idInmueble,
            @RequestParam String idCliente,
            ModelMap modelo
    ) throws MyException {
        try {
            ofertaServicio.crearOfertaCliente(montoOferta, idInmueble, idCliente);
            modelo.put("exito", "Oferta enviada. Espere respuesta del Dueño de la propiedad");
            return "inmuebleList.html";
        } catch (MyException ex) {
            modelo.put("error", ex.getMessage());
            modelo.put("montoOferta", montoOferta);
            modelo.put("idInmueble", idInmueble);
            modelo.put("idCliente", idCliente);
            return "oferta_form.html";
        }
    }
    
    @GetMapping("/inmueble/{idInmueble}")
    public String listarOfertasInmuebles(
            @PathVariable String idInmueble,
            ModelMap modelo
    ){
        List<Oferta> ofertas = ofertaServicio.lsitarOfertasInmueble(idInmueble);
        Oferta oferta = ofertaServicio.mejorOferta(idInmueble);
        modelo.addAttribute(oferta);
        modelo.addAttribute("ofertas", ofertas);
        return "/oferta_inmueble_list.html";
    }
    
    @GetMapping("/cliente/{idCliente}")
    public String listarOfertasClientes(
            @PathVariable String idCliente,
            ModelMap modelo
    ){
        List<Oferta> ofertas =ofertaServicio.listarOfertaCliente(idCliente);
        modelo.addAttribute("ofertas", ofertas);
        return "/oferta_cliente_list.html";
    }
}

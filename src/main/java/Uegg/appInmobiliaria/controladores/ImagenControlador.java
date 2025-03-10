package Uegg.appInmobiliaria.controladores;

import Uegg.appInmobiliaria.entidades.Imagen;
import Uegg.appInmobiliaria.entidades.Usuario;
import Uegg.appInmobiliaria.excepciones.MyException;
import Uegg.appInmobiliaria.servicios.ImagenServicio;
import Uegg.appInmobiliaria.servicios.InmuebleServicio;
import Uegg.appInmobiliaria.servicios.UsuarioServicio;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/imagen")
public class ImagenControlador {

    @Autowired
    UsuarioServicio usuarioServicio;

    @Autowired
    ImagenServicio imagenServicio;

    @Autowired
    InmuebleServicio inmuebleServicio;

    @GetMapping("/perfil/{id}")
    public ResponseEntity<byte[]> imagenUsuario(@PathVariable String id) {

        Usuario usuario = usuarioServicio.getOne(id);

        byte[] imagen = usuario.getImagen().getContenido();

        HttpHeaders headers = new HttpHeaders();

        headers.setContentType(MediaType.IMAGE_JPEG);

        return new ResponseEntity<>(imagen, headers, HttpStatus.OK);
    }
    
/*
    @GetMapping("/lista/inmueble/{inmueble_id}")
    public List<Imagen> listaImagenesPorIdInmueble(@PathVariable String inmueble_id, ModelMap model) {
        List<Imagen> imagenes = imagenServicio.obtenerImagenesPorInmueble(inmueble_id);
        model.addAttribute("imagenes", imagenes);
        return imagenes;
    }
*/
    
    @GetMapping("/inmueble/{id}")
    public ResponseEntity<byte[]> imagenInmueble(@PathVariable String id) {

        Imagen img = imagenServicio.getOne(id);

        byte[] imagen = img.getContenido();

        HttpHeaders headers = new HttpHeaders();

        headers.setContentType(MediaType.IMAGE_JPEG);

        return new ResponseEntity<>(imagen, headers, HttpStatus.OK);
    }
    
}

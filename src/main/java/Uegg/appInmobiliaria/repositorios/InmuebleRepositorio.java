
package Uegg.appInmobiliaria.repositorios;

import Uegg.appInmobiliaria.entidades.Inmueble;
import Uegg.appInmobiliaria.enums.Tipo;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface InmuebleRepositorio extends JpaRepository<Inmueble, String>{
    
   
    @Query("SELECT l FROM Inmueble l WHERE l.tipo = :tipo AND l.tipoOferta = :tipoOferta")
    public List<Inmueble> buscarPorTipo(@Param("tipo") Tipo tipo, @Param("tipoOferta") String tipoOferta);
    
    @Query("SELECT l FROM Inmueble l WHERE l.ambientes = :ambientes")
    public List<Inmueble> buscarPorAmbientes(@Param("ambientes") Integer ambientes);
    
    @Query("SELECT l FROM Inmueble l WHERE l.usuarioEnte.id = :id")
    public List<Inmueble> buscarPorEnte(@Param("id") String id);
    
     @Query("SELECT l FROM Inmueble l WHERE l.tipo = :tipo AND l.tipoOferta = :venta AND l.ambientes = :ambientes AND l.precioVenta BETWEEN minPrecioVenta AND maxPrecioVenta" )
    public List<Inmueble> busquedaPersonalizadaVenta (@Param ("tipo") Tipo tipo, @Param("ambientes")  Integer ambientes,
            @Param("maxPrecioVenta") Double maxprecioVenta,@Param("minPrecioVenta")  Double minprecioVenta );
}

    
    
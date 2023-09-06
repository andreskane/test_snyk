package ar.elea.apx.backend.projection;

import ar.elea.apx.backend.entity.TransferType;

import java.sql.Date;


public interface TranferenciaProjection {
     Long getId();
     String getDoctorNombre();
     String getDoctorApellido();
     String getNombreApmOrigen();
     String getApellidoApmOrigen();
     String getNombreApmDestino();
     String getApellidoApmDestino();
     String getNombreUsuario();
     String getApellidoUsuario();
     Date getFecha();
     Date getFechaProgramada();
     TransferType getTipo();
     String getComentario();
}

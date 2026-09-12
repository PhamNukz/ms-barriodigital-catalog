package cl.duoc.barriodigital.catalog.repo;

import cl.duoc.barriodigital.catalog.domain.TipoTramite;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TipoTramiteRepository extends JpaRepository<TipoTramite, Long> {

    /** El nombre es unique en BD; se consulta antes de insertar para poder dar un mensaje claro. */
    boolean existsByNombreIgnoreCase(String nombre);
}

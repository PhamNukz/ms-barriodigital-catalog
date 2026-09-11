package cl.duoc.barriodigital.catalog.service;

import cl.duoc.barriodigital.catalog.domain.TipoTramite;
import cl.duoc.barriodigital.catalog.repo.TipoTramiteRepository;
import cl.duoc.barriodigital.catalog.web.TipoTramiteDtos.ActualizarRequest;
import cl.duoc.barriodigital.catalog.web.TipoTramiteDtos.CrearRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class TipoTramiteService {

    private final TipoTramiteRepository repo;

    public TipoTramiteService(TipoTramiteRepository repo) {
        this.repo = repo;
    }

    @Transactional(readOnly = true)
    public List<TipoTramite> listar() {
        return repo.findAll();
    }

    @Transactional(readOnly = true)
    public TipoTramite obtener(Long id) {
        return repo.findById(id).orElseThrow(() -> new NoSuchElementException("Tipo de tramite " + id + " no existe"));
    }

    @Transactional
    public TipoTramite crear(CrearRequest req) {
        return repo.save(new TipoTramite(req.nombre(), req.requisitos(), req.cupoDiario()));
    }

    @Transactional
    public TipoTramite actualizar(Long id, ActualizarRequest req) {
        TipoTramite tipo = obtener(id);
        tipo.actualizar(req.requisitos(), req.cupoDiario(), req.activo());
        return tipo;
    }
}

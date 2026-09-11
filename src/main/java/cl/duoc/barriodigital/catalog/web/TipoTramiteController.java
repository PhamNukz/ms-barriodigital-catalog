package cl.duoc.barriodigital.catalog.web;

import cl.duoc.barriodigital.catalog.service.TipoTramiteService;
import cl.duoc.barriodigital.catalog.web.TipoTramiteDtos.ActualizarRequest;
import cl.duoc.barriodigital.catalog.web.TipoTramiteDtos.CrearRequest;
import cl.duoc.barriodigital.catalog.web.TipoTramiteDtos.Response;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/catalog/procedures")
public class TipoTramiteController {

    private final TipoTramiteService service;

    public TipoTramiteController(TipoTramiteService service) {
        this.service = service;
    }

    /** Cualquier usuario autenticado (Admin, Funcionario o Vecino) consulta el catalogo. */
    @GetMapping
    public List<Response> listar() {
        return service.listar().stream().map(Response::from).toList();
    }

    @GetMapping("/{id}")
    public Response obtener(@PathVariable Long id) {
        return Response.from(service.obtener(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasRole('Admin')")
    public Response crear(@Valid @RequestBody CrearRequest req) {
        return Response.from(service.crear(req));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('Admin')")
    public Response actualizar(@PathVariable Long id, @Valid @RequestBody ActualizarRequest req) {
        return Response.from(service.actualizar(id, req));
    }
}

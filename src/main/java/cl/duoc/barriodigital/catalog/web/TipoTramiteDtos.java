package cl.duoc.barriodigital.catalog.web;

import cl.duoc.barriodigital.catalog.domain.TipoTramite;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public final class TipoTramiteDtos {

    private TipoTramiteDtos() {
    }

    public record CrearRequest(
            @NotBlank String nombre,
            String requisitos,
            @NotNull @Min(0) Integer cupoDiario) {
    }

    public record ActualizarRequest(
            String requisitos,
            @NotNull @Min(0) Integer cupoDiario,
            boolean activo) {
    }

    public record Response(Long id, String nombre, String requisitos, Integer cupoDiario, boolean activo) {
        public static Response from(TipoTramite t) {
            return new Response(t.getId(), t.getNombre(), t.getRequisitos(), t.getCupoDiario(), t.isActivo());
        }
    }
}

package cl.duoc.barriodigital.catalog.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "tipo_tramite")
public class TipoTramite {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 120, unique = true)
    private String nombre;

    @Column(length = 1000)
    private String requisitos;

    @Column(name = "cupo_diario", nullable = false)
    private Integer cupoDiario;

    @Column(nullable = false)
    private boolean activo = true;

    protected TipoTramite() {
    }

    public TipoTramite(String nombre, String requisitos, Integer cupoDiario) {
        this.nombre = nombre;
        this.requisitos = requisitos;
        this.cupoDiario = cupoDiario;
    }

    public Long getId() { return id; }
    public String getNombre() { return nombre; }
    public String getRequisitos() { return requisitos; }
    public Integer getCupoDiario() { return cupoDiario; }
    public boolean isActivo() { return activo; }

    public void actualizar(String requisitos, Integer cupoDiario, boolean activo) {
        this.requisitos = requisitos;
        this.cupoDiario = cupoDiario;
        this.activo = activo;
    }
}

package entities;

import pojos.Recaudacion;
import pojos.RecaudacionIngreso;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "recaudaciones")
public class RecaudacionesEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_licencia", referencedColumnName = "id")
    private LicenciasEntity licenciasEntity;

    @OneToMany(mappedBy = "recaudacionesEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RecaudacionIngresosEntity> recaudacionIngresosEntities;

    @Column(name = "servicios_inicio")
    private Integer serviciosInicio;

    @Column(name = "servicions_fin")
    private Integer servicionsFin;

    @Column(name = "numeracion_inicio")
    private BigDecimal numeracionInicio;

    @Column(name = "numeracion_fin")
    private BigDecimal numeracionFin;

    @Column(name = "km_totales_inicio")
    private Integer kmTotalesInicio;

    @Column(name = "km_totales_fin")
    private Integer kmTotalesFin;

    @Column(name = "km_cargado_inicio")
    private Integer kmCargadoInicio;

    @Column(name = "km_cargado_fin")
    private Integer kmCargadoFin;

    @Column(name = "mes")
    private Integer mes;

    @Column(name = "ano")
    private Integer ano;

    @Column(name = "creado")
    @Temporal(TemporalType.TIMESTAMP)
    private Date creado;

    @Column(name = "actualizado")
    @Temporal(TemporalType.TIMESTAMP)
    private Date actualizado;

    public RecaudacionesEntity() {
    }

    public RecaudacionesEntity(Recaudacion recaudacion) {
        this.id = recaudacion.getId();
        this.licenciasEntity = new LicenciasEntity(recaudacion.getLicencia());

        this.recaudacionIngresosEntities = new ArrayList<>();
        if (recaudacion.getRecaudacionIngresos() != null) {
            for (RecaudacionIngreso recaudacionIngreso : recaudacion.getRecaudacionIngresos()) {
                RecaudacionIngresosEntity recaudacionIngresosEntity = new RecaudacionIngresosEntity(recaudacionIngreso);
                this.recaudacionIngresosEntities.add(recaudacionIngresosEntity);
            }
        }

        this.serviciosInicio = recaudacion.getServiciosInicio();
        this.servicionsFin = recaudacion.getServicionsFin();
        this.numeracionInicio = recaudacion.getNumeracionInicio();
        this.numeracionFin = recaudacion.getNumeracionFin();
        this.kmTotalesInicio = recaudacion.getKmTotalesInicio();
        this.kmTotalesFin = recaudacion.getKmTotalesFin();
        this.kmCargadoInicio = recaudacion.getKmCargadoInicio();
        this.kmCargadoFin = recaudacion.getKmCargadoFin();
        this.mes = recaudacion.getMes();
        this.ano = recaudacion.getAno();
        this.creado = recaudacion.getCreado();
        this.actualizado = recaudacion.getActualizado();
    }

    @PrePersist
    public void prePersist() {
        creado = new Date();
        actualizado = new Date();
    }

    @PreUpdate
    public void preUpdate() {
        actualizado = new Date();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RecaudacionesEntity that = (RecaudacionesEntity) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(serviciosInicio, that.serviciosInicio) &&
                Objects.equals(servicionsFin, that.servicionsFin) &&
                Objects.equals(numeracionInicio, that.numeracionInicio) &&
                Objects.equals(numeracionFin, that.numeracionFin) &&
                Objects.equals(kmTotalesInicio, that.kmTotalesInicio) &&
                Objects.equals(kmTotalesFin, that.kmTotalesFin) &&
                Objects.equals(kmCargadoInicio, that.kmCargadoInicio) &&
                Objects.equals(kmCargadoFin, that.kmCargadoFin) &&
                Objects.equals(mes, that.mes) &&
                Objects.equals(ano, that.ano) &&
                Objects.equals(creado, that.creado) &&
                Objects.equals(actualizado, that.actualizado);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, serviciosInicio, servicionsFin, numeracionInicio, numeracionFin, kmTotalesInicio, kmTotalesFin, kmCargadoInicio, kmCargadoFin, mes, ano, creado, actualizado);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LicenciasEntity getLicenciasEntity() {
        return licenciasEntity;
    }

    public void setLicenciasEntity(LicenciasEntity licenciasEntity) {
        this.licenciasEntity = licenciasEntity;
    }

    public List<RecaudacionIngresosEntity> getRecaudacionIngresosEntities() {
        return recaudacionIngresosEntities;
    }

    public void setRecaudacionIngresosEntities(List<RecaudacionIngresosEntity> recaudacionIngresosEntities) {
        this.recaudacionIngresosEntities = recaudacionIngresosEntities;
    }

    public Integer getServiciosInicio() {
        return serviciosInicio;
    }

    public void setServiciosInicio(Integer serviciosInicio) {
        this.serviciosInicio = serviciosInicio;
    }

    public Integer getServicionsFin() {
        return servicionsFin;
    }

    public void setServicionsFin(Integer servicionsFin) {
        this.servicionsFin = servicionsFin;
    }

    public BigDecimal getNumeracionInicio() {
        return numeracionInicio;
    }

    public void setNumeracionInicio(BigDecimal numeracionInicio) {
        this.numeracionInicio = numeracionInicio;
    }

    public BigDecimal getNumeracionFin() {
        return numeracionFin;
    }

    public void setNumeracionFin(BigDecimal numeracionFin) {
        this.numeracionFin = numeracionFin;
    }

    public Integer getKmTotalesInicio() {
        return kmTotalesInicio;
    }

    public void setKmTotalesInicio(Integer kmTotalesInicio) {
        this.kmTotalesInicio = kmTotalesInicio;
    }

    public Integer getKmTotalesFin() {
        return kmTotalesFin;
    }

    public void setKmTotalesFin(Integer kmTotalesFin) {
        this.kmTotalesFin = kmTotalesFin;
    }

    public Integer getKmCargadoInicio() {
        return kmCargadoInicio;
    }

    public void setKmCargadoInicio(Integer kmCargadoInicio) {
        this.kmCargadoInicio = kmCargadoInicio;
    }

    public Integer getKmCargadoFin() {
        return kmCargadoFin;
    }

    public void setKmCargadoFin(Integer kmCargadoFin) {
        this.kmCargadoFin = kmCargadoFin;
    }

    public Integer getMes() {
        return mes;
    }

    public void setMes(Integer mes) {
        this.mes = mes;
    }

    public Integer getAno() {
        return ano;
    }

    public void setAno(Integer ano) {
        this.ano = ano;
    }

    public Date getCreado() {
        return creado;
    }

    public void setCreado(Date creado) {
        this.creado = creado;
    }

    public Date getActualizado() {
        return actualizado;
    }

    public void setActualizado(Date actualizado) {
        this.actualizado = actualizado;
    }
}

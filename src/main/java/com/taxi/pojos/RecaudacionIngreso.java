package com.taxi.pojos;

import com.taxi.entities.RecaudacionIngresosEntity;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;
import java.util.stream.Collectors;

import static com.taxi.utils.BigDecimalUtils.ensureNotNull;

public class RecaudacionIngreso implements Identified {

    private Long id;
    private Recaudacion recaudacionObj;
    private Conductor conductor;
    private EstadosIngreso estado;
    private Integer dia;
    private String turno;
    private BigDecimal numeracion;
    private BigDecimal anulados;
    private BigDecimal recaudacion;
    private BigDecimal liquido;
    private BigDecimal tarjeta;
    private BigDecimal pagos;
    private BigDecimal efectivo;
    private BigDecimal app;
    private BigDecimal t;
    private String observaciones;
    private Date creado;
    private Date actualizado;

    public RecaudacionIngreso() {
    }

    public RecaudacionIngreso(RecaudacionIngresosEntity recaudacionIngresosEntity) {
        this.id = recaudacionIngresosEntity.getId();
        this.recaudacionObj = null;
        this.conductor = new Conductor(recaudacionIngresosEntity.getConductorEntity());
        this.estado = recaudacionIngresosEntity.getRecaudacionesIngresosEstadosEntity() != null ?
                new EstadosIngreso(recaudacionIngresosEntity.getRecaudacionesIngresosEstadosEntity()) : new EstadosIngreso();
        this.dia = recaudacionIngresosEntity.getDia();
        this.turno = recaudacionIngresosEntity.getTurno();
        this.numeracion = ensureNotNull(recaudacionIngresosEntity.getNumeracion());
        this.liquido = recaudacionIngresosEntity.getLiquido();
        this.anulados = ensureNotNull(recaudacionIngresosEntity.getAnulados());
        this.tarjeta = recaudacionIngresosEntity.getTarjeta();
        this.pagos = recaudacionIngresosEntity.getPagos();
        this.efectivo = recaudacionIngresosEntity.getEfectivo();
        this.app = recaudacionIngresosEntity.getApp();
        this.recaudacion = ensureNotNull(recaudacionIngresosEntity.getRecaudacion());
        this.observaciones = recaudacionIngresosEntity.getObservaciones();
        this.creado = recaudacionIngresosEntity.getCreado();
        this.actualizado = recaudacionIngresosEntity.getActualizado();
        this.t = recaudacionIngresosEntity.getT();
    }

    public void replace(RecaudacionIngreso recaudacionIngreso) {
        this.id = recaudacionIngreso.getId();
        this.recaudacionObj = recaudacionIngreso.getRecaudacionObj();
        this.conductor = recaudacionIngreso.getConductor();
        this.estado = recaudacionIngreso.getEstado();
        this.dia = recaudacionIngreso.getDia();
        this.turno = recaudacionIngreso.getTurno();
        this.numeracion = ensureNotNull(recaudacionIngreso.getNumeracion());
        this.liquido = recaudacionIngreso.getLiquido();
        this.anulados = ensureNotNull(recaudacionIngreso.getAnulados());
        this.tarjeta = recaudacionIngreso.getTarjeta();
        this.pagos = recaudacionIngreso.getPagos();
        this.efectivo = recaudacionIngreso.getEfectivo();
        this.app = recaudacionIngreso.getApp();
        this.recaudacion = ensureNotNull(recaudacionIngreso.getRecaudacion());
        this.observaciones = recaudacionIngreso.getObservaciones();
        this.creado = recaudacionIngreso.getCreado();
        this.actualizado = recaudacionIngreso.getActualizado();
        this.t = recaudacionIngreso.getT();
    }

    public static RecaudacionIngreso createEmpty(Integer dia, String turno) {
        RecaudacionIngreso recaudacionIngreso = new RecaudacionIngreso();
        recaudacionIngreso.setDia(dia);
        recaudacionIngreso.setTurno(turno);
        recaudacionIngreso.setConductor(new Conductor());
        recaudacionIngreso.setEstado(new EstadosIngreso());

        return recaudacionIngreso;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("<div class='node-root'><div><b>")
                .append(getClass().getSimpleName()).append("</b></div>")
                .append("<ul>");

        sb.append(Arrays.stream(getClass().getDeclaredFields()).map(e -> {
            try {
                Object o = e.get(this);
                return o != null? "<li>" + "<b>" + e.getName() + ": &nbsp;<b>" + o.toString() + "</li>": "";
            } catch (IllegalAccessException illegalAccessException) {
                return "<li>" + illegalAccessException.toString() + "</li>";
            }
        }).collect(Collectors.joining("\n")));


        return sb.append("</ul>").append("</div>").toString();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Conductor getConductor() {
        return conductor;
    }

    public void setConductor(Conductor conductor) {
        this.conductor = conductor;
    }

    public EstadosIngreso getEstado() {
        return estado;
    }

    public void setEstado(EstadosIngreso estado) {
        this.estado = estado;
    }

    public Integer getDia() {
        return dia;
    }

    public void setDia(Integer dia) {
        this.dia = dia;
    }

    public String getTurno() {
        return turno;
    }

    public void setTurno(String turno) {
        this.turno = turno;
    }

    public BigDecimal getNumeracion() {
        return numeracion;
    }

    public void setNumeracion(BigDecimal numeracion) {
        this.numeracion = numeracion;
    }

    public BigDecimal getAnulados() {
        return anulados;
    }

    public void setAnulados(BigDecimal anulados) {
        this.anulados = anulados;
    }

    public BigDecimal getRecaudacion() {
        return recaudacion;
    }

    public void setRecaudacion(BigDecimal recaudacion) {
        this.recaudacion = recaudacion;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
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

    public Recaudacion getRecaudacionObj() {
        return recaudacionObj;
    }

    public void setRecaudacionObj(Recaudacion recaudacionObj) {
        this.recaudacionObj = recaudacionObj;
    }

    public BigDecimal getLiquido() {
        return liquido;
    }

    public void setLiquido(BigDecimal liquido) {
        this.liquido = liquido;
    }

    public BigDecimal getTarjeta() {
        return tarjeta;
    }

    public void setTarjeta(BigDecimal tarjeta) {
        this.tarjeta = tarjeta;
    }

    public BigDecimal getEfectivo() {
        return efectivo;
    }

    public BigDecimal getPagos() {
        return pagos;
    }

    public void setPagos(BigDecimal pagos) {
        this.pagos = pagos;
    }

    public void setEfectivo(BigDecimal efectivo) {
        this.efectivo = efectivo;
    }

    public BigDecimal getApp() {
        return app;
    }

    public void setApp(BigDecimal app) {
        this.app = app;
    }

    public BigDecimal getT() {
        return t;
    }

    public void setT(BigDecimal t) {
        this.t = t;
    }
}


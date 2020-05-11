package faces;

import business.ConductoresBean;
import business.EmpresasBean;
import datamodels.LazyConductorDataModel;
import datamodels.LazyEmpresaDataModel;
import org.apache.log4j.Logger;
import org.primefaces.component.datatable.DataTable;
import org.primefaces.event.CellEditEvent;
import org.primefaces.model.LazyDataModel;
import pojos.Conductor;
import pojos.Empresa;

import javax.annotation.PostConstruct;
import javax.el.MethodExpression;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

@ViewScoped
@Named(EmpresasFace.BEAN_NAME)
public class EmpresasFace implements Serializable {

    public static final String BEAN_NAME = "EmpresasFace";

    @Inject
    private EmpresasBean empresasBean;

    @Inject
    private transient Logger logger;

    private LazyDataModel<Empresa> lazyModel;


    @PostConstruct
    public void init() {
        lazyModel = new LazyEmpresaDataModel(empresasBean);
    }

    public void onCellEdit(CellEditEvent event) {
        try {
            Object oldValue = event.getOldValue();
            Object newValue = event.getNewValue();
            Empresa empresa = (Empresa) ((DataTable) event.getComponent()).getRowData();
            empresasBean.update(empresa);

            if (newValue != null && !newValue.equals(oldValue)) {
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Celda modificada", "Anterior: " + oldValue + ", Nuevo:" + newValue);
                FacesContext.getCurrentInstance().addMessage(null, msg);
            }
        } catch (Throwable e) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error actualizando empresa", e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }

    public void deleteEmpresa(Long id) {
        try {
            empresasBean.delete(id);
        } catch (Throwable e) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error eliminando empresa", e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }

    public LazyDataModel<Empresa> getLazyModel() {
        return lazyModel;
    }

    public void setLazyModel(LazyDataModel<Empresa> lazyModel) {
        this.lazyModel = lazyModel;
    }
}
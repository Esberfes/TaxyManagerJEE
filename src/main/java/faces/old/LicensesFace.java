package faces.old;

import java.io.Serializable;

public class LicensesFace implements Serializable {
/*
    public static final String BEAN_NAME = "LicensesFace";

    @Inject
    private LicensesBean licensesBean;

    @Inject
    private transient Logger logger;

    private LazyDataModel<License> lazyModel;

    private String code;

    @PostConstruct
    public void init() {
        lazyModel = new LazyLicenseDataModel(licensesBean);
    }

    public LazyDataModel<License> getLazyModel() {
        return lazyModel;
    }

    public void onCellEdit(CellEditEvent event) {
        try {
            Object oldValue = event.getOldValue();
            Object newValue = event.getNewValue();

            License license = (License) ((DataTable) event.getComponent()).getRowData();
            licensesBean.update(license);

            if (newValue != null && !newValue.equals(oldValue)) {
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Celda modificada", "Anterior: " + oldValue + ", Nuevo:" + newValue);
                FacesContext.getCurrentInstance().addMessage(null, msg);
            }
        } catch (Throwable e) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error actualizando licencia", e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }

    public void insertLicense() {
        try {
            licensesBean.insertLicense(new License(code));
            code = null;
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Nuevo licencia insertada", "");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        } catch (Throwable e) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error insertando licencia", e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }

    public void deleteLicense(Long id) {
        try {
            licensesBean.deleteLicense(id);
        } catch (Throwable e) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error eliminado licencia", e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

 */
}
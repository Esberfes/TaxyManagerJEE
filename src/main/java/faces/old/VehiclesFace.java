package faces.old;

import java.io.Serializable;

public class VehiclesFace implements Serializable {
/*
    public static final String BEAN_NAME = "VehiclesFace";

    @Inject
    private VehiclesBean vehiclesBean;

    @Inject
    private LicensesBean licensesBean;

    @Inject
    private transient Logger logger;

    private LazyDataModel<Vehicle> lazyModel;

    private String licenseCode;
    private String brand;
    private String registration;

    @PostConstruct
    public void init() {
        lazyModel = new LazyVehicleDataModel(vehiclesBean);
    }

    public LazyDataModel<Vehicle> getLazyModel() {
        return lazyModel;
    }

    public void insertVehicle() {
        try {
            vehiclesBean.insertVehicle(brand, registration, licenseCode);
            brand = null;
            registration = null;
            licenseCode = null;

            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Nueva vehiculo insertada", "");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        } catch (Throwable e) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error insertando vehiculo", e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }

    public void onCellEdit(CellEditEvent event) {
        try {
            Object oldValue = event.getOldValue();
            Object newValue = event.getNewValue();

            Vehicle vehicle = (Vehicle) ((DataTable) event.getComponent()).getRowData();
            vehiclesBean.update(vehicle);

            if (newValue != null && !newValue.equals(oldValue) || newValue == null && oldValue != null || newValue != null) {
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Celda modificada", "Anterior: " + oldValue + ", Nuevo:" + newValue);
                FacesContext.getCurrentInstance().addMessage(null, msg);
            }
        } catch (Throwable e) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error actualizando vehiculo", e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }

    public List<String> completeLicense(String value) {
        return licensesBean.findLicensesByCod(value).stream().map(License::getCode).collect(Collectors.toList());
    }

    public void deleteVehicle(Long id) {
        vehiclesBean.deleteVehicle(id);
    }

    public String getLicenseCode() {
        return licenseCode;
    }

    public void setLicenseCode(String licenseCode) {
        this.licenseCode = licenseCode;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getRegistration() {
        return registration;
    }

    public void setRegistration(String registration) {
        this.registration = registration;
    }
    */
}
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import facades.AreaBeanFacade;
import java.util.Iterator;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import models.AreaBean;

/**
 *
 * @author DMART
 */
@ManagedBean(name = "areaBean")
@RequestScoped
public class MAreasBean {

    private AreaBean area;
    private List<AreaBean> areas;
    
    @EJB
    private AreaBeanFacade areaBeanFacade;

    /**
     * Creates a new instance of areasBean
     */
    public MAreasBean() {
    }

    @PostConstruct
    public void init() {
        this.area = new AreaBean();
        this.areas = this.areaBeanFacade.findAll();
    }

    public AreaBean getArea() {
        return area;
    }

    public void setArea(AreaBean area) {
        this.area = area;
    }

    public String crear() {
        
        try {
            if (this.area != null) {
                this.areaBeanFacade.create(area);

                FacesMessage msg = new FacesMessage("Area creada con éxito", "INFO MSG");
                msg.setSeverity(FacesMessage.SEVERITY_INFO);
                FacesContext.getCurrentInstance().addMessage(null, msg);
            }

            this.area = new AreaBean();
            this.areas = this.areaBeanFacade.findAll();
            
        } catch (EJBException e) {
            @SuppressWarnings("ThrowableResultIgnored")
            Exception cause = e.getCausedByException();
            if (cause instanceof ConstraintViolationException) {
                @SuppressWarnings("ThrowableResultIgnored")
                ConstraintViolationException cve = (ConstraintViolationException) e.getCausedByException();
                for (Iterator<ConstraintViolation<?>> it = cve.getConstraintViolations().iterator(); it.hasNext();) {
                    ConstraintViolation<? extends Object> v = it.next();
                    FacesMessage msg = new FacesMessage("En el campo " + v.getPropertyPath() + ", " + v.getMessageTemplate(), "ERROR MSG");
                    msg.setSeverity(FacesMessage.SEVERITY_ERROR);
                    FacesContext.getCurrentInstance().addMessage(null, msg);
                }
            }
            else
            {
                e.printStackTrace();
            }
        }
        
        return "/SistemaDeTickets/secured/maters/area.xhtml";
    }

    public List<AreaBean> getAreas() {
        return areas;
    }

    public void setAreas(List<AreaBean> areas) {
        this.areas = areas;
    }
}

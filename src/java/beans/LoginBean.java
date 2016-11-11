/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import com.PasswordStorage;
import facades.UsuarioBeanFacade;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import models.CategoriaPermisoBean;
import models.PermisoBean;
import models.RolBean;
import models.UsuarioBean;

/**
 *
 * @author DMART
 */
@ManagedBean(name = "loginBean")
@SessionScoped
public class LoginBean implements Serializable {

    private String username;
    private String password;

    private boolean loggedIn;

    @ManagedProperty(value = "#{navigationBean}")
    private NavigationBean navigantionBean;

    @EJB
    private UsuarioBeanFacade usuarioBeanFacade;

    
    @PersistenceContext(unitName = "SistemaDeTicketsPU")
    private EntityManager em;
    @Resource
    private javax.transaction.UserTransaction utx;
    
    /**
     * Creates a new instance of LoginBean
     */
    public LoginBean() {

    }

    public String doLogin() {
        //this.createAll();
        try {
            UsuarioBean usuario = this.usuarioBeanFacade.find(this.username);

            if (username.equals(usuario.getUsername())
                    && PasswordStorage.verifyPassword(password, usuario.getPassword())) {
                navigantionBean.setUsuario(usuario);
                navigantionBean.ejecutarAutorizaciones();
                loggedIn = true;
                return navigantionBean.redirectToWelcome();
            }
        } catch (Exception ex) {
            String ns = ex.toString();
        }

        FacesMessage msg = new FacesMessage("Usuario y Contraseña no corresponse a ningun usuario", "ERROR MSG");
        msg.setSeverity(FacesMessage.SEVERITY_ERROR);
        FacesContext.getCurrentInstance().addMessage(null, msg);

        return navigantionBean.toLogin();
    }

    public String doLogout() {
        loggedIn = false;
        FacesMessage msg = new FacesMessage("Logout Success!", "INFO MSG");
        msg.setSeverity(FacesMessage.SEVERITY_INFO);
        FacesContext.getCurrentInstance().addMessage(null, msg);

        return navigantionBean.redirectToLogin();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isLoggedIn() {
        return loggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }

    public NavigationBean getNavigantionBean() {
        return navigantionBean;
    }

    public void setNavigantionBean(NavigationBean navigantionBean) {
        this.navigantionBean = navigantionBean;
    }

    
    public void createAll() {
        try {
            CategoriaPermisoBean cp1 = new CategoriaPermisoBean();
            cp1.setNombre("Tickets");
            cp1.setIcon("fa-ticket");
            
            CategoriaPermisoBean cp2 = new CategoriaPermisoBean();
            cp2.setNombre("Usuarios y Roles");
            cp2.setIcon("fa-users");
            
            CategoriaPermisoBean cp3 = new CategoriaPermisoBean();
            cp3.setNombre("Maestros");
            cp3.setIcon("fa-th-large");
            
            save(cp1);
            save(cp2);
            save(cp3);
            
            PermisoBean p1 = new PermisoBean();
            p1.setNombre("Crear Ticket");
            p1.setUrl("/SistemaDeTickets/secured/secured/tickets/createTicket.xhtml");
            p1.setCategoria(cp1);

            PermisoBean p2 = new PermisoBean();
            p2.setNombre("Mis Tickets");
            p2.setUrl("/SistemaDeTickets/secured/tickets/myTickets.xhtml");
            p2.setCategoria(cp1);

            PermisoBean p3 = new PermisoBean();
            p3.setNombre("Usuarios");
            p3.setUrl("/SistemaDeTickets/secured/users/createUser.xhtml");
            p3.setCategoria(cp2);

            PermisoBean p4 = new PermisoBean();
            p4.setNombre("Roles");
            p4.setUrl("/SistemaDeTickets/secured/users/createRol.xhtml");
            p4.setCategoria(cp2);

            PermisoBean p5 = new PermisoBean();
            p5.setNombre("Areas");
            p5.setUrl("/SistemaDeTickets/secured/masters/createArea.xhtml");
            p5.setCategoria(cp3);

            PermisoBean p6 = new PermisoBean();
            p6.setNombre("Categorías");
            p6.setUrl("/SistemaDeTickets/secured/masters/createCategory.xhtml");
            p6.setCategoria(cp3);

            PermisoBean p7 = new PermisoBean();
            p7.setNombre("Estados");
            p7.setUrl("/SistemaDeTickets/secured/masters/createStatus.xhtml");
            p7.setCategoria(cp3);

            save(p1);
            save(p2);
            save(p3);
            save(p4);
            save(p5);
            save(p6);
            save(p7);

            List<PermisoBean> permisos1 = new ArrayList<>();
            permisos1.add(p1);
            permisos1.add(p2);
            permisos1.add(p3);
            permisos1.add(p4);
            permisos1.add(p5);
            permisos1.add(p6);
            permisos1.add(p7);

            RolBean rol1 = new RolBean();
            rol1.setNombre("Administrador");
            rol1.setPermisos(permisos1);
            save(rol1);

            List<PermisoBean> permisos2 = new ArrayList<>();
            permisos2.add(p1);
            permisos2.add(p2);
            
            //Lista para tickets
            List<PermisoBean> permisos3 = new ArrayList<>();
            permisos3.add(p1);
            permisos3.add(p2);
            //Lista para usuarios 
            List<PermisoBean> permisos4 = new ArrayList<>();
            permisos4.add(p3);
            permisos4.add(p4);
            //Lista para maestros 
            List<PermisoBean> permisos5 = new ArrayList<>();
            permisos5.add(p5);
            permisos5.add(p6);
            permisos5.add(p7);
            
            cp1.setPermisos(permisos3);
            cp2.setPermisos(permisos4);
            cp3.setPermisos(permisos5);
            
            this.update(cp1);
            this.update(cp2);
            this.update(cp3);
            
            RolBean rol2 = new RolBean();
            rol2.setNombre("Estandar");
            rol2.setPermisos(permisos2);
            save(rol2);

            List<RolBean> roles1 = new ArrayList<>();
            roles1.add(rol1);

            List<RolBean> roles2 = new ArrayList<>();
            roles2.add(rol2);

            UsuarioBean u1 = new UsuarioBean();
            u1.setUsername("admin");
            u1.setPassword(PasswordStorage.createHash("admin"));
            u1.setCorreo("danielmartinez@diapahn.com");
            u1.setSap(false);
            u1.setRoles(roles1);
            save(u1);

            UsuarioBean u2 = new UsuarioBean();
            u2.setUsername("dmart");
            u2.setPassword(PasswordStorage.createHash("dmart"));
            u2.setCorreo("danbreakingnews@gmail.com");
            u2.setSap(false);
            u2.setRoles(roles2);
            save(u2);

        } catch (Exception ex) {
            String mensaje = ex.toString();
        }
    }

    public void save(Object object) {
        try {
            utx.begin();
            em.persist(object);
            utx.commit();
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", e);
            throw new RuntimeException(e);
        }
    }
    
    public void update(Object object) {
        try {
            utx.begin();
            em.merge(object);
            utx.commit();
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", e);
            throw new RuntimeException(e);
        }
    }
    
}

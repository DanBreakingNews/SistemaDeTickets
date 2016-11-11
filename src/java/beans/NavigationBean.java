/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import facades.CategoriaPermisoBeanFacade;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Objects;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import models.CategoriaPermisoBean;
import models.PermisoBean;
import models.RolBean;
import models.UsuarioBean;
/**
 *
 * @author DMART
 */
@ManagedBean(name = "navigationBean")
@SessionScoped
public class NavigationBean implements Serializable {

    @EJB
    private CategoriaPermisoBeanFacade categoriaPermisoBeanFacade;

    private UsuarioBean usuario;
    private List<CategoriaPermisoBean> categorias;

    public UsuarioBean getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioBean usuario) {
        this.usuario = usuario;
    }

    public List<CategoriaPermisoBean> getCategorias() {
        return categorias;
    }

    public void setCategorias(List<CategoriaPermisoBean> categorias) {
        this.categorias = categorias;
    }
    
    /**
     * Creates a new instance of NavigationBean
     */
    public NavigationBean() {
    }
    
    /**
     * Redirect to login page.
     * @return Login page name.
     */
    public String redirectToLogin() {
        return "/login.xhtml?faces-redirect=true";
    }
     
    /**
     * Go to login page.
     * @return Login page name.
     */
    public String toLogin() {
        return "/login.xhtml";
    }
     
    /**
     * Redirect to info page.
     * @return Info page name.
     */
    public String redirectToInfo() {
        return "/info.xhtml?faces-redirect=true";
    }
     
    /**
     * Go to info page.
     * @return Info page name.
     */
    public String toInfo() {
        return "/info.xhtml";
    }
     
    /**
     * Redirect to welcome page.
     * @return Welcome page name.
     */
    public String redirectToWelcome() {
        return "/secured/home.xhtml?faces-redirect=true";
    }
     
    /**
     * Go to welcome page.
     * @return Welcome page name.
     */
    public String toWelcome() {
        return "/secured/home.xhtml";
    }
    
    public void ejecutarAutorizaciones()
    {
        this.categorias = null;
        
        List<PermisoBean> permisos = new ArrayList<>();
        List<RolBean> roles = usuario.getRoles();
        
        for( RolBean rol : roles )
        {
            permisos.addAll(rol.getPermisos());
        }
        
        permisos = new ArrayList<>( new LinkedHashSet<>(permisos) );
        
        this.categorias = this.categoriaPermisoBeanFacade.findAll();
        
        for( CategoriaPermisoBean categoria : categorias )
        {
            for( PermisoBean permiso : categoria.getPermisos() )
            {
                for( PermisoBean autorizado : permisos )
                {
                    if( Objects.equals(permiso.getId(), autorizado.getId()) )
                    {
                        permiso.setAutorizado(true);
                    }
                }
            }
        }
    }
}

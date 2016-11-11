/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 *
 * @author DMART
 */
@Entity
@Table( name="usuario" )
public class UsuarioBean implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Size(min=1,max=256)
    private String username;
    @Size(min=1,max=2048)
    private String password;
    @Pattern(regexp="\\w+([.-]?\\w+)*@\\w+([.-]?\\w+)*(.\\w{2,4})+")
    private String correo;
    @NotNull
    private boolean sap;
    @ManyToMany
    @JoinTable(name="usuario_rol")
    private List<RolBean> roles;

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

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public boolean isSap() {
        return sap;
    }

    public void setSap(boolean sap) {
        this.sap = sap;
    }

    public List<RolBean> getRoles() {
        return roles;
    }

    public void setRoles(List<RolBean> roles) {
        this.roles = roles;
    }
    
    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (username != null ? username.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UsuarioBean)) {
            return false;
        }
        UsuarioBean other = (UsuarioBean) object;
        if ((this.username == null && other.username != null) || (this.username != null && !this.username.equals(other.username))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "models.Usuario[ username=" + username + " ]";
    }
    
}

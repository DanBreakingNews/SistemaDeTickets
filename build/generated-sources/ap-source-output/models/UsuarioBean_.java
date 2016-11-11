package models;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import models.RolBean;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-11-10T16:47:47")
@StaticMetamodel(UsuarioBean.class)
public class UsuarioBean_ { 

    public static volatile SingularAttribute<UsuarioBean, String> password;
    public static volatile SingularAttribute<UsuarioBean, Boolean> sap;
    public static volatile SingularAttribute<UsuarioBean, String> correo;
    public static volatile ListAttribute<UsuarioBean, RolBean> roles;
    public static volatile SingularAttribute<UsuarioBean, String> username;

}
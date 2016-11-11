package models;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import models.PermisoBean;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-11-10T16:47:47")
@StaticMetamodel(CategoriaPermisoBean.class)
public class CategoriaPermisoBean_ { 

    public static volatile SingularAttribute<CategoriaPermisoBean, String> icon;
    public static volatile SingularAttribute<CategoriaPermisoBean, Long> id;
    public static volatile ListAttribute<CategoriaPermisoBean, PermisoBean> permisos;
    public static volatile SingularAttribute<CategoriaPermisoBean, String> nombre;

}
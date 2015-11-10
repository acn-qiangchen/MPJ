package com.tink.mpj.persistence.model;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.0.v20130507-rNA", date="2015-11-08T22:44:14")
@StaticMetamodel(Purchase.class)
public class Purchase_ { 

    public static volatile SingularAttribute<Purchase, Long> id;
    public static volatile SingularAttribute<Purchase, Integer> amount;
    public static volatile SingularAttribute<Purchase, Date> createTime;
    public static volatile SingularAttribute<Purchase, String> status;
    public static volatile SingularAttribute<Purchase, Long> memberId;
    public static volatile SingularAttribute<Purchase, Long> tranId;

}
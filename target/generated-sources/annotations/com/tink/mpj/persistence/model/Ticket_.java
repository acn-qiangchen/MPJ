package com.tink.mpj.persistence.model;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.0.v20130507-rNA", date="2015-11-15T21:47:54")
@StaticMetamodel(Ticket.class)
public class Ticket_ { 

    public static volatile SingularAttribute<Ticket, Long> id;
    public static volatile SingularAttribute<Ticket, Integer> fee;
    public static volatile SingularAttribute<Ticket, String> carNo;
    public static volatile SingularAttribute<Ticket, Date> inTime;
    public static volatile SingularAttribute<Ticket, String> location;
    public static volatile SingularAttribute<Ticket, Integer> purAmount;
    public static volatile SingularAttribute<Ticket, String> memberId;
    public static volatile SingularAttribute<Ticket, String> planId;
    public static volatile SingularAttribute<Ticket, Date> outTime;

}
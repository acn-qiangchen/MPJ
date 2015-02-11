/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tink.mpj.app.bean;

import com.tink.mpj.business.ejb.MemberFacade;
import com.tink.mpj.common.logging.Loggable;
import com.tink.mpj.common.logging.Slogger;
import com.tink.mpj.common.util.IFSeqNoGenerator;
import com.tink.mpj.persistence.model.Member;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.enterprise.context.ConversationScoped;
import javax.enterprise.context.Conversation;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

//import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.inject.Named;
import javax.interceptor.ExcludeClassInterceptors;
import javax.validation.ConstraintViolationException;

/**
 * 会員登録画面のBackBeanです
 *
 * @author shearer
 */
//@Model
@Named
@ConversationScoped
//@RequestScoped
//@SessionScoped
//@ApplicationScoped
@Loggable
public class MemberAdmin implements Serializable {

    @Inject
    private Slogger logger;
    @Inject
    private IFSeqNoGenerator seqGen;
    @Inject
    private Conversation conversation;
     @Inject
    MemberFacade ejb;
    
    private Member member;

    @PostConstruct
    private void init(){
        // converstion.end までは再びinitを呼ばれない
        logger.debug("initializing member....");
        conversation.begin();
        member = new Member();
        //member.setSeqNo(0);
    }
    
    @ExcludeClassInterceptors
    public void setMember(Member member) {
        this.member = member;
    }

    @ExcludeClassInterceptors
    public Member getMember() {
        return member;
    }

    // 登録ボタンAction
    public String add() {
        logger.debug(member);
            return "confirm.xhtml";        
    }
    
    // 検索ボタンAction
    //TODO Search group Bean Validationを適用 
    public String search(){
        member = ejb.find(member.getSeqNo());
        return "confirm.xhtml";
    }

    // 確認ボタンAction
    public String confirm() {
        try{
            logger.debug(member);

            //TODO JPA Validation
            member.setEmail("");
            ejb.create(member);
            //member = new Member();
            conversation.end();
            FacesContext ctx = FacesContext.getCurrentInstance();
            ctx.addMessage(null, new FacesMessage(
            FacesMessage.SEVERITY_INFO, 
            "", "会員情報が登録されました。"));
            return "confirm.xhtml";   
        }catch (Exception e){
            String msg = "error happend during db operation.";
            if(e.getCause() instanceof ConstraintViolationException){
                ConstraintViolationException cve = (ConstraintViolationException)e.getCause();
                msg = cve.getConstraintViolations().iterator().next().getMessage();
            }
            FacesContext ctx = FacesContext.getCurrentInstance();
            ctx.addMessage(null, new FacesMessage(
            FacesMessage.SEVERITY_ERROR, 
            "", msg));
            return "confirm.xhtml"; 
        }
    }

    // キャンセルボタンAction
    public String cancel() {
        logger.debug(member);
        //conversation.end();
        return "newMember.xhtml";

    }
    
//    public boolean validate(){
//        FacesContext ctx = FacesContext.getCurrentInstance();
//        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
//        Validator validator = factory.getValidator();
//        Set<ConstraintViolation<Member>> constraintViolations = validator.validate( member, BeforeSave.class );
//        for(ConstraintViolation cv : constraintViolations){
//            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, cv.getMessage(), cv.getMessage());
//            ctx.addMessage(null, fm);
//        }
//        return constraintViolations.isEmpty();
//    }
}

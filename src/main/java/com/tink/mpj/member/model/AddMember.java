/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tink.mpj.member.model;

import com.tink.mpj.common.logging.Loggable;
import com.tink.mpj.common.logging.Slogger;
import com.tink.mpj.common.util.IFSeqNoGenerator;
import com.tink.mpj.member.beans.Member;
import com.tink.mpj.member.service.MemberService;
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
public class AddMember implements Serializable {

    @Inject
    private Slogger logger;
    @Inject
    private transient MemberService service;
    @Inject
    private IFSeqNoGenerator seqGen;
    @Inject
    private Conversation conversation;
    
    private Member member;

    @PostConstruct
    private void init(){
        // concerstion.end までは再びinitが呼ばれない
        logger.debug("initializing member....");
        conversation.begin();
        member = new Member();
        member.setSeqNo(seqGen.getNo());
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

    // 確認ボタンAction
    public String confirm() {
        logger.debug(member);
        //MemberService service = new MemberService();
        service.insert(member); // DB登録
        member = new Member();
        conversation.end();
        FacesContext ctx = FacesContext.getCurrentInstance();
        ctx.addMessage(null, new FacesMessage(
                FacesMessage.SEVERITY_INFO, 
                "", "会員情報が登録されました。"));
        return "newMember.xhtml";
    }

    // キャンセルボタンAction
    public String cancel() {
        logger.debug(member);
        //conversation.end();
        return "newMember.xhtml";

    }
}

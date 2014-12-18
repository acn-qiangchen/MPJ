/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tink.mpj.member.beans;

import com.tink.mpj.common.constraint.CarNo;
import com.tink.mpj.common.constraint.Email;
import com.tink.mpj.common.constraint.Required;
import com.tink.mpj.common.constraint.ZipCode;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author shearer
 */
public class Member {

    @NotNull
    private String seqNo;
    @Required(name="名前")
    private String name;
    @Required(name="Email")
    @Email
    private String email;
    @Required(name="住所")
    private String address;
    @Required(name="郵便番号")
    @ZipCode
    private String zipCode;
    @CarNo
    private String carNo;

    @Override
    public String toString() {
        return "Member{" + "seqNo=" + seqNo + ", name=" + name + ", email=" + email + ", address=" + address + ", zipCode=" + zipCode + ", carNo=" + carNo + '}';
    }

    public String getSeqNo() {
        return seqNo;
    }

    public void setSeqNo(String seqNo) {
        this.seqNo = seqNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getCarNo() {
        return carNo;
    }

    public void setCarNo(String carNo) {
        this.carNo = carNo;
    }
}

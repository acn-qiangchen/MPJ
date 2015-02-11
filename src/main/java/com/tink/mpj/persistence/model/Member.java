/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tink.mpj.persistence.model;

import com.tink.mpj.common.constraint.CarNo;
import com.tink.mpj.common.constraint.Email;
import com.tink.mpj.common.constraint.Required;
import com.tink.mpj.common.constraint.ZipCode;
import com.tink.mpj.common.groups.BeforeSave;
import com.tink.mpj.common.groups.BeforeSearch;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

/**
 *
 * @author shearer
 */
@Entity(name = "MEMBER")//テーブル名を指定
public class Member {

    @NotNull(groups = BeforeSearch.class)
    @Id // primary key
    @GeneratedValue // DBによって自動作成
    private long seqNo;
    @Required(name="名前", groups = BeforeSave.class)
    private String name;
    @Required(name="Email", groups = BeforeSave.class)
    @Email(groups = BeforeSave.class)
    private String email;
    @Required(name="住所", groups = BeforeSave.class)
    private String address;
    @Required(name="郵便番号", groups = BeforeSave.class)
    @ZipCode(groups = BeforeSave.class)
    private String zipCode;
    @CarNo
    private String carNo;

    @Override
    public String toString() {
        return "Member{" + "seqNo=" + seqNo + ", name=" + name + ", email=" + email + ", address=" + address + ", zipCode=" + zipCode + ", carNo=" + carNo + '}';
    }

    public long getSeqNo() {
        return seqNo;
    }

    public void setSeqNo(long seqNo) {
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

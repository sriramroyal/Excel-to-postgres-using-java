package com.example.demo.EntityPackage;


import javax.persistence.*;
import java.util.Date;

@Entity
public class EmpData {

    @Column(nullable = false,name = "sno")
    private Integer sno ;
    @Id
    @Column(nullable = false,name = "id")
    private String id ;
    @Column(nullable = false, name = "name")
    private String name ;
    @Column(nullable = false, name = "primaryskill")
    private String primaryskill;
    @Column(nullable = false, name = "secondaryskill")
    private String secondaryskill ;
    @Column(nullable = false,name = "domain")
    private String domain ;
//    @Column(nullable = false,name = "fileName")
//    private String fileName;
    @Temporal(TemporalType.DATE)
    private Date date=new Date(System.currentTimeMillis());
    

    public EmpData() {
    }

    public EmpData(Integer sno, String id, String name, String primaryskill, String secondaryskill, String domain) {
        this.sno = sno;
        this.id = id;
        this.name = name;
        this.primaryskill = primaryskill;
        this.secondaryskill = secondaryskill;
        this.domain = domain;
    }

    public Integer getSno() {
        return sno;
    }

    public void setSno(Integer sno) {
        this.sno = sno;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrimaryskill() {
        return primaryskill;
    }

    public void setPrimaryskill(String primaryskill) {
        this.primaryskill = primaryskill;
    }

    public String getSecondaryskill() {
        return secondaryskill;
    }

    public void setSecondaryskill(String secondaryskill) {
        this.secondaryskill = secondaryskill;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    @Override
    public String toString() {
        return "EmpData{" +
                "sno=" + sno +
                ", id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", primaryskill='" + primaryskill + '\'' +
                ", secondaryskill='" + secondaryskill + '\'' +
                ", domain='" + domain + '\'' +
                '}';
    }
}

package com.project4.admin.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="brand")
public class Brand {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int brandId;
    @Column(length = 50,nullable = false, name = "brandName")
    private String brandName;
    @Column(nullable = false, name = "logo")
    private String logo;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name="brandId")
    private List<Product> productList;

    public Brand(Integer brandId, String brandName, String logo) {
        this.brandId = brandId;
        this.brandName=brandName;
        this.logo=logo;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public Brand() {

    }

    public int getBrandId() {
        return brandId;
    }

    public void setbrandId(int brandId) {
        this.brandId = brandId;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }
}

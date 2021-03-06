package com.demo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.ParamDef;
import org.hibernate.annotations.SQLDelete;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tbl_customers")
@SQLDelete(sql="UPDATE tbl_customers SET deleted = true WHERE id=?")
//@Where(clause = "deleted=false")
@FilterDef(name="deletedCustomerFilter", parameters= @ParamDef(name="isDeleted", type="boolean"))
@Filter(name="deletedCustomerFilter", condition = "deleted = :isDeleted")
@Data
@NoArgsConstructor
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private boolean deleted = Boolean.FALSE;

    @Builder
    public Customer(String name, String email){
        this.name = name;
        this.email = email;
    }
}

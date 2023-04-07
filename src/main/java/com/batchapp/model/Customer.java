package com.batchapp.model;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Customer extends BaseModel{
    private String firstname;
    private String lastname;
    private String email;
    private String phone;

    @OneToMany(mappedBy = "customer", fetch = FetchType.LAZY)
    List<Invoice> invoices = new ArrayList<>();
}

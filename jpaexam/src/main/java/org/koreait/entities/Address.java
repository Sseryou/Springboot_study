package org.koreait.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Address extends BaseEntity{
    @Id @GeneratedValue
    private Long id;

    private String zipcode;
    private String addr1;
    private String addr2;
}

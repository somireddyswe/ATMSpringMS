package org.springframework.samples.atm.bank;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.validation.constraints.NotEmpty;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Entity
@Table(name = "bank")
public class BankEntity {

    @Column(name = "denom_type")
    @NotEmpty
    private String denom_type;
    
    @Id
    @Column(name = "denom_value")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int denom_value;

    @Column(name = "denom_count")
    private int denom_count;

    public String getDenomType(){
        return this.denom_type;
    }

    public void setDenomType(String denom_type) {
        this.denom_type = denom_type;
    }

    public int getDenomValue(){
        return this.denom_value;
    }

    public void setDenomValue(int denom_value) {
        this.denom_value = denom_value;
    }

    public int getDenomCount(){
        return this.denom_count;
    }

    public void setDenomCount(int denom_count) {
        this.denom_count = denom_count;
    }

    @Override
    public String toString() {
        return "Bank{" +
               "type=" + this.getDenomType() + "\n" +
               "value="+ this.getDenomValue() + "\n" +
               "count="+ this.getDenomCount() + "\n" +
               "}";
    }
    
}

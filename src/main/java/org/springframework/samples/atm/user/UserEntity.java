package org.springframework.samples.atm.user;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.validation.constraints.NotEmpty;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Entity
@Table(name = "users")
public class UserEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "card_num")
    @NotEmpty
    private String card_num;

    @Column(name = "pin")
    @NotEmpty
    private String pin;

    @Column(name = "amount")
    private String amount;

    public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

    public String getCardNum(){
        return this.card_num;
    }

    public void setCardNum(String card_num) {
        this.card_num = card_num;
    }

    public String getPin(){
        return this.pin;
    }

    public void setDenomValue(String pin) {
        this.pin = pin;
    }

    public String getAmount(){
        return this.amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "User{" +
               "id = " + this.getId() + "\n" +
               "card_num=" + this.getCardNum() + "\n" +
               "pin="+ this.getPin() + "\n" +
               "balance amount="+ this.getAmount() + "\n" +
               "}";
    }
    
}

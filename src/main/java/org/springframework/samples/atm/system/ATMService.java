package org.springframework.samples.atm.system;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.HashMap;
import org.springframework.samples.atm.bank.BankController;
import org.springframework.samples.atm.user.UserController;

@Service
public class ATMService {
    @Autowired
	BankController bankController;

	@Autowired
	UserController userController;

    public ResponseEntity<Object> getBalance(String cardnum)
	{
        return new ResponseEntity<>("Available Balance ::"+ userController.getBalance(cardnum) , HttpStatus.OK);
    }

    public ResponseEntity<Object> withdrawAmount(String cardnum, int amnt, int denom)
	{   		
		String balance = userController.getBalance(cardnum);
		int amount = Integer.parseInt(balance);
		
		if(amount < amnt){
            return new ResponseEntity<>("Insufficient Balance", HttpStatus.BAD_REQUEST);
		}

		HashMap<Integer, Integer> valueCountMap = bankController.getBankDenomCount();
		if (amnt % denom == 0)
		{
            if(amnt/denom < valueCountMap.get(denom)){
				int newBalance =amount-amnt;
				userController.updateBalance(cardnum, String.valueOf(newBalance));
				bankController.updateDenomCount(valueCountMap.get(denom) - (amnt/denom), denom);
                return new ResponseEntity<>("Successfull Transaction of Amount: $" + amnt, HttpStatus.OK);
            }else{		 
                return new ResponseEntity<>("Insufficient Denominations in ATM" +amnt, HttpStatus.INTERNAL_SERVER_ERROR); 
			}  
		}
		else{
            return new ResponseEntity<>("Invalid Amount / No denominations available" +amnt, HttpStatus.NOT_FOUND); 
		}
	
	}

}

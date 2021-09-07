package org.springframework.samples.atm.bank;

import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.HashMap;

@Controller
public class BankController {
    private final Logger logger = LoggerFactory.getLogger(BankController.class);
    
    @Autowired
    BankRepository bankRepository;

    HashMap<Integer, Integer> valueCountMap = new HashMap<Integer, Integer>();

    public HashMap<Integer, Integer> getBankDenomCount(){
        List<BankEntity> bank = bankRepository.findAll();

        for (int i = 0; i< bank.size(); i++) {
            valueCountMap.put(bank.get(i).getDenomValue(), bank.get(i).getDenomCount());
        }
        logger.info("Got data from database : {}", bank);
        return valueCountMap;
    }

    public void updateDenomCount(int updatedBankAmt, int bankValue){
            String bankType = this.getBankType(bankValue);

            BankEntity bank = bankRepository.findById(bankValue).get();
            bank.setDenomCount(updatedBankAmt);
            bankRepository.save(bank);
            logger.info(bankType +"Denomination Count : " + updatedBankAmt);
            System.out.println("Done");
    }

    public HashMap<Integer, Integer> getBankValues(){
        return valueCountMap;
    }

    public String getBankType(int bankValue){
        String bankType;
        switch (bankValue){
            case 50:
                bankType = "FIFTY";
                break;
            case 20:
                bankType = "TWENTY";
                break;
            default:
                bankType = "TEN";
                break;
        }
        return bankType;
    }
}

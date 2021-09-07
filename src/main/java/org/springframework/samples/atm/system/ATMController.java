package org.springframework.samples.atm.system;

import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;

import org.springframework.samples.atm.bank.BankController;
import org.springframework.samples.atm.user.UserController;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Controller
@SessionAttributes("cardnum")
public class ATMController {
    private final Logger logger = LoggerFactory.getLogger(ATMController.class);

	@Autowired
	BankController bankController;

	@Autowired
	UserController userController;

	@RequestMapping(value="/login", method = RequestMethod.GET)
    public String showLoginPage(ModelMap model){
        return "login";
    }

	@RequestMapping(value="/login", method = RequestMethod.POST)
    public String showActionsPage(ModelMap model, @RequestParam String cardnum, @RequestParam String pin){

        boolean isValidUser = userController.userAuthentication(cardnum, pin);

        if (!isValidUser) {
            model.put("errorMessage", "Invalid Credentials");
            return "login";
        }
        model.put("cardnum", cardnum);

        return "actions";
    }

	@RequestMapping(value="/balance", method = RequestMethod.GET)
	public String showBalancePage(ModelMap model){
		model.addAttribute("balance", userController.getBalance(model.get("cardnum").toString()));
        return "balance";
    }
	
	@RequestMapping(value="/withdraw", method = RequestMethod.GET)
	public String showWithdrawPage(ModelMap model){
		return "withdraw";
    }

	@RequestMapping(value="/withdraw", method = RequestMethod.POST)
	public String withdrawAmount(ModelMap model, int amnt, int denom)
	{   
		String response = "";
		try{
		String cardnum = model.get("cardnum").toString();
		String balance = userController.getBalance(cardnum);
		int amount = Integer.parseInt(balance);
		
		if(amount < amnt){
			response = "Insufficient Balance"; 
			logger.info(response);
			 
			model.addAttribute("response", response);
			return "response";
		}

		HashMap<Integer, Integer> valueCountMap = bankController.getBankDenomCount();
		if (amnt % denom == 0)
		{
            if(amnt/denom < valueCountMap.get(denom)){
				int newBalance =amount-amnt;
				userController.updateBalance(cardnum, String.valueOf(newBalance));
				bankController.updateDenomCount(valueCountMap.get(denom) - (amnt/denom), denom);
				response = "Successfull Transaction of Amount: $" + amnt;
            }else{		 
				response = "Insufficient Denominations in ATM" + amnt;  
			}  
		}
		else{
			response = "Invalid Amount / No denominations available" + amnt;
		}
	}catch(Exception e){
		logger.error(e.toString());
	}
		logger.info(response);
		model.addAttribute("response", response);

		return "response";
	}

	@ExceptionHandler(value = RuntimeException.class)
    public String handleException(ModelMap model, String msg) {
        model.addAttribute("message", msg);
        return "error"; 
    }

}

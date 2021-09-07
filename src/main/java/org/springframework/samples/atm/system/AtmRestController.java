package org.springframework.samples.atm.system;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/atm")
public class AtmRestController {
	
	@Autowired
	ATMService atmService;

    @GetMapping("/getBalance")
    public ResponseEntity<Object> getBalance(@PathVariable String cardnum) throws Exception
	{
        return atmService.getBalance(cardnum);	
	}
    
    @GetMapping(value = "/getAmount/{cardnum}/{amnt}/{denom}")
    public ResponseEntity<Object> withdrawAmount(@PathVariable("cardnum") String cardnum,
                                                 @PathVariable("amnt") String amnt, 
                                                 @PathVariable("denom") String denom) 
    { 
        int amount = Integer.parseInt(amnt);
        int denomination = Integer.parseInt(denom);
        return atmService.withdrawAmount(cardnum, amount,denomination);
   }  
	
	@ExceptionHandler(value ={Exception.class})
	public ResponseEntity<Object> generalException ()
	{
        return new ResponseEntity<>("There is a issue with your request. Please try again", HttpStatus.NOT_FOUND);
	}
	
}

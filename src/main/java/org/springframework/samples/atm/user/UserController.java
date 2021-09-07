package org.springframework.samples.atm.user;

import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller
public class UserController {
    private final Logger logger = LoggerFactory.getLogger(UserController.class);
    
    @Autowired
    UserRepository userRepository;

    public boolean userAuthentication(String cardNum , String pin){
        boolean authentic = false;
        String userPin = userRepository.getUserPin(cardNum);
        if (null == userPin){
            throw new RuntimeException("Cannot Validate this user.");
        }
        if(userPin.equalsIgnoreCase(pin)){
            authentic = true;
        }
        logger.info(authentic ? "Login sucess" : "Login failed");
        return authentic;
    }
    
    public String getBalance(String cardNum){
        String balance = userRepository.getUserBalance(cardNum);
        logger.info("User Account Balance" + balance);
        return balance;
    }

    public void updateBalance(String cardNum, String amount){
        Integer id = userRepository.getUserId(cardNum);
        logger.info("User New Account Balance" + amount);

		UserEntity user = userRepository.findById(id).get();
		user.setAmount(amount);
		userRepository.save(user);
		System.out.println("Done");
    }
    
}

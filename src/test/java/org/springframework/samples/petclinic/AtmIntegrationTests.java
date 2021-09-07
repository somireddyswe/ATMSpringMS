package org.springframework.samples.petclinic;

import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.samples.atm.user.UserRepository;
import org.springframework.samples.atm.bank.BankRepository;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;
import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AtmIntegrationTests {

    @Autowired
    private UserRepository users;

    @Autowired
    private BankRepository bank;

    @Test
    public void testFindAll() throws Exception {
        users.findAll();
    }

    @Test
    public void testFind() throws Exception {
        bank.findAll();
    }
    
    private TestRestTemplate testRest;
    @Test
	public void testGetBalance() throws Exception {
		String url = "http://localhost:8080/atm/getBalance/123456789";
		
		UriComponents builder  = UriComponentsBuilder.fromHttpUrl(url).build();
		
		HttpEntity<String> requestEntity = new HttpEntity<>(null,null);
		ResponseEntity<String> res = testRest.exchange(builder.toString(),HttpMethod.GET,requestEntity,String.class);
		
		System.out.println("Inside test :" + res.getBody());

		assertEquals("Available Balance ::100",res.getBody());
    }

    @Test
	public void testGetAmount() throws Exception {
		String url = "http://localhost:8080/atm/getAmount/123456789/20/10";
		
		UriComponents builder  = UriComponentsBuilder.fromHttpUrl(url).build();
		
		HttpEntity<String> requestEntity = new HttpEntity<>(null,null);
		ResponseEntity<String> res = testRest.exchange(builder.toString(),HttpMethod.GET,requestEntity,String.class);
		
		System.out.println("Inside test :" + res.getBody());

		assertEquals("Successfull Transaction of Amount: $20",res.getBody());
    }
}

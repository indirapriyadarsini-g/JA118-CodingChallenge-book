package com.book.CodingChallenge;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.book.CodingChallenge.repository.UserInfoRepository;
import com.book.CodingChallenge.service.UserService;

@SpringBootTest
class CodingChallengeApplicationTests {

	@Mock
	UserInfoRepository userInfoRepository;
	
	@InjectMocks
	UserService userService;
	
	
	
	@Test
	void contextLoads() {
	}
	
	

}

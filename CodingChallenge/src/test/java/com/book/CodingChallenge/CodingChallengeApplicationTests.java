package com.book.CodingChallenge;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.book.CodingChallenge.repository.UserInfoRepository;


@SpringBootTest
class CodingChallengeApplicationTests {

	@Mock
	UserInfoRepository userInfoRepository;

	
	
	
	@Test
	void contextLoads() {
	}
	
	

}

package com.book.CodingChallenge.controller;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.book.CodingChallenge.config.JwtUtil;
import com.book.CodingChallenge.dto.MessageDto;
import com.book.CodingChallenge.entity.Admin;
import com.book.CodingChallenge.entity.Book;
import com.book.CodingChallenge.entity.UserInfo;
import com.book.CodingChallenge.enums.RoleType;
import com.book.CodingChallenge.exception.InvalidCredentialsException;
import com.book.CodingChallenge.repository.AdminRepository;
import com.book.CodingChallenge.repository.UserInfoRepository;
import com.book.CodingChallenge.service.BookService;
import com.book.CodingChallenge.service.MyUserDetailsService;


@RestController
public class BookController {
	
	@Autowired
	private BookService bookService;
	
	@Autowired
    private AuthenticationManager authenticationManager;
 
    @Autowired
    private MyUserDetailsService userDetailsService;
	
	 @Autowired
	    private JwtUtil jwtUtil;
	 
	 @Autowired
		private UserInfoRepository userRepository;
		
	@Autowired
	private PasswordEncoder passwordEncoder;
		
	@Autowired
	private AdminRepository adminRepository;

	@PostMapping("/auth/signup")
	public ResponseEntity<?> signup(@RequestBody UserInfo userInfo,MessageDto dto){
		try{
			
			
			
			userInfo.setRole(RoleType.USER);
	    	userInfo.setPassword(passwordEncoder.encode(userInfo.getPassword()));
	    	
	    	UserInfo user = userRepository.save(userInfo);
			return ResponseEntity.ok(user);
		}
		catch(Exception e) {
			dto.setMsg(e.getMessage());
			return ResponseEntity.badRequest().body(dto);
		}
	}
   
	@PostMapping("/auth/admin")
	public ResponseEntity<?> adminLogin(@RequestBody Admin admin,MessageDto dto){
		try{
			
			 UserInfo userInfo = admin.getUserInfo();
			    if (userInfo != null && userInfo.getId() == 0) {
			    	userInfo.setPassword(passwordEncoder.encode(userInfo.getPassword()));
			        userInfo = userRepository.save(userInfo); 
			        admin.getUserInfo().setRole(RoleType.ADMIN);
			        admin.setUserInfo(userInfo);
			    }

	    	Admin ad = adminRepository.save(admin);
			return ResponseEntity.ok(ad);
		}
		catch(Exception e) {
			dto.setMsg(e.getMessage());
			return ResponseEntity.badRequest().body(dto);
		}
	}
	
	
    @PostMapping("/auth/token")
    public String createAuthenticationToken(@RequestBody UserInfo authenticationRequest) throws Exception {
 
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword())
            );
        } catch (Exception e) {
            throw new Exception("Incorrect username or password", e);
        }
 
        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
        System.out.println(userDetails.getUsername());
        final String jwt = jwtUtil.generateToken(userDetails.getUsername());
 
        return jwt;
    }
    
    @GetMapping("/retrieve-all")
    public ResponseEntity<?> getAllBooks(){
    	List<Book> bookList = bookService.getAllBooks();
    	if(!bookList.isEmpty()) return ResponseEntity.ok(bookList);
    	else	return ResponseEntity.badRequest().body(new String("No books available"));
    	
    }
    
    @PostMapping("/add-new-book")
    public ResponseEntity<?> addNewBook(@RequestBody Book b,MessageDto dto){
    	try {
    		Book book = bookService.addBook(b);
    		if(book==null)	throw new InvalidCredentialsException("Book not added");
    		return ResponseEntity.ok(book);
    	}
    	catch(Exception e) {
    		dto.setMsg(e.getMessage());
    		return ResponseEntity.badRequest().body(dto);
    	}
    }
    
    @GetMapping("retrieve-single-book-by-ISBN/{isbn}")
    public ResponseEntity<?> addNewBook(@PathVariable int isbn,MessageDto dto){
    	try {
    		Book book = bookService.getBookByIsbn(isbn);
    		return ResponseEntity.ok(book);
    	}
    	catch(Exception e) {
    		dto.setMsg(e.getMessage());
    		return ResponseEntity.badRequest().body(dto);
    	}
    }
//    
//    @PostMapping("/add-new-book")
//    public ResponseEntity<?> addNewBook(@RequestBody Principal principal,MessageDto dto){
//    	try {
//    		
//    		return ResponseEntity.ok(null);
//    	}
//    	catch(Exception e) {
//    		dto.setMsg(e.getMessage());
//    		return ResponseEntity.badRequest().body(dto);
//    	}
//    }
    
//    @PostMapping("/add-new-book")
//    public ResponseEntity<?> addNewBook(@RequestBody Principal principal,MessageDto dto){
//    	try {
//    		
//    		return ResponseEntity.ok(null);
//    	}
//    	catch(Exception e) {
//    		dto.setMsg(e.getMessage());
//    		return ResponseEntity.badRequest().body(dto);
//    	}
//    }
//    
//    @PostMapping("/add-new-book")
//    public ResponseEntity<?> addNewBook(@RequestBody Principal principal,MessageDto dto){
//    	try {
//    		
//    		return ResponseEntity.ok(null);
//    	}
//    	catch(Exception e) {
//    		dto.setMsg(e.getMessage());
//    		return ResponseEntity.badRequest().body(dto);
//    	}
//    }
    
    
    
    
    
    
    
    
	
}

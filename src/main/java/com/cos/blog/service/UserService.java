package com.cos.blog.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cos.blog.model.User;
import com.cos.blog.repisitory.UserRepository;

// 스프링이 컴포넌트 스캔을 통해서 Bean에 등록을 해줌. IoC를 해준다
@Service
public class UserService {
	
	@Autowired // 의존성 주입(DI)
	private UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	@Transactional(readOnly = true)
	public User 회원찾기(String username) {
		User user = userRepository.findByUsername(username);
	
		return user;
	}
	
	
	@Transactional
	public int 회원가입(User user) {
		String rawPassword = user.getPassword(); // 비밀번호 원문
		String encPassword = encoder.encode(rawPassword); // 해쉬 값
		user.setPassword(encPassword);
		try{
			userRepository.save(user);
			return 1;
		}catch(Exception e) {
			return -1;
		}
	}
	
	@Transactional
	public void 회원수정(User user) {
		// 수정시에는 영속성 컨텍스트 User 오브젝트를 영속화 시키고 영속화된 User 오브젝트를 수정
		// select를 해서 User 오브젝트를 DB에서 가져오는 이유는 영속화 하기 위해서.
		// 영속화된 오브젝트를 변경하면 자동으로 DB에 update문을 날려줌.
		User persistance = userRepository.findById(user.getId()).orElseThrow(()->{
			return new IllegalArgumentException("회원찾기 실패");
		});
		
		// Validate 체크 ->oauth에 값이 없으면 수정가능
		if(persistance.getOauth() == null || persistance.getOauth().equals("")) {
			String rawPassword = user.getPassword(); // 비밀번호 원문
			String encPassword = encoder.encode(rawPassword); // 해쉬 값
			persistance.setPassword(encPassword);
			persistance.setEmail(user.getEmail());
		}
		// 회원 수정 함수 종료시 = 서비스 종료 = 트랜잭션 종료 = commit이 자동으로 된다.
		// 영속화된 persistance 객체의 변화가 감지되면 더티체킹이 되어 update문을 날려준다.
	}
}

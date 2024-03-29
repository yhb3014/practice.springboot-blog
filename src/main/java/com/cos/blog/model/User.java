package com.cos.blog.model;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity //User 클래스가 MySQL에 테이블이 생성이 된다.
public class User {
	
	@Id //Primary key
	@GeneratedValue(strategy = GenerationType.IDENTITY) //프로젝트에서 연결된 DB의 넘버링 전략을 따라간다. (MySQL = auto_increment)
	private int id; //시퀀스, auto_increment
	
	@Column(nullable = false, length = 100, unique=true)
	private String username;
	
	@Column(nullable = false, length = 100)
	private String password;
	
	@Column(nullable = false, length = 50)
	private String email;
	
	//DB는 RoleType이 없기때문에 import 해야함
	//@ColumnDefault("'user'")
	private String role; //Enum을 쓰는게 좋다. //ADMIN, USER
	
    public List<String> getRoleList(){
        if(this.role.length() > 0){
            return Arrays.asList(this.role.split(","));
        }
        return new ArrayList<>();
    }
	
	private String oauth; // kakao 등의 로그인인지 확인 
	
	@CreationTimestamp // 시간이 자동으로 입력이 된다.
	private Timestamp creatDate;
	
	private String provider;
	private String providerId;
	
}

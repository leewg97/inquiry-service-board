package com.fastcampus.domain;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.CreationTimestamp;

import lombok.Data;

@Data
@Entity
public class Post {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(nullable = false, length = 100)
	private String title;

	@Lob // 아주 긴 문자데이터(GB)를 저장할 수 있는 설정
	@Column(nullable = false)
	private String content;

	@CreationTimestamp
	private Timestamp createDate;

	// 포스트 : 회원은 N : 1 관계다.
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "USER_ID") // FK
	private User user;

	@OrderBy("id desc")
	@JsonIgnoreProperties({"post"})
	@OneToMany(mappedBy = "post", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
	private List<Reply> replys = new ArrayList<>();


}

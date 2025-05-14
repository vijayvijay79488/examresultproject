package com.example.examresult.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.examresult.model.chatmodel;

@Repository
public interface chatrepo extends JpaRepository<chatmodel, Integer> {
	@Query("SELECT c FROM chatmodel c WHERE " + "(c.form = :user1 AND c.too = :user2) ")
//	@Query("SELECT c FROM chatmodel c WHERE " + "(c.form = :user1 AND c.too = :user2) OR "
//			+ "(c.form = :user2 AND c.too = :user1)")
	List<chatmodel> FindChatBetweenUsers(String user1, String user2);

	@Query("SELECT c.message FROM chatmodel c WHERE (c.form = :user1 AND c.too = :user2)")
	List<String> FindMsgBetweenUsers(String user1, String user2);

//	List<chatmodel> findByName(String form);

}

package com.adrmanagement.member.infrastructure.repository.mapper;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.adrmanagement.member.domain.model.entity.Member;

@Repository
public interface MemberMapper extends JpaRepository<Member, Long>{
	
	@Query(value = "SELECT m FROM Member m where m.account= :account")
	Optional<Member> findByAccount(@Param("account") String account);

}

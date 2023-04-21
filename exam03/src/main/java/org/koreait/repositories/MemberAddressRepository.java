package org.koreait.repositories;

import org.koreait.entities.MemberAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface MemberAddressRepository extends JpaRepository<MemberAddress, Long>, QuerydslPredicateExecutor {

}

package com.work.pac.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.work.pac.entity.Work;

@Repository
public interface WorkRepository extends JpaRepository<Work, Long> {

	List<Work> findByNameContainingAndStateContainingAndPriorityContaining(String name, String state, String priority);
	List<Work> findByNameContainingAndStateEqualsAndPriorityContaining(String name, String state, String priority);
}

package com.apd.skilldb.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.apd.skilldb.entity.Skill;

public interface SkillRepository extends JpaRepository<Skill, Integer>{
	List<Skill> findBySkillName(String skillName);
}

package com.apd.skilldb.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.apd.skilldb.entity.Skill;
import com.apd.skilldb.repository.SkillRepository;

@Service
public class SkillService {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	SkillRepository skillRepo;


	public List<Skill> findAll(){
		List<Skill> skills = skillRepo.findAll(sortByCategoryAndNameAsc());
		if(logger.isDebugEnabled()){
			logger.debug("all skills size: " + (skills != null ? skills.size() : null));
		}
		
		return skills;	
	}	
	
	public Skill save(Skill skill){
		List<Skill> skills = skillRepo.findBySkillName(skill.getSkillName().trim());

		if(skills != null && skills.size() > 0)
			return skills.get(0);

		return skillRepo.save(skill);
	}

	private Sort sortByCategoryAndNameAsc() {
		return new Sort(Sort.Direction.ASC, "skillCategory", "skillName");
	}

}

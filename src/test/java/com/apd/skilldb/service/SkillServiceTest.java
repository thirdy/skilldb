package com.apd.skilldb.service;

import static org.junit.Assert.*;

import java.util.List;

import javax.validation.constraints.AssertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.apd.skilldb.MyWebAppInitializer;
import com.apd.skilldb.SpringConfiguration;
import com.apd.skilldb.entity.Skill;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {MyWebAppInitializer.class, SpringConfiguration.class})
public class SkillServiceTest {
	 @Autowired
	 SkillService skillService;
	 
	  @Test
	  public void findAll(){
		  List<Skill> skills = skillService.findAll();
		  assertTrue(skills.size() > 0);
	  }  
}

package com.apd.skilldb.service;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apd.skilldb.entity.Check;
import com.apd.skilldb.entity.Skill;
import com.apd.skilldb.repository.CheckRepository;
import com.apd.skilldb.repository.SkillRepository;

@Service
public class InitDbService {

	@Autowired
	private CheckRepository checkRepository;

	@Autowired
	private SkillRepository skillRepository;

	@PostConstruct
	public void init() {
		System.out.println("*** INIT DATABASE START ***");
		{
			Check check = new Check();
			check.setName("example");
			check.setUrl("http://www.example.com");
			checkRepository.save(check);
		}
		{
			Check check = new Check();
			check.setName("sitemonitoring");
			check.setUrl("http://sitemonitoring.sourceforge.net");
			checkRepository.save(check);
		}
		{
			Check check = new Check();
			check.setName("javavids");
			check.setUrl("http://www.javavids.com");
			checkRepository.save(check);
		}
		
		skillRepository.save(new Skill("Containers and Microservices", "Docker"));
		skillRepository.save(new Skill("Containers and Microservices", "Cloud Foundry"));
		skillRepository.save(new Skill("Containers and Microservices", "Jboss Fabric/Fuse"));
		skillRepository.save(new Skill("Continuous Integration and Deployment", "Maven, Ant, Gradle, Grunt, npm, bower"));
		skillRepository.save(new Skill("Continuous Integration and Deployment", "Hudson/Jenkins/other"));
		skillRepository.save(new Skill("Source Control", "Microsoft Team Service"));
		skillRepository.save(new Skill("Source Control", "Sitecore TDS"));
		skillRepository.save(new Skill("Source Control", "GIT"));
		skillRepository.save(new Skill("Source Control", "SVN"));
		skillRepository.save(new Skill("CMS", "Oracle/Fatwire"));
		skillRepository.save(new Skill("CMS", "AEM"));
		skillRepository.save(new Skill("CMS", "LifeRay"));
		skillRepository.save(new Skill("CMS", "dotCMS"));
		skillRepository.save(new Skill("CMS", "Open Text/Vignette"));
		skillRepository.save(new Skill("CMS", "Hippo CMS"));
		skillRepository.save(new Skill("CMS", "Sitecore"));
		skillRepository.save(new Skill("CMS", "Kentico"));
		skillRepository.save(new Skill("CMS", "Umbraco"));
		skillRepository.save(new Skill("CMS", "Wordpress"));
		skillRepository.save(new Skill("Database", "NoSQL - MongoDB"));
		skillRepository.save(new Skill("Database", "Microsoft SQL"));
		skillRepository.save(new Skill("Database", "MySQL"));
		skillRepository.save(new Skill("Database", "Oracle DB"));
		skillRepository.save(new Skill("Database", "DB2"));
		skillRepository.save(new Skill("Database", "Postgres Database"));
		skillRepository.save(new Skill("Ecommerce", "CommerceTools"));
		skillRepository.save(new Skill("Ecommerce", "IBM Commerce"));
		skillRepository.save(new Skill("Ecommerce", "Hybris"));
		skillRepository.save(new Skill("Ecommerce", "Sitecore Commerce"));
		skillRepository.save(new Skill("Ecommerce", "Magento"));
		skillRepository.save(new Skill("Framework", "JEE: Database integration (ORM frameworks Hibernate, Eclipse Link, JPA, JTA)"));
		skillRepository.save(new Skill("Framework", "JEE: Templating/UI (Servlets, JE, JSP, JSTL, JSF, Spring MVC, WebSockets)"));
		skillRepository.save(new Skill("Framework", "JEE: EJBs"));
		skillRepository.save(new Skill("Framework", "Javascript/JQuery"));
		skillRepository.save(new Skill("Framework", "Angular"));
		skillRepository.save(new Skill("Framework", "AJAX"));
		skillRepository.save(new Skill("Framework", "Hybrid apps frameworks like Cordova, PhoneGap or Lithium"));
		skillRepository.save(new Skill("Framework", "JSON"));
		skillRepository.save(new Skill("Framework", "Modernizr"));
		skillRepository.save(new Skill("Framework", "LABJs"));
		skillRepository.save(new Skill("Framework", "PicturefillJS"));
		skillRepository.save(new Skill("Framework", "shivJS"));
		skillRepository.save(new Skill("Framework", "UnderscoreJS"));
		skillRepository.save(new Skill("Framework", "CompressJS"));
		skillRepository.save(new Skill("Framework", "Backbone"));
		skillRepository.save(new Skill("Framework", "Jquery Mobile"));
		skillRepository.save(new Skill("Framework", "ASP.net"));
		skillRepository.save(new Skill("Framework", "MVC"));
		skillRepository.save(new Skill("Framework", "Entity Framework"));
		skillRepository.save(new Skill("Framework (CSS)", "Bootstrap"));
		skillRepository.save(new Skill("Framework", "Xamarin"));
		skillRepository.save(new Skill("Framework (CSS)", "Foundation"));
		skillRepository.save(new Skill("Server", "Node JS and npm"));
		skillRepository.save(new Skill("Hosting", "AWS"));
		skillRepository.save(new Skill("Hosting", "Azure"));
		skillRepository.save(new Skill("Hosting", "Google App Engine"));
		skillRepository.save(new Skill("Hosting", "Heroku"));
		skillRepository.save(new Skill("Language", "XML (XML, XSD, XSLT, Xpath)"));
		skillRepository.save(new Skill("Language", "HTML5"));
		skillRepository.save(new Skill("Language", "CSS"));
		skillRepository.save(new Skill("Language", "C#"));
		skillRepository.save(new Skill("Language", "Java"));
		skillRepository.save(new Skill("Language", "Ruby"));
		skillRepository.save(new Skill("Language", "Scala"));
		skillRepository.save(new Skill("Language", "Closure"));
		skillRepository.save(new Skill("Marketing Tool", "IBM Marketing Cloud"));
		skillRepository.save(new Skill("Multi-media", "Brightcove"));
		skillRepository.save(new Skill("Multi-media", "JWPlayer"));
		skillRepository.save(new Skill("Product", "IBM Websphere"));
		skillRepository.save(new Skill("Product", "BEA WebLogic"));
		skillRepository.save(new Skill("Product", "Adobe Campaign"));

		skillRepository.save(new Skill("Product", "Adobe Target"));
		skillRepository.save(new Skill("Product", "Adobe Audience Manager"));
		skillRepository.save(new Skill("Protocols", "JEE: Web Service Integration (JAX-RS, JAX-WS, SOAP, REST, Jackson, JAXB, JAXR, JSON API)"));
		skillRepository.save(new Skill("Protocols (Authentication)", "SSO, OAuth2, JWT"));
		skillRepository.save(new Skill("Protocols", "JEE: Messaging (JMS etc)"));
		skillRepository.save(new Skill("Protocols", "SaSS/ScSS"));
		skillRepository.save(new Skill("Search", "Lucene"));
		skillRepository.save(new Skill("Search", "SOLR"));
		skillRepository.save(new Skill("Search", "Elastic Search"));
		skillRepository.save(new Skill("Search", "Coveo"));
		skillRepository.save(new Skill("Standard", "W3C"));
		skillRepository.save(new Skill("Standard", "WCAG"));
		skillRepository.save(new Skill("Standard", "SSI"));
		skillRepository.save(new Skill("Performance Test", "Jmeter"));
		skillRepository.save(new Skill("Performance Test", "Visual Studio Performance test tool (local & cloud)"));
		skillRepository.save(new Skill("Testing (Java)", "Junit, Mockito, Cobertura"));
		skillRepository.save(new Skill("Testing (JavaScript)", "Karma, Jasmine, PhantomJs"));
		skillRepository.save(new Skill("Testing (BDD)", "Cucumber"));
		skillRepository.save(new Skill("Testing (Functional)", "Selenium"));
		skillRepository.save(new Skill("Operating Systems", "Unix"));
		skillRepository.save(new Skill("Analytics", "Google Analytics"));
		skillRepository.save(new Skill("Analytics", "WebTrends"));
		skillRepository.save(new Skill("Analytics", "Adobe Analytics/Omniture/SiteCatalyst"));
		skillRepository.save(new Skill("Social App Frameworks", "Facebook, Twitter, LinkedIn, Instagram, Weibo, QQ, and so on"));

		Skill skill = skillRepository.findBySkillName("W3C").get(0);
		System.out.println("SKILLFOUND: " +  skill.getSkillName()  + " " + skill.getId());
		
		System.out.println("*** INIT DATABASE FINISH ***");
	}
}

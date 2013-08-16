package service;

import javax.validation.ValidationException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import domain.GeneratedId;
import domain.GivenId;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:/applicationContext.xml")
@Transactional
@ActiveProfiles("embedded")
public class MyEntityTest
{
	@Autowired
	GeneratedIdService	generatedIdService;

	@Autowired
	GivenIdService		givenIdService;

	// This test will pass
	@Test(expected = ValidationException.class)
	public void shouldNotAllowNullValues1()
	{
		this.generatedIdService.save(new GeneratedId());
	}

	// This test will fail
	@Test(expected = ValidationException.class)
	public void shouldNotAllowNullValues2()
	{
		this.givenIdService.save(new GivenId(1L, null));
	}
}

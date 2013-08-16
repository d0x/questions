package service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import repository.GivenIdRepository;

import domain.GivenId;

@Service
public class GivenIdService
{
	@Autowired
	GivenIdRepository	repository;

	public GivenId save(final GivenId entity)
	{
		return this.repository.save(entity);
	}
}

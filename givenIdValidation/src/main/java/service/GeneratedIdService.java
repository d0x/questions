package service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import repository.GeneratedIdRepository;

import domain.GeneratedId;

@Service
public class GeneratedIdService
{
	@Autowired
	GeneratedIdRepository	repository;

	public GeneratedId save(final GeneratedId entity)
	{
		return this.repository.save(entity);
	}
}

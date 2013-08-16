package repository;

import org.springframework.data.jpa.repository.JpaRepository;

import domain.GivenId;

public interface GivenIdRepository extends JpaRepository<GivenId, Long>
{
}

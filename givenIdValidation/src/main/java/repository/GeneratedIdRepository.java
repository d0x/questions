package repository;

import org.springframework.data.jpa.repository.JpaRepository;

import domain.GeneratedId;

public interface GeneratedIdRepository extends JpaRepository<GeneratedId, Long>
{
}

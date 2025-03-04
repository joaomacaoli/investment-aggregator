package tech.buildrun.agregadordeinvestimentos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tech.buildrun.agregadordeinvestimentos.entity.Stock;

@Repository
public interface StockRepository extends JpaRepository<Stock, String> {}

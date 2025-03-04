package tech.buildrun.agregadordeinvestimentos.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tech.buildrun.agregadordeinvestimentos.entity.BillingAddress;

@Repository
public interface BillingAddressRepository
  extends JpaRepository<BillingAddress, UUID> {}

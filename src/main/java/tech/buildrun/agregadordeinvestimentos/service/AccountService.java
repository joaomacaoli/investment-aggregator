package tech.buildrun.agregadordeinvestimentos.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import tech.buildrun.agregadordeinvestimentos.client.BrapiClient;
import tech.buildrun.agregadordeinvestimentos.controller.dto.AccountStockResposeDTO;
import tech.buildrun.agregadordeinvestimentos.controller.dto.AssociateAccountStockDTO;
import tech.buildrun.agregadordeinvestimentos.entity.AccountStock;
import tech.buildrun.agregadordeinvestimentos.entity.AccountStockId;
import tech.buildrun.agregadordeinvestimentos.repository.AccountRepository;
import tech.buildrun.agregadordeinvestimentos.repository.AccountStockRepository;
import tech.buildrun.agregadordeinvestimentos.repository.StockRepository;

@Service
public class AccountService {

  // @Value("#{environment.TOKEN}")
  // private String TOKEN;
  private AccountRepository accountRepository;
  private StockRepository stockRepository;
  private AccountStockRepository accountStockRepository;
  private BrapiClient brapiClient;

  public AccountService(
    AccountRepository accountRepository,
    StockRepository stockRepository,
    AccountStockRepository accountStockRepository,
    BrapiClient brapiClient
  ) {
    this.accountRepository = accountRepository;
    this.stockRepository = stockRepository;
    this.accountStockRepository = accountStockRepository;
    this.brapiClient = brapiClient;
  }

  public void associateStock(
    String accountId,
    AssociateAccountStockDTO associateAccountStockDTO
  ) {

    var account = accountRepository.findById(UUID.fromString(accountId))
      .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

    var stock = stockRepository.findById(associateAccountStockDTO.stockId())
    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

    // DTO -> ENTITY

    var id = new AccountStockId(account.getAccountId(), stock.getStockId());
    var entity = new AccountStock(
      id,
      account,
      stock,
      associateAccountStockDTO.quantity()
    );

    accountStockRepository.save(entity);
  }
  public List<AccountStockResposeDTO> listStocks(String accountId) {

    var account = accountRepository.findById(UUID.fromString(accountId))
      .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

    return account.getAccountStocks()
      .stream()
      .map(accountStock ->
        new AccountStockResposeDTO(
          accountStock.getStock().getStockId(),
          accountStock.getQuantity(),
          getTotal(
            accountStock.getQuantity(),
            accountStock.getStock().getStockId()
          )
        ))
      .toList();
  }

  private double getTotal(Integer quantity, String stockId) {

    var TOKEN = ""; // Adicionar o valor do token da brapi.

    // como estou usando o VSCode durante o curso não quis perder
    // tempo implementando o dotenv, mas é uma boa prática.

    var response = brapiClient.getQuote(TOKEN, stockId);

    var price = response.results().getFirst().regularMarketPrice();

    return quantity * price;
  }
}

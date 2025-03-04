package tech.buildrun.agregadordeinvestimentos.service;

import org.springframework.stereotype.Service;
import tech.buildrun.agregadordeinvestimentos.controller.dto.CreateStockDTO;
import tech.buildrun.agregadordeinvestimentos.entity.Stock;
import tech.buildrun.agregadordeinvestimentos.repository.StockRepository;

@Service
public class StockService {

  private StockRepository stockRepository;

  public StockService(StockRepository stockRepository) {
    this.stockRepository = stockRepository;
  }

  public void createStock(CreateStockDTO createStockDTO) {

    // DTO -> ENTITY
    var stock = new Stock(
      createStockDTO.stockId(),
      createStockDTO.description()
    );

    stockRepository.save(stock);
  }
}

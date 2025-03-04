package tech.buildrun.agregadordeinvestimentos.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tech.buildrun.agregadordeinvestimentos.controller.dto.CreateStockDTO;
import tech.buildrun.agregadordeinvestimentos.service.StockService;

@RestController
@RequestMapping("/v1/stocks")
public class StockController {

  private StockService stockService;

  public StockController(StockService stockService) {
    this.stockService = stockService;
  }

  @PostMapping
  public ResponseEntity<Void> createStock(@RequestBody CreateStockDTO createStockDTO) {

    stockService.createStock(createStockDTO);

    return ResponseEntity.ok().build();
  }

}

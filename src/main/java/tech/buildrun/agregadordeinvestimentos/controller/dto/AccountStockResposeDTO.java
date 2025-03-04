package tech.buildrun.agregadordeinvestimentos.controller.dto;

public record AccountStockResposeDTO(
  String stockId,
  int quantity,
  double total
) {}

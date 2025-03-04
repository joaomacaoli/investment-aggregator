package tech.buildrun.agregadordeinvestimentos.client.dto;

import java.util.List;

public record BrapiResponseDTO(
  List<StockDTO> results
) {}

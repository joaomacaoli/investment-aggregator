package tech.buildrun.agregadordeinvestimentos.controller.dto;

public record CreateAccountDTO(
  String description,
  String street,
  Integer number
) {}

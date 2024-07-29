package com.leandroProject.apideinvestimentos.controller.DTO;

import java.util.List;

public record BrapiResponseDto(List<StockDto> results) {
}

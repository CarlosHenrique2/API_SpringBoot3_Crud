package com.example.ApiCrud.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TasksDTOResponse {

    private Integer id;

    @NotEmpty(message = "Obrigatório")
    private String titulo;
    private String descricao;
    private Date data;
    private String prioridades;
}

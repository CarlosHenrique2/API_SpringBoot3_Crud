package com.example.ApiCrud.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TasksDTORequest {

    private String titulo;
    private String descricao;
    private Date data;
    private String prioridade;
}

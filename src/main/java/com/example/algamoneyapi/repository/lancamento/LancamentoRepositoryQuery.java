package com.example.algamoneyapi.repository.lancamento;

import com.example.algamoneyapi.model.Lancamento;
import com.example.algamoneyapi.repository.filter.LancamentoFilter;

import java.util.List;

public interface LancamentoRepositoryQuery {

    public List<Lancamento> filtrar(LancamentoFilter lancamentoFilter);

}
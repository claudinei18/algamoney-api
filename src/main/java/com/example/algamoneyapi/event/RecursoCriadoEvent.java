package com.example.algamoneyapi.event;

import org.springframework.context.ApplicationEvent;

import javax.servlet.http.HttpServletResponse;

public class RecursoCriadoEvent extends ApplicationEvent{

    private HttpServletResponse httpServletResponse;
    private Long codigo;

    public RecursoCriadoEvent(Object source, HttpServletResponse httpServletResponse, Long codigo) {
        super(source);
        this.httpServletResponse = httpServletResponse;
        this.codigo = codigo;
    }

    public HttpServletResponse getHttpServletResponse() {
        return httpServletResponse;
    }

    public Long getCodigo() {
        return codigo;
    }
}

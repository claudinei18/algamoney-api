package com.example.algamoneyapi.event.listener;

import com.example.algamoneyapi.event.RecursoCriadoEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletResponse;
import java.net.URI;

@Component
public class RecursoCriadoListener implements ApplicationListener<RecursoCriadoEvent>{

    @Override
    public void onApplicationEvent(RecursoCriadoEvent recursoCriadoEvent) {
        HttpServletResponse httpServletResponse = recursoCriadoEvent.getHttpServletResponse();
        Long codigo = recursoCriadoEvent.getCodigo();

        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri()
                .path("/{codigo}").buildAndExpand(codigo).toUri();
        httpServletResponse.setHeader("location", uri.toASCIIString());

    }
}

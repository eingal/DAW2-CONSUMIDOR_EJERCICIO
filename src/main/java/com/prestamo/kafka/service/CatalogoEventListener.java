	package com.prestamo.kafka.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.prestamo.entity.Catalogo;
import com.prestamo.kafka.config.Event;
import com.prestamo.kafka.entity.CatalogoCreateEvent;


@Component
public class CatalogoEventListener {

	
	@KafkaListener( topics = "${topic.customer.name:topic-catalogoinga}",
					containerFactory = "kafkaListenerContainerFactory",
					groupId = "escuchador-catalogo")
	public void consumer(Event<?> event) {
		System.out.println(">>1 Evento recibido: " + event);
		
		
		if (event.getClass().isAssignableFrom(CatalogoCreateEvent.class)) {
			
			System.out.println(">>2  Evento Ctalogo creado");
			CatalogoCreateEvent objEvent = (CatalogoCreateEvent) event;
			
			String id = objEvent.getId();
			String tipoEvento = 	objEvent.getType().name();
			
			Catalogo objCatalogo = objEvent.getData();

			
			int idCatalogo = objCatalogo.getIdCatalogo();
			String nombre = objCatalogo.getDescripcion();
			
			System.out.println(">>3  ID: " + id);
			System.out.println(">>4  Tipo de evento: " + tipoEvento);
			System.out.println(">>6  ID Catalogo: " + idCatalogo);
			System.out.println(">>8  Nombre: " + nombre);
		}
		
	}
	
}
package com.prestamo.kafka.service;

import java.text.SimpleDateFormat;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.prestamo.entity.Pais;
import com.prestamo.kafka.config.Event;
import com.prestamo.kafka.entity.PaisCreateEvent;


@Component
public class PaisEventListener {

	
	@KafkaListener( topics = "${topic.customer.name:topic-pais}",
					containerFactory = "kafkaListenerContainerFactory",
					groupId = "escuchador-pais")
	public void consumer(Event<?> event) {
		System.out.println(">>1 Evento recibido: " + event);
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		
		if (event.getClass().isAssignableFrom(PaisCreateEvent.class)) {
			
			System.out.println(">>2  Evento Pais creado");
			PaisCreateEvent objEvent = (PaisCreateEvent) event;
			
			String id = objEvent.getId();
			String tipoEvento = 	objEvent.getType().name();
			String fecha = sdf.format(objEvent.getDate());
			Pais objPais = objEvent.getData();
			
			int idPais = objPais.getIdPais();
			String iso = objPais.getIso();
			String nombre = objPais.getNombre();
			
			System.out.println(">>3  ID: " + id);
			System.out.println(">>4  Tipo de evento: " + tipoEvento);
			System.out.println(">>5  Fecha: " + fecha);
			
			System.out.println(">>6  ID Pais: " + idPais);
			System.out.println(">>7  ISO: " + iso);
			System.out.println(">>8  Nombre: " + nombre);
		}
		
	}
	
}
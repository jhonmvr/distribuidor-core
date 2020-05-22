package ec.fin.segurossucre.sa.websocket;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import javax.inject.Inject;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import com.google.gson.Gson;

import ec.fin.segurossucre.pa.wrapper.MessageWrapper;

@ServerEndpoint("/siniestrows/{hash}")
public class SiniestroWebSocket {
	
	
	private static Map<String,Session> peers = Collections.synchronizedMap(new HashMap<>());

	@Inject
	Logger log;
	
	/*@Inject 
	SiniestroAgricolaService sas;*/
	
	@OnMessage
	public String onMessage(String message, Session session,@PathParam("hash") String hash) {
		try {
			log.info("web sochet server onMessage hash: " + hash);
			Gson gson = new Gson();
			MessageWrapper mw = gson.fromJson(message, MessageWrapper.class);
			log.info("received message from client getFechaDesde " + mw.getFechaDesde());
			log.info("received message from client getFechaHasta " + mw.getFechaHasta());
			log.info("received message from client getEstadoSiniestro " + mw.getEstadoSiniestro());
			log.info("received message from client session session sender " + session.getId() );
			log.info("received message from client session reveiver " + mw.getSessionIdReceiver() );
			log.info("received message from client hash origen " + mw.getHash());
			log.info("received message from client peers " + peers);
			Session s = peers.get( mw.getHash() );
			if( s != null) {
				log.info("Encontro hash " + mw.getSessionIdReceiver() );
				mw.setSessionIdSender(session.getId()  );
				log.info("*****************PROCESANDO " + mw.getLastId() );
				//verifyStatus(mw); 
				message=gson.toJson( mw );
				s.getBasicRemote().sendText(gson.toJson( mw ));
				log.info("send message to peer ");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return message;
	}
	
	/*private void verifyStatus(MessageWrapper mw) throws SegSucreException {
		TbSaSiniestro sin=this.sas.findSiniestroById( Long.valueOf( mw.getLastId() ) );
		if(  sin != null && sin.getEstadoSiniestro().compareTo(EstadoSiniestroAgricolaEnum.RESERVA )==0) {
			mw.setFinished( Boolean.TRUE );
		} else {
			mw.setFinished( Boolean.FALSE );
		}
		
	}*/

	@OnOpen
	public void onOpen(Session session,@PathParam("hash") String hash) {
		try {
			log.info("web sochet server onOpen hash: " + hash);
			Gson gson = new Gson();
			log.info("mediator: opened websocket channel for client " + hash);
			peers.put(hash,session);
			MessageWrapper mw = new MessageWrapper();
			mw.setHash(hash  );
			mw.setSessionIdSender( session.getId() );
			session.getBasicRemote().sendText( gson.toJson( mw ) );
		} catch (IOException e) {
			log.info("ERROR WEB SOCKET HASH " + hash + "  SESSION " + session.getId());
		}
	}

	@OnClose
	public void onClose(Session session, @PathParam("hash") String hash) {
		log.info("web sochet server onClose hash: " + hash);
		peers.remove(hash);
	}
}

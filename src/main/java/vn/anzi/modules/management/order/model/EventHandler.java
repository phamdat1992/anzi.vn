package vn.anzi.modules.management.order.model;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import vn.anzi.modules.management.order.entity.OrderInfoNotConfirmEntity;

import java.io.IOException;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import static org.springframework.web.servlet.mvc.method.annotation.SseEmitter.event;

@Component
public class EventHandler {

    private static final String MESsAGE_TYPE = "order";
    private static final AtomicInteger ID_COUNTER = new AtomicInteger(1);
    public static final long DEFAULT_TIMEOUT = 86_400_000L;
    private final Set<Client> registeredClients = new HashSet<>();

    public SseEmitter registerClient(Long eateryId) {
        var emitter = new SseEmitter(DEFAULT_TIMEOUT);
        var client = new Client();
        client.setSseEmitter(emitter);
        client.setEateryId(eateryId);
        emitter.onCompletion(() -> {
            synchronized (this.registeredClients) {
                this.registeredClients.remove(client);
            }
        });
        emitter.onError((err) -> removeAndLogError(client));
        emitter.onTimeout(() -> removeAndLogError(client));
        registeredClients.add(client);
        return emitter;
    }

    private void removeAndLogError(Client client) {
        registeredClients.remove(client);
    }

    public void broadcast(OrderInfoNotConfirmEntity order, Long eateryId) {
        Set<Client> clients = Set.copyOf(registeredClients);
        for (Client client: clients) {
            if (Objects.equals(client.getEateryId(), eateryId)) {
                OrderServerEvent orderEvent = new OrderServerEvent();
                orderEvent.setId(order.getId());
                orderEvent.setName(order.getName());
                orderEvent.setLocation(order.getLocation());
                orderEvent.setTypeId(order.getTypeId());
                orderEvent.setTotalDish(order.getTotalDish());
                orderEvent.setTotalPrice(order.getTotalPrice());
                orderEvent.setCreatedTime(order.getCreatedTime());
                sendMessage(client, orderEvent);
            }
        }
    }

    private void sendMessage(Client client, OrderServerEvent event) {
        var sseEmitter = client.getSseEmitter();
        try {
            var eventId = ID_COUNTER.incrementAndGet();
            SseEmitter.SseEventBuilder eventBuilder = event().name(MESsAGE_TYPE)
                    .id(String.valueOf(eventId))
                    .data(event, MediaType.APPLICATION_JSON);
            sseEmitter.send(eventBuilder);
            sseEmitter.complete();
        } catch (IOException e) {
            sseEmitter.completeWithError(e);
        }
    }

}
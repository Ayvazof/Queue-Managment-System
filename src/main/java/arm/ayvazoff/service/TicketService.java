package arm.ayvazoff.service;

import arm.ayvazoff.domain.Order;
import arm.ayvazoff.domain.State;
import arm.ayvazoff.domain.Ticket;
import arm.ayvazoff.repository.OrderRepository;
import arm.ayvazoff.repository.TaskRepository;
import arm.ayvazoff.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class TicketService {

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private TaskRepository taskRepository;

    public Ticket changeState(Ticket ticket, State state) {
        ticket.setState(state);
        ticketRepository.save(ticket);
        return ticket;
    }

    public void addTicket(String taskId) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
        Date date = new Date(System.currentTimeMillis());
        String dateTime = formatter.format(date);

        Order ord = new Order();
        ord.setCreateTimeDate(dateTime);
        Order ord0 = orderRepository.save(ord);

        Ticket ticket = new Ticket();
        ticket.setCreateTimeDate(dateTime);
        ticket.setState(State.WAIT);
        ticket.setQueueNum(ord0.getId());
        ticket.setTask(taskRepository.getOne(taskId));

        ticketRepository.save(ticket);
    }
}

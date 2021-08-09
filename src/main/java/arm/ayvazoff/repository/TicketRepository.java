package arm.ayvazoff.repository;

import arm.ayvazoff.domain.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TicketRepository extends JpaRepository<Ticket, Integer> {
    @Query(value = "select ticket.id, ticket.create_time_date, " +
            "ticket.queue_num, ticket.state, " +
            "ticket.state_change_date_time, " +
            "ticket.task_id, ticket.user_id, task.priority " +
            "from ticket left join task on ticket.task_id = task.id " +
            "where ticket.state = 0 " +
            "order by priority desc, queue_num " +
            "limit 1", nativeQuery = true)
    Ticket getNext();
}
